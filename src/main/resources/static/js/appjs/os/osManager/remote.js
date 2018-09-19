$().ready(function() {
	validateRule();
	loadTypeTab("chosen-select", "exec_cmd", "cmdType");
});

$.validator.setDefaults({
	submitHandler : function() {
		execCmd();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/os/osManager/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 1) {
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
				required : icon + "请输入名字"
			}
		}
	})
}
var interval;
var intervalCount = 0;
function execCmd() {
	
	debugger;
	var cmd = $("#cmdType").val();
	if(cmd == "" || cmd == undefined){
		layer.msg("请选择执行命令");
		return false;
	}
	
	$.ajax({
		cache : true,
		type : "POST",
		url : "/os/osCmd/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 1) {
				$("#submit").attr("disabled", true);
				parent.layer.msg("执行中...");
				$("#result").text("");
				//parent.reLoad();
				//var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				//parent.layer.close(index);
				interval = setInterval(getCmdResult, 5000);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}

function getCmdResult() {
	intervalCount ++;
	var osId = $("#osId").val();
	$.ajax({
		cache : false,
		type : "GET",
		url : "/os/osCmd/getCmdResult?osId="+osId,
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			//console.log(data);
			$("#result").text(data.data.cmdResult);
			
			if(data.data.cmdStatus ==3){
				clearInterval(interval);
				parent.layer.alert("执行成功");
			}else if(data.data.cmdStatus ==4){
				clearInterval(interval);
				parent.layer.alert("执行失败");
			}else if(intervalCount >=100){
				clearInterval(interval);
				parent.layer.alert("执行超时，请重试");
			}
		}
	});

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