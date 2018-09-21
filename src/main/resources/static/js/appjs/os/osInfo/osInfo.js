var prefix = "/os/osInfo"
$(function() {
	load();
});

function load(deptId) {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/listOs", // 服务器数据的加载地址
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
								osIp:$('#searchName').val()
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
									title : '编号',
									width:50
								},
								{
									field : 'osIp',
									title : '主机IP'
								},
								{
									field : 'osName',
									title : '主机名'
								},
								{
									field : 'uninstallPasswd',
									title : '卸载码'
								},
								{
									field : 'terminalTag',
									title : '终端标识'
								},
								{
									field : 'personLiableName',
									title : '责任人名称'
								},
								{
									field : 'deptName',
									title : '部门名称'
								},
								
								{
									field : 'osType',
									title : '终端类型',
									formatter : function(value, row, index){
										if(value==1){
											return '<span class=" ">单机</span>';
										}else if(value==2){
											return '<span class=" ">联网主机</span>';
										}
									}
								},
								
								{
									field : 'onlineStatus',
									title : '在线状态',
									formatter : function(value, row, index){
										
										//var d =new Date();
										//console.log("当前时间："+d.getTime()/1000);
										//console.log("活跃时间1："+row.lastActiveTime);
										//console.log("活跃时间2："+(parseInt(row.lastActiveTime)+parseInt(2*60)));
										//console.log(row.serverTime);
										if((parseInt(row.lastActiveTime)+parseInt(2*60))>=parseInt(row.serverTime)){
											return '<span class=" ">在线</span>'
										}else{
											return '<span class=" ">离线</span>';
										}
										
										/*if(value==1){
											return '<span class=" ">在线</span>';
										}else if(value==2){
											return '<span class=" ">离线</span>';
										}*/
									}
								},
								{
									field : 'taskStatus',
									title : '策略状态',
									formatter : function(value, row, index){
										if(value==1){
											return '<span class=" ">待下发</span>';
										}else if(value==2){
											return '<span class=" ">下发中</span>';
										}else if(value==3){
											return '<span class=" ">已生效</span>';
										}else if(value==4){
											return '<span class=" ">配置失败</span>';
										}else{
											return '<span class=" ">待下发</span>';
										}
									}
								},
								
								{
									field : 'lastActiveTime',
									title : '最后活跃时间',
									formatter : function(value, row, index) {
										return formatUnixTime(value);
									}
								},
								
								{
									field : 'createTime',
									title : '创建时间',
									formatter : function(value, row, index) {
										return formatUnixTime(value);
									}
								},
								{
									title : '操作',
									field : 'id',
									align : 'center',
									width:250,
									formatter : function(value, row, index) {
										
										
										var e = '<a class="btn btn-warning btn-sm '
												+ s_edit_h
												+ '" href="#" mce_href="#" title="远程协助" onclick="remote(\''
												+ row.id+ '\',\'' + row.osIp
												+ '\')"><i class="fa fa-street-view"></i></a> ';
										var f = '<a class="btn btn-primary btn-sm '
											+ s_edit_h
											+ '" href="#" mce_href="#" title="导出离线策略" onclick="singleExport(\''
											+ row.id+ '\',\'' + row.osIp
											+ '\')"><i class="fa fa-external-link"></i></a> ';
										var g = '<a class="btn btn-success btn-sm" href="#" title="查看策略"  mce_href="#" onclick="showStrategy(\''
											+ row.templetName
											+ '\')"><i class="fa fa-group"></i></a> ';
										
										var h = '<a class="btn btn-danger '
											+ s_remove_h
											+ '" href="#" title="非法外联管理"  mce_href="#" onclick="irregularConnection(\''
											+ row.id+ '\',\'' + row.osIp+ '\',\'' + row.osName
											+ '\')"><i class="fa fa-sliders"></i></a> ';
										/*var f = '<a class="btn btn-warning btn-sm '
												+ s_remove_h
												+ '" href="#" title="删除"  mce_href="#" onclick="remove(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										*/
										return e+f+h+g ;
									}
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

function remote(id,osIp) {
	layer.open({
		type : 2,
		title : '远程协助',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/os/osManager/remote?id=' + id+'&osIp='+osIp // iframe的url
	});
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

function showStrategy(content) {
	
	if(!content || content=='null'){
		content = "暂无可生效策略！";
	}
	
	layer.alert(content);
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
function setCode() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要设置的数据");
		return;
	}
	
	var ids = new Array();
	var ips = new Array();
	// 遍历所有选择的行数据，取每条数据对应的ID
	$.each(rows, function(i, row) {
		ids[i] = row['id'];
		ips[i] = row['osIp'];
	});
	
	var idsStr = ids.join(",");
	var ipsStr = ips.join(",");
	layer.open({
		type : 2,
		title : '卸载码',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/os/osManager/uninstallCode?osIds=' + idsStr+'&osIps='+ipsStr // iframe的url
	});
	
}

function irregularConnection(osId,osIp,osName) {

	layer.open({
		type : 2,
		title : '非法外联',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : '/os/osManager/irregularConnection?osId=' + osId+'&osIp='+osIp+'&osName='+osName // iframe的url
	});
	
}



function offlineExport() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要导出的数据");
		return;
	}
	
	var ids = new Array();
	// 遍历所有选择的行数据，取每条数据对应的ID
	$.each(rows, function(i, row) {
		if(row['id']){
			
			var osIp = row['osIp'];
			osIp = osIp.replace(/[.]/g,"_");
			var url ='/os/osManager/offlineExport?osId=' + row['id']+'&osIp='+osIp;
			sleep(1000);
		    download(url);
		}
	});
	
}

function singleExport(osId,osIp){
	osIp = osIp.replace(/[.]/g,"_");
	window.location.href='/os/osManager/offlineExport?osId=' + osId+'&osIp='+osIp;
	//var url = '/os/osManager/offlineExport?osId=' + osId+'&osIp='+osIp;
	//window.location.href=url;
	//var exportXlsButton = document.getElementById("exportXlsButton");  
	//exportXlsButton.href = url; //url地址  
	//exportXlsButton.click(); 
}



function sleep(d){
    for(var t = Date.now();Date.now() - t <= d;);
}
 
 
function download(src) {
    var $a = document.createElement('a');
    $a.setAttribute("href", src);
    $a.setAttribute("download", "");
 
    var evObj = document.createEvent('MouseEvents');
    evObj.initMouseEvent( 'click', true, true, window, 0, 0, 0, 0, 0, false, false, true, false, 0, null);
    $a.dispatchEvent(evObj);
};




