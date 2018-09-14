package com.anhuay.os.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anhuay.common.utils.DateUtils;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;
import com.anhuay.os.domain.OsCmdDO;
import com.anhuay.os.domain.OsManagerDO;
import com.anhuay.os.domain.OsManagerVO;
import com.anhuay.os.manager.OsManagerInterface;
import com.anhuay.os.service.OsCmdService;
import com.anhuay.os.service.OsManagerService;
import com.common.constant.CommonEnum;

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
	
	@GetMapping()
	@RequiresPermissions("os:osManager:osManager")
	String OsManager(){
	    return "os/osManager/osManager";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("os:osManager:osManager")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OsManagerDO> osManagerList = osManagerService.list(query);
		int total = osManagerService.count(query);
		PageUtils pageUtils = new PageUtils(osManagerList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("os:osManager:add")
	String add(){
	    return "os/osManager/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("os:osManager:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OsManagerDO osManager = osManagerService.get(id);
		model.addAttribute("osManager", osManager);
	    return "os/osManager/edit";
	}
	
	@GetMapping("/remote")
	String remote(Long id,String osIp,Model model){
		
		model.addAttribute("osId", id);
		model.addAttribute("osIp", osIp);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("osId", id);
		OsCmdDO osCmd = osCmdService.getOsCmd(map);
		if (osCmd!=null && StringUtils.isNotBlank(osCmd.getCmdResult())) {
			osCmd.setCmdResult(DateUtils.getTime(osCmd.getUpdateTime()) + " 操作信息：" + osCmd.getCmdResult());
		}
		
		
		model.addAttribute("osCmd", osCmd);
		return "os/osManager/remote";
	}
	
	@GetMapping("/uninstallCode")
	String uninstallCode(String osIds,String osIps,Model model){
		
		model.addAttribute("osIds", osIds);
		model.addAttribute("osIps", osIps);
		return "os/osManager/uninstallCode";
	}
	
	
	/**
	 * 删除
	 */
	@PostMapping( "/setUninstallPassword")
	@ResponseBody
	public R remove(String osIds,String osIps,String code){
		
		if(StringUtils.isNotBlank(osIds)){
			
			String[] idsArray = StringUtils.split(osIds, ",");
			String[] ipsArray = StringUtils.split(osIps, ",");
			
			for (int i = 0; i < idsArray.length; i++) {
				
				try {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("osId", idsArray[i]);
					OsManagerDO osManager = osManagerService.getOsManager(map);
					
					if(osManager != null){
						
						osManager.setUninstallStatus(StringUtils.isBlank(osManager.getUninstallPasswd())?"0":"1");
						osManager.setUninstallPasswd(code);
						osManager.setUpdateTime(System.currentTimeMillis());
						osManagerService.update(osManager);
					}else{
						
						osManager = new OsManagerDO();
						osManager.setOsId(String.valueOf(idsArray[i]));
						osManager.setOsIp(ipsArray[i]);
						osManager.setUninstallStatus("1");
						osManager.setUninstallPasswd(code);
						osManager.setCreateTime(System.currentTimeMillis());
						osManagerService.save(osManager);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}
		
		
		
		//osManagerService.batchUpdateStatus(ids);
		return R.ok();
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("os:osManager:add")
	public R save( OsManagerVO osManager){
		
		osManagerInterface.saveOsManager(osManager);
		
		return R.ok();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("os:osManager:edit")
	public R update( OsManagerDO osManager){
		osManagerService.update(osManager);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("os:osManager:remove")
	public R remove( Integer id){
		if(osManagerService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("os:osManager:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		osManagerService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}
