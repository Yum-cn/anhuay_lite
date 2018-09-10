
//function initAlermPlace(defaultValue){
$.ajax({
	type : "GET",
	url : "/common/dict/citys?timeStamp=" + new Date().getTime(),
	data : null,
	success : function(data) {
		// console.log(data);
		var defaultValue = "";

		if (irregularConnection != null && irregularConnection != undefined
				&& irregularConnection.monitorRules != null
				&& irregularConnection.monitorRules != undefined) {

			var province = irregularConnection.monitorRules.first_alerm_place;
			var city = irregularConnection.monitorRules.second_alerm_place;
			var area = irregularConnection.monitorRules.third_alerm_place;
			
			defaultValue = province.substr(province.indexOf(":") + 1)
					+ "," + city.substr(city.indexOf(":") + 1)
					+ "," + area.substr(area.indexOf(":") + 1);
		}
		initSecletDataCombo(JSON.parse(data.data), "alerm_place",'city_level_3', defaultValue);
	},
	beforeSend : function() {
	},
	error : function(xhr, msg, obj) {
		console.log("Error" + msg);
	}
});

// }

