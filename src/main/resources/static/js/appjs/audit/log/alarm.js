var prefix = "/audit/log"
$(function() {
	var deptId = '';
	getTreeData();
	load(deptId);
});

function load(deptId) {
	$('#exampleTable').bootstrapTable({
		method : 'get', // 服务器数据的请求方式 get or post
		url : prefix + "/listAlarm", // 服务器数据的加载地址
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
			field : 'logid',
			title : '编号',
			width : 50
		}, 
		
		{
			field : 'entryTime',
			title : '发生时间',
			formatter : function(value, row, index) {
				return formatTimeStamp(value);
			}
		}, {
			field : 'beType',
			title : '告警级别',
			formatter : function(value, row, index) {
				if (value == 1) {
					return '<span class=" ">一般行为</span>';
				} else if (value == 2) {
					return '<span class=" ">异常行为</span>';
				} else if (value == 3) {
					return '<span class=" ">违规行为</span>';
				}
			}
		}, {
			field : 'deptName',
			title : '部门名称'
		}, {
			field : 'personLiableName',
			title : '用户名'
		}, {
			field : 'osName',
			title : '主机名称'
		}, {
			field : 'osIp',
			title : '主机IP'
		}, {
			field : 'info',
			title : '模块名称'
		},
		{
			field : 'details',
			title : '告警内容'
		}, 
		{
			field : 'type',
			title : '状态',
			formatter : function(value, row, index) {
				if (value == 'm') {
					return '<span class=" ">管理操作</span>';
				} else if (value == 'a') {
					return '<span class=" ">用户操作</span>';
				} else if (value == 's') {
					return '<span class=" ">其它</span>';
				}
			}
		}, ]
	});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}

function getTreeData() {
	$.ajax({
		type : "GET",
		url : "/system/sysDept/tree",
		success : function(tree) {
			loadTree(tree);
		}
	});
}
function loadTree(tree) {
	$('#jstree').jstree({
		'core' : {
			'data' : tree
		},
		"plugins" : [ "search" ]
	});
	$('#jstree').jstree().open_all();
}
$('#jstree').on("changed.jstree", function(e, data) {
	if (data.selected == -1) {
		var opt = {
			query : {
				deptId : '',
			}
		}
		$('#exampleTable').bootstrapTable('refresh', opt);
	} else {
		var opt = {
			query : {
				deptId : data.selected[0],
			}
		}
		$('#exampleTable').bootstrapTable('refresh', opt);
	}

});