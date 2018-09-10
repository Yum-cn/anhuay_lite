package com.anhuay.system.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anhuay.common.annotation.Log;
import com.anhuay.common.controller.BaseController;
import com.anhuay.common.service.DictService;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;
import com.anhuay.system.domain.UserDO;
import com.anhuay.system.domain.UserExtendDO;
import com.anhuay.system.service.RoleService;
import com.anhuay.system.service.UserExtendService;
import com.anhuay.system.service.UserService;
import com.anhuay.system.vo.UserVO;
import com.common.constant.CommonEnum;
import com.common.util.BaseResult;
import com.common.util.BaseResultHelper;

import net.sf.json.JSONObject;

@RequestMapping("/system/userExtend")
@Controller
public class UserExtendController extends BaseController {
	private String prefix = "system/userExtend";
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	DictService dictService;
	@Autowired
	private UserExtendService userExtendService;

	/**
	 * 钥匙管理
	 * 
	 * @author Yum
	 */
	@RequiresPermissions("system:userExtend")
	@GetMapping("/key")
	String user(Model model) {
		model.addAttribute("userId", getUserId());
		return prefix + "/key";
	}

	@GetMapping("/userList")
	@ResponseBody
	PageUtils userList(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<UserDO> sysUserList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}

	@RequiresPermissions("system:userExtend:login")
	@Log("登录设置")
	@GetMapping("/loginConfig")
	String add(Model model) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", getUserId());
		List<UserExtendDO> userExtendList = userExtendService.list(map);
		
		UserExtendDO bean = CollectionUtils.isNotEmpty(userExtendList)?userExtendList.get(0):new UserExtendDO();
		
		model.addAttribute("userExtend", bean);
		return prefix + "/loginConfig";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/saveLonginConfig")
	@RequiresPermissions("system:userExtend:add")
	public Object saveLonginConfig(@RequestBody JSONObject body, UserExtendDO userExtend) {

		UserExtendDO bean = new UserExtendDO();
		bean.setUserId(getUserId()); 
		bean.setLoginRules(body.toString());
		bean.setUpdateTime(System.currentTimeMillis() / 1000);

		if(body.optLong("id")>0){
			bean.setId(body.optLong("id"));
			userExtendService.update(bean);
		}else{
			bean.setStatus(CommonEnum.STATUS.ONE.value);
			bean.setCreateTime(System.currentTimeMillis() / 1000);
			userExtendService.save(bean);
		}
		return  BaseResultHelper.success(bean);

		/*
		 * BaseResult<Object> result =
		 * strategyTempletManager.saveStrategyTempletTab(body); return result;
		 */
	}

	@GetMapping("/setPassword")
	String setPassword(Model model) {
		model.addAttribute("user", getUser());
		return "system/userExtend/setPassword";
	}
	

	@Log("提交更改用户密码")
	@PostMapping("/resetPwd")
	@ResponseBody
	BaseResult<Object> resetPwd(UserVO userVO) {
	
		try{
			userService.resetPwd(userVO,getUser());
			
			Long userId = userVO.getUserDO().getUserId();
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", userId);
			List<UserExtendDO> userExtendList = userExtendService.list(map);
			
			UserExtendDO bean = CollectionUtils.isNotEmpty(userExtendList)?userExtendList.get(0):new UserExtendDO();
			bean.setUserId(getUserId()); 
			
			JSONObject jsonObject = new JSONObject();
			long lastUpdateTime = System.currentTimeMillis() / 1000;
			String expiredType = userVO.getExpiredType();
			jsonObject.put("lastUpdateTime", lastUpdateTime);
			jsonObject.put("expired_type", userVO.getExpiredType());
			
			String str = String.valueOf(lastUpdateTime);
			if (str.length() <= 10) {
				lastUpdateTime = lastUpdateTime * 1000L;
			}
			Calendar expiredCal = Calendar.getInstance();
			expiredCal.setTimeInMillis(lastUpdateTime);
			
			if(StringUtils.isNotBlank(expiredType)){
				
				if(StringUtils.equals(expiredType, CommonEnum.TIMETYPE.WEEK.name)){
					expiredCal.add(Calendar.DATE, 7);
				}else if(StringUtils.equals(expiredType, CommonEnum.TIMETYPE.MONTH.name)){
					expiredCal.add(Calendar.DATE, 30);
				}else if(StringUtils.equals(expiredType, CommonEnum.TIMETYPE.YEAR.name)){
					expiredCal.add(Calendar.DATE, 365);
				}
			}
			
			jsonObject.put("expired_time", expiredCal.getTimeInMillis()/1000);
			bean.setPasswordRules(jsonObject.toString());
			bean.setUpdateTime(System.currentTimeMillis() / 1000);

			if(bean.getId()!=null &&bean.getId()>0){
				userExtendService.update(bean);
			}else{
				bean.setStatus(CommonEnum.STATUS.ONE.value);
				bean.setCreateTime(System.currentTimeMillis() / 1000);
				userExtendService.save(bean);
			}
			
			return BaseResultHelper.success();
		}catch (Exception e){
			return  BaseResultHelper.error(e.getMessage());
		}

	}
	
	@GetMapping()
	String UserExtend() {
		return "system/userExtend/userExtend";
	}

	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<UserExtendDO> userExtendList = userExtendService.list(query);
		int total = userExtendService.count(query);
		PageUtils pageUtils = new PageUtils(userExtendList, total);
		return pageUtils;
	}


	@GetMapping("/add")
	@RequiresPermissions("system:userExtend:add")
	String add() {
		return "system/userExtend/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:userExtend:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		UserExtendDO userExtend = userExtendService.get(id);
		model.addAttribute("userExtend", userExtend);
		return "system/userExtend/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:userExtend:add")
	public R save(UserExtendDO userExtend) {
		if (userExtendService.save(userExtend) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:userExtend:edit")
	public R update(UserExtendDO userExtend) {
		userExtendService.update(userExtend);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("system:userExtend:remove")
	public R remove(Long id) {
		if (userExtendService.updateStatus(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:userExtend:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		userExtendService.batchUpdateStatus(ids);
		return R.ok();
	}
}
