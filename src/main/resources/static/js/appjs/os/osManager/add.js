$().ready(function() {
	validateRule();
	$("#os_info_show").dblclick(function() {
		$("#os_info_show option:selected").remove();
		document.getElementById('os_info_show')[0].selected = true;
		dealOptions("os_info_show","osIds", "osNames");

	});
	loadTypeTab("chosen-select", "password_rule", "passwordRule");
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
		url : "/os/osManager/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				//parent.reLoad();
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
			}
		},
		messages : {
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

function loadTypeTab(className,type,id,checkedValue){
	var html = "";
	$.ajax({
		url : '/common/dict/list/'+type,
		success : function(data) {
			
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