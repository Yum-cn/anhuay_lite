$().ready(function() {
	validateRule();

	$("#device_white_show").dblclick(function() {
		$("option:selected", this).remove();
		document.getElementById('device_white_show')[0].selected = true;
		dealOptionsValue("device_white_show", "device_white_values");
	});
	$("#removeDeviceId").click(function() {
		$("#device_white_show option:selected").remove();
		$("#device_white_show option:first").attr("selected", true);
		dealOptionsValue("device_white_show", "device_white_values");
	});

	initData();
});

$.validator.setDefaults({
	submitHandler : function() {
		saveLonginConfig();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/userExtend/saveLonginConfig",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}

function initData() {
	
	debugger;
	
	var returnData = JSON.parse(bean.loginRules);
	if (bean.id != undefined && bean.id != "") {
		$("#id").val(bean.id);
	}
	if (returnData != undefined) {

		if (returnData.white_status == '1') {
			$("#white_status").attr("checked", true);
		}

		var hiddenValue = returnData.device_white_values;
		if (hiddenValue != undefined && hiddenValue != "") {

			$("#device_white_values").val(hiddenValue);
			var hiddenValueArray = hiddenValue.split(";");
			for ( var i in hiddenValueArray) {
				var html = '<option value="' + hiddenValueArray[i] + '">'
						+ hiddenValueArray[i] + '</option>';
				$("#device_white_show").append(html);
			}
			document.getElementById('device_white_show')[0].selected = true;
		}

		if (returnData.login_time_status == '1') {
			$("#login_time_status").attr("checked", true);
		}
		if (returnData.login_day != undefined) {
			var arr = returnData.login_day;

			if (Array.isArray(arr)) {
				for (var i = 0; i < arr.length; i++) {
					$("input[name=login_day][value='" + arr[i] + "']").attr(
							"checked", true);
				}
			} else {
				$("input[name=login_day][value='" + arr + "']").attr("checked",
						true);
			}

		}
		if (returnData.login_start_time != undefined) {
			$("#login_start_time").val(returnData.login_start_time);
		}
		if (returnData.login_end_time != undefined) {
			$("#login_end_time").val(returnData.login_end_time);
		}

	}

}
function addDeviceId() {

	var input_1 = $("#device_id_input").val();

	// 校验修改密码表单
	var flag = $("#device_id_input").valid();
	if (!flag) {
		// 没有通过验证
		return;
	}
	var mergeInput = input_1;

	var html = '<option value="' + mergeInput + '">' + mergeInput + '</option>';
	$("#device_white_show").append(html);
	$("#device_white_show option:first").attr("selected", true);
	dealOptionsValue("device_white_show", "device_white_values");
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

function saveLonginConfig() {

	var form = $("#signupForm").serializeJson();

	// var jsonString = '{"bar":"property","baz":3}';
	// var jsObject = JSON.parse(proRuestl_1); //转换为json对象
	/*
	 * var jsons = "{" + "\"localFileForm\":" + JSON.stringify(localFileForm) +
	 * "}"; // 转换为json类型的字符串
	 */
	$.ajax({
		cache : true,

		dataType : "json",
		contentType : "application/json; charset=utf-8",
		type : "POST",
		url : "/system/userExtend/saveLonginConfig",
		data : JSON.stringify(form),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			debugger;
			if (data.success == true) {
				parent.layer.msg("操作成功");
				parent.reLoad();

				// var id = data.data.id;
				// $("#id").val(id);
				var index = parent.layer.getFrameIndex(window.name); //
				// 获取窗口索引
				parent.layer.close(index);
			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}