package com.anhuay.alarm.controller;

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

import com.anhuay.alarm.domain.EmailConfigDO;
import com.anhuay.alarm.service.EmailConfigService;
import com.anhuay.common.annotation.Log;
import com.anhuay.common.utils.PageUtils;
import com.anhuay.common.utils.Query;
import com.anhuay.common.utils.R;
import com.common.constant.CommonEnum;

/**
 * 邮箱配置
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-04 14:42:51
 */
 
@Controller
@RequestMapping("/alarm/emailConfig")
public class EmailConfigController {
	@Autowired
	private EmailConfigService emailConfigService;
	
	@GetMapping()
	@RequiresPermissions("alarm:emailConfig")
	String EmailConfig(){
	    return "alarm/emailConfig/emailConfig";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("alarm:emailConfig:emailConfig")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<EmailConfigDO> emailConfigList = emailConfigService.list(query);
		int total = emailConfigService.count(query);
		PageUtils pageUtils = new PageUtils(emailConfigList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("alarm:emailConfig:add")
	String add(){
	    return "alarm/emailConfig/add";
	}
	@Log("编辑邮件配置")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("alarm:emailConfig:edit")
	String edit(@PathVariable("id") Long id,Model model){
		EmailConfigDO emailConfig = emailConfigService.get(id);
		model.addAttribute("emailConfig", emailConfig);
	    return "alarm/emailConfig/add";
	}
	
	/**
	 * 保存
	 */
	@Log("保存邮件配置")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("alarm:emailConfig:add")
	public R save( EmailConfigDO emailConfig){
		emailConfig.setUpdateTime(System.currentTimeMillis() / 1000);
		if(emailConfig.getId()!=null && emailConfig.getId()>0){
			if(emailConfigService.update(emailConfig)>0){
				return R.ok();
			}
		}else{
			emailConfig.setStatus(CommonEnum.STATUS.ONE.value);
			emailConfig.setCreateTime(System.currentTimeMillis() / 1000);
			
			if(emailConfigService.save(emailConfig)>0){
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
	@RequiresPermissions("alarm:emailConfig:edit")
	public R update( EmailConfigDO emailConfig){
		emailConfigService.update(emailConfig);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@Log("删除邮件配置")
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("alarm:emailConfig:remove")
	public R remove( Long id){
		if(emailConfigService.updateStatus(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("alarm:emailConfig:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		emailConfigService.batchUpdateStatus(ids);
		return R.ok();
	}
	
}
