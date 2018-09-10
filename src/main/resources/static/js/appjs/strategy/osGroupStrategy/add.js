$().ready(function() {
	validateRule();
	$("#os_group_show").dblclick(function() {
		$("#os_group_show option:selected").remove();
		document.getElementById('os_group_show')[0].selected = true;
		dealOptions("os_group_show","osGroupIds", "osGroupNames");

	});
	$("#templet_name_show").dblclick(function() {
		$("#templet_name_show option:selected").remove();
		document.getElementById('templet_name_show')[0].selected = true;
		dealOptions("templet_name_show","templetId", "templetName");
	});
	
	initData();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/strategy/osGroupStrategy/save",
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
			os_group_show : {
				required : true
			},
			templet_name_show : {
				required : true
			}
		},
		messages : {
			os_group_show : {
				required : icon + "请选择主机组"
			},
			templet_name_show : {
				required : icon + "请选择策略模板"
			}
		}
	})
}

function initData(){
	
	var hiddenValue = bean.templetName;
	if(hiddenValue!=undefined&&hiddenValue!=""){
		$("#templetName").val(hiddenValue);
		var hiddenValueArray=hiddenValue.split(";");
	    for(var i in hiddenValueArray){
	    	var html = '<option value="' +hiddenValueArray[i] + '">' + hiddenValueArray[i] + '</option>';
			$("#templet_name_show").append(html);
			$("#templet_name_show option:first").attr("selected", true);
	    }
		
	}
	var hiddenValue2 = bean.osGroupNames;
	if(hiddenValue2!=undefined&&hiddenValue2!=""){
		$("#osGroupNames").val(hiddenValue2);
		var hiddenValueArray=hiddenValue2.split(";");
		for(var i in hiddenValueArray){
			var html = '<option value="' +hiddenValueArray[i] + '">' + hiddenValueArray[i] + '</option>';
			$("#os_group_show").append(html);
			$("#os_group_show option:first").attr("selected", true);
		}
		
	}
	
}

function selectStrategyTemplet() {

	// 获取当前窗口名称
	layer.open({
		type : 2,
		title : '选择策略模板',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/strategy/strategyTemplet/select' // iframe的url
	});

}

function loadTemplet(id, name) {

	// console.log(id,name);
	//$("#templet_name_show").val(name);
	//$("#templetId").val(id);

	var html = '<option value="' + id + '">' + name + '</option>';
	$("#templet_name_show").html(html);
	$("#templet_name_show option:first").attr("selected", true);
	dealOptions("templet_name_show","templetId", "templetName");

}

function removeTemplet() {
	$("#templet_name_show option:selected").remove();
	$("#templet_name_show option:first").attr("selected", true);
	dealOptions("templet_name_show", "templetId", "templetName");
}

function selectOsGroup() {

	// 获取当前窗口名称
	layer.open({
		type : 2,
		title : '选择主机组',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/os/osGroup/select' // iframe的url
	});

}
function loadOsGroup(id, name) {
	// alert(deptId+">>>"+deptName);

	//$("#dept_name_show").val(deptName);
	//$("#deptIds").val(deptId);

	var html = '<option value="' + id + '">' + name + '</option>';
	$("#os_group_show").html(html);
	$("#os_group_show option:first").attr("selected", true);
	dealOptions("os_group_show","osGroupIds", "osGroupNames");

}

function removeOsGroup() {
	$("#os_group_show option:selected").remove();
	$("#os_group_show option:first").attr("selected", true);
	dealOptions("os_group_show", "osGroupIds", "osGroupNames");
}

function dealOptions(sourceId, targetId,targetName) {
	
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