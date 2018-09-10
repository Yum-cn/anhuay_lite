$().ready(function() {
	loadType("alarm_level","process_alarm_level");
	loadType("alarm_level","os_config_black_alarm_level");
	loadType("alarm_level","os_config_white_alarm_level");
	loadType("alarm_level","server_alarm_level");
	loadType("alarm_level","connection_black_alarm_level");
	loadType("alarm_level","connection_white_alarm_level");
	loadType("alarm_level","connection_alarm_level");
	loadType("alarm_level","network_flow_alarm_level");
	loadType("alarm_level","disk_space_alarm_level");
	loadType("share_type","share_monitor_type");
	 loadTypeTab("yum-select2","alarm_level","alarm_id");
//	loadType("alarm_level","alarm_id");
//	loadType("cd_access_type","cd_rom");
//	loadType("record_mode","record_mode");
	initData();
	validateRule();
    // console.log(osAudit);
	$("#temp_addresses").dblclick(function() {
		$("option:selected", this).remove();
		document.getElementById('temp_addresses')[0].selected = true;
		dealOptionsValue("temp_addresses","server_connection_black_addresses");
	});
	$("#remove").click(function() {
		$("#temp_addresses option:selected").remove();
		$("#temp_addresses option:first").attr("selected", true);
		dealOptionsValue("temp_addresses","server_connection_black_addresses");
	});
	
	
	$("#move_media_show").dblclick(function() {
		$("option:selected", this).remove();
		document.getElementById('move_media_show')[0].selected = true;
		dealOptionsValue("move_media_show","move_media_names");
	});
	$("#removeMoveMedia").click(function() {
		$("#move_media_show option:selected").remove();
		$("#move_media_show option:first").attr("selected", true);
		dealOptionsValue("move_media_show","move_media_names");
	});
	
	
	$("#port_monitor_show").dblclick(function() {
		$("option:selected", this).remove();
		document.getElementById('port_monitor_show')[0].selected = true;
		dealOptionsValue("port_monitor_show","port_monitor_ports");
	});
	$("#removeMonitorPorts").click(function() {
		$("#port_monitor_show option:selected").remove();
		$("#port_monitor_show option:first").attr("selected", true);
		dealOptionsValue("port_monitor_show","port_monitor_ports");
	});
	
	
	$("#connection_addresses_show").dblclick(function() {
		$("option:selected", this).remove();
		document.getElementById('connection_addresses_show')[0].selected = true;
		dealOptionsValue("connection_addresses_show","connection_white_addresses");
	});
	$("#removeWhiteConnectionAdress").click(function() {
		$("#connection_addresses_show option:selected").remove();
		$("#connection_addresses_show option:first").attr("selected", true);
		dealOptionsValue("connection_addresses_show","connection_white_addresses");
	});
	
	
	$("#file_control_show").dblclick(function() {
		$("option:selected", this).remove();
		document.getElementById('file_control_show')[0].selected = true;
		dealOptionsValue("file_control_show","file_control_values");
	});
	$("#removefileControl").click(function() {
		$("#file_control_show option:selected").remove();
		$("#file_control_show option:first").attr("selected", true);
		dealOptionsValue("file_control_show","file_control_values");
	});

	$("#device_white_show").dblclick(function() {
		$("option:selected", this).remove();
		document.getElementById('device_white_show')[0].selected = true;
		dealOptionsValue("device_white_show","device_white_values");
	});
	$("#removeDeviceId").click(function() {
		$("#device_white_show option:selected").remove();
		$("#device_white_show option:first").attr("selected", true);
		dealOptionsValue("device_white_show","device_white_values");
	});
	
	$("#upload_file_show").dblclick(function() {
		$("option:selected", this).remove();
		document.getElementById('upload_file_show')[0].selected = true;
		dealOptionsValue("upload_file_show","upload_files");
	});
	$("#removeUploadFile").click(function() {
		$("#upload_file_show option:selected").remove();
		$("#upload_file_show option:first").attr("selected", true);
		dealOptionsValue("upload_file_show","upload_files");
	});
	
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function initData() {
	
	console.log(osAudit);
	if(osAudit != null&&osAudit != undefined){
		$("#osAuditId").val(osAudit.id);

		initProcessMonitor(osAudit);
		initServerMonitor(osAudit);
		
		initLocalFile(osAudit);
		initDiskFile(osAudit);
		initUdiskFile(osAudit);
		initOsOnoff(osAudit);
		initPrintMonitor(osAudit);
		initOsInfo(osAudit);
		initSystemLog(osAudit);
		initAccountMonitor(osAudit);
		initShareMonitor(osAudit);
		initExceptionMonitor(osAudit);
		initOsConfig(osAudit);
		initMoveMedia(osAudit);
		initPortMonitor(osAudit);
		initConnectionMonitor(osAudit);
		initNetworkFlow(osAudit);
		initDiskSpace(osAudit);
		initFileControl(osAudit);
	}
	
	if(irregularConnection != null && irregularConnection != undefined){
        $("#irregularConnectionId").val(irregularConnection.id);
    }
	if(deviceControl != null && deviceControl != undefined){
        $("#deviceControlId").val(deviceControl.id);
    }
	if(diskRecord != null && diskRecord != undefined){
        $("#diskRecordId").val(diskRecord.id);
    }
	if(softDistribute != null && softDistribute != undefined){
	     $("#softDistributeId").val(softDistribute.id);
	     initSoftDistribute(softDistribute);
	}
	
}

function initProcessMonitor(osAudit){
	
	debugger;
	var processMonitor = osAudit.processMonitorRules;
	if(processMonitor!=undefined){
		
		if(processMonitor.process_black_status=='1'){
			 $("#process_black_status").attr("checked",true);
		}
		if(processMonitor.process_black_rules!=undefined){
			$("#process_black_rules").val(processMonitor.process_black_rules);
		}
		if(processMonitor.process_white_status=='1'){
			$("#process_white_status").attr("checked",true);
		}
		if(processMonitor.process_white_rules!=undefined){
			$("#process_white_rules").val(processMonitor.process_white_rules);
		}
		if(processMonitor.process_alarm_level!=undefined){
			loadType("alarm_level","process_alarm_level",processMonitor.process_alarm_level);
		}
		// console.log(processMonitorRules.process_black_status);
	}
}

function initServerMonitor(osAudit){
	
	var serverMonitor = osAudit.serverMonitorRules;
	console.log(serverMonitor);
	if(serverMonitor!=undefined){
		
		if(serverMonitor.server_black_status=='1'){
			 $("#server_black_status").attr("checked",true);
		}
		if(serverMonitor.server_black_rules!=undefined){
			$("#server_black_rules").val(serverMonitor.server_black_rules);
		}
		if(serverMonitor.server_white_status=='1'){
			$("#server_white_status").attr("checked",true);
		}
		if(serverMonitor.server_white_rules!=undefined){
			$("#server_white_rules").val(serverMonitor.server_white_rules);
		}
		if(serverMonitor.server_connection_black_status=='1'){
			$("#server_connection_black_status").attr("checked",true);
		}
		var addressValue = serverMonitor.server_connection_black_addresses;
		if(addressValue!=undefined&&addressValue!=""){
			
			$("#server_connection_black_addresses").val(addressValue);
			var addressValueArray=addressValue.split(";");
		    for(var i in addressValueArray){
		    	var html = '<option value="' +addressValueArray[i] + '">' + addressValueArray[i] + '</option>';
				$("#temp_addresses").append(html);
		    }
			
		}
		if(serverMonitor.server_alarm_level!=undefined){
			loadType("alarm_level","server_alarm_level",serverMonitor.server_alarm_level);
		}
		// console.log(processMonitorRules.process_black_status);
	}
}

function addServerConnectionBlackAddress() {
    
	var ip = $("#server_connection_black_ip").val();
	var ports = $("#server_connection_black_ports").val();
	// debugger;
	
	// 校验修改密码表单
	var flag = $("#server_connection_black_ip,#server_connection_black_ports").valid();
	// var flag = $("#signupForm").valid();
	// var flag =
	// $("#signupForm").validate().element($("#server_connection_black_ip"));
    if(!flag){
        // 没有通过验证
        return;
    }
	var serverConnectionBlackAddress = ip+":"+ports;
	
    var html = '<option value="' +serverConnectionBlackAddress + '">' + serverConnectionBlackAddress + '</option>';
    $("#temp_addresses").append(html);
    $("#temp_addresses option:first").attr("selected", true);
    dealOptionsValue("temp_addresses","server_connection_black_addresses");
    
}

function initLocalFile(osAudit){
	var returnData = osAudit.localFileRules;
	if(returnData!=undefined){
		
		if(returnData.local_file_status=='1'){
			 $("#local_file_status").attr("checked",true);
		}
		if(returnData.local_file_options!=undefined){
			var arr = returnData.local_file_options;
			
			if(Array.isArray(arr)){
				for(var i=0;i<arr.length;i++){
					 $("input[name=local_file_options][value='"+arr[i]+"']").attr("checked",true);
					}
			}else{
				 $("input[name=local_file_options][value='"+arr+"']").attr("checked",true);
			}
			
			
		}
		if(returnData.local_file_rules!=undefined){
			$("#local_file_rules").val(returnData.local_file_rules);
		}
	}
}

function initDiskFile(osAudit){
	var returnData = osAudit.diskFileRules;
	if(returnData!=undefined){
		
		if(returnData.disk_file_status=='1'){
			 $("#disk_file_status").attr("checked",true);
		}
		if(returnData.disk_file_options!=undefined){
			var arr = returnData.disk_file_options;
			
			if(Array.isArray(arr)){
				for(var i=0;i<arr.length;i++){
					 $("input[name=disk_file_options][value='"+arr[i]+"']").attr("checked",true);
					}
			}else{
				 $("input[name=disk_file_options][value='"+arr+"']").attr("checked",true);
			}
			
			
		}
		if(returnData.disk_file_status!=undefined){
			$("#disk_file_rules").val(returnData.disk_file_rules);
		}
	}
}

function initUdiskFile(osAudit){
	var returnData = osAudit.udiskFileRules;
	if(returnData!=undefined){
		
		if(returnData.udisk_file_status=='1'){
			 $("#udisk_file_status").attr("checked",true);
		}
		if(returnData.udisk_file_options!=undefined){
			var arr = returnData.udisk_file_options;
			
			if(Array.isArray(arr)){
				for(var i=0;i<arr.length;i++){
					 $("input[name=udisk_file_options][value='"+arr[i]+"']").attr("checked",true);
					}
			}else{
				 $("input[name=udisk_file_options][value='"+arr+"']").attr("checked",true);
			}
			
			
		}
		if(returnData.udisk_file_rules!=undefined){
			$("#udisk_file_rules").val(returnData.udisk_file_rules);
		}
	}
}

function initOsOnoff(osAudit){
	debugger;
	var returnData = osAudit.osOnoffRules;
	if(returnData!=undefined){
		
		if(returnData.os_onoff_status=='1'){
			 $("#os_onoff_status").attr("checked",true);
		}
		if(returnData.workDay!=undefined){
			var arr = returnData.workDay;
			
			if(Array.isArray(arr)){
				for(var i=0;i<arr.length;i++){
					 $("input[name=workDay][value='"+arr[i]+"']").attr("checked",true);
					}
			}else{
				 $("input[name=workDay][value='"+arr+"']").attr("checked",true);
			}
			
			
		}
		if(returnData.startWorkTime!=undefined){
			$("#startWorkTime").val(returnData.startWorkTime);
		}
		if(returnData.endWorkTime!=undefined){
			$("#endWorkTime").val(returnData.endWorkTime);
		}
		
	}
}

function initPrintMonitor(osAudit){
	var returnData = osAudit.printMonitorRules;
	if(returnData!=undefined){
		if(returnData.print_monitor_status=='1'){
			 $("#print_monitor_status").attr("checked",true);
		}
	}
}

function initOsInfo(osAudit){
	var returnData = osAudit.osInfoRules;
	if(returnData!=undefined){
		if(returnData.os_info_status=='1'){
			 $("#os_info_status").attr("checked",true);
		}
	}
}

function initSystemLog(osAudit){
	var returnData = osAudit.systemLogRules;
	if(returnData!=undefined){
		if(returnData.system_log_status=='1'){
			 $("#system_log_status").attr("checked",true);
		}
		if(returnData.others_system_log_status=='1'){
			$("#others_system_log_status").attr("checked",true);
		}
	}
}

function initAccountMonitor(osAudit){
	var returnData = osAudit.accountMonitorRules;
	if(returnData!=undefined){
		if(returnData.account_monitor_status=='1'){
			 $("#account_monitor_status").attr("checked",true);
		}
	}
}

function initShareMonitor(osAudit){
	var returnData = osAudit.shareMonitorRules;
	if(returnData!=undefined){
		if(returnData.share_monitor_status=='1'){
			 $("#share_monitor_status").attr("checked",true);
		}
		if(returnData.share_monitor_type!=undefined){
			loadType("share_type","share_monitor_type",returnData.share_monitor_type);
		}
	}
}

function initExceptionMonitor(osAudit){
	var returnData = osAudit.exceptionMonitorRules;
	if(returnData!=undefined){
		
		if(returnData.exception_monitor_status=='1'){
			 $("#exception_monitor_status").attr("checked",true);
		}
		if(returnData.exception_monitor_memory!=undefined){
			$("#exception_monitor_memory").val(returnData.exception_monitor_memory);
		}
		if(returnData.exception_monitor_cpu!=undefined){
			$("#exception_monitor_cpu").val(returnData.exception_monitor_cpu);
		}
		if(returnData.exception_monitor_runtime!=undefined){
			$("#exception_monitor_runtime").val(returnData.exception_monitor_runtime);
		}
		
	}
}

function initOsConfig(osAudit){

	var returnData = osAudit.osConfigRules;
	if(returnData!=undefined){
		
		if(returnData.os_config_black_status=='1'){
			 $("#os_config_black_status").attr("checked",true);
		}
		if(returnData.os_config_black_rules!=undefined){
			$("#os_config_black_rules").val(returnData.os_config_black_rules);
		}
		if(returnData.os_config_black_alarm_level!=undefined){
			loadType("alarm_level","os_config_black_alarm_level",returnData.os_config_black_alarm_level);
		}
		
		
		if(returnData.os_config_white_status=='1'){
			$("#os_config_white_status").attr("checked",true);
		}
		if(returnData.os_config_white_rules!=undefined){
			$("#os_config_white_rules").val(returnData.os_config_white_rules);
		}
		if(returnData.os_config_white_alarm_level!=undefined){
			loadType("alarm_level","os_config_white_alarm_level",returnData.os_config_white_alarm_level);
		}
	}
	
}

function initMoveMedia(osAudit){
	var returnData = osAudit.moveMediaRules;
	if(returnData!=undefined){
		if(returnData.move_media_white_status=='1'){
			 $("#move_media_white_status").attr("checked",true);
		}
		
		var hiddenValue = returnData.move_media_names;
		if(hiddenValue!=undefined&&hiddenValue!=""){
			
			$("#move_media_names").val(hiddenValue);
			var hiddenValueArray=hiddenValue.split(";");
		    for(var i in hiddenValueArray){
		    	var html = '<option value="' +hiddenValueArray[i] + '">' + hiddenValueArray[i] + '</option>';
				$("#move_media_show").append(html);
		    }
			
		}
	}
	
}
function addMoveMedia() {
    
	var tempInput = $("#move_media_input").val();
	// 校验修改密码表单
	var flag = $("#move_media_input").valid();
    if(!flag){
        // 没有通过验证
        return;
    }
    var html = '<option value="' +tempInput + '">' + tempInput + '</option>';
    $("#move_media_show").append(html);
    $("#move_media_show option:first").attr("selected", true);
    dealOptionsValue("move_media_show","move_media_names");
    
}

function initPortMonitor(osAudit){
	var returnData = osAudit.portMonitorRules;
	if(returnData!=undefined){
		if(returnData.port_monitor_status=='1'){
			 $("#port_monitor_status").attr("checked",true);
		}
		
		var hiddenValue = returnData.port_monitor_ports;
		if(hiddenValue!=undefined&&hiddenValue!=""){
			
			$("#port_monitor_ports").val(hiddenValue);
			var hiddenValueArray=hiddenValue.split(";");
		    for(var i in hiddenValueArray){
		    	var html = '<option value="' +hiddenValueArray[i] + '">' + hiddenValueArray[i] + '</option>';
				$("#port_monitor_show").append(html);
		    }
			
		}
	}
}
function addPortMonitor() {
    
	var tempInput = $("#port_monitor_input").val();
	// 校验修改密码表单
	var flag = $("#port_monitor_input").valid();
    if(!flag){
        // 没有通过验证
        return;
    }
    var html = '<option value="' +tempInput + '">' + tempInput + '</option>';
    $("#port_monitor_show").append(html);
    $("#port_monitor_show option:first").attr("selected", true);
    dealOptionsValue("port_monitor_show","port_monitor_ports");
    
}

function initConnectionMonitor(osAudit){

	var returnData = osAudit.connectionMonitorRules;
	if(returnData!=undefined){
		
		if(returnData.connection_black_status=='1'){
			 $("#connection_black_status").attr("checked",true);
		}
		if(returnData.connection_black_rules!=undefined){
			$("#connection_black_rules").val(returnData.connection_black_rules);
		}
		if(returnData.connection_black_alarm_level!=undefined){
			loadType("alarm_level","connection_black_alarm_level",returnData.connection_black_alarm_level);
		}
		
		
		if(returnData.connection_white_status=='1'){
			$("#connection_white_status").attr("checked",true);
		}
		if(returnData.connection_white_rules!=undefined){
			$("#connection_white_rules").val(returnData.connection_white_rules);
		}
		if(returnData.connection_white_alarm_level!=undefined){
			loadType("alarm_level","connection_white_alarm_level",returnData.connection_white_alarm_level);
		}
		
		if(returnData.network_connection_white_status=='1'){
			$("#network_connection_white_status").attr("checked",true);
		}
		var addressValue = returnData.connection_white_addresses;
		if(addressValue!=undefined&&addressValue!=""){
			
			$("#connection_white_addresses").val(addressValue);
			var addressValueArray=addressValue.split(";");
		    for(var i in addressValueArray){
		    	var html = '<option value="' +addressValueArray[i] + '">' + addressValueArray[i] + '</option>';
				$("#connection_addresses_show").append(html);
		    }
			
		}
		if(returnData.connection_alarm_level!=undefined){
			loadType("alarm_level","connection_alarm_level",returnData.connection_alarm_level);
		}
	}
}
function addConnectionWhiteAddress() {
    
	var ip = $("#connection_white_ip").val();
	var ports = $("#connection_white_ports").val();
	// debugger;
	
	// 校验修改密码表单
	var flag = $("#connection_white_ip,#connection_white_ports").valid();
	// var flag = $("#signupForm").valid();
	// var flag =
	// $("#signupForm").validate().element($("#server_connection_black_ip"));
    if(!flag){
        // 没有通过验证
        return;
    }
	var connectionWhiteAddress = ip+":"+ports;
	
    var html = '<option value="' +connectionWhiteAddress + '">' + connectionWhiteAddress + '</option>';
    $("#connection_addresses_show").append(html);
    $("#connection_addresses_show option:first").attr("selected", true);
    dealOptionsValue("connection_addresses_show","connection_white_addresses");
    
}

function initNetworkFlow(osAudit){
	var returnData = osAudit.networkFlowRules;
	if(returnData!=undefined){
		if(returnData.network_flow_status=='1'){
			 $("#network_flow_status").attr("checked",true);
		}
		if(returnData.network_flow_size!=undefined){
			$("#network_flow_size").val(returnData.network_flow_size);
		}
		if(returnData.network_flow_alarm_level!=undefined){
			loadType("alarm_level","network_flow_alarm_level",returnData.network_flow_alarm_level);
		}
	}
}

function initDiskSpace(osAudit){
	var returnData = osAudit.diskSpaceRules;
	if(returnData!=undefined){
		if(returnData.disk_space_status=='1'){
			 $("#disk_space_status").attr("checked",true);
		}
		if(returnData.disk_space_utilizationrate!=undefined){
			$("#disk_space_utilizationrate").val(returnData.disk_space_utilizationrate);
		}
		if(returnData.disk_space_alarm_level!=undefined){
			loadType("alarm_level","disk_space_alarm_level",returnData.disk_space_alarm_level);
		}
	}
}

function initFileControl(osAudit){
	var returnData = osAudit.fileControlRules;
	if(returnData!=undefined){
		
		if(returnData.file_control_status=='1'){
			 $("#file_control_status").attr("checked",true);
		}
		
		var hiddenValue = returnData.file_control_values;
		if(hiddenValue!=undefined&&hiddenValue!=""){
			
			$("#file_control_values").val(hiddenValue);
			var hiddenValueArray=hiddenValue.split(";");
		    for(var i in hiddenValueArray){
		    	var html = '<option value="' +hiddenValueArray[i] + '">' + hiddenValueArray[i] + '</option>';
				$("#file_control_show").append(html);
		    }
			
		}
	}
}

function addFileControlRules() {
    
	var input_1 = $("#file_control_input").val();
	
	var input_2=$('input:radio[name=file_control_file_type]:checked').val();
	// debugger;
	
	// 校验修改密码表单
	var flag = $("#file_control_input,#file_control_file_type").valid();
	// var flag = $("#signupForm").valid();
	// var flag =
	// $("#signupForm").validate().element($("#server_connection_black_ip"));
    if(!flag){
        // 没有通过验证
        return;
    }
	var mergeInput = input_1+":"+input_2;
	
    var html = '<option value="' +mergeInput + '">' + mergeInput + '</option>';
    $("#file_control_show").append(html);
    $("#file_control_show option:first").attr("selected", true);
    dealOptionsValue("file_control_show","file_control_values");
    
}

function initIrregularConnection(irregularConnection){
	var returnData = irregularConnection.monitorRules;
	if(returnData!=undefined){
		
		if(returnData.monitor_status=='1'){
			 $("#monitor_status").attr("checked",true);
		}
		if(returnData.alarm_id!=undefined){
			 //loadTypeTab("chosen-select2","alarm_level","alarm_id");
			 loadTypeTab("chosen-select2","alarm_level","alarm_id",returnData.alarm_id);
		}
		if(returnData.probe_address!=undefined){
			$("#probe_address").val(returnData.probe_address);
		}
		if(returnData.alarm_server!=undefined){
			$("#alarm_server").val(returnData.alarm_server);
		}
	}
}
function initDeviceControl(deviceControl){
	var returnData = deviceControl.deviceControlRules;
	if(returnData!=undefined){
		
		if(returnData.device_control_status=='1'){
			 $("#device_control_status").attr("checked",true);
		}
		
		if(returnData.cd_rom!=undefined){
			loadTypeTab("chosen-select3","cd_access_type","cd_rom",returnData.cd_rom);
			//loadType("cd_access_type","cd_rom",returnData.cd_rom);
		}
		if(returnData.phone=='1'){
			 $("#phone").attr("checked",true);
		}
		if(returnData.bluetooth=='1'){
			$("#bluetooth").attr("checked",true);
		}
		if(returnData.device_1394=='1'){
			$("#device_1394").attr("checked",true);
		}
		if(returnData.com_interface=='1'){
			$("#com_interface").attr("checked",true);
		}
		if(returnData.infrared_device=='1'){
			$("#infrared_device").attr("checked",true);
		}
		if(returnData.floppy_drive=='1'){
			$("#floppy_drive").attr("checked",true);
		}
		if(returnData.modem=='1'){
			$("#modem").attr("checked",true);
		}
		if(returnData.sd_adapter=='1'){
			$("#sd_adapter").attr("checked",true);
		}
		
		if(returnData.image_device=='1'){
			$("#image_device").attr("checked",true);
		}
		if(returnData.print_device=='1'){
			$("#print_device").attr("checked",true);
		}
		if(returnData.pcmcia_device=='1'){
			$("#pcmcia_device").attr("checked",true);
		}
		if(returnData.card_reader=='1'){
			$("#card_reader").attr("checked",true);
		}
		if(returnData.udisk_device=='1'){
			$("#udisk_device").attr("checked",true);
		}
		if(returnData.mlan=='1'){
			$("#mlan").attr("checked",true);
		}
		if(returnData.diy_device=='1'){
			$("#diy_device").attr("checked",true);
		}
		if(returnData.wireless_device=='1'){
			$("#wireless_device").attr("checked",true);
		}
		
		var hiddenValue = returnData.device_white_values;
		if(hiddenValue!=undefined&&hiddenValue!=""){
			
			$("#device_white_values").val(hiddenValue);
			var hiddenValueArray=hiddenValue.split(";");
		    for(var i in hiddenValueArray){
		    	var html = '<option value="' +hiddenValueArray[i] + '">' + hiddenValueArray[i] + '</option>';
				$("#device_white_show").append(html);
		    }
			
		}
	}
	
}
function addDeviceId(){
	
var input_1 = $("#device_id_input").val();
	
	// 校验修改密码表单
	var flag = $("#device_id_input").valid();
    if(!flag){
        // 没有通过验证
        return;
    }
	var mergeInput = input_1;
	
    var html = '<option value="' +mergeInput + '">' + mergeInput + '</option>';
    $("#device_white_show").append(html);
    $("#device_white_show option:first").attr("selected", true);
    dealOptionsValue("device_white_show","device_white_values");
}


function initDiskRecord(diskRecord){
	var returnData = diskRecord.diskRecordRules;
	if(returnData!=undefined){
		
		if(returnData.disk_record_status=='1'){
			 $("#disk_record_status").attr("checked",true);
		}
		if(returnData.record_mode!=undefined){
			loadTypeTab("chosen-select4","record_mode","record_mode",returnData.record_mode);
			//loadType("record_mode","record_mode",returnData.record_mode);
		}
	}
}

function initSoftDistribute(diskRecord){
	var returnData = diskRecord.softDistributeRules;
	if(returnData!=undefined){
		
		if(returnData.default_download_url!=undefined){
			$("#default_download_url").val(returnData.default_download_url);
		}
		if(returnData.default_file_path!=undefined){
			$("#default_file_path").val(returnData.default_file_path);
		}
		if(returnData.retry_times!=undefined){
			$("#retry_times").val(returnData.retry_times);
		}
		if(returnData.retry_interval!=undefined){
			$("#retry_interval").val(returnData.retry_interval);
		}
		
		var hiddenValue = returnData.upload_files;
		if(hiddenValue!=undefined&&hiddenValue!=""){
			
			$("#upload_files").val(hiddenValue);
			var hiddenValueArray=hiddenValue.split(";");
		    for(var i in hiddenValueArray){
		    	var html = '<option value="' +hiddenValueArray[i] + '">' + hiddenValueArray[i] + '</option>';
				$("#upload_file_show").append(html);
		    }
			
		}
	}
}

function addSoftDistributeFile(id,fileName){
	
	var defaultFiles = $("#upload_files").val();
	if(defaultFiles!=null &&defaultFiles!=undefined){
		defaultFiles+=";"+id+"-"+fileName;
	}
	var mergeInput = id+"-"+fileName;
    var html = '<option value="' +mergeInput + '">' + mergeInput + '</option>';
    $("#upload_file_show").append(html);
    $("#upload_file_show option:first").attr("selected", true);
    dealOptionsValue("upload_file_show","upload_files");
}

function addStrategyTempletTab() {
	
	
	var localFileForm = $("#localFileForm").serializeJson();
	var diskFileForm = $("#diskFileForm").serializeJson();
	var udiskFileForm = $("#udiskFileForm").serializeJson();
	var osOnoffForm = $("#osOnoffForm").serializeJson();
	
	var processMonitorForm = $("#processMonitorForm").serializeJson();// 进程监控
	
	var printMonitorForm = $("#printMonitorForm").serializeJson();// 打印
	var osInfoForm = $("#osInfoForm").serializeJson();// 主机信息
	var systemLogForm = $("#systemLogForm").serializeJson();// 系统日志
	var accountMonitorForm = $("#accountMonitorForm").serializeJson();// 账户监控
	var shareMonitorForm = $("#shareMonitorForm").serializeJson();// 共享监控
	var exceptionMonitorForm = $("#exceptionMonitorForm").serializeJson();// 异常监控
	var osConfigForm = $("#osConfigForm").serializeJson();// 主机配置
	var moveMediaForm = $("#moveMediaForm").serializeJson();// 移动介质
	var portMonitorForm = $("#portMonitorForm").serializeJson();// 端口监控
	
	var serverMonitorForm = $("#serverMonitorForm").serializeJson();// 服务监控
	var connectionMonitorForm = $("#connectionMonitorForm").serializeJson();// 连接监控
	var networkFlowForm = $("#networkFlowForm").serializeJson();// 网络流量
	var diskSpaceForm = $("#diskSpaceForm").serializeJson();// 磁盘空间
	var fileControlForm = $("#fileControlForm").serializeJson();// 磁盘空间
	
	var irregularConnectionForm = $("#irregularConnectionForm").serializeJson();// 违规外联
	var deviceControlForm = $("#deviceControlForm").serializeJson();// 设备控制
	var diskRecordForm = $("#diskRecordForm").serializeJson();// 光盘刻录
	var softDistributeForm = $("#softDistributeForm").serializeJson();// 光盘刻录
	
	var extendParamForm = $("#extendParamForm").serializeJson();// 扩展参数

	// var jsonString = '{"bar":"property","baz":3}';
	// var jsObject = JSON.parse(proRuestl_1); //转换为json对象
	var jsons = "{" 
			+ "\"localFileForm\":" + JSON.stringify(localFileForm)
			+ ",\"diskFileForm\":" + JSON.stringify(diskFileForm)
			+ ",\"udiskFileForm\":" + JSON.stringify(udiskFileForm)
			+ ",\"osOnoffForm\":" + JSON.stringify(osOnoffForm)
			
			+ ",\"processMonitorForm\":" + JSON.stringify(processMonitorForm)
			
			+ ",\"printMonitorForm\":" + JSON.stringify(printMonitorForm)
			+ ",\"osInfoForm\":" + JSON.stringify(osInfoForm)
			+ ",\"systemLogForm\":" + JSON.stringify(systemLogForm)
			+ ",\"accountMonitorForm\":" + JSON.stringify(accountMonitorForm)
			+ ",\"shareMonitorForm\":" + JSON.stringify(shareMonitorForm)
			+ ",\"exceptionMonitorForm\":" + JSON.stringify(exceptionMonitorForm)
			+ ",\"osConfigForm\":" + JSON.stringify(osConfigForm)
			+ ",\"moveMediaForm\":" + JSON.stringify(moveMediaForm)
			+ ",\"portMonitorForm\":" + JSON.stringify(portMonitorForm)
			
			+ ",\"serverMonitorForm\":" + JSON.stringify(serverMonitorForm) 
			+ ",\"connectionMonitorForm\":" + JSON.stringify(connectionMonitorForm) 
			+ ",\"networkFlowForm\":" + JSON.stringify(networkFlowForm) 
			+ ",\"diskSpaceForm\":" + JSON.stringify(diskSpaceForm) 
			+ ",\"fileControlForm\":" + JSON.stringify(fileControlForm) 
			
			+ ",\"irregularConnectionForm\":" + JSON.stringify(irregularConnectionForm) 
			+ ",\"deviceControlForm\":" + JSON.stringify(deviceControlForm) 
			+ ",\"diskRecordForm\":" + JSON.stringify(diskRecordForm) 
			+ ",\"softDistributeForm\":" + JSON.stringify(softDistributeForm) 
			
			+ ",\"extendParamForm\":" + JSON.stringify(extendParamForm) 
			+ "}"; // 转换为json类型的字符串

	$.ajax({
		cache : true,

		dataType : "json",
		contentType : "application/json; charset=utf-8",
		type : "POST",
		url : "/strategy/strategyTemplet/addStrategyTempletTab",
		data : jsons,// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			// debugger;
			if (data.success == true) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				
				var osAuditId = data.data.osAuditId;
				var irregularConnectionId = data.data.irregularConnectionId;
				var deviceControlId = data.data.deviceControlId;
				var diskRecordId = data.data.diskRecordId;
				var softDistributeId = data.data.softDistributeId;
				$("#osAuditId").val(osAuditId);
				$("#irregularConnectionId").val(irregularConnectionId);
				$("#deviceControlId").val(deviceControlId);
				$("#diskRecordId").val(diskRecordId);
				$("#softDistributeId").val(softDistributeId);
				// var index = parent.layer.getFrameIndex(window.name); //
				// 获取窗口索引
				// parent.layer.close(index);
			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}


function dealOptionsValue(sourceId,targetId){
	
    
    var all = "";
    $("#"+sourceId+" option").each(function() {
    	
    	if(all!=""){
    		all +=";";
    	}
        all += $(this).attr("value");
    });
    $("#"+targetId).val(all);
}





function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#server_connection_black_ip").validate({
		rules : {
			server_connection_black_ip : {
				required : true
			}
		},
		messages : {
			server_connection_black_ip : {
				required : icon + "IP/域名地址"
			}
		}
	})
}

function loadType(type,id,checkedValue){
	var html = "";
	$.ajax({
		url : '/common/dict/list/'+type,
		success : function(data) {
			
			// debugger;
			// 加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			
			if(id==undefined && checkedValue==undefined){
				$(".chosen-select").append(html);
			}else if(id!=undefined && checkedValue==undefined){
				
				$(".chosen-select[name="+id+"]").append(html);
			}
			$(".chosen-select").chosen({
				maxHeight : 200
			});
			if(id!=undefined && checkedValue!=undefined){
				$("#"+id).val(checkedValue);
			}
			$(".chosen-select").trigger("chosen:updated");
			// 点击事件
			$('.chosen-select').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				$('#exampleTable').bootstrapTable('refresh', opt);
			});
		}
	});
}


function loadTypeTab(className,type,id,checkedValue){
	var html = "";
	$.ajax({
		url : '/common/dict/list/'+type,
		success : function(data) {
			
			 debugger;
			//alert(className+">>>"+type+">>>"+id+">>>"+checkedValue);
			// 加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name + '</option>'
			}
			
			if(id!=undefined && checkedValue==undefined){
				//alert($("."+className+"[name="+id+"]").text());
				//alert(html);
				$("."+className+"[name="+id+"]").append(html);
			}
			$("."+className).chosen({
				maxHeight : 200
			});
			if(id!=undefined && checkedValue!=undefined){
				$("#"+id).val(checkedValue);
			}
			$("."+className).trigger("chosen:updated");
			// 点击事件
			$('.'+className).on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				$('#exampleTable').bootstrapTable('refresh', opt);
			});
		}
	});
}


