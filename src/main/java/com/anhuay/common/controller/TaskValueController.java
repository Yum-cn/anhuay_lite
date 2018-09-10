package com.anhuay.common.controller;

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

import com.anhuay.common.domain.TaskValueDO;
import com.anhuay.common.service.TaskValueService;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;

/**
 * 同步数据表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-12 12:09:47
 */
 
@Controller
@RequestMapping("/common/taskValue")
public class TaskValueController {
	@Autowired
	private TaskValueService taskValueService;
	
	@GetMapping()
	@RequiresPermissions("common:taskValue:taskValue")
	String TaskValue(){
	    return "common/taskValue/taskValue";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("common:taskValue:taskValue")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TaskValueDO> taskValueList = taskValueService.list(query);
		int total = taskValueService.count(query);
		PageUtils pageUtils = new PageUtils(taskValueList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("common:taskValue:add")
	String add(){
	    return "common/taskValue/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("common:taskValue:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TaskValueDO taskValue = taskValueService.get(id);
		model.addAttribute("taskValue", taskValue);
	    return "common/taskValue/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("common:taskValue:add")
	public R save( TaskValueDO taskValue){
		if(taskValueService.save(taskValue)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("common:taskValue:edit")
	public R update( TaskValueDO taskValue){
		taskValueService.update(taskValue);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("common:taskValue:remove")
	public R remove( Integer id){
		if(taskValueService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("common:taskValue:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		taskValueService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}
