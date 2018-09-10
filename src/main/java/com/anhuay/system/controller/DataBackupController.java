package com.anhuay.system.controller;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
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
import com.anhuay.system.domain.DataBackupDO;
import com.anhuay.system.domain.PropertyDO;
import com.anhuay.system.service.DataBackupService;
import com.anhuay.system.service.PropertyService;
import com.common.constant.CommonEnum;

import net.sf.json.JSONObject;

/**
 * 数据备份表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-08-07 21:11:30
 */

@Controller
@RequestMapping("/system/dataBackup")
public class DataBackupController {
	@Autowired
	private DataBackupService dataBackupService;
	@Autowired
	private PropertyService propertyService;

	@GetMapping()
	@RequiresPermissions("system:dataBackup:dataBackup")
	String DataBackup() {
		return "system/dataBackup/dataBackup";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:dataBackup:dataBackup")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<DataBackupDO> dataBackupList = dataBackupService.list(query);
		int total = dataBackupService.count(query);
		PageUtils pageUtils = new PageUtils(dataBackupList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("system:dataBackup:add")
	String add() {
		return "system/dataBackup/add";
	}
	@Log("编辑数据备份")
	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:dataBackup:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		DataBackupDO dataBackup = dataBackupService.get(id);
		model.addAttribute("dataBackup", dataBackup);
		return "system/dataBackup/edit";
	}

	/**
	 * 备份
	 */
	@Log("数据备份")
	@ResponseBody
	@GetMapping("/backup")
	@RequiresPermissions("system:dataBackup:dataBackup")
	public R save(Model model) {
		
		try {
			String fileName = databaseBackup();
			DataBackupDO dataBackupDO = new DataBackupDO();
			dataBackupDO.setFileName(fileName);
			dataBackupDO.setUpdateTime(System.currentTimeMillis() / 1000);
			dataBackupDO.setStatus(CommonEnum.STATUS.ONE.value);
			dataBackupDO.setCreateTime(System.currentTimeMillis() / 1000);
			
			if(dataBackupService.save(dataBackupDO)>0){
				return R.ok();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:dataBackup:edit")
	public R update(DataBackupDO dataBackup) {
		dataBackupService.update(dataBackup);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@Log("删除数据备份")
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("system:dataBackup:remove")
	public R remove(Long id) {
		if (dataBackupService.updateStatus(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:dataBackup:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		dataBackupService.batchUpdateStatus(ids);
		return R.ok();
	}

	public  String databaseBackup() throws InterruptedException {

		PrintWriter printWriter = null;
		BufferedReader bufferedReader = null;
		try {

			Process process = null;
			String fPath = "";
			try {

				Map<String,Object> queryMap = new HashMap<String,Object>();
				queryMap.put("propName", "database_backup");
				List<PropertyDO> propertyList = propertyService.list(queryMap);
				
				PropertyDO property = CollectionUtils.isEmpty(propertyList)?new PropertyDO():propertyList.get(0);
				
				//String jsonStr = "{\"datasource_ip\":\"127.0.0.1\",\"database_name\":\"anhuay\",\"database_username\":\"root\",\"database_password\":\"1qaz2wsx\",\"database_backup_path\":\"/var/uploaded_files/\"}";
				JSONObject jsonObject = JSONObject.fromObject(property.getPropValue());
				if (jsonObject != null) {

					fPath = jsonObject.optString("database_backup_path") + "/" + new Date().getTime() + "_"
							+ jsonObject.optString("database_name") + ".sql";

					String execCommond = " mysqldump -h" + jsonObject.optString("datasource_ip") + " -u"
							+ jsonObject.optString("database_password") + " -p"
							+ jsonObject.optString("database_password") + " " + jsonObject.optString("database_name")
							+ " >" + fPath;
					/*fPath = "D:/backupDatabase" + "/" + new Date().getTime() + "_"
							+ jsonObject.optString("database_name") + ".sql";
					String execCommond = "C:/Program Files/MySQL/MySQL Server 5.7/bin/mysqldump.exe -h127.0.0.1 -uroot -proot --set-charset=UTF8 anhuay";*/

					System.out.println("exceCommond:" + execCommond);
					process = Runtime.getRuntime().exec(execCommond);
				}

			} catch (Exception e) {
				e.printStackTrace();
				process = Runtime.getRuntime()
						.exec("mysqldump -h127.0.0.1 -uroot -p1qaz2wsx  server >/var/uploaded_files/"
								+ new Date().getTime() + "_anhuay.sql");
			}

			printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fPath), "utf8"));
			InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				printWriter.println(line);
			}
			printWriter.flush();
			if (process.waitFor() == 0) {// 0 表示线程正常终止。
				return fPath;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
