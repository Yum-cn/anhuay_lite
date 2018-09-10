$().ready(function() {
	validateRule();
	initData();
	
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
		var hiddenValue2 = bean.osIps;
		if (hiddenValue2 != undefined && hiddenValue2 != "") {
			//$("#deptName").val(hiddenValue2);
			var hiddenValueArray = hiddenValue2.split(";");
			for ( var i in hiddenValueArray) {
				var html = '<option value="' + hiddenValueArray[i] + '">'
						+ hiddenValueArray[i] + '</option>';
				$("#os_info_show").append(html);
				
			}
			$("#os_info_show option:first").attr("selected", true);
		}
	}
}
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/os/osGroup/save",
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
			osGroupName : {
				required : true
			},
			os_info_show : {
				required : true
			}
		},
		messages : {
			osGroupName : {
				required : icon + "请输入主机组名称"
			},
			os_info_show : {
				required : icon + "请选择主机"
			}
		}
	})
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
	var count = 0;
	$("#" + sourceId + " option").each(function() {
		count++;
		if (allIds != "") {
			allIds += ";";
			allValues += ";";
		}
		allIds += $(this).attr("value");
		allValues += $(this).text();
	});
	$("#" + targetId).val(allIds);
	$("#" + targetName).val(allValues);
	$("#osCount").val(count);
}