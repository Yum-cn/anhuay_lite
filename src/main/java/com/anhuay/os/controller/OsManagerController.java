package com.anhuay.os.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anhuay.common.config.BootdoConfig;
import com.anhuay.common.utils.DateUtils;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;
import com.anhuay.os.domain.NetInfoDO;
import com.anhuay.os.domain.OsCmdDO;
import com.anhuay.os.domain.OsManagerDO;
import com.anhuay.os.domain.OsManagerVO;
import com.anhuay.os.manager.OsManagerInterface;
import com.anhuay.os.service.NetInfoService;
import com.anhuay.os.service.OsCmdService;
import com.anhuay.os.service.OsManagerService;
import com.common.util.EncryptUtil;
import com.common.util.GenericPropertyConverterUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-09-10 17:03:24
 */

@Controller
@RequestMapping("/os/osManager")
public class OsManagerController {
	@Autowired
	private OsManagerService osManagerService;
	@Autowired
	private OsManagerInterface osManagerInterface;
	@Autowired
	private OsCmdService osCmdService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private NetInfoService netInfoService;

	@GetMapping()
	@RequiresPermissions("os:osManager:osManager")
	String OsManager() {
		return "os/osManager/osManager";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("os:osManager:osManager")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<OsManagerDO> osManagerList = osManagerService.list(query);
		int total = osManagerService.count(query);
		PageUtils pageUtils = new PageUtils(osManagerList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("os:osManager:add")
	String add() {
		return "os/osManager/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("os:osManager:edit")
	String edit(@PathVariable("id") Integer id, Model model) {
		OsManagerDO osManager = osManagerService.get(id);
		model.addAttribute("osManager", osManager);
		return "os/osManager/edit";
	}

	@GetMapping("/remote")
	String remote(Long id, String osIp, Model model) {

		model.addAttribute("osId", id);
		model.addAttribute("osIp", osIp);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("osId", id);
		OsCmdDO osCmd = osCmdService.getOsCmd(map);
		if (osCmd != null && StringUtils.isNotBlank(osCmd.getCmdResult())) {
			osCmd.setCmdResult(DateUtils.getTime(osCmd.getUpdateTime()) + " 操作信息：" + osCmd.getCmdResult());
		}

		model.addAttribute("osCmd", osCmd);
		return "os/osManager/remote";
	}

	@GetMapping("/uninstallCode")
	String uninstallCode(String osIds, String osIps, Model model) {

		model.addAttribute("osIds", osIds);
		model.addAttribute("osIps", osIps);
		return "os/osManager/uninstallCode";
	}

	@GetMapping("/irregularConnection")
	String irregularConnection(String osId, String osIp, String osName, Model model) {

		model.addAttribute("osId", osId);
		model.addAttribute("osIp", osIp);
		model.addAttribute("osName", osName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("osId", osId);
		List<NetInfoDO> list = netInfoService.list(map);
		model.addAttribute("list", list);

		return "os/osManager/irregularConnection";
	}

	/**
	 * 
	 * @author Yum
	 */
	@PostMapping("/setUninstallPassword")
	@ResponseBody
	public R setUninstallPassword(String osIds, String osIps, String code) {

		if (StringUtils.isNotBlank(osIds)) {

			String[] idsArray = StringUtils.split(osIds, ",");
			String[] ipsArray = StringUtils.split(osIps, ",");

			for (int i = 0; i < idsArray.length; i++) {

				try {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("osId", idsArray[i]);
					OsManagerDO osManager = osManagerService.getOsManager(map);

					if (osManager != null) {

						osManager.setUninstallStatus(StringUtils.isBlank(code) ? "0" : "1");
						osManager.setUninstallPasswd(code);
						osManager.setUpdateTime(System.currentTimeMillis());
						osManager.setTaskStatus("1");
						osManagerService.update(osManager);
					} else {

						osManager = new OsManagerDO();
						osManager.setOsId(String.valueOf(idsArray[i]));
						osManager.setOsIp(ipsArray[i]);
						osManager.setUninstallStatus("1");
						osManager.setUninstallPasswd(code);
						osManager.setCreateTime(System.currentTimeMillis());
						osManager.setTaskStatus("1");
						osManagerService.save(osManager);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		// osManagerService.batchUpdateStatus(ids);
		return R.ok();
	}

	/**
	 * 
	 * @author Yum
	 */
	/*
	 * @PostMapping("/netInfoManager")
	 * 
	 * @ResponseBody public R netInfoManager(String osId,String aaenableIds,
	 * String bbdisableIds, Model model) {
	 * 
	 * System.out.println(osId+">>>"+aaenableIds+">>>"+bbdisableIds);
	 * 
	 * if (StringUtils.isNotBlank(osIds)) {
	 * 
	 * String[] idsArray = StringUtils.split(osIds, ","); String[] ipsArray =
	 * StringUtils.split(osIps, ",");
	 * 
	 * for (int i = 0; i < idsArray.length; i++) {
	 * 
	 * try { Map<String, Object> map = new HashMap<String, Object>();
	 * map.put("osId", idsArray[i]); OsManagerDO osManager =
	 * osManagerService.getOsManager(map);
	 * 
	 * if (osManager != null) {
	 * 
	 * osManager.setUninstallStatus(StringUtils.isBlank(code) ? "0" : "1");
	 * osManager.setUninstallPasswd(code);
	 * osManager.setUpdateTime(System.currentTimeMillis());
	 * osManager.setTaskStatus("1"); osManagerService.update(osManager); } else
	 * {
	 * 
	 * osManager = new OsManagerDO();
	 * osManager.setOsId(String.valueOf(idsArray[i]));
	 * osManager.setOsIp(ipsArray[i]); osManager.setUninstallStatus("1");
	 * osManager.setUninstallPasswd(code);
	 * osManager.setCreateTime(System.currentTimeMillis());
	 * osManager.setTaskStatus("1"); osManagerService.save(osManager); } } catch
	 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * } } // osManagerService.batchUpdateStatus(ids); return R.ok(); }
	 */

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/netInfoManager")
	public R netInfoManager(String osId,String osIp, String enableIds, String disableIds, Model model) {
		// 8>>>1>>>2;4,2
		System.out.println(osId + ">>>" + enableIds + ">>>" + disableIds);
		// ahys_os_manager的netlink_monitor_rules字段就写成一个json 包含俩字段
		// enable:1,2,3;disable:4,5
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("osId", osId);

		OsManagerDO osManager = osManagerService.getOsManager(map);
		if (osManager != null) {
			osManager.setNetlinkMonitorStatus("1");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("enable", enableIds);
			jsonObject.put("disable", disableIds);
			osManager.setNetlinkMonitorRules(jsonObject.toString());
			osManager.setUpdateTime(System.currentTimeMillis());
			osManager.setTaskStatus("1");
			osManagerService.update(osManager);
		}else{
			osManager = new OsManagerDO();
			osManager.setOsId(osId);
			osManager.setOsIp(osIp);
			osManager.setNetlinkMonitorStatus("1");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("enable", enableIds);
			jsonObject.put("disable", disableIds);
			osManager.setNetlinkMonitorRules(jsonObject.toString());
			osManager.setCreateTime(System.currentTimeMillis());
			osManager.setTaskStatus("1");
			osManagerService.save(osManager);
		}

		if (StringUtils.isNotBlank(enableIds)) {

			String[] enableArray = enableIds.split(",");
			if (enableArray != null && enableArray.length > 0) {
				
				for (int i = 0; i < enableArray.length; i++) {
					
					try {
						NetInfoDO netInfo = netInfoService.get(Integer.parseInt(enableArray[i]));
						if(netInfo != null){
							netInfo.setDevStatus("1");
							netInfoService.update(netInfo);
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}

		}

		if (StringUtils.isNotBlank(disableIds)) {

			String[] disableArray = disableIds.split(",");
			if (disableArray != null && disableArray.length > 0) {
				
				for (int i = 0; i < disableArray.length; i++) {
					
					try {
						NetInfoDO netInfo = netInfoService.get(Integer.parseInt(disableArray[i]));
						if(netInfo != null){
							netInfo.setDevStatus("0");
							netInfoService.update(netInfo);
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
		}

		netInfoService.list(map);

		return R.ok();
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("os:osManager:add")
	public R save(OsManagerVO osManager) {

		osManagerInterface.saveOsManager(osManager);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("os:osManager:edit")
	public R update(OsManagerDO osManager) {
		osManagerService.update(osManager);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("os:osManager:remove")
	public R remove(Integer id) {
		if (osManagerService.updateStatus(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("os:osManager:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids) {
		osManagerService.batchUpdateStatus(ids);
		return R.ok();
	}

	private static final String SALT = "SAFECLI98725695";

	@RequestMapping(value = "/offlineExport", method = RequestMethod.GET)
	public void offlineExport(HttpServletResponse res, String osId, String osIp) {

		if (StringUtils.isBlank(osId)) {
			return;
		}
		OsManagerDO osManager = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("osId", osId);
			osManager = osManagerService.getOsManager(map);

			if (osManager == null) {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String dateStr = DateFormat.getDateInstance(DateFormat.MEDIUM).format(new Date());
		String tempFilePath = dateStr + "/offline/" + UUID.randomUUID() + "/safe_cli.cfg";
		String uploadFileName = bootdoConfig.getUploadPath() + tempFilePath;
		JSONObject jsonObject = JSONObject.fromObject(osManager);
		// jsonObject.put("test", "hello,Yum!");

		try {
			FileUtils.writeStringToFile(new File(uploadFileName),
					EncryptUtil.encryptDES(
							GenericPropertyConverterUtils.camelToUnderlineForJsonObject(jsonObject).toString(), SALT),
					"UTF-8", false);
			// System.out.println(EncryptUtil.decryptDES("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",
			// SALT));

		} catch (Exception e) {
			e.printStackTrace();
		}

		String fileName = osIp + "_safe_cli.cfg";
		res.setHeader("content-type", "application/octet-stream");
		res.setContentType("application/octet-stream");
		res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			os = res.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(new File(uploadFileName)));
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("success");
	}

	@RequestMapping(value = "/offlineExportBatch", method = RequestMethod.GET)
	public void offlineExportBatch(HttpServletResponse res, String osIds) {

		if (StringUtils.isBlank(osIds)) {
			return;
		}

		List<OsManagerDO> list = new ArrayList<OsManagerDO>();

		String[] idsArray = StringUtils.split(osIds, ",");

		for (int i = 0; i < idsArray.length; i++) {
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("osId", idsArray[i]);
				OsManagerDO osManager = osManagerService.getOsManager(map);

				if (osManager == null) {
					continue;
				}
				list.add(osManager);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String dateStr = DateFormat.getDateInstance(DateFormat.MEDIUM).format(new Date());
		String tempFilePath = dateStr + "/offline/" + UUID.randomUUID() + "/safe_cli.cfg";
		String uploadFileName = bootdoConfig.getUploadPath() + tempFilePath;
		JSONArray jsonObject = JSONArray.fromObject(list);
		// jsonObject.put("test", "hello,Yum!");

		try {
			FileUtils.writeStringToFile(new File(uploadFileName),
					EncryptUtil.encryptDES(
							GenericPropertyConverterUtils.camelToUnderlineForJsonArray(jsonObject).toString(), SALT),
					"UTF-8", false);
			// System.out.println(EncryptUtil.decryptDES("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",
			// SALT));

		} catch (Exception e) {
			e.printStackTrace();
		}

		String fileName = "safe_cli.cfg";
		res.setHeader("content-type", "application/octet-stream");
		res.setContentType("application/octet-stream");
		res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			os = res.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(new File(uploadFileName)));
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("success");
	}

}
