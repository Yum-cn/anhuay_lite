$().ready(function() {
	validateRule();
	initData();
});


function initData(){
	
	debugger;
	var crypt = bean.crypt;
	
	if (crypt != undefined&&crypt!=null) {
		
		var arr = crypt.split(",");
		if (Array.isArray(arr)) {
			for (var i = 0; i < arr.length; i++) {
				$("input[name=crypt][value='" + arr[i] + "']").attr(
						"checked", true);
			}
		} else {
			$("input[name=crypt][value='" + arr + "']").attr(
					"checked", true);
		}
	}
}

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/alarm/emailConfig/save",
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
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}