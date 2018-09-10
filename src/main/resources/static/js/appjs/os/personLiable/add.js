$().ready(function() {
	validateRule();

	$("#removeDept").dblclick(function() {
		$("#dept_name_show option:selected").remove();
		document.getElementById('dept_name_show')[0].selected = true;
		dealOptions("dept_name_show","deptId", "deptName");

	});
	
	initData();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function initData(){

	var hiddenValue = personLiable.deptName;
	if(hiddenValue!=undefined&&hiddenValue!=""){
		$("#deptName").val(hiddenValue);
		var hiddenValueArray=hiddenValue.split(";");
		for(var i in hiddenValueArray){
			var html = '<option value="' +hiddenValueArray[i] + '">' + hiddenValueArray[i] + '</option>';
			$("#dept_name_show").append(html);
			$("#dept_name_show option:first").attr("selected", true);
		}
		
	}
	
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
	dealOptions("dept_name_show","deptId", "deptName");

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
		url : "/os/personLiable/save",
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
			personLiableName : {
				required : true
			}
		},
		messages : {
			dept_name_show : {
				required : icon + "请选择部门"
			},
			personLiableName : {
				required : icon + "责任人名称"
			}
		}
	})
}
