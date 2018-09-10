$().ready(function() {
	validateRule();
	$("#os_info_show").dblclick(function() {
		$("#os_info_show option:selected").remove();
		document.getElementById('os_info_show')[0].selected = true;
		dealOptions("os_info_show","osIds", "osNames");

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
		url : "/strategy/osStrategy/save",
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
			os_info_show : {
				required : true
			},
			templet_name_show : {
				required : true
			}
		},
		messages : {
			os_info_show : {
				required : icon + "请选择主机"
			},
			templet_name_show : {
				required : icon + "请选择策略模板"
			}
		}
	})
}

function initData(){
	
	if(bean!=null&&bean!=undefined){
		
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
		var hiddenValue2 = bean.osNames;
		if(hiddenValue2!=undefined&&hiddenValue2!=""){
			$("#osNames").val(hiddenValue2);
			var hiddenValueArray=hiddenValue2.split(";");
			for(var i in hiddenValueArray){
				var html = '<option value="' +hiddenValueArray[i] + '">' + hiddenValueArray[i] + '</option>';
				$("#os_info_show").append(html);
				$("#os_info_show option:first").attr("selected", true);
			}
			
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
		var html = '<option value="' + id[x] + '">' + name[x] + '</option>';
		$("#os_info_show").append(html);
	}
	$("#os_info_show option:first").attr("selected", true);
	dealOptions("os_info_show", "osIds", "osNames");
}

function removeOsInfo() {
	$("#os_info_show option:selected").remove();
	$("#os_info_show option:first").attr("selected", true);
	dealOptions("os_info_show", "osIds", "osNames");
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