

/**
 * jQuery : 下拉选框-一级
 * 
 * @param data
 *            [{"id":"130000","pid":"1","name":"河北省","referCode":"null","sub_data":[{"id":"130100","pid":"130000","name":"石家庄市","referCode":"null","sub_data":[]},{"id":"130200","pid":"130000","name":"唐山市","referCode":"null","sub_data":[]}]}]
 * @param htmlId
 *            $("#textId").html();
 * @author Yum
 */

$.fn.CascadeMenu_L1 = function(htmlId, First, selectedValue, isHasSelect) {
	// debugger;
	var _self = this;
	// 定义默认值
	_self.data("first", [ "请选择", "" ]);

	// 分别获取对应下拉框
	var $sel1 = _self;
	if (isHasSelect == undefined || isHasSelect == true) {
		// 插入空的下拉框
		_self.append("<select style='width:120px;' id='first_" + htmlId
				+ "' name='first_" + htmlId + "'></select>");
		$sel1 = _self.find("select").eq(0);

	}

	// 默认省级下拉
	if (_self.data("first")) {
		$sel1.append("<option value='" + _self.data("first")[1] + "'>"
				+ _self.data("first")[0] + "</option>");
	}
	$.each(First, function(index, data) {
		var is_select = "";
		if (selectedValue == data.substr(data.indexOf(":") + 1)) {
			is_select = "selected";
		}
		$sel1.append("<option value='" + data + "'" + is_select + ">"
				+ data.substr(data.indexOf(":") + 1) + "</option>");
	});

	return _self;
};

/**
 * jQuery : 下拉选框-二级（城市联动）
 * 
 * @param data
 *            [{"id":"130000","pid":"1","name":"河北省","referCode":"null","sub_data":[{"id":"130100","pid":"130000","name":"石家庄市","referCode":"null","sub_data":[]},{"id":"130200","pid":"130000","name":"唐山市","referCode":"null","sub_data":[]}]}]
 * @param htmlId
 *            $("#textId").html();
 * @author Yum
 */
$.fn.CascadeMenu_L2 = function(htmlId, First, Second, selectedValue) {
	// console.log("selectedValue"+selectedValue);
	var first_select = "";
	var second_select = "";
	if (selectedValue != undefined && selectedValue != null) {
		var defaultVs = selectedValue.split(',');
		first_select = defaultVs[0];
		second_select = defaultVs[1];
		// console.log(">>>"+defaultVs[0]+">>>"+defaultVs[1]);
	}

	var _self = this;
	// 定义默认值
	_self.data("first", [ "请选择", "" ]);
	_self.data("second", [ "请选择", "" ]);
	// 插入空的下拉框
	_self.append("<select style='width:120px;' id='first_" + htmlId
			+ "' name='first_" + htmlId + "'></select>");
	_self.append("&nbsp;<select style='width:120px;' id='second_" + htmlId
			+ "' name='second_" + htmlId + "'></select>");

	// 分别获取对应下拉框
	var $sel1 = _self.find("select").eq(0);
	var $sel2 = _self.find("select").eq(1);
	// 默认省级下拉
	if (_self.data("first")) {
		$sel1.append("<option value='" + _self.data("first")[1] + "'>"
				+ _self.data("first")[0] + "</option>");
	}
	$.each(First, function(index, data) {
		var is_select = "";
		if (first_select == data.substr(data.indexOf(":") + 1)) {
			is_select = "selected";
		}
		$sel1.append("<option value='" + data + "'" + is_select + ">" + data
				+ "</option>");
	});
	// 默认城市下拉
	if (_self.data("second")) {
		$sel2.append("<option value='" + _self.data("second")[1] + "'>"
				+ _self.data("second")[0] + "</option>");
	}
	// 省级联动 控制
	var index1 = "";
	$sel1.change(
			function() {
				// 清空其它下拉框
				$sel2[0].options.length = 0;
				index1 = this.selectedIndex;
				if (index1 == 0) { // 当选择的为 “请选择” 时
					if (_self.data("second")) {
						$sel2.append("<option value='"
								+ _self.data("second")[1] + "'>"
								+ _self.data("second")[0] + "</option>");
					}
				} else {

					$.each(Second[index1 - 1],
							function(index, data) {
								var is_select = "";
								if (second_select == data.substr(data
										.indexOf(":") + 1)) {
									is_select = "selected";
								}
								$sel2.append("<option value='" + data + "'"
										+ is_select + ">"
										+ data.substr(data.indexOf(":") + 1)
										+ "</option>");
							});
				}
			}).change();

	return _self;
};

/**
 * jQuery : 三级下拉选框联动
 * 
 * @param data
 *            [{"id":"130000","pid":"1","name":"河北省","referCode":"null","sub_data":[{"id":"130100","pid":"130000","name":"石家庄市","referCode":"null","sub_data":[]},{"id":"130200","pid":"130000","name":"唐山市","referCode":"null","sub_data":[]}]}]
 * @param htmlId
 *            $("#textId").html();
 * @author Yum
 */

$.fn.CascadeMenu_L3 = function(htmlId, First, Second, Third, selectedValue) {

	var first_select = "";
	var second_select = "";
	var third_select = "";
	if (selectedValue != undefined && selectedValue != null) {
		var defaultVs = selectedValue.split(',');
		first_select = defaultVs[0];
		second_select = defaultVs[1];
		third_select = defaultVs[2];
		// console.log(">>>"+defaultVs[0]+">>>"+defaultVs[1]);
	}
	var _self = this;
	// 定义默认值
	_self.data("first", [ "请选择", "" ]);
	_self.data("second", [ "请选择", "" ]);
	_self.data("third", [ "请选择", "" ]);
	// 插入空的下拉框
	_self.append("<select style='width:120px;' id='first_" + htmlId
			+ "' name='first_" + htmlId + "'></select>");
	_self.append("&nbsp;<select style='width:120px;' id='second_" + htmlId
			+ "' name='second_" + htmlId + "'></select>");
	_self.append("&nbsp;<select style='width:120px;' id='third_" + htmlId
			+ "' name='third_" + htmlId + "'></select>");
	// 分别获取对应下拉框
	var $sel1 = _self.find("select").eq(0);
	var $sel2 = _self.find("select").eq(1);
	var $sel3 = _self.find("select").eq(2);
	// 默认省级下拉
	if (_self.data("first")) {
		$sel1.append("<option value='" + _self.data("first")[1] + "'>"
				+ _self.data("first")[0] + "</option>");
	}
	$.each(First, function(index, data) {
		var is_select = "";
		if (first_select == data.substr(data.indexOf(":") + 1)) {
			is_select = "selected";
		}
		$sel1.append("<option value='" + data + "'" + is_select + ">"
				+ data.substr(data.indexOf(":") + 1) + "</option>");
	});
	// 默认1级下拉
	if (_self.data("second")) {
		$sel2.append("<option value='" + _self.data("second")[1] + "'>"
				+ _self.data("second")[0] + "</option>");
	}
	// 默认2级下拉
	if (_self.data("third")) {
		$sel3.append("<option value='" + _self.data("third")[1] + "'>"
				+ _self.data("third")[0] + "</option>");
	}
	// 省级联动 控制
	var index1 = "";
	$sel1.change(
			function() {
				// 清空其它下拉框
				$sel2[0].options.length = 0;
				$sel3[0].options.length = 0;
				index1 = this.selectedIndex;
				// console.log(index1);
				if (index1 == 0) { // 当选择的为 “请选择” 时
					if (_self.data("second")) {
						$sel2.append("<option value='"
								+ _self.data("second")[1] + "'>"
								+ _self.data("second")[0] + "</option>");
					}
					if (_self.data("third")) {
						$sel3.append("<option value='" + _self.data("third")[1]
								+ "'>" + _self.data("third")[0] + "</option>");
					}
				} else {
					var secIndex = 0;
					$.each(Second[index1 - 1],
							function(index, data) {
								var is_select = "";
								if (second_select == data.substr(data
										.indexOf(":") + 1)) {
									secIndex = index;
									is_select = "selected";
								}
								$sel2.append("<option value='" + data + "'"
										+ is_select + ">"
										+ data.substr(data.indexOf(":") + 1)
										+ "</option>");
							});
					/*
					 * $sel3.append("<option value=''>" + "请选择" + "</option>");
					 */
					$.each(Third[index1 - 1][secIndex],
							function(index, data) {
								var is_select = "";
								if (third_select == data.substr(data
										.indexOf(":") + 1)) {
									is_select = "selected";
								}
								$sel3.append("<option value='" + data + "'"
										+ is_select + ">"
										+ data.substr(data.indexOf(":") + 1)
										+ "</option>");
							});
				}
			}).change();
	// 1级下的联动控制
	var index2 = "";
	$sel2.change(function() {
		$sel3[0].options.length = 0;
		index2 = this.selectedIndex;
		/*
		 * $sel3.append("<option value=''>" + "请选择" + "</option>");
		 */
		$.each(Third[index1 - 1][index2], function(index, data) {
			var is_select = "";
			if (third_select == data.substr(data.indexOf(":") + 1)) {
				is_select = "selected";
			}
			$sel3.append("<option value='" + data + "'" + is_select + ">"
					+ data.substr(data.indexOf(":") + 1) + "</option>");
		});
	});
	return _self;
};

/**
 * jQuery : 城市特殊处理 三级非必选
 * 
 * @param data
 *            [{"id":"130000","pid":"1","name":"河北省","referCode":"null","sub_data":[{"id":"130100","pid":"130000","name":"石家庄市","referCode":"null","sub_data":[]},{"id":"130200","pid":"130000","name":"唐山市","referCode":"null","sub_data":[]}]}]
 * @param htmlId
 *            $("#textId").html();
 * @author Yum
 */

$.fn.CascadeMenu_city_L3 = function(htmlId, First, Second, Third, selectedValue) {

	var first_select = "";
	var second_select = "";
	var third_select = "";
	if (selectedValue != undefined && selectedValue != null) {
		var defaultVs = selectedValue.split(',');
		first_select = defaultVs[0];
		second_select = defaultVs[1];
		third_select = defaultVs[2];
		// console.log(">>>"+defaultVs[0]+">>>"+defaultVs[1]);
	}
	var _self = this;
	// 定义默认值
	_self.data("first", [ "请选择", "" ]);
	_self.data("second", [ "请选择", "" ]);
	_self.data("third", [ "请选择", "" ]);
	// 插入空的下拉框
	_self
			.append("<select  class='form-control-select' style='width:120px;' id='first_"
					+ htmlId + "' name='first_" + htmlId + "'></select>");
	_self
			.append("&nbsp;<select  class='form-control-select' style='width:120px;' id='second_"
					+ htmlId + "' name='second_" + htmlId + "'></select>");
	_self
			.append("&nbsp;<select  class='form-control-select' style='width:120px;' id='third_"
					+ htmlId + "' name='third_" + htmlId + "'></select>");
	// 分别获取对应下拉框
	var $sel1 = _self.find("select").eq(0);
	var $sel2 = _self.find("select").eq(1);
	var $sel3 = _self.find("select").eq(2);
	// 默认省级下拉
	if (_self.data("first")) {
		$sel1.append("<option value='" + _self.data("first")[1] + "'>"
				+ _self.data("first")[0] + "</option>");
	}
	$.each(First, function(index, data) {
		var is_select = "";
		if (first_select == data.substr(data.indexOf(":") + 1)) {
			is_select = "selected";
		}
		$sel1.append("<option value='" + data + "'" + is_select + ">"
				+ data.substr(data.indexOf(":") + 1) + "</option>");
	});
	// 默认1级下拉
	if (_self.data("second")) {
		$sel2.append("<option value='" + _self.data("second")[1] + "'>"
				+ _self.data("second")[0] + "</option>");
	}
	// 默认2级下拉
	if (_self.data("third")) {
		$sel3.append("<option value='" + _self.data("third")[1] + "'>"
				+ _self.data("third")[0] + "</option>");
	}
	// 省级联动 控制
	var index1 = "";
	$sel1.change(
			function() {
				// 清空其它下拉框
				$sel2[0].options.length = 0;
				$sel3[0].options.length = 0;
				index1 = this.selectedIndex;
				// console.log(index1);
				if (index1 == 0) { // 当选择的为 “请选择” 时
					if (_self.data("second")) {
						$sel2.append("<option value='"
								+ _self.data("second")[1] + "'>"
								+ _self.data("second")[0] + "</option>");
					}
					if (_self.data("third")) {
						$sel3.append("<option value='" + _self.data("third")[1]
								+ "'>" + _self.data("third")[0] + "</option>");
					}
				} else {
					var secIndex = 0;
					$.each(Second[index1 - 1],
							function(index, data) {
								var is_select = "";
								if (second_select == data.substr(data
										.indexOf(":") + 1)) {
									secIndex = index;
									is_select = "selected";
								}
								$sel2.append("<option value='" + data + "'"
										+ is_select + ">"
										+ data.substr(data.indexOf(":") + 1)
										+ "</option>");
							});
					$sel3.append("<option value=''>" + "请选择" + "</option>");
					$.each(Third[index1 - 1][secIndex],
							function(index, data) {
								var is_select = "";
								if (third_select == data.substr(data
										.indexOf(":") + 1)) {
									is_select = "selected";
								}
								$sel3.append("<option value='" + data + "'"
										+ is_select + ">"
										+ data.substr(data.indexOf(":") + 1)
										+ "</option>");
							});
				}
			}).change();
	// 1级下的联动控制
	var index2 = "";
	$sel2.change(function() {
		$sel3[0].options.length = 0;
		index2 = this.selectedIndex;
		$sel3.append("<option value=''>" + "请选择" + "</option>");
		$.each(Third[index1 - 1][index2], function(index, data) {
			var is_select = "";
			if (third_select == data.substr(data.indexOf(":") + 1)) {
				is_select = "selected";
			}
			$sel3.append("<option value='" + data + "'" + is_select + ">"
					+ data.substr(data.indexOf(":") + 1) + "</option>");
		});
	});

	return _self;
};

/**
 * jQuery : 初始化select数据
 * 
 * @param data-----公共字典（dict——web）json数据格式
 * @param htmlId
 *            页面停靠seclet元素标识
 * @param type
 *            多级菜单标识(level_1,level_2,level_3)
 * @param selectedValue
 *            默认值选中与type配合使用（value1,value2,value3）
 * @author Yum
 */
function initSecletData(data, htmlId, type, selectedValue) {
	// console.log(data);
	var First = new Array();
	var Second = new Array();
	var Third = new Array();

	$
			.each(
					data,
					function(i, item) {
						First[i] = /* item.id + ":" + */item.name;

						var secondObj = new Array();
						var thirdjObj = new Array();
						if (item.sub_data != "" && item.sub_data != undefined) {
							$
									.each(
											item.sub_data,
											function(j, item_sub) {
												secondObj[j] = /*
																 * item_sub.id +
																 * ":" +
																 */item_sub.name;

												var thirdkObj = new Array();
												$
														.each(
																item_sub.sub_data,
																function(k,
																		item_sub_sub) {
																	thirdkObj[k] = /*
																					 * item_sub_sub.id +
																					 * ":"+
																					 */item_sub_sub.name;
																});
												thirdjObj[j] = thirdkObj;
											});
						}

						Second[i] = secondObj;
						Third[i] = thirdjObj;
					});

	if (type == '' || type == undefined || type == 'level_1') {
		$("#" + htmlId).CascadeMenu_L1(htmlId, First, selectedValue);
	} else if (type == 'level_2') {
		$("#" + htmlId).CascadeMenu_L2(htmlId, First, Second, selectedValue);
	} else if (type == 'level_3') {
		$("#" + htmlId).CascadeMenu_L3(htmlId, First, Second, Third,
				selectedValue);
	} else if (type == 'city_level_3') {
		$("#" + htmlId).CascadeMenu_city_L3(htmlId, First, Second, Third,
				selectedValue);
	}

}

/**
 * jQuery : 初始化select数据
 * 
 * @param data(包含key:value格式)-----公共字典（dict——web）json数据格式
 * @param htmlId
 *            页面停靠seclet元素标识
 * @param type
 *            多级菜单标识(level_1,level_2,level_3)
 * @param selectedValue
 *            默认值选中与type配合使用（value1,value2,value3）
 * @author Yum
 */
function initSecletDataCombo(data, htmlId, type, selectedValue) {

	// debugger;
	// console.log(data);
	var First = new Array();
	var Second = new Array();
	var Third = new Array();

	$.each(data, function(i, item) {
		First[i] = item.id + ":" + item.name;

		var secondObj = new Array();
		var thirdjObj = new Array();
		if (item.sub_data != "" && item.sub_data != undefined) {
			$.each(item.sub_data, function(j, item_sub) {
				secondObj[j] = item_sub.id + ":" + item_sub.name;

				var thirdkObj = new Array();
				$.each(item_sub.sub_data, function(k, item_sub_sub) {
					thirdkObj[k] = item_sub_sub.id + ":" + item_sub_sub.name;
				});
				thirdjObj[j] = thirdkObj;
			});
		}

		Second[i] = secondObj;
		Third[i] = thirdjObj;
	});

	$("#" + htmlId).html("");
	if (type == '' || type == undefined || type == 'level_1') {// 普通一级下拉
		$("#" + htmlId).CascadeMenu_L1(htmlId, First, selectedValue, true);
	} else if (type == 'level_2') {// 普通二级下拉
		$("#" + htmlId).CascadeMenu_L2(htmlId, First, Second, selectedValue,
				true);
	} else if (type == 'level_3') {// 普通三级下拉
		$("#" + htmlId).CascadeMenu_L3(htmlId, First, Second, Third,
				selectedValue);
	} else if (type == 'city_level_3') {// 城市二级可选
		$("#" + htmlId).CascadeMenu_city_L3(htmlId, First, Second, Third,
				selectedValue);
	} else if (type == 'unsel_level_1') {// 一级不包含select选择域
		$("#" + htmlId).CascadeMenu_L1(htmlId, First, selectedValue, false);
	} else if (type == 'unsel_level_2') {// 二级不包含select选择域
		$("#" + htmlId).CascadeMenu_L2(htmlId, First, Second, selectedValue,
				false);
	}

}