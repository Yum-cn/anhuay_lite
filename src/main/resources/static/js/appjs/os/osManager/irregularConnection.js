var prefix = "/os/netInfo"

$(function() {
	$("#s1 option:first,#s2 option:first").attr("selected", true);
	$("#s1").dblclick(function() {
		$("option:selected", this).clone().appendTo("#s2");
		$("option:selected", this).remove();
		$("#s1 option:first,#s2 option:first").attr("selected", true);
		dealOptionsValue("s1","s2");
	});
	$("#s2").dblclick(function() {
		$("option:selected", this).clone().appendTo("#s1");
		$("option:selected", this).remove();
		$("#s1 option:first,#s2 option:first").attr("selected", true);
		dealOptionsValue("s1","s2");
	});
	$("#add").click(function() {
		$("#s1 option:selected").clone().appendTo("#s2");
		$("#s1 option:selected").remove();
		$("#s1 option:first,#s2 option:first").attr("selected", true);
		dealOptionsValue("s1","s2");
	});
	$("#remove").click(function() {
		$("#s2 option:selected").clone().appendTo("#s1");
		$("#s2 option:selected").remove();
		$("#s1 option:first,#s2 option:first").attr("selected", true);
		dealOptionsValue("s1","s2");
	});


	initData();
});


$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

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

/*$.validator.setDefaults({
	//console.log("come in ");
	submitHandler : function() {
		save();
	}
});*/
function save() {
	//debugger;
	//console.log($('#signupForm').serialize());
	dealOptionsValue("s1","s2");
	console.log($("#enableIds").val());
	console.log($("#disableIds").val());
	console.log($('#signupForm').serialize());
	$.ajax({
		type : "POST",
		url : "/os/osManager/netInfoManager",
//		url : "/os/osManager/save",
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

function initData() {
	
	if(bean){
		var htmlS1 = "";
		for (var i = 0; i < bean.length; i++) {
			if(bean[i].devStatus == 1){
				htmlS1 += '<option value="' + bean[i].id + '">' + bean[i].devName + '</option>';
			}
		}
		$("#s1").append(htmlS1);
		
		var htmlS2 = "";
		for (var i = 0; i < bean.length; i++) {
			if(bean[i].devStatus == 0){
				htmlS2 += '<option value="' + bean[i].id + '">' + bean[i].devName + '</option>';
			}
		}
		$("#s2").append(htmlS2);
	}
	
	console.log(bean);
}


function dealOptionsValue(sourceId,targetId){
	
    
    var sourceAll = "";
    $("#"+sourceId+" option").each(function() {
    	
    	if(sourceAll!=""){
    		sourceAll +=",";
    	}
    	sourceAll += $(this).attr("value");
    });
    
    var targetAll = "";
    $("#"+targetId+" option").each(function() {
    	
    	if(targetAll!=""){
    		targetAll +=",";
    	}
    	targetAll += $(this).attr("value");
    });
    $("#enableIds").val(sourceAll);
    $("#disableIds").val(targetAll);
    console.log(sourceAll+">>>"+targetAll);
}