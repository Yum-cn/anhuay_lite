package com.anhuay.os.controller;

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

import com.anhuay.common.annotation.Log;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;
import com.anhuay.os.domain.PersonLiableDO;
import com.anhuay.os.domain.PersonLiableVO;
import com.anhuay.os.service.PersonLiableService;
import com.common.constant.CommonEnum;

/**
 * 责任人表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-23 18:51:06
 */
 
@Controller
@RequestMapping("/os/personLiable")
public class PersonLiableController {
	@Autowired
	private PersonLiableService personLiableService;
	
	@GetMapping()
	@RequiresPermissions("os:personLiable:personLiable")
	String PersonLiable(){
	    return "os/personLiable/personLiable";
	}
	
	@GetMapping("/select")
	String PersonLiableSelect(){
	    return "os/personLiable/personLiableSelect";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PersonLiableDO> personLiableList = personLiableService.list(query);
		//List<PersonLiableVO> personLiableList = personLiableService.listJoin(query);
		int total = personLiableService.count(query);
		PageUtils pageUtils = new PageUtils(personLiableList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("os:personLiable:add")
	String add(){
	    return "os/personLiable/add";
	}
	@Log("编辑责任人")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("os:personLiable:edit")
	String edit(@PathVariable("id") Long id,Model model){
		PersonLiableDO personLiable = personLiableService.get(id);
		model.addAttribute("personLiable", personLiable);
	    return "os/personLiable/add";
	}
	
	/**
	 * 保存
	 */
	@Log("保存责任人")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("os:personLiable:add")
	public R save( PersonLiableDO personLiable){

		personLiable.setUpdateTime(System.currentTimeMillis() / 1000);
		if(personLiable.getId()!=null && personLiable.getId()>0){
			if(personLiableService.update(personLiable)>0){
				return R.ok();
			}
		}else{
			personLiable.setStatus(CommonEnum.STATUS.ONE.value);
			personLiable.setCreateTime(System.currentTimeMillis() / 1000);
			
			if(personLiableService.save(personLiable)>0){
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
	@RequiresPermissions("os:personLiable:edit")
	public R update( PersonLiableDO personLiable){
		personLiableService.update(personLiable);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@Log("删除责任人")
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("os:personLiable:remove")
	public R remove( Long id){
		if(personLiableService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("os:personLiable:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		personLiableService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}
