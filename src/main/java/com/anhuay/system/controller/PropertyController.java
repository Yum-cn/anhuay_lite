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

import com.anhuay.system.domain.PropertyDO;
import com.anhuay.system.service.PropertyService;
import com.common.constant.CommonEnum;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;

/**
 * 属性配置表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-08 15:55:30
 */
 
@Controller
@RequestMapping("/system/property")
public class PropertyController {
	@Autowired
	private PropertyService propertyService;
	
	@GetMapping()
	@RequiresPermissions("system:property")
	String Property(){
	    return "system/property/property";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:property")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PropertyDO> propertyList = propertyService.list(query);
		int total = propertyService.count(query);
		PageUtils pageUtils = new PageUtils(propertyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:property:add")
	String add(){
	    return "system/property/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:property:edit")
	String edit(@PathVariable("id") Long id,Model model){
		PropertyDO property = propertyService.get(id);
		model.addAttribute("property", property);
	    return "system/property/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:property:add")
	public R save( PropertyDO property){
		
		property.setUpdateTime(System.currentTimeMillis() / 1000);
		if(property.getId()!=null && property.getId()>0){
			if(propertyService.update(property)>0){
				return R.ok();
			}
		}else{
			property.setStatus(CommonEnum.STATUS.ONE.value);
			property.setCreateTime(System.currentTimeMillis() / 1000);
			
			if(propertyService.save(property)>0){
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
	@RequiresPermissions("system:property:edit")
	public R update( PropertyDO property){
		property.setUpdateTime(System.currentTimeMillis() / 1000);
		propertyService.update(property);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:property:remove")
	public R remove( Long id){
		if(propertyService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:property:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		propertyService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}
