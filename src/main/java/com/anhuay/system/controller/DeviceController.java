package com.anhuay.system.controller;

import java.util.List;
import java.util.Map;

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

import com.anhuay.common.domain.DictDO;
import com.anhuay.common.domain.Tree;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;
import com.anhuay.system.domain.DeviceDO;
import com.anhuay.system.service.DeviceService;
import com.common.constant.CommonEnum;

/**
 * 设备表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-10 20:02:56
 */
 
@Controller
@RequestMapping("/system/device")
public class DeviceController {
	@Autowired
	private DeviceService deviceService;
	
	@GetMapping()
	@RequiresPermissions("system:device:device")
	String Device(){
	    return "system/device/device";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:device:device")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DeviceDO> deviceList = deviceService.list(query);
		int total = deviceService.count(query);
		PageUtils pageUtils = new PageUtils(deviceList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:device:add")
	String add(){
	    return "system/device/add";
	}
	
	
	// 类别已经指定增加
	@GetMapping("/add/{dictId}/{dictName}")
	@RequiresPermissions("system:device:add")
	String addDefault(Model model, @PathVariable("dictId") Long dictId, @PathVariable("dictName") String dictName) {
		
		DeviceDO deviceDO = new DeviceDO();
		deviceDO.setDictId(dictId);
		deviceDO.setDictName(dictName);
		model.addAttribute("device", deviceDO);
		return "system/device/add";
	}


	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:device:edit")
	String edit(@PathVariable("id") Long id,Model model){
		DeviceDO device = deviceService.get(id);
		model.addAttribute("device", device);
	    return "system/device/add";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:device:add")
	public R save( DeviceDO device){

		device.setUpdateTime(System.currentTimeMillis() / 1000);
		if(device.getId()!=null && device.getId()>0){
			if(deviceService.update(device)>0){
				return R.ok();
			}
		}else{
			device.setStatus(CommonEnum.STATUS.ONE.value);
			device.setCreateTime(System.currentTimeMillis() / 1000);
			
			if(deviceService.save(device)>0){
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
	@RequiresPermissions("system:device:edit")
	public R update( DeviceDO device){
		deviceService.update(device);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:device:remove")
	public R remove( Long id){
		if(deviceService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:device:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		deviceService.batchUpdateStatus(ids);
		return R.ok();
	}
	
	@GetMapping("/tree")
	@ResponseBody
	public Tree<DictDO> tree(@RequestParam Map<String, Object> params) {
		Tree<DictDO> tree = new Tree<DictDO>();
		tree = deviceService.getTree(params);
		return tree;
	}
	
}
