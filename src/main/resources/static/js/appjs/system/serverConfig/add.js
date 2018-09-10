$().ready(function() {
	loadTypeTab("chosen-select", "save_cycle", "saveCycle");
	validateRule();
	initData();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function initData() {

	// debugger;
	if (bean != null && bean != undefined) {
		loadTypeTab("chosen-select", "save_cycle", "saveCycle", bean.saveCycle);

		if (bean.stopReportTag == '1') {
			$("#stopReportTag").attr("checked", true);
		}

		debugger;
		
		var alarmModeNormal = bean.alarmModeNormal;
		var arrAlarmModeNormal = alarmModeNormal.split(",");
		if (Array.isArray(arrAlarmModeNormal)) {
			for (var i = 0; i < arrAlarmModeNormal.length; i++) {
				$("input[name=alarmModeNormal][value='" + arrAlarmModeNormal[i] + "']").attr(
						"checked", true);
			}
		} else {
			$("input[name=alarmModeNormal][value='" + arrAlarmModeNormal + "']").attr(
					"checked", true);
		}

		var alarmModeException = bean.alarmModeException;
		var arrAlarmModeException = alarmModeException.split(",");
		if (Array.isArray(arrAlarmModeException)) {
			for (var i = 0; i < arrAlarmModeException.length; i++) {
				$("input[name=alarmModeException][value='" + arrAlarmModeException[i] + "']")
						.attr("checked", true);
			}
		} else {
			$("input[name=alarmModeException][value='" + arrAlarmModeException + "']").attr(
					"checked", true);
		}

		var alarmModeError = bean.alarmModeError;
		var arrAlarmModeError = alarmModeError.split(",");
		if (Array.isArray(arrAlarmModeError)) {
			for (var i = 0; i < arrAlarmModeError.length; i++) {
				$("input[name=alarmModeError][value='" + arrAlarmModeError[i] + "']").attr(
						"checked", true);
			}
		} else {
			$("input[name=alarmModeError][value='" + arrAlarmModeError + "']").attr(
					"checked", true);
		}
		
		if (bean.serverClockSyncTag == '1') {
			$("#serverClockSyncTag").attr("checked", true);
		}
		
		if (bean.bubbleTag == '1') {
			$("#bubbleTag").attr("checked", true);
		}

	}

}

function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/serverConfig/save",
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

function loadTypeTab(className, dictType, id, checkedValue) {

	debugger;

	var html = "";
	$.ajax({
		url : '/common/dict/list/' + dictType,
		success : function(data) {

			debugger;
			// alert(className+">>>"+type+">>>"+id+">>>"+checkedValue);
			// 加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name
						+ '</option>'
			}

			if (id != undefined && checkedValue == undefined) {
				// alert($("."+className+"[name="+id+"]").text());
				// alert(html);
				$("." + className + "[name=" + id + "]").append(html);
			}
			$("." + className).chosen({
				maxHeight : 200
			});
			if (id != undefined && checkedValue != undefined) {
				$("#" + id).val(checkedValue);
			}
			$("." + className).trigger("chosen:updated");
			// 点击事件
			$('.' + className).on('change', function(e, params) {
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