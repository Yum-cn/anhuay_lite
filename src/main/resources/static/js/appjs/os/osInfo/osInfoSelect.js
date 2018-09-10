var prefix = "/os/osInfo"
$(function() {
	var deptId = '';
	load(deptId);
});

function load(deptId) {
	$('#exampleTable').bootstrapTable({
		method : 'get', // 服务器数据的请求方式 get or post
		url : prefix + "/list", // 服务器数据的加载地址
		// showRefresh : true,
		// showToggle : true,
		// showColumns : true,
		iconSize : 'outline',
		toolbar : '#exampleToolbar',
		striped : true, // 设置为true会有隔行变色效果
		dataType : "json", // 服务器返回的数据类型
		pagination : true, // 设置为true会在底部显示分页条
		// queryParamsType : "limit",
		// //设置为limit则会发送符合RESTFull格式的参数
		singleSelect : false, // 设置为true将禁止多选
		// contentType : "application/x-www-form-urlencoded",
		// //发送到服务器的数据编码类型
		pageSize : 10, // 如果设置了分页，每页数据条数
		pageNumber : 1, // 如果设置了分布，首页页码
		// search : true, // 是否显示搜索框
		showColumns : false, // 是否显示内容下拉框（选择显示的列）
		sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
		// "server"
		queryParams : function(params) {
			return {
				// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
				limit : params.limit,
				offset : params.offset,
				deptId : deptId,
				osIp : $('#searchName').val()
			// name:$('#searchName').val(),
			// username:$('#searchName').val()
			};
		},
		// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
		// queryParamsType = 'limit' ,返回参数必须包含
		// limit, offset, search, sort, order 否则, 需要包含:
		// pageSize, pageNumber, searchText, sortName,
		// sortOrder.
		// 返回false将会终止请求
		columns : [ {
			checkbox : true
		}, {
			field : 'id',
			title : '编号',
			width : 50
		}, {
			field : 'osIp',
			title : '主机IP'
		}, {
			field : 'osName',
			title : '主机名'
		}, {
			field : 'installCode',
			title : '安装码'
		}, {
			field : 'terminalTag',
			title : '终端标识'
		}, {
			field : 'personLiableName',
			title : '责任人名称'
		}, {
			field : 'deptName',
			title : '部门名称'
		} ]
	});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	var index = layer.open({
		type : 2,
		title : '添加主机信息',
		content : prefix + '/add',
		area : [ '800px', '520px' ],
		maxmin : true
	});
	layer.full(index);
}
function edit(id) {
	/*
	 * layer.open({ type : 2, title : '编辑', maxmin : true, shadeClose : false, //
	 * 点击遮罩关闭层 area : [ '800px', '520px' ], content : prefix + '/edit/' + id //
	 * iframe的url });
	 */
	var index = layer.open({
		type : 2,
		title : '编辑主机信息',
		content : prefix + '/edit/' + id,
		area : [ '800px', '520px' ],
		maxmin : true
	});
	layer.full(index);
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix + "/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})
}

function resetPwd(id) {
}
function selectSubmit() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择数据");
		return;
	}
	var ids = new Array();
	var ips = new Array();
	// 遍历所有选择的行数据，取每条数据对应的ID
	$.each(rows, function(i, row) {
		ids[i] = row['id'];
		ips[i] = row['osIp'];
	});

	parent.loadOsInfo(ids, ips);
	var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
	parent.layer.close(index);

}
