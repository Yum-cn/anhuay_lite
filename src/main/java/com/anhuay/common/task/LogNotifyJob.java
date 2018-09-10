/*package com.anhuay.common.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.anhuay.audit.domain.AuditLogDO;
import com.anhuay.audit.service.AuditLogService;
import com.anhuay.common.domain.TaskValueDO;
import com.anhuay.common.service.TaskValueService;
import com.anhuay.oa.dao.NotifyDao;
import com.anhuay.oa.dao.NotifyRecordDao;
import com.anhuay.oa.domain.NotifyDO;
import com.anhuay.oa.domain.NotifyRecordDO;
import com.anhuay.oa.domain.Response;
import com.anhuay.system.domain.PropertyDO;
import com.anhuay.system.domain.UserDO;
import com.anhuay.system.service.PropertyService;
import com.anhuay.system.service.SessionService;

@Component
public class LogNotifyJob implements Job{
	@Autowired
	SimpMessagingTemplate template;
	@Autowired
	private AuditLogService logService;
	@Autowired
	private TaskValueService taskValueService;
	@Autowired
    private NotifyDao notifyDao;
    @Autowired
    private NotifyRecordDao recordDao;
    @Autowired
	private PropertyService propertyService;
    @Autowired
    private SessionService sessionService;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
    	
    	Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("jobName", "logNotify");
		List<TaskValueDO> taskValueList = taskValueService.list(queryMap);
		
		TaskValueDO taskValue = CollectionUtils.isEmpty(taskValueList)?new TaskValueDO():taskValueList.get(0);
		
		if(taskValue!=null && taskValue.getThresholdValue()>=0){
			
			Map<String,Object> queryLogMap = new HashMap<String,Object>();
			queryLogMap.put("logQueryId", taskValue.getThresholdValue());
			queryLogMap.put("limit", 1000);
			List<AuditLogDO> logList = logService.listLog(queryLogMap);
			
			if(CollectionUtils.isNotEmpty(logList)){
				long maxId = 0;
				for (int i = 0; i < logList.size(); i++) {
					AuditLogDO bean= logList.get(i);
					if(i==0){
						maxId=bean.getLogid();
					}
					
					//行为类别（3-违规行为，2-异常行为，1-一般行为）
					//private String beType;
					//日志分类（1.本地文件审计，2.U盘文件审计，3.开机关机审计，4.进程监控审计，5.打印监控审计，6.主机信息审计，7.系统日志审计，8.账户监控审计，9.共享监控审计， 10.异常监控审计，11.主机配置审计， 12.移动介质审计，13.端口监控审计，14.服务监控审计，15.连接监控审计，16.网络流量检测，17.磁盘空间检测，18.文件控制，19.光盘刻录审计）
					//private String logType;
					
					if(StringUtils.equals(bean.getBeType(), "3")){
						
						NotifyDO notify = new NotifyDO();
						notify.setType("2");
						notify.setTitle("日志告警通知");
						
						String logType = "";
						switch(bean.getLogType()){  
						    case "1":logType="本地文件审计";break;  
						    case "2":logType="U盘文件审计";break;  
						    case "3":logType="开机关机审计";break;  
						    case "4":logType="进程监控审计";break;  
						    case "5":logType="打印监控审计";break;  
						    case "6":logType="系统日志审计";break;  
						    case "7":logType="账户监控审计";break;  
						    case "8":logType="账户监控审计";break;  
						    case "9":logType="共享监控审计";break;  
						    case "10":logType="异常监控审计";break;  
						    case "11":logType="主机配置审计";break;  
						    case "12":logType="移动介质审计";break;  
						    case "13":logType="端口监控审计";break;  
						    case "14":logType="服务监控审计";break;  
						    case "15":logType="连接监控审计";break;  
						    case "16":logType="网络流量检测";break;  
						    case "17":logType="磁盘空间检测";break;  
						    case "18":logType="文件控制";break;  
						    case "19":logType="光盘刻录审计";break;  
						    default:  
						}  
						
						String beType = "";
						switch(bean.getBeType()){  
						    case "1":beType="一般行为";break;  
						    case "2":beType="异常行为";break;  
						    case "3":beType="违规行为";break;  
						    default:  
						} 
						
						Map<String,Object> queryPropertyMap = new HashMap<String,Object>();
						queryPropertyMap.put("propName", "log_notify_users");
						List<PropertyDO> propertyList = propertyService.list(queryMap);
						PropertyDO property = CollectionUtils.isEmpty(propertyList)?new PropertyDO():propertyList.get(0);
						
						if(property==null||StringUtils.isBlank(property.getPropValue())){
							return;
						}
						
						
						
						notify.setContent(logType+":"+beType);
						notify.setStatus("1");
						notify.setCreateBy(1L);
						notify.setUpdateDate(new Date());
				        notifyDao.save(notify);
				        // 保存到接受者列表中
				        String[] userIds = property.getPropValue().split(",");
				        Long notifyId = notify.getId();
				        List<NotifyRecordDO> records = new ArrayList<>();
				        for (String userId : userIds) {
				            NotifyRecordDO record = new NotifyRecordDO();
				            record.setNotifyId(notifyId);
				            record.setUserId(NumberUtils.toLong(userId));
				            record.setIsRead(0);
				            records.add(record);
				        }
				        recordDao.batchSave(records);
				        //给在线用户发送通知
				        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,1,0, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
				        executor.execute(new Runnable() {
				            @Override
				            public void run() {
				                for (UserDO userDO : sessionService.listOnlineUser()) {
				                    for (String userId : userIds) {
				                        if (StringUtils.equals(userId,String.valueOf(userDO.getUserId()))) {
				                            template.convertAndSendToUser(userDO.toString(), "/queue/notifications", "新消息：" + notify.getTitle());
				                        }
				                    }
				                }
				            }
				        });
				        executor.shutdown();
						//template.convertAndSend("/topic/getResponse", new Response("欢迎体验bootdo,这是一个任务计划，使用了websocket和quzrtz技术，可以在计划列表中取消，欢迎您加入qq群交流学习!" ));
					}
					
				}
				if(maxId>0){
					taskValue.setLastUpdateDate(new Date());
					taskValue.setThresholdValue(maxId);
					taskValueService.update(taskValue);
				}
			}
			
		}
		
    	
    	//template.convertAndSend("/topic/getResponse", new Response("欢迎体验bootdo,这是一个任务计划，使用了websocket和quzrtz技术，可以在计划列表中取消，欢迎您加入qq群交流学习!" ));

    }

}*/