$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}

});

function addStrategyTemplet() {
	
	var flag = $("#signupForm").valid();
    if(!flag){
        //没有通过验证
        return;
    }

    console.log($("#signupForm").serializeJson());
	$.ajax({

			dataType : "json",
			contentType : "application/json; charset=utf-8",
			cache : true,
			type : "POST",
			url : "/strategy/strategyTemplet/addStrategyTemplet",
			data : JSON.stringify($("#signupForm").serializeJson()),// 你的formid
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				//debugger;
				if (data.code == 1) {
					parent.layer.msg("操作成功");
					parent.reLoad();
					var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
					// parent.layer.close(index);
					var id = data.data.id;
					$("#id").val(id);
					window.location.href = "/strategy/strategyTemplet/toAddStrategyTempletTab?strategyTempletId="+ id;
				} else {
					parent.layer.alert(data.msg)
				}

			}
		});

}


function returnList() {
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    parent.layer.close(index);

}

function validateRule() {
	//debugger;
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			templetName : {
				required : true
			}
		},
		messages : {
			templetName : {
				required : icon + "模板名称不能为空"
			}
		}
	})
}


