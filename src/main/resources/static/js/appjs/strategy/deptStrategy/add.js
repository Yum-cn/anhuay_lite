$().ready(function() {
	validateRule();

	$("#dept_name_show").dblclick(function() {
		$("#dept_name_show option:selected").remove();
		document.getElementById('dept_name_show')[0].selected = true;
		dealOptions("dept_name_show","deptIds", "deptNames");

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

function initData(){
	
	var hiddenValue = deptStrategy.templetName;
	if(hiddenValue!=undefined&&hiddenValue!=""){
		$("#templetName").val(hiddenValue);
		var hiddenValueArray=hiddenValue.split(";");
	    for(var i in hiddenValueArray){
	    	var html = '<option value="' +hiddenValueArray[i] + '">' + hiddenValueArray[i] + '</option>';
			$("#templet_name_show").append(html);
			$("#templet_name_show option:first").attr("selected", true);
	    }
		
	}
	var hiddenValue2 = deptStrategy.deptNames;
	if(hiddenValue2!=undefined&&hiddenValue2!=""){
		$("#deptNames").val(hiddenValue2);
		var hiddenValueArray=hiddenValue2.split(";");
		for(var i in hiddenValueArray){
			var html = '<option value="' +hiddenValueArray[i] + '">' + hiddenValueArray[i] + '</option>';
			$("#dept_name_show").append(html);
			$("#dept_name_show option:first").attr("selected", true);
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

function selectDept() {

	// 获取当前窗口名称
	layer.open({
		type : 2,
		title : '选择部门',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/system/sysDept/treeView' // iframe的url
	});

}
function loadDept(deptId, deptName) {
	// alert(deptId+">>>"+deptName);

	//$("#dept_name_show").val(deptName);
	//$("#deptIds").val(deptId);

	var html = '<option value="' + deptId + '">' + deptName + '</option>';
	$("#dept_name_show").html(html);
	$("#dept_name_show option:first").attr("selected", true);
	dealOptions("dept_name_show","deptIds", "deptNames");

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

function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/strategy/deptStrategy/save",
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
			dept_name_show : {
				required : true
			},
			templet_name_show : {
				required : true
			}
		},
		messages : {
			dept_name_show : {
				required : icon + "请选择部门"
			},
			templet_name_show : {
				required : icon + "请选择策略模板"
			}
		}
	})
}