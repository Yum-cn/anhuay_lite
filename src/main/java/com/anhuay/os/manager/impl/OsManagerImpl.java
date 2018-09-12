package com.anhuay.os.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anhuay.os.domain.OsManagerDO;
import com.anhuay.os.domain.OsManagerVO;
import com.anhuay.os.manager.OsManagerInterface;
import com.anhuay.os.service.OsManagerService;
import com.common.manager.impl.BaseManagerImpl;
import com.common.util.BaseResult;
import com.common.util.BaseResultHelper;

import net.sf.json.JSONObject;

@Service(value = "osManager")
public class OsManagerImpl extends BaseManagerImpl implements OsManagerInterface {

	private Logger logger = LoggerFactory.getLogger(OsManagerImpl.class);

	@Autowired
	private OsManagerService osManagerService;
	


	/**
	 * 批量逻辑删除
	 * 
	 * @author Yum
	 */
	@Override
	public BaseResult<Object> saveOsManager(OsManagerVO bean) {

		try {
			
			/*osManager.setUpdateTime(System.currentTimeMillis() / 1000);
			if(osManager.getId()!=null && osManager.getId()>0){
				if(osManagerService.update(osManager)>0){
					return R.ok();
				}
			}else{
				//osManager.setStatus(CommonEnum.STATUS.ONE.value);
				osManager.setCreateTime(System.currentTimeMillis() / 1000);
				
				if(osManagerService.save(osManager)>0){
					return R.ok();
				}
			}
			
			private Integer id;
	//主机id（每个id最多只能有一条记录）
	private String osId;
	//主机ip
	private String osIp;
	//应用安全状态
	private String processMonitorStatus;
	//应用安全规则
	private String processMonitorRules;
	//账户安全状态
	private String accountMonitorStatus;
	//账户安全规则
	private String accountMonitorRules;
	//杀毒软件检测状态
	private String sdSoftMonitorStatus;
	//杀毒软件检查规则
	private String sdSoftMonitorRules;
	//网络连接监控状态
	private String netlinkMonitorStatus;
	//网络连接监控规则
	private String netlinkMonitorRules;
	//关闭是否启用密码
	private String closeStatus;
	//关闭密码
	private String closePasswd;
	//卸载是否需要密码
	private String uninstallStatus;
	//卸载密码
	private String uninstallPasswd;
	//策略下发状态（1-待下发，2-下发中，3-已生效，4-配置失败）
	private String taskStatus;
	//创建时间
	private Long createTime;
	//修改时间
	private Long updateTime;
			*/
			
			String osIds = bean.getOsIds();
			String osNames = bean.getOsNames();
			String accountMonitorStatus = bean.getAccountMonitorStatus();
			String antiVirusSoftwareStatus = bean.getAntiVirusSoftwareStatus();
			String passwordRule = bean.getPasswordRule();
			String processBlackStatus = bean.getProcessBlackStatus();
			String processBlackRules = bean.getProcessBlackRules();
			String processMonitorStatus = bean.getProcessMonitorStatus();
			String processWhiteStatus = bean.getProcessWhiteStatus();
			String processWhiteRules = bean.getProcessWhiteRules();
			
			if(StringUtils.isBlank(osIds)){
				return BaseResultHelper.error();
			}
			
			String[] idArray = StringUtils.split(osIds,";");
			String[] namesArray = StringUtils.split(osNames,";");
			for (int i = 0; i < idArray.length; i++) {
				try {
					OsManagerDO osManager = new OsManagerDO();
					Map<String,Object> queryMap = new HashMap<String,Object>();
					queryMap.put("osId", idArray[i]);
					List<OsManagerDO> list = osManagerService.list(queryMap);
					if(CollectionUtils.isNotEmpty(list)){
						osManager = list.get(0);
					}
					
					osManager.setOsId(idArray[i]);
					osManager.setOsIp(namesArray[i]);
					osManager.setAccountMonitorStatus(accountMonitorStatus);
					osManager.setAccountMonitorRules(passwordRule);
					osManager.setSdSoftMonitorStatus(antiVirusSoftwareStatus);
					osManager.setAccountMonitorRules(passwordRule);
					osManager.setProcessMonitorStatus(processMonitorStatus);
					//{"process_black_status":"1","process_black_rules":"4","process_white_status":"1","process_white_rules":"5","process_alarm_level":"2"}
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("process_monitor_status",processMonitorStatus);
					jsonObject.put("process_black_status",processBlackStatus );
					jsonObject.put("process_black_rules", processBlackRules);
					jsonObject.put("process_white_status", processWhiteStatus);
					jsonObject.put("process_white_rules", processWhiteRules);
					osManager.setProcessMonitorRules(jsonObject.toString());
					
					if(CollectionUtils.isNotEmpty(list)){
						osManager.setUpdateTime(System.currentTimeMillis());
						osManagerService.update(osManager);
					}else{
						osManager.setCreateTime(System.currentTimeMillis());
						osManagerService.save(osManager);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			return BaseResultHelper.success(null);
		} catch (Exception e) {
			e.printStackTrace();
			return BaseResultHelper.error();
		}
	}

}
