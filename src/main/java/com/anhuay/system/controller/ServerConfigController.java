package com.anhuay.system.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anhuay.system.domain.ServerConfigDO;
import com.anhuay.system.service.ServerConfigService;
import com.common.constant.CommonEnum;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;

/**
 * 服务配置表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-07 15:22:38
 */
 
@Controller
@RequestMapping("/system/serverConfig")
public class ServerConfigController {
	@Autowired
	private ServerConfigService serverConfigService;
	
	@GetMapping()
	@RequiresPermissions("system:serverConfig:serverConfig")
	String ServerConfig(){
	    return "system/serverConfig/serverConfig";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:serverConfig:serverConfig")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ServerConfigDO> serverConfigList = serverConfigService.list(query);
		int total = serverConfigService.count(query);
		PageUtils pageUtils = new PageUtils(serverConfigList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:serverConfig:add")
	String add(){
	    return "system/serverConfig/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:serverConfig:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ServerConfigDO serverConfig = serverConfigService.get(id);
		model.addAttribute("serverConfig", serverConfig);
	    return "system/serverConfig/add";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:serverConfig:add")
	public R save( ServerConfigDO serverConfig){
		
		serverConfig.setUpdateTime(System.currentTimeMillis() / 1000);
		if(serverConfig.getId()!=null && serverConfig.getId()>0){
			if(serverConfigService.update(serverConfig)>0){
				return R.ok();
			}
		}else{
			serverConfig.setStatus(CommonEnum.STATUS.ONE.value);
			serverConfig.setCreateTime(System.currentTimeMillis() / 1000);
			
			if(serverConfigService.save(serverConfig)>0){
				return R.ok();
			}
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:serverConfig:edit")
	public R update( ServerConfigDO serverConfig){
		serverConfigService.update(serverConfig);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:serverConfig:remove")
	public R remove( Long id){
		if(serverConfigService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:serverConfig:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		serverConfigService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}
