//--------------------------------------------------------------------------
// File:        common.js
// Description: common function
// Rev:         0.9
// Created:     Apr 13, 2011
//
//
//--------------------------------------------------------------------------

var idNameSelect, idValueSelect, flagSelect, idNameSelectNum, idValueSelectNum, flagSelectNum, type;
// check the value is alpha
function isAlpha(value) {
	var alphaReg = /[a-z]+/g;
	if (value.search(alphaReg) != -1) {
		return true;
	}
	return false;
}

function iFrameHeight() {
	var ifm = document.getElementById("iframepage");
	var subWeb = document.frames ? document.frames["iframepage"].document
			: ifm.contentDocument;
	if (ifm != null && subWeb != null) {
		ifm.height = subWeb.body.scrollHeight;
	}
}

// check the value is numeric
function isNumeric(value) {
	var numericReg = /[0-9]+/g;
	if (value.search(numericReg) != -1) {
		return true;
	}
	return false;
}

// check the value is Alphanumeric
function isAlphanumeric(value) {
	var alphanumericReg = /[a-z0-9]+/g;
	if (value.search(alphanumericReg) != -1) {
		return true;
	}
	return false;
}

// check the value is Float
function isFloat(value) {
	var floatReg = /[0-9]+(\.[0-9]+)?/g;
	if (value.search(floatReg) != -1) {
		return true;
	}
	return false;
}

function openWinHtml(url, arg) {
	var args = "width=1020,height=750,top=0,left=0,titlebar=no,alwaysRaised=no,location=no,Resizable=no,scrollbars=yes";
	window.open(url, "win", args);
}

function gotoUpload(cust_id, cust_type, bizid, filterChar) {
	var url = ctx + '/template/templateAction_gotoUploadHTML.action?'
			+ sessInfo + "&cust_id=" + cust_id + "&cust_type=" + cust_type
			+ "&bizid=" + bizid + "&filterChar=" + filterChar;
	openWinHtml(url);
}

function openHrefHtml(url) {
	window.open(url, "_blank");
}

// check the value max length
function chkMaxLength(value, maxLength) {
	if (value.length > maxLength) {
		return false;
	}
	return true;
}

// check the value min length
function chkMinLength(value, minLength) {
	if (value.length < minLength) {
		return false;
	}
	return true;
}

// Check string not empty
function isEmptyStr(s) {
	return ((s == null) || (trim(s).length == 0));
}

// To trim blank space in front and behind from a string
function trim(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
}

function checkDate(textValue) {
	var temp, s;
	temp = new String(textValue);
	s = new String("");
	for (var i = 0; i <= temp.length - 1; i++) {
		if (temp.charAt(i) == "-" || temp.charAt(i) == "/")
			s = s + "/";
		else {
			if (isNaN(Number(temp.charAt(i))))
				return false;
			else
				s = s + temp.charAt(i);
		}
	}
	d = new Date(s);
	if (d.toString() == "NaN")
		return false;
	return true
}
/**
 * js日期对象转换成yyyy-mm-dd格式的日期字符
 * 
 * @param {Object}
 *            date
 * @return {TypeName}
 */
function dateToString(date) {
	var thisYear = date.getFullYear();
	var thisMonth = date.getMonth() + 1;
	// 如果月份长度是一位则前面补0
	if (thisMonth < 10) {
		thisMonth = "0" + thisMonth;
	}
	var thisDay = date.getDate();
	// 如果天的长度是一位则前面补0
	if (thisDay < 10) {
		thisDay = "0" + thisDay;
	}
	return thisYear + "-" + thisMonth + "-" + thisDay;
}
/**
 * js日期对象转换成yyyy-mm-dd HH:mm:ss格式的日期字符
 * 
 * @param {Object}
 *            date
 * @return {TypeName}
 */
function timeToString(date) {
	var thisYear = date.getFullYear();
	var thisMonth = date.getMonth() + 1;
	// 如果月份长度是一位则前面补0
	if (thisMonth < 10) {
		thisMonth = "0" + thisMonth;
	}
	var thisDay = date.getDate();
	// 如果天的长度是一位则前面补0
	if (thisDay < 10) {
		thisDay = "0" + thisDay;
	}
	var thisHour = date.getHours();
	if (thisHour < 10) {
		thisHour = "0" + thisHour;
	}
	var thisMin = date.getMinutes();
	if (thisMin < 10) {
		thisMin = "0" + thisMin;
	}
	var thisSec = date.getSeconds();
	if (thisSec < 10) {
		thisSec = "0" + thisSec;
	}
	return thisYear + "-" + thisMonth + "-" + thisDay + " " + thisHour + ":" + thisMin + ":" + thisSec;
}
/**
 * yyyy-mm-dd格式的日期转换成js的日期格式
 * 
 * @param {Object}
 *            dateString
 * @return {TypeName}
 */
function stringToDate(dateString) {
	var ss = dateString.split("-");
	if (ss.length == 3) {
		var tmp = dateString.replace(/-/g, "/");
		return new Date(Date.parse(tmp));
	} else {
		alert("时间格式出错");
	}
}

/**
 * 在yyyy-mm-dd格式的日期的基础上加n天
 * 
 * @param {Object}
 *            day ：yyyy-mm-dd格式的日期
 * @param {Object}
 *            n ：加的天数
 * @return {TypeName}
 */
function addDays(day, n) {
	if (checkDate(day)) {
		var d = stringToDate(day);
		d.setDate(d.getDate() + n);
		return dateToString(d);
	} else {
		alert('日期格式不对！');
	}
}
/**
 * 在yyyy-mm-dd格式的日期的基础上加months月
 * 
 * @param {Object}
 *            day yyyy-mm-dd格式的日期
 * @param {Object}
 *            months 加的月数
 * @return {TypeName}
 */
function addMonths(day, months) {
	if (checkDate(day)) {
		var d = stringToDate(day);
		d.setMonth(d.getMonth() + months);
		return dateToString(d);
	} else {
		alert('日期格式不对！');
	}
}
/**
 * 比较两个时间字符串的大小，开始大于结束返回false，否则返回true
 * 
 * @param {Object}
 *            beginDate ，开始时间，YYYY-MM-DD格式的字符串
 * @param {Object}
 *            endDate ，结束时间，YYYY-MM-DD格式的字符串
 * @return {TypeName} 开始大于结束返回false，否则返回true
 */
function compareDate(beginDate, endDate) {
	if (!checkDate(beginDate)) {
		alert('开始时间格式不正确！');
		return false;
	} else if (!checkDate(endDate)) {
		alert('结束时间格式不正确！');
		return false;
	} else {
		var d1 = stringToDate(beginDate);
		var d2 = stringToDate(endDate);
		if (d1 > d2) {
			return false;
		} else
			return true;
	}
}

function fmoney(num) {

	if (isNaN(num)) {

		return "";

	}

	num = num + "";

	if (/^.*\..*$/.test(num)) {

		var pointIndex = num.lastIndexOf(".");

		var intPart = num.substring(0, pointIndex);

		var pointPart = num.substring(pointIndex + 1, num.length);

		intPart = intPart + "";

		var re = /(-?\d+)(\d{3})/

		while (re.test(intPart)) {

			intPart = intPart.replace(re, "$1,$2")

		}

		num = intPart + "." + pointPart;

	} else {

		num = num + "";

		var re = /(-?\d+)(\d{3})/

		while (re.test(num)) {

			num = num.replace(re, "$1,$2")

		}

	}

	return num;
}

/**
 * 级联公共函数
 * 
 * @param url
 *            查询下个下拉框值的action
 * @param value
 *            当前选中下拉框的值
 * @param subId
 *            级联下拉框的select id
 * @param subValue
 *            级联下拉框的value值
 * @param subName
 *            级联下拉框需要展示的值
 */
function commonCascade(url, value, subId, subValue, subName) {
	$.ajax({
		url : ctx + url,
		type : "post",
		dataType : "json",
		cache : false,
		async : false,
		data : {
			value : value
		},
		success : function(data) {
			$("#" + subId).empty();
			var options = "<option value=''>==请选择==</option>";
			$("#" + subId).append("");

			for (var i = 0; i < data.length; i++) {
				var value = "", name = "";
				for (attribute in data[i]) {
					if (subValue == attribute) {
						value = data[i][attribute];
					} else if (subName = attribute) {
						name = data[i][attribute];
					}
				}
				options += "<option value='" + value + "'>" + name
						+ "</option>";
			}
			$("#" + subId).append(options);
		}
	});
}

/**
 * 导入js文件方法
 */
function importJS(arguments) {
	for (var i = 0; i < arguments.length; i++) {
		var file = arguments;
		if (file.match(/\\.js$/i))
			document.write('<script type=\\"text/javascript\\" src=\\"' + file
					+ '\\"></sc' + 'ript>');
		else
			document.write('<style type=\\"text/css\\">@import \\"' + file
					+ '\\" ;</style>');
	}
}

/**
 * 银行的联想方法
 * 
 * @param objId
 */
function associationBankObj(obj) {
	var tags = new Array();
	getBankArray(tags);
	dataAssociation(obj, null, tags, 'obj');
}
/**
 * 获取银行数据
 * 
 * @param tags
 */
function getBankArray(tags) {
	$
			.ajax({
				url : ctx
						+ "/commonUtilsAction_getDataAssociation.action?${sessInfo}&TP_ID=CUST_PBOC_LIST",
				type : "post",
				dataType : "json",
				cache : false,
				async : false,
				success : function(data) {
					for (var i = 0; i < data.length; i++) {
						var obj = new Object();
						obj.value = data[i].BANKID;
						obj.name = data[i].BANKNAME;
						tags.push(obj);
					}
				}
			});
}

/**
 * 店名和电话联想数据
 * 
 * @param objId
 */
function associationCommonName(idName, idValue) {
	var tags = new Array();
	// TO DO 获取所有数据的数组，参照getBankArray函数的写法
	// 店名联想数据
	getArray(tags, "name");
	dataAssociation(idName, idValue, tags);
}

/**
 * 店名和电话联想数据
 * 
 * @param objId
 */
function associationCommonNum(idName, idValue) {
	var tags = new Array();
	// TO DO 获取所有数据的数组，参照getBankArray函数的写法
	// 电话联想数据
	getArray(tags, "number");
	dataAssociationNum(idName, idValue, tags);
}

/**
 * 获取店名数据
 * 
 * @param tags
 */
function getArray(tags, type) {
	$
			.ajax({
				url : ctx
						+ "/fsCustVisitAction_getDataAssociation.action?${sessInfo}&TP_ID=FS_CUST_VISIT_LIST",
				type : "post",
				dataType : "json",
				cache : false,
				async : false,
				success : function(data) {
					for (var i = 0; i < data.length; i++) {
						var obj = new Object();
						obj.value = data[i].FS_ID;
						if (type == "name") {
							obj.name = data[i].CUST_NAME;
							obj.type = "name";
						} else if (type == "number") {
							obj.name = data[i].PHONE_NUM;
							obj.type = "number";
						}
						tags.push(obj);
					}
				}
			});
}

/**
 * 数据输入联想
 * 
 * @param idName
 *            输入框id
 * @param idValue
 *            下发数据的value值
 * @param objects
 *            联想匹配的所有数据
 * @param flag
 *            判断列表页跟表单页带id或者直接带obj
 */
function dataAssociation(idName, idValue, objects, flag) {
	idNameSelect = idName;
	idValueSelect = idValue;
	flagSelect = flag;
	var objName;
	if (flag == 'obj') {
		objName = idName;
	} else {
		objName = $("#" + idName);
	}
	// objects为json数据源对象
	objName.autocomplete(objects, {
		minChars : 0, // 表示在自动完成激活之前填入的最小字符
		max : 10, // 表示列表里的条目数
		autoFill : true, // 表示自动填充
		mustMatch : false, // 表示必须匹配条目,文本框里输入的内容,必须是data参数里的数据,如果不匹配,文本框就被清空
		matchContains : true, // 表示包含匹配,相当于模糊匹配
		scrollHeight : 200, // 表示列表显示高度,默认高度为180

		formatItem : function(row) {
			return row.name;
		},
		formatMatch : function(row) {
			return row.name;
		},
		formatResult : function(row) {
			return row.value;
		}
	});
	$(":text, textarea").result(log).next("#" + idValue).click(function() {
		$(this).prev().search();
	});
}

/**
 * 数据输入联想
 * 
 * @param idName
 *            输入框id
 * @param idValue
 *            下发数据的value值
 * @param objects
 *            联想匹配的所有数据
 * @param flag
 *            判断列表页跟表单页带id或者直接带obj
 */
function dataAssociationNum(idName, idValue, objects, flag) {
	idNameSelectNum = idName;
	idValueSelectNum = idValue;
	flagSelectNum = flag;
	var objName;
	if (flag == 'obj') {
		objName = idName;
	} else {
		objName = $("#" + idName);
	}
	// objects为json数据源对象
	objName.autocomplete(objects, {
		minChars : 0, // 表示在自动完成激活之前填入的最小字符
		max : 10, // 表示列表里的条目数
		autoFill : true, // 表示自动填充
		mustMatch : false, // 表示必须匹配条目,文本框里输入的内容,必须是data参数里的数据,如果不匹配,文本框就被清空
		matchContains : true, // 表示包含匹配,相当于模糊匹配
		scrollHeight : 200, // 表示列表显示高度,默认高度为180

		formatItem : function(row) {
			return row.name;
		},
		formatMatch : function(row) {
			return row.name;
		},
		formatResult : function(row) {
			return row.value;
		}
	});

	$(":text, textarea").result(log).next("#" + idValue).click(function() {
		$(this).prev().search();
	});
}

function log(event, data, formatted) {
	if (flagSelect) {
		idNameSelect.val(data.name);
	} else {
		if (data.type == "name") {
			$("#" + idNameSelect).val(data.name);
			$("#" + idValueSelect).val(data.value);
		} else if (data.type == "number") {
			$("#" + idNameSelectNum).val(data.name);
			$("#" + idValueSelectNum).val(data.value);
		}

	}
}

/**
 * 改变首日还款日重新加载还款计划列表
 * 
 * @param obj
 */
function reLoadData(obj, objId) {
	// alert(obj);
	$
			.ajax({
				url : ctx
						+ "/repaymentPlanAction_calculateRepaymentPlan.action?condition.TP_ID=MARKET_TRAVEL",
				type : "post",
				dataType : "json",
				data : $('#' + objId).serialize(),
				cache : false,
				async : false,
				success : function(data) {
					if (data && data.success == '1') {
						alert("计算失败");
						cust_tb.refresh();
					}
					cust_tb.refresh();
				},
				error : function(request) {
					alert("连接失败");
					cust_tb.refresh();
				}
			});
}

/**
 * 根据行业大类获取行业中类
 * 
 * @param industryClass
 */
function getDivision(industryClass) {
	// alert("11111");
	$
			.ajax({
				url : ctx
						+ "/commonUtilsAction_getDivision.action?condition.TP_ID=CUST_SERVICE_INIT_FORM",
				type : "post",
				dataType : "json",
				data : {
					"industryClass" : industryClass
				},
				cache : false,
				async : false,
				success : function(data) {
					// alert("success");
					$CUST_SERVICE_INIT_LIST_INDUSTRY_DIVISION = $("#CUST_SERVICE_INIT_LIST_INDUSTRY_DIVISION");
					$CUST_SERVICE_INIT_LIST_INDUSTRY_DIVISION.html("");

					var optionHtmlHead = '<option value>请选择</option>';
					$CUST_SERVICE_INIT_LIST_INDUSTRY_DIVISION
							.append(optionHtmlHead);
					var optionHtml;

					for (var i = 0; i < data.length; i++) {
						var code = data[i].ATTRCODE;
						var desc = data[i].ATTRDESC;

						optionHtml = '<option value=' + code + '>' + desc
								+ '</option>';

						$CUST_SERVICE_INIT_LIST_INDUSTRY_DIVISION
								.append(optionHtml);
					}
				},
				error : function(request) {
					alert("连接失败");
				}
			});
}

/**
 * 根据行业大类行业中类获取行业小类
 * 
 * @param industryDivision
 */
function getSection(industryDivision) {
	$
			.ajax({
				url : ctx
						+ "/commonUtilsAction_getSection.action?condition.TP_ID=CUST_SERVICE_INIT_FORM",
				type : "post",
				dataType : "json",
				data : {
					"industryDivision" : industryDivision
				},
				cache : false,
				async : false,
				success : function(data) {
					$CUST_SERVICE_INIT_LIST_INDUSTRY_SECTION = $("#CUST_SERVICE_INIT_LIST_INDUSTRY_SECTION");
					$CUST_SERVICE_INIT_LIST_INDUSTRY_SECTION.html("");

					var optionHtmlHead = '<option value>请选择</option>';
					$CUST_SERVICE_INIT_LIST_INDUSTRY_SECTION
							.append(optionHtmlHead);
					var optionHtml;

					for (var i = 0; i < data.length; i++) {
						var code = data[i].ATTRCODE;
						var desc = data[i].ATTRDESC;

						optionHtml = '<option value=' + code + '>' + desc
								+ '</option>';

						$CUST_SERVICE_INIT_LIST_INDUSTRY_SECTION
								.append(optionHtml);
					}
				},
				error : function(request) {
					alert("连接失败");
				}
			});
}

var ajaxRequest = function(cmdName, params, f$successFn, f$beforeSendFn, f$errorFn) {
	var jsonString = JSON.stringify(params);
	var enjson = encodeURIComponent(jsonString);
	if (params == null) {
		enjson = "";
	}
	$.ajax({
		url : st.apiPath + cmdName,
		timeout : 120000,
		type : "POST",
		dataType : "json",
		data : {
			"cnds" : enjson
		},
		success:f$successFn,
		beforeSend:f$beforeSendFn,
		error:f$errorFn
	});
};

var ajaxSyncRequest = function(cmdName, params, callBack) {
	var jsonString = JSON.stringify(params);
	var enjson = encodeURIComponent(jsonString);
	if (params == null) {
		enjson = "";
	}
	$.ajax({
		url : st.apiPath + cmdName,
		timeout : 120000,
		type : "POST",
		dataType : "json",
		data : {
			"cnds" : enjson
		},
		async: false,
		success : callBack
	});
};

var arrToJson = function(n1, n2, n3, n4) {
	var o1 = document.getElementsByName(n1);
	var o2 = document.getElementsByName(n2);
	var o3 = document.getElementsByName(n3);
	var o4 = document.getElementsByName(n4);
	var arr = [];
	for ( var i in o1) {
		if (undefined != o1[i].value) {
			var tmp = "{'" + n1 + "':'" + o1[i].value + "','" + n2 + "':'"
					+ o2[i].value + "','" + n3 + "':'" + o3[i].value + "','"
					+ n4 + "':'" + o4[i].value + "'}";
			arr.push(tmp);
		}
	}
	return JSON.stringify(arr);
}

var arrToJson2 = function(n1, n2) {
	var o1 = document.getElementsByName(n1);
	if (o1 == null || o1 == undefined) {
		return "";
	}
	var o2 = document.getElementsByName(n2);
	var arr = [];
	for ( var i in o1) {
		if (undefined != o1[i].value) {
			var tmp = "{'" + n1 + "':'" + o1[i].value + "','" + n2 + "':'"
					+ o2[i].value + "'}";
			arr.push(tmp);
		}
	}
	return JSON.stringify(arr);
}

var arrToJson6 = function(n1, n2, n3, n4, n5, n6, n7) {
	var o1 = document.getElementsByName(n1);
	var o2 = document.getElementsByName(n2);
	var o3 = document.getElementsByName(n3);
	var o4 = document.getElementsByName(n4);
	var o5 = document.getElementsByName(n5);
	var o6 = document.getElementsByName(n6);
	var o7 = document.getElementsByName(n7);
	var arr = [];
	for ( var i in o1) {
		if (undefined != o1[i].value) {
			var tmp = "{'" + n1 + "':'" + o1[i].value + "','" + n2 + "':'"
					+ o2[i].value + "','" + n3 + "':'" + o3[i].value + "','"
					+ n4 + "':'" + o4[i].value + "','" + n5 + "':'"
					+ o5[i].value + "','" + n6 + "':'" + o6[i].value + "','"
					+ n7 + "':'" + o7[i].value + "'}";
			arr.push(tmp);
		}
	}
	return JSON.stringify(arr);
}

var arrTjson = function(arr) {
	var condition = {};
	for ( var s in arr) {
		var id = arr[s] + "_cnd";
		condition[id] = $("#" + id).val();
	}
	var b = {};
	b["cnds"] = encodeURIComponent(JSON.stringify(condition))
	return b;
}

function Cmd(cmd, params) {
	this.cmd = cmd;
	this.params = params;
}

var IdentityCodeValid = function(code) {
	var city = {
		11 : "北京",
		12 : "天津",
		13 : "河北",
		14 : "山西",
		15 : "内蒙古",
		21 : "辽宁",
		22 : "吉林",
		23 : "黑龙江 ",
		31 : "上海",
		32 : "江苏",
		33 : "浙江",
		34 : "安徽",
		35 : "福建",
		36 : "江西",
		37 : "山东",
		41 : "河南",
		42 : "湖北 ",
		43 : "湖南",
		44 : "广东",
		45 : "广西",
		46 : "海南",
		50 : "重庆",
		51 : "四川",
		52 : "贵州",
		53 : "云南",
		54 : "西藏 ",
		61 : "陕西",
		62 : "甘肃",
		63 : "青海",
		64 : "宁夏",
		65 : "新疆",
		71 : "台湾",
		81 : "香港",
		82 : "澳门",
		91 : "国外 "
	};
	var tip = "";
	var pass = true;

	if (!code
			|| !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i
					.test(code)) {
		pass = false;
	}

	else if (!city[code.substr(0, 2)]) {
		pass = false;
	} else {
		// 18位身份证需要验证最后一位校验位
		if (code.length == 18) {
			code = code.split('');
			// ∑(ai×Wi)(mod 11)
			// 加权因子
			var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
			// 校验位
			var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
			var sum = 0;
			var ai = 0;
			var wi = 0;
			for (var i = 0; i < 17; i++) {
				ai = code[i];
				wi = factor[i];
				sum += ai * wi;
			}
			var last = parity[sum % 11];
			if (parity[sum % 11] != code[17]) {
				pass = false;
			}
		}
	}
	return pass;
}

// 检验身份证号

var isIdCardNo = function(num) {
	if (num == "" || num.length == 0) {
		return false;
	}
	num = num.toUpperCase();
	if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {
		return false
	}
	var len, re;
	len = num.length;
	re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
	var arrSplit = num.match(re);
	var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
	var bGoodDay;
	bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2]))
			&& ((dtmBirth.getMonth() + 1) == Number(arrSplit[3]))
			&& (dtmBirth.getDate() == Number(arrSplit[4]));
	if (!bGoodDay) {
		return false
	} else {
		var valnum;
		var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8,
				4, 2);
		var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3',
				'2');
		var nTemp = 0, i;
		for (i = 0; i < 17; i++) {
			nTemp += num.substr(i, 1) * arrInt[i]
		}
		valnum = arrCh[nTemp % 11];
		if (valnum != num.substr(17, 1)) {
			return false
		}
		return true
	}
}

function getPayByMonth(dke,ns,nll) {
    var pay = 0;
    var yll = nll / 12;
    pay = (dke * yll * (Math.pow((1 + yll), ns * 12))) / (Math.pow((1 + yll), ns * 12) - 1);
    return pay;
}

//泰安创投计算规则
function getPayByMonth4TACT(applamt,lx,qs) {
    var pay = 0;
    pay = (eval(applamt) + eval(applamt*lx*qs)) / qs ;
    return pay;
}

function getContractAmt(cooperation,val,ns){
	var contract_amt = "";
	if("0" == cooperation){
		 contract_amt = Math.floor(val * 10 / 9);
		 $("#contract_amt").val(contract_amt);
		 var first_pay = eval(contract_amt) - eval(val);
		 $("#first_pay").val(first_pay);
	}else{
		ajaxRequest("queryMonthPay",{"ns" : ns * 12}, function(data){
			if(data.resultNode == "success"){
				var nhl = (data.rows.value) / 100.0;
				var monthly = $("#monthly").val();
				var yll = nhl / 12;
				contract_amt = monthly * (Math.pow((1 + yll), ns * 12) - 1) / (yll * (Math.pow((1 + yll), ns * 12)));
				contract_amt = Math.ceil(contract_amt);
				$("#contract_amt").val(contract_amt);
				var first_pay = eval(contract_amt) - eval(val);
				$("#first_pay").val(first_pay);
			}else{
				$.messager.alert('提示',data.resultNode,'info');
			}
		}); 
	}
}

function countDK(){
	var cooperation = $("#cooperation").val();
	if(cooperation == ""){
		return;
	}
	var val=$("#apply_amt").val();
	var loanProduct=$("#loan_product").val();
	if(val == ""){
		return ;
	}
	if($("#apply_periods").val() == ""){
		return ;
	}
	//定义月息
	var yx = "0.020";
	if($("#apply_periods").val() == '6'){
		yx = "0.020";
	}
	if(loanProduct == '12'){
		yx = "0.035";
	}else if(loanProduct == '14'){
		yx = "0.033";
	}
	var pay = "";
	if(cooperation == "0"){
		getContractAmt(cooperation,val,eval($("#apply_periods").val())/12);
		pay = getPayByMonth($("#contract_amt").val(),eval($("#apply_periods").val())/12,10/100);
		$("#monthly").val(pay.toFixed(2));
	}else{
		pay = getPayByMonth4TACT($("#apply_amt").val(),yx,$("#apply_periods").val());
		$("#monthly").val(pay.toFixed(2));
		getContractAmt(cooperation,val,eval($("#apply_periods").val())/12);
	}
	$("#monthly").val(pay.toFixed(2));
	
	$("#loan_amt").val(Math.floor(eval(yx) * eval($("#apply_periods").val()) * eval(val) + eval(val)));
}

function countDKByYG(){
	var pay = $("#monthly").val();
	var loanProduct = $("#loan_product").val();
	var pay_ratio = $("#pay_ratio").val() == "" ? "0.93" : eval($("#pay_ratio").val()) * 0.01;
	$("#loan_amt").val((Math.floor(pay * $("#apply_periods").val())).toFixed(2));
	if(loanProduct == '6'){
		$("#apply_amt").val((Math.floor(pay * $("#apply_periods").val()) * pay_ratio).toFixed(2));
	}else{
		var par = {
				"qx" : $("#apply_periods").val(),	
				"loanProduct" : loanProduct,	
				"pay" : pay	
		}
		ajaxRequest("querySettleAmt",par, function(data){
			if(data.resultNode == "success"){
				$("#apply_amt").val(data.rows);
			}else{
				$.messager.alert('提示',data.resultNode,'info');
			}
		}); 
	}
}


/**
 * 切换角色
 * @param type
 * @returns
 */
function switchRole(type){
	if($("#loan_product_tmp").val() != "" && $("#loan_product_tmp").val() !=type){
		$.messager.alert('提示',"您在新增时已经选择了产品类型，不可更改，如修改请删除重新添加！！",'info');
		$("#loan_product").val($("#loan_product_tmp").val());
		return;
	}
	if(type=="2" || type=="6" || type=="7" || type=="9" || type=="11" || type=="12" || type == "14"){
		if(type=="7"){
			$("#apply_periods option[value='9']").remove();  
			$("#apply_periods option[value='11']").remove();  
			/*$("#apply_periods option[value='12']").remove();  */
			$("#apply_periods option[value='15']").remove();  
			$("#apply_periods option[value='18']").remove();  
			$("#apply_periods option[value='24']").remove();  
		}
		$("#school_desc").html('工作单位:<font color="red">*</font>');
		$("#stu_desc").html('部门:<font color="red">*</font>');
		$("#stu_no_desc").html('职务:<font color="red">*</font>');
		$("#bdr_addr_desc").html('门牌号:<font color="red">*</font>');
		$("#school_job_desc").html('其他收入:<font color="red">*</font>');
		$("#monthly_amt_desc").html('月薪:<font color="red">*</font>');
		$("#entra_date_desc").html('入职时间:<font color="red">*</font>');
		if(type=="9" || type=="11"){
			$("#addressDesc").html('联系地址:<font color="red">*</font>');
			$("#apply_periods").empty();
			$("#apply_periods").append("<option value='9'>9</option>");
			$("#apply_periods").append("<option value='12'>12</option>");
			$("#apply_periods").append("<option value='15'>15</option>");
			$("#apply_periods").append("<option value='18'>18</option>");
			$("#apply_periods").append("<option value='24'>24</option>");
		}
		$("#search1").hide();
		$("#school_name").removeAttr("readonly");
		$("#cnt_type_10").find("option:selected").text("办公室电话");
		$("#ship_type_2").find("option:selected").text("朋友1");
		$("#ship_type_3").find("option:selected").text("朋友2");
		$("#tr4").show();
		$("#tr5").show();
		
		// 骑手分期是6 9 12
		if(type=="14"){
			$("#addressDesc").html('联系地址:<font color="red">*</font>');
			$("#item_code").val("电瓶车分期");
			$("#apply_periods").empty();
			$("#apply_periods").append("<option value='6'>6</option>");
			$("#apply_periods").append("<option value='9'>9</option>");
			$("#apply_periods").append("<option value='12'>12</option>");
			$("#ship_type_0").find("option:selected").text("第一直系亲属");
			$("#ship_type_1").find("option:selected").text("第二直系亲属");
			$("#ship_type_2").find("option:selected").text("配偶");
			$("#ship_type_3").find("option:selected").text("朋友");
			$("#ship_type_4").find("option:selected").text("同事");
			$("#adiv").hide();
		}
		
		// 盛典只做9 12
		if(type=="12"){
			$("#addressDesc").html('联系地址:<font color="red">*</font>');
			$("#apply_periods").empty();
			$("#apply_periods").append("<option value='12'>12</option>");
			$("#apply_amt").val("10000.00");
			$("#apply_amt").attr("readonly","true");
			$("#item_code").val("超好借");
			$("#cnt_type_12").find("option:selected").text("社保账号");
			//计算一下结果
			countDK();
			//$("#adiv").hide();
		}else if(type=="14"){
			$("#cnt_type_11").find("option:selected").text("社保账号");
		}else{
			$("#cnt_type_11").find("option:selected").text("社保账号");
			$("#adiv").show();
		}
	}else{
		if(type=="8"){
			$("#apply_periods option[value='3']").remove();  
			$("#apply_periods option[value='15']").remove();  
			$("#apply_periods option[value='18']").remove();  
			$("#apply_periods option[value='24']").remove();  
			$("#apply_periods option[value='11']").remove();
			$("#splx").html('借款用途:<font color="red">*</font>');
		}
		$("#school_desc").html('学校:<font color="red">*</font>');
		$("#stu_desc").html('班级:<font color="red">*</font>');
		$("#stu_no_desc").html('学号:<font color="red">*</font>');
		$("#bdr_addr_desc").html('寝室号:<font color="red">*</font>');
		$("#school_job_desc").html('在校职位:<font color="red">*</font>');
		$("#monthly_amt_desc").html('生活费:<font color="red">*</font>');
		$("#entra_date_desc").html('入校时间:<font color="red">*</font>');
		$("#search1").show();
		$("#school_name").attr("readonly","readonly");
		$("#cnt_type_10").find("option:selected").text("教务管理系统");
		$("#cnt_type_11").find("option:selected").text("学信网");
		$("#ship_type_2").find("option:selected").text("室友");
		$("#ship_type_3").find("option:selected").text("辅导员");
		$("#tr4").hide();
		$("#tr5").hide();
		if(type=="3"){
			$("#adiv").show();
		}else{
			$("#adiv").hide();
		}
	}
}

/**
 * 切换角色
 * @param type
 * @returns
 */
function switchRole_dy(type){
	if($("#loan_product_tmp").val() != "" && $("#loan_product_tmp").val() !=type){
		$.messager.alert('提示',"您在新增时已经选择了产品类型，不可更改，如修改请删除重新添加！！",'info');
		$("#loan_product").val($("#loan_product_tmp").val());
		return;
	}
	if(type=="2" || type=="6" || type=="7" || type=="9" || type=="11" || type=="12" || type=="14"){
		if(type=="7"){
			$("#apply_periods option[value='9']").remove();  
			$("#apply_periods option[value='11']").remove();  
			/*$("#apply_periods option[value='12']").remove();  */
			$("#apply_periods option[value='15']").remove();  
			$("#apply_periods option[value='18']").remove();  
			$("#apply_periods option[value='24']").remove();  
		}
		$("#school_desc").html('工作单位:<font color="red">*</font>');
		$("#stu_desc").html('部门:<font color="red">*</font>');
		$("#stu_no_desc").html('职务:<font color="red">*</font>');
		$("#bdr_addr_desc").html('门牌号:<font color="red">*</font>');
		$("#school_job_desc").html('其他收入:<font color="red">*</font>');
		$("#monthly_amt_desc").html('月薪:<font color="red">*</font>');
		$("#entra_date_desc").html('入职时间:<font color="red">*</font>');
		if(type=="9"){
			$("#addressDesc").html('联系地址:<font color="red">*</font>');
			$("#apply_periods").empty();
			$("#apply_periods").append("<option value='9'>9</option>");
			$("#apply_periods").append("<option value='12'>12</option>");
			$("#apply_periods").append("<option value='15'>15</option>");
			$("#apply_periods").append("<option value='18'>18</option>");
			$("#apply_periods").append("<option value='24'>24</option>");
		}
		// 盛典只做9 12
		if(type=="11"){
			$("#addressDesc").html('联系地址:<font color="red">*</font>');
			$("#apply_periods").empty();
			$("#apply_periods").append("<option value='9'>9</option>");
			$("#apply_periods").append("<option value='12'>12</option>");
		}
		// 骑手分期是6 9 12
		if(type=="14"){
			$("#addressDesc").html('联系地址:<font color="red">*</font>');
			$("#apply_periods").empty();
			$("#apply_periods").append("<option value='6'>6</option>");
			$("#apply_periods").append("<option value='9'>9</option>");
			$("#apply_periods").append("<option value='12'>12</option>");
		}
		// 盛典只做9 12
		if(type=="12"){
			$("#addressDesc").html('联系地址:<font color="red">*</font>');
			$("#apply_periods").empty();
			$("#apply_periods").append("<option value='12'>12</option>");
			$("#cnt_type_12").find("option:selected").text("社保账号");
		}else{
			$("#cnt_type_11").find("option:selected").text("社保账号");
		}
		$("#search1").hide();
		$("#school_name").removeAttr("readonly");
		$("#cnt_type_10").find("option:selected").text("办公室电话");
		$("#ship_type_2").find("option:selected").text("朋友1");
		$("#ship_type_3").find("option:selected").text("朋友2");
		$("#tr4").show();
		$("#tr5").show();
		$("#adiv").show();
	}else{
		if(type=="8"){
			$("#apply_periods option[value='3']").remove();  
			$("#apply_periods option[value='15']").remove();  
			$("#apply_periods option[value='18']").remove();  
			$("#apply_periods option[value='24']").remove();  
			$("#apply_periods option[value='11']").remove();
			$("#splx").html('借款用途:<font color="red">*</font>');
		}
		/*$("#school_desc").html('学校:<font color="red">*</font>');
		$("#stu_desc").html('班级:<font color="red">*</font>');
		$("#stu_no_desc").html('学号:<font color="red">*</font>');
		$("#bdr_addr_desc").html('寝室号:<font color="red">*</font>');
		$("#school_job_desc").html('在校职位:<font color="red">*</font>');
		$("#monthly_amt_desc").html('生活费:<font color="red">*</font>');
		$("#entra_date_desc").html('入校时间:<font color="red">*</font>');
		$("#search1").show();
		$("#school_name").attr("readonly","readonly");
		$("#cnt_type_10").find("option:selected").text("教务管理系统");
		$("#cnt_type_11").find("option:selected").text("学信网11");
		$("#ship_type_2").find("option:selected").text("室友");
		$("#ship_type_3").find("option:selected").text("辅导员");*/
		$("#tr4").hide();
		$("#tr5").hide();
		if(type=="3"){
			$("#adiv").show();
		}else{
			$("#adiv").hide();
		}
	}
}



$.extend({
	isEmpty:function(value, allowEmptyString) {
		return (null==value)
			||(undefined==value)
			||(!allowEmptyString ? ''==value.toString() : false)
			||($.isArray(value)&&0==value.toString().length);
	}
});

$.extend($, {
	ERROR:'100001',
	SUCCESS:'100002'
});

$.extend($.fn.datagrid.methods, {
	editCell:function(j$objJq, parms) {
		return j$objJq.each(function() {
			var opts = $(this).datagrid('options');
			var fields = $(this).datagrid('getColumnFields', true).concat($(this).datagrid('getColumnFields'));
			for (var i = 0; i < fields.length; i++) {
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor1 = col.editor;
				if (fields[i]!=parms.field) col.editor = null;
			}
			$(this).datagrid('beginEdit', parms.index);
			for(var i = 0; i < fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor = col.editor1;
			}
		});
	}
});

/**
 * 金额千分位格式化
 * @param s
 * @returns
 */
function toThousands(num) {
	if(num=='' || num == null){
		return "0";
	}
	num = num.toString().replace(/\$|\,/g,'');
	if(isNaN(num))
		num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num*100+0.50000000001);
	cents = num%100;
	num = Math.floor(num/100)+"";
	if(cents<10)
		cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		num = num.substring(0,num.length-(4*i+3))+','+
	num.substring(num.length-(4*i+3));
	return (((sign)?'':'-') + num + '.' + cents);
}