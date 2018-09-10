$().ready(function() {
	validateRule();
	loadTypeTabDIY("chosen-select","device_classification","dictId");
	initData();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});

function initData(){
	var dictId = bean.dictId;
	if(dictId!=undefined){
		loadTypeTabDIY("chosen-select","device_classification","dictId",dictId);
	}
}

function save() {
	
	var flag = $("#signupForm").valid();
    if(!flag){
        //没有通过验证
        return;
    }
    
    var checkText=$("#dictId").find("option:selected").text();
    $("#dictName").val(checkText);
	
	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/device/save",
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
			fileName : {
				required : true
			},
			downUrl : {
				required : true
			}
		},
		messages : {
			fileName : {
				required : icon + "请输入文件名称"
			},
			downUrl : {
				required : icon + "请上传文件"
			}
		}
	})
}



function loadTypeTabDIY(className,type,id,checkedValue){
	var html = "";
	$.ajax({
		url : '/common/dict/list/'+type,
		success : function(data) {
			
			 debugger;
			//alert(className+">>>"+type+">>>"+id+">>>"+checkedValue);
			// 加载数据
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].id + '">' + data[i].name + '</option>'
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