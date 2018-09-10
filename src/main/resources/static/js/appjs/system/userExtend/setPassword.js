var prefix = "/sys/user"
$(function() {
	loadType();
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

$("#pwd_save").click(function() {

	var tag = tempValidate();
	if ($("#modifyPwd").valid() && tag) {
		$.ajax({
			cache : true,
			type : "POST",
			url : "/system/userExtend/resetPwd",
			data : $('#modifyPwd').serialize(),
			async : false,
			error : function(request) {
				parent.laryer.alert("Connection error");
			},
			success : function(data) {

				console.log(data);
				if (data.code == 1) {

					// layer.alert('更新密码成功',test());
					layer.alert('更新密码成功', function() {
						var index = layer.load(1, {
							shade : [ 0.1, '#fff' ]
						// 0.1透明度的白色背景
						});
						parent.location.href = '/index';
					});

				} else {
					layer.alert(data.message == null ? "" : data.message)
				}
			}
		});
	}
});

function test() {

	// parent.location.href = '/index';
}

function tempValidate() {
	var expiredType = $("#expiredType").val();
	if (expiredType != "" && expiredType != null && expiredType != undefined) {
		$("#selectTemp").html("");
		return true;
	} else {
		var html = '<label style="color:#cc5965" ><i class="fa fa-times-circle"></i> <font style="color:#cc5965">密码过期类型不能为空</font></label>';
		$("#selectTemp").html("");
		$("#selectTemp").append(html);
		return false;
	}
}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#modifyPwd").validate({
		rules : {
			pwdOld : {
				required : true
			},
			pwdNew : {
				required : true,
				minlength : 6
			},
			confirm_password : {
				required : true,
				minlength : 6,
				equalTo : "#pwdNew"
			},
			expiredType : {
				minlength : 1,
				required : true
			}
		},
		messages : {
			pwdOld : {
				required : icon + "旧密码不能为空"
			},
			pwdNew : {
				required : icon + "新密码不能为空",
				minlength : "*密码不能小于3个字符"
			},
			confirm_password : {
				required : icon + "确认密码不能为空",
				minlength : "*密码不能小于3个字符",
				equalTo : "*请再次输入相同的值"
			},
			expiredType : {
				minlength : "aaaaaaaaaaa",
				required : icon + "密码过期类型不能为空"
			}

		}
	})
}

function loadType() {
	var html = "";
	$.ajax({
		url : '/common/dict/list/expired_type',
		success : function(data) {
			// 加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].value + '">' + data[i].name
						+ '</option>'
			}
			$(".chosen-select").append(html);
			$(".chosen-select").chosen({
				maxHeight : 200
			});
			// 点击事件
			$('.chosen-select').on('change', function(e, params) {
				$("#selectTemp").html("");
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