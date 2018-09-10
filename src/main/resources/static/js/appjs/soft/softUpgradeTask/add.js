$().ready(function() {
	validateRule();
	initData();
	
	$("#templet_name_show").dblclick(function() {
		$("#templet_name_show option:selected").remove();
		document.getElementById('templet_name_show')[0].selected = true;
		dealOptions("templet_name_show", "softFileId",
		"softFileName");
	});
	
	$("#os_info_show").dblclick(function() {
		$("#os_info_show option:selected").remove();
		document.getElementById('os_info_show')[0].selected = true;
		dealOptions("os_info_show","osIds", "osIps");
	});
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function initData() {

	if (bean != null && bean != undefined) {

		var hiddenValue = bean.softFileName;
		if (hiddenValue != undefined && hiddenValue != "") {
			//$("#personLiableName").val(hiddenValue);
			var hiddenValueArray = hiddenValue.split(";");
			for ( var i in hiddenValueArray) {
				var html = '<option value="' + hiddenValueArray[i] + '">'
						+ hiddenValueArray[i] + '</option>';
				$("#templet_name_show").append(html);
				$("#templet_name_show option:first").attr("selected", true);
			}

		}
		var hiddenValue2 = bean.osIps;
		if (hiddenValue2 != undefined && hiddenValue2 != "") {
			//$("#deptName").val(hiddenValue2);
			var hiddenValueArray = hiddenValue2.split(";");
			for ( var i in hiddenValueArray) {
				var html = '<option value="' + hiddenValueArray[i] + '">'
						+ hiddenValueArray[i] + '</option>';
				$("#os_info_show").append(html);
				$("#os_info_show option:first").attr("selected", true);
			}

		}
	}
}

function save() {
	
	/*var flag = $("#signupForm").valid();
    if(!flag){
        //没有通过验证
        return;
    }*/
	
	$.ajax({
		cache : true,
		type : "POST",
		url : "/soft/softUpgradeTask/save",
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
			templet_name_show : {
				required : true
			},
			upgradeName : {
				required : true
			},
			upgradeTime : {
				required : true
			},
			upgradeVersion : {
				required : true
			},
			os_info_show : {
				required : true
			}
		},
		messages : {
			templet_name_show : {
				required : icon + "请选择要升级的文件包"
			},
			upgradeName : {
				required : icon + "请输入升级任务名称"
			},
			upgradeTime : {
				required : icon + "请输入升级时间"
			},
			upgradeVersion : {
				required : icon + "请输入版本号"
			},
			os_info_show : {
				required : icon + "请选择要升级的主机"
			}
		}
	})
}

function selectTemplet() {
	// 获取当前窗口名称
	layer.open({
		type : 2,
		title : '选择程序升级文件',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/soft/softClassification/select' // iframe的url
	});

}

function loadTemplet(id, name,url) {
	var html = '<option value="' + id + '">' + name + '</option>';
	$("#templet_name_show").html(html);
	$("#templet_name_show option:first").attr("selected", true);
	dealOptions("templet_name_show", "softFileId","softFileName");
	$("#softFileDownloadUrl").val(url);
}

function removeTemplet() {
	$("#templet_name_show option:selected").remove();
	$("#templet_name_show option:first").attr("selected", true);
	dealOptions("templet_name_show", "softFileId",
			"softFileName");
	$("#softFileDownloadUrl").val("");
}

function selectOsInfo() {
	// 获取当前窗口名称
	layer.open({
		type : 2,
		title : '选择主机',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/os/osInfo/select' // iframe的url
	});

}

function loadOsInfo(id, name) {
	console.log(id);
	console.log(name);
	$("#os_info_show").html("");
	for (x in id)
	{
	//console.log(id[x]+">>>"+name[x]);
		var html = '<option value="' + id[x] + '">' + name[x] + '</option>';
		$("#os_info_show").append(html);
	}
	$("#os_info_show option:first").attr("selected", true);
	dealOptions("os_info_show", "osIds", "osIps");
}

function removeOsInfo() {
	$("#os_info_show option:selected").remove();
	$("#os_info_show option:first").attr("selected", true);
	dealOptions("os_info_show", "osIds", "osIps");
}

function dealOptions(sourceId, targetId, targetName) {

	var allIds = "";
	var allValues = "";
	$("#" + sourceId + " option").each(function() {

		if (allIds != "") {
			allIds += ";";
			allValues += ";";
		}
		allIds += $(this).attr("value");
		allValues += $(this).text();
	});
	$("#" + targetId).val(allIds);
	$("#" + targetName).val(allValues);
}