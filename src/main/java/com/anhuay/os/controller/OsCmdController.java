package com.anhuay.os.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
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
import com.anhuay.os.service.OsCmdService;
import com.common.util.BaseResult;
import com.common.util.BaseResultHelper;

import net.sf.json.JSONObject;

/**
 * 
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-09-10 17:03:24
 */

@Controller
@RequestMapping("/os/osCmd")
public class OsCmdController {
	@Autowired
	private OsCmdService osCmdService;

	@GetMapping()
	@RequiresPermissions("os:osCmd:osCmd")
	String OsCmd() {
		return "os/osCmd/osCmd";
	}

	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<OsCmdDO> osCmdList = osCmdService.list(query);
		int total = osCmdService.count(query);
		PageUtils pageUtils = new PageUtils(osCmdList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("os:osCmd:add")
	String add() {
		return "os/osCmd/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("os:osCmd:edit")
	String edit(@PathVariable("id") Integer id, Model model) {
		OsCmdDO osCmd = osCmdService.get(id);
		model.addAttribute("osCmd", osCmd);
		return "os/osCmd/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public Object save(OsCmdDO osCmd) {
		// osCmd.setUpdateTime(System.currentTimeMillis() / 1000);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("osId", osCmd.getOsId());

		if (osCmd.getId() != null && osCmd.getId() > 0) {

			OsCmdDO osCmdDO = osCmdService.get(osCmd.getId());
			osCmdDO.setUpdateTime(osCmd.getUpdateTime());
			osCmdDO.setCmdType(osCmd.getCmdType());
			osCmdDO.setCmdStatus("1");

			if (osCmdService.update(osCmdDO) > 0) {

				if (StringUtils.isNotBlank(osCmdDO.getCmdResult())) {
					osCmdDO.setCmdResult(osCmdDO.getUpdateTime()==null?"":DateUtils.getTime(osCmdDO.getUpdateTime()) + " 操作信息：" + osCmdDO.getCmdResult());
				}
				return BaseResultHelper.success(osCmdDO);
			}
		} else {

			OsCmdDO osCmdDO = new OsCmdDO();
			osCmdDO.setOsId(osCmd.getOsId());
			osCmdDO.setOsIp(osCmd.getOsIp());
			osCmdDO.setCreateTime(System.currentTimeMillis());
			osCmdDO.setUpdateTime(System.currentTimeMillis());
			osCmdDO.setCmdType(osCmd.getCmdType());
			osCmdDO.setCmdStatus("1");
			if (osCmdService.save(osCmdDO) > 0) {
				//osCmdDO.setCmdResult(DateUtils.getTime(osCmdDO.getUpdateTime()) + " 命令执行中......");
				return BaseResultHelper.success(osCmdDO);
			}
		}
		return BaseResultHelper.error();
	}

	@ResponseBody
	@GetMapping("/getCmdResult")
	public Object get(@RequestParam Map<String, Object> params) {

		OsCmdDO osCmdDO = osCmdService.getOsCmd(params);
		
		if(osCmdDO==null){
			osCmdDO = new OsCmdDO();
		}
		
		if (StringUtils.isNotBlank(osCmdDO.getCmdResult())) {
			osCmdDO.setCmdResult(DateUtils.getTime(osCmdDO.getCreateTime()) + " 操作信息：" + osCmdDO.getCmdResult());
		}else{
			//osCmdDO.setCmdResult(DateUtils.getTime(osCmdDO.getCreateTime()) + " 命令执行中......");
		}
		return BaseResultHelper.success(osCmdDO);
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("os:osCmd:edit")
	public R update(OsCmdDO osCmd) {
		osCmdService.update(osCmd);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("os:osCmd:remove")
	public R remove(Integer id) {
		if (osCmdService.updateStatus(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("os:osCmd:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids) {
		osCmdService.batchUpdateStatus(ids);
		return R.ok();
	}

}
