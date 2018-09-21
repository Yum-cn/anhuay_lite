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

import com.anhuay.os.domain.NetInfoDO;
import com.anhuay.os.service.NetInfoService;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;

/**
 * 
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-09-19 17:42:22
 */
 
@Controller
@RequestMapping("/os/netInfo")
public class NetInfoController {
	@Autowired
	private NetInfoService netInfoService;
	
	@GetMapping()
	@RequiresPermissions("os:netInfo:netInfo")
	String NetInfo(){
	    return "os/netInfo/netInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("os:netInfo:netInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<NetInfoDO> netInfoList = netInfoService.list(query);
		int total = netInfoService.count(query);
		PageUtils pageUtils = new PageUtils(netInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("os:netInfo:add")
	String add(){
	    return "os/netInfo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("os:netInfo:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		NetInfoDO netInfo = netInfoService.get(id);
		model.addAttribute("netInfo", netInfo);
	    return "os/netInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("os:netInfo:add")
	public R save( NetInfoDO netInfo){
		if(netInfoService.save(netInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("os:netInfo:edit")
	public R update( NetInfoDO netInfo){
		netInfoService.update(netInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("os:netInfo:remove")
	public R remove( Integer id){
		if(netInfoService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("os:netInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		netInfoService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}
