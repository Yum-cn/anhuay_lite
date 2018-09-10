package com.anhuay.system.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anhuay.common.annotation.Log;
import com.anhuay.common.controller.BaseController;
import com.anhuay.common.domain.FileDO;
import com.anhuay.common.domain.Tree;
import com.anhuay.common.service.FileService;
import com.anhuay.common.utils.IPUtils;
import com.anhuay.common.utils.MD5Utils;
import com.anhuay.common.utils.R;
import com.anhuay.common.utils.ShiroUtils;
import com.anhuay.system.domain.MenuDO;
import com.anhuay.system.domain.UserExtendDO;
import com.anhuay.system.license.License;
import com.anhuay.system.license.LicenseManager;
import com.anhuay.system.service.MenuService;
import com.anhuay.system.service.UserExtendService;
import com.common.constant.CommonEnum;
import com.common.util.BaseResult;
import com.common.util.BaseResultHelper;

import net.sf.json.JSONObject;

@Controller
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MenuService menuService;
	@Autowired
	FileService fileService;
	@Autowired
	private UserExtendService userExtendService;

	@GetMapping({ "/", "" })
	String welcome(Model model) {

		return "redirect:/index";
	}

	@Log("请求访问主页")
	@GetMapping({ "/index" })
	String index(Model model) {
		List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
		model.addAttribute("menus", menus);
		model.addAttribute("name", getUser().getName());
		FileDO fileDO = fileService.get(getUser().getPicId());
		if (fileDO != null && fileDO.getUrl() != null) {
			if (fileService.isExist(fileDO.getUrl())) {
				model.addAttribute("picUrl", fileDO.getUrl());
			} else {
				model.addAttribute("picUrl", "/img/photo_s.jpg");
			}
		} else {
			model.addAttribute("picUrl", "/img/photo_s.jpg");
		}
		model.addAttribute("username", getUser().getUsername());
		return "index_v1";
	}

	@GetMapping("/login")
	String login() {
		return "login";
	}

	@Log("登录")
	@PostMapping("/login")
	@ResponseBody
	Object ajaxLogin(String username, String password, HttpServletRequest request) {

		
		/*try {
			boolean valid = false;
			LicenseManager licenseManager = LicenseManager.getInstance();
			License license = licenseManager.getLicense();
			System.out.println("license = " + license);
			valid = licenseManager.isValidLicense(license);
			if(!valid){
				return BaseResultHelper.error(CommonEnum.CODE.INVALID_LICENSE.code,
						CommonEnum.CODE.INVALID_LICENSE.description);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return BaseResultHelper.error(CommonEnum.CODE.INVALID_LICENSE.code,
					CommonEnum.CODE.INVALID_LICENSE.description);
		}*/

		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", getUserId());
			List<UserExtendDO> userExtendList = userExtendService.list(map);

			UserExtendDO bean = CollectionUtils.isNotEmpty(userExtendList) ? userExtendList.get(0) : null;

			if (bean == null) {
				return BaseResultHelper.error(CommonEnum.CODE.PASSWORD_DEFAULT.code,
						CommonEnum.CODE.PASSWORD_DEFAULT.description);
			}

			BaseResult<Object> result = checkLoginTime(request, bean);
			if (result.getCode() != CommonEnum.CODE.SUCCESS.code) {// 失败
				return result;
			}

			result = checkPasswordExpire(bean);
			if (result.getCode() != CommonEnum.CODE.SUCCESS.code) {// 失败
				return result;
			}

			return BaseResultHelper.success();
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}

	private BaseResult<Object> checkPasswordExpire(UserExtendDO bean) {
		// 检查密码有效期 需要增加最后一次修改密码时间
		JSONObject passwordJson = JSONObject.fromObject(bean.getPasswordRules());
		if (passwordJson == null || passwordJson.isNullObject()) {
			return BaseResultHelper.success();
		}
		// {"lastUpdateTime":1533386101,"expired_type":"week"}
		// Long lastUpdateTime = passwordJson.optLong("lastUpdateTime");
		// String expiredType = passwordJson.optString("expired_type");
		Long expiredTime = passwordJson.optLong("expired_time");

		if (expiredTime != null && expiredTime > 0) {

			String str = String.valueOf(expiredTime);
			if (str.length() <= 10) {
				expiredTime = expiredTime * 1000L;
			}
			Calendar expiredCal = Calendar.getInstance();
			expiredCal.setTimeInMillis(expiredTime);
			Calendar todayCal = Calendar.getInstance();

			if (expiredCal.before(todayCal)) {
				return BaseResultHelper.error(CommonEnum.CODE.PASSWORD_EXPIRED.code,
						CommonEnum.CODE.PASSWORD_EXPIRED.description);
			}
		}

		return BaseResultHelper.success();
	}

	private BaseResult<Object> checkLoginTime(HttpServletRequest request, UserExtendDO bean) {
		JSONObject loginJson = JSONObject.fromObject(bean.getLoginRules());

		if (loginJson == null || loginJson.isNullObject()) {
			return BaseResultHelper.success();
		}

		String ip = IPUtils.getIpAddr(request);
		String device_white_values = loginJson.optString("device_white_values");
		if (StringUtils.equals("1", loginJson.optString("white_status"))
				&& StringUtils.isNotBlank(device_white_values)) {
			if (!StringUtils.contains(device_white_values, ip)) {
				return BaseResultHelper.error(CommonEnum.CODE.WHITE_IP_UNAUTHORIZED.code,
						CommonEnum.CODE.WHITE_IP_UNAUTHORIZED.description);
			}
		}
		// 管理端登录时段
		Map<String, String> weekMap = new HashMap<String, String>();
		weekMap.put("1", "Sunday");
		weekMap.put("2", "Monday");
		weekMap.put("3", "Tuesday");
		weekMap.put("4", "Wednesday");
		weekMap.put("5", "Thursday");
		weekMap.put("6", "Friday");
		weekMap.put("7", "Saturday");

		String login_day = loginJson.optString("login_day");
		String login_start_time = loginJson.optString("login_start_time");
		String login_end_time = loginJson.optString("login_end_time");
		if (StringUtils.equals("1", loginJson.optString("login_time_status"))) {

			if (StringUtils.isNotBlank(login_day)) {
				Calendar cal = Calendar.getInstance();
				String todayIndex = String.valueOf(cal.get(Calendar.DAY_OF_WEEK));

				if (!StringUtils.contains(login_day, weekMap.get(todayIndex))) {
					return BaseResultHelper.error(CommonEnum.CODE.LOGIN_TIME_UNAUTHORIZED.code,
							CommonEnum.CODE.LOGIN_TIME_UNAUTHORIZED.description);
					// return BaseResultHelper.error("今天不在管理端登录日内，请改天登录！");
				}
			}

			if (StringUtils.isNotBlank(login_start_time) && StringUtils.isNotBlank(login_end_time)) {
				String pattern = "HH:mm";
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);// 格式化为年月
				try {
					Calendar currCal = Calendar.getInstance();
					currCal.setTime(sdf.parse(sdf.format(new Date())));

					Calendar min = Calendar.getInstance();
					Calendar max = Calendar.getInstance();

					min.setTime(sdf.parse(login_start_time));
					max.setTime(sdf.parse(login_end_time));

					if (currCal.getTimeInMillis() > max.getTimeInMillis()
							|| currCal.getTimeInMillis() < min.getTimeInMillis()) {
						// if (currCal.after(max) && currCal.before(min) &&
						// !currCal.equals(min) && !currCal.equals(max)) {
						return BaseResultHelper.error(CommonEnum.CODE.LOGIN_TIME_UNAUTHORIZED.code,
								CommonEnum.CODE.LOGIN_TIME_UNAUTHORIZED.description);
					}

				} catch (ParseException e) {
					e.printStackTrace();
				}

			}

		}
		return BaseResultHelper.success();
	}

	@Log("退出登录")
	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/login";
	}

	@GetMapping("/main")
	String main() {
		return "main";
	}

}
