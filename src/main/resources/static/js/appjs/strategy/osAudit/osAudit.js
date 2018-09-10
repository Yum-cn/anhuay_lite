
var prefix = "/strategy/osAudit"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
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
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset
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
						columns : [
								{
									checkbox : true
								},
																{
									field : 'id', 
									title : '主键编号' 
								},
																{
									field : 'templetId', 
									title : '模板编号' 
								},
																{
									field : 'localFileStatus', 
									title : '本地文件审计状态' 
								},
																{
									field : 'localFileRules', 
									title : '本地文件审计规则' 
								},
																{
									field : 'diskFileStatus', 
									title : '光盘文件审计状态' 
								},
																{
									field : 'diskFileRules', 
									title : '光盘文件审计规则' 
								},
																{
									field : 'udiskFileStatus', 
									title : 'U盘文件审计状态' 
								},
																{
									field : 'udiskFileRules', 
									title : 'U盘文件审计规则' 
								},
																{
									field : 'osOnoffStatus', 
									title : '开机关机审计状态' 
								},
																{
									field : 'osOnoffRules', 
									title : '开机关机审计规则' 
								},
																{
									field : 'processMonitorStatus', 
									title : '进程监控审计状态' 
								},
																{
									field : 'processMonitorRules', 
									title : '进程监控审计规则' 
								},
																{
									field : 'printMonitorStatus', 
									title : '打印监控审计状态' 
								},
																{
									field : 'printMonitorRules', 
									title : '打印监控审计规则' 
								},
																{
									field : 'osInfoStatus', 
									title : '主机信息审计状态' 
								},
																{
									field : 'osInfoRules', 
									title : '主机信息审计规则' 
								},
																{
									field : 'systemLogStatus', 
									title : '系统日志审计状态' 
								},
																{
									field : 'systemLogRules', 
									title : '系统日志审计规则' 
								},
																{
									field : 'accountMonitorStatus', 
									title : '账户监控审计状态' 
								},
																{
									field : 'accountMonitorRules', 
									title : '账户监控审计规则' 
								},
																{
									field : 'shareMonitorStatus', 
									title : '共享监控审计状态' 
								},
																{
									field : 'shareMonitorRules', 
									title : '共享监控审计规则' 
								},
																{
									field : 'exceptionMonitorStatus', 
									title : '异常监控审计状态' 
								},
																{
									field : 'exceptionMonitorRules', 
									title : '异常监控审计规则' 
								},
																{
									field : 'osConfigStatus', 
									title : '主机配置审计状态' 
								},
																{
									field : 'osConfigRules', 
									title : '主机配置审计规则' 
								},
																{
									field : 'moveMediaStatus', 
									title : '移动介质审计状态' 
								},
																{
									field : 'moveMediaRules', 
									title : '移动介质审计规则' 
								},
																{
									field : 'portMonitorStatus', 
									title : '端口监控审计状态' 
								},
																{
									field : 'portMonitorRules', 
									title : '端口监控审计规则' 
								},
																{
									field : 'serverMonitorStatus', 
									title : '服务监控审计状态' 
								},
																{
									field : 'serverMonitorRules', 
									title : '服务监控审计规则' 
								},
																{
									field : 'connectionMonitorStatus', 
									title : '连接监控审计状态' 
								},
																{
									field : 'connectionMonitorRules', 
									title : '连接监控审计规则' 
								},
																{
									field : 'networkFlowStatus', 
									title : '网络流量审计状态' 
								},
																{
									field : 'networkFlowRules', 
									title : '网络流量审计规则' 
								},
																{
									field : 'diskSpaceStatus', 
									title : '磁盘空间审计状态' 
								},
																{
									field : 'diskSpaceRules', 
									title : '磁盘空间审计规则' 
								},
																{
									field : 'fileControlStatus', 
									title : '文件控制审计状态' 
								},
																{
									field : 'fileControlRules', 
									title : '文件控制审计规则' 
								},
																{
									field : 'status', 
									title : '状态' 
								},
																{
									field : 'createTime', 
									title : '创建时间' 
								},
																{
									field : 'updateTime', 
									title : '修改时间' 
								},
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
												+ row.id
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="resetPwd(\''
												+ row.id
												+ '\')"><i class="fa fa-key"></i></a> ';
										return e + d ;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function resetPwd(id) {
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}