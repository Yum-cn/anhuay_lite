$().ready(function() {

	$("#templet_name_show").dblclick(function() {
		$("#templet_name_show option:selected").remove();
		document.getElementById('templet_name_show')[0].selected = true;
		dealOptions("templet_name_show","deptId", "deptName");
		$("#deptId").val("");
		$("#deptName").val("");
	});
	
	loadType("security_classification", "level");
	loadType("os_type", "osType");
	loadType("install_status", "installStatus");
	loadType("online_status", "onlineStatus");
	loadType("sync_status", "syncStatus");
	validateRule();
	initData();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function initData() {

	if (bean != null && bean != undefined) {

		var hiddenValue = bean.personLiableName;
		if (hiddenValue != undefined && hiddenValue != "") {
			$("#personLiableName").val(hiddenValue);
			var hiddenValueArray = hiddenValue.split(";");
			for ( var i in hiddenValueArray) {
				var html = '<option value="' + hiddenValueArray[i] + '">'
						+ hiddenValueArray[i] + '</option>';
				$("#templet_name_show").append(html);
				$("#templet_name_show option:first").attr("selected", true);
			}

		}
		var hiddenValue2 = bean.deptName;
		if (hiddenValue2 != undefined && hiddenValue2 != "") {
			$("#deptName").val(hiddenValue2);
			var hiddenValueArray = hiddenValue2.split(";");
			for ( var i in hiddenValueArray) {
				var html = '<option value="' + hiddenValueArray[i] + '">'
						+ hiddenValueArray[i] + '</option>';
				$("#dept_name_show").append(html);
				$("#dept_name_show option:first").attr("selected", true);
			}

		}

		loadType("security_classification", "level", bean.level);
		loadType("os_type", "osType", bean.osType);
		loadType("install_status", "installStatus", bean.installStatus);
		loadType("online_status", "onlineStatus", bean.onlineStatus);
		loadType("sync_status", "syncStatus", bean.syncStatus);
	}
}

function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/os/osInfo/save",
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
			installCode : {
				required : true
			},
			templet_name_show : {
				required : true
			},
			dept_name_show : {
				required : true
			}
		},
		messages : {
			installCode : {
				required : icon + "安装码"
			},
			templet_name_show : {
				required : icon + "责任人"
			},
			dept_name_show : {
				required : icon + "部门"
			}

		}
	})
}

function geneInstallCode() {
	$("#installCode").val(randomWord(false, 6, 8));
}

/*
 * * randomWord 产生任意长度随机字母数字组合 * randomFlag-是否任意长度 min-任意长度最小位[固定位数] max-任意长度最大位
 */
function randomWord(randomFlag, min, max) {
	var str = "", range = min, arr = [ '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
			'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
			'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
			'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
			'Y', 'Z' ];

	// 随机产生
	if (randomFlag) {
		range = Math.round(Math.random() * (max - min)) + min;
	}
	for (var i = 0; i < range; i++) {
		pos = Math.round(Math.random() * (arr.length - 1));
		str += arr[pos];
	}
	return str;
}

function selectTemplet() {
	// 获取当前窗口名称
	layer.open({
		type : 2,
		title : '选择责任人',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/os/personLiable/select' // iframe的url
	});

}

function loadTemplet(id, name,deptId,deptName) {
	var html = '<option value="' + id + '">' + name + '</option>';
	$("#templet_name_show").html(html);
	$("#templet_name_show option:first").attr("selected", true);
	dealOptions("templet_name_show", "personLiableId", "personLiableName");
	console.log(deptId+">>"+deptName);
	$("#deptId").val(deptId);
	$("#deptName").val(deptName);
}

function removeTemplet() {
	$("#templet_name_show option:selected").remove();
	$("#templet_name_show option:first").attr("selected", true);
	dealOptions("templet_name_show", "personLiableId", "personLiableName");
	$("#deptId").val("");
	$("#deptName").val("");
}

/*
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
	var html = '<option value="' + deptId + '">' + deptName + '</option>';
	$("#dept_name_show").html(html);
	$("#dept_name_show option:first").attr("selected", true);
	dealOptions("dept_name_show", "deptId", "deptName");

}

function removeDept() {
	$("#dept_name_show option:selected").remove();
	$("#dept_name_show option:first").attr("selected", true);
	dealOptions("dept_name_show", "deptId", "deptName");

}*/

function loadType(type, id, checkedValue) {
	var html = "";
	$.ajax({
		url : '/common/dict/list/' + type,
		success : function(data) {

			// debugger;
			// 加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name
						+ '</option>'
			}

			if (id == undefined && checkedValue == undefined) {
				$(".chosen-select").append(html);
			} else if (id != undefined && checkedValue == undefined) {

				$(".chosen-select[name=" + id + "]").append(html);
			}
			$(".chosen-select").chosen({
				maxHeight : 200
			});
			if (id != undefined && checkedValue != undefined) {
				$("#" + id).val(checkedValue);
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