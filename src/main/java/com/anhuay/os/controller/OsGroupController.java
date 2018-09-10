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

import com.anhuay.os.domain.OsGroupDO;
import com.anhuay.os.service.OsGroupService;
import com.common.constant.CommonEnum;
import com.anhuay.common.annotation.Log;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;

/**
 * 主机组
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-24 14:45:03
 */
 
@Controller
@RequestMapping("/os/osGroup")
public class OsGroupController {
	@Autowired
	private OsGroupService osGroupService;
	
	@GetMapping()
	@RequiresPermissions("os:osGroup:osGroup")
	String OsGroup(){
	    return "os/osGroup/osGroup";
	}
	
	@GetMapping("/select")
	String OsGroupSelect(){
	    return "os/osGroup/osGroupSelect";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OsGroupDO> osGroupList = osGroupService.list(query);
		int total = osGroupService.count(query);
		PageUtils pageUtils = new PageUtils(osGroupList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("os:osGroup:add")
	String add(){
	    return "os/osGroup/add";
	}
	@Log("编辑主机组")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("os:osGroup:edit")
	String edit(@PathVariable("id") Long id,Model model){
		OsGroupDO osGroup = osGroupService.get(id);
		model.addAttribute("osGroup", osGroup);
	    return "os/osGroup/add";
	}
	
	/**
	 * 保存
	 */
	@Log("保存主机组")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("os:osGroup:add")
	public R save( OsGroupDO osGroup){
		
		osGroup.setUpdateTime(System.currentTimeMillis() / 1000);
		if(osGroup.getId()!=null && osGroup.getId()>0){
			if(osGroupService.update(osGroup)>0){
				return R.ok();
			}
		}else{
			osGroup.setStatus(CommonEnum.STATUS.ONE.value);
			osGroup.setCreateTime(System.currentTimeMillis() / 1000);
			
			if(osGroupService.save(osGroup)>0){
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
	@RequiresPermissions("os:osGroup:edit")
	public R update( OsGroupDO osGroup){
		osGroupService.update(osGroup);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@Log("删除主机组")
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("os:osGroup:remove")
	public R remove( Long id){
		if(osGroupService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("os:osGroup:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		osGroupService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}
