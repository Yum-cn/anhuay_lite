package com.anhuay.os.controller;

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

import com.anhuay.os.domain.OsGroupOsDO;
import com.anhuay.os.service.OsGroupOsService;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;

/**
 * 主机组主机
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-24 14:45:03
 */
 
@Controller
@RequestMapping("/os/osGroupOs")
public class OsGroupOsController {
	@Autowired
	private OsGroupOsService osGroupOsService;
	
	@GetMapping()
	@RequiresPermissions("os:osGroupOs:osGroupOs")
	String OsGroupOs(){
	    return "os/osGroupOs/osGroupOs";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OsGroupOsDO> osGroupOsList = osGroupOsService.list(query);
		int total = osGroupOsService.count(query);
		PageUtils pageUtils = new PageUtils(osGroupOsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("os:osGroupOs:add")
	String add(){
	    return "os/osGroupOs/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("os:osGroupOs:edit")
	String edit(@PathVariable("id") Long id,Model model){
		OsGroupOsDO osGroupOs = osGroupOsService.get(id);
		model.addAttribute("osGroupOs", osGroupOs);
	    return "os/osGroupOs/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("os:osGroupOs:add")
	public R save( OsGroupOsDO osGroupOs){
		if(osGroupOsService.save(osGroupOs)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("os:osGroupOs:edit")
	public R update( OsGroupOsDO osGroupOs){
		osGroupOsService.update(osGroupOs);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("os:osGroupOs:remove")
	public R remove( Long id){
		if(osGroupOsService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("os:osGroupOs:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		osGroupOsService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}
