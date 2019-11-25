/**
 * Created with Eclipse. User: ruizhang Date: 14-3-29 Time: 下午4:34
 */
(function($) {

	$.fn.serializeProps = function() {
		var da = $(this).serializeArray();
		var ob = {};
		_.each(da, function(v, k) {
			var name = $.trim(v.name);
			var value = $.trim(v.value);

			if (_.isUndefined(ob[name])) {
				ob[name] = value;
			} else {
				if (!(value === "")) {
					ob[name] += ',' + value;
				}
			}
		});
		return ob;
	};

	$.fn.serializeObject = function() {
		var da = $(this).serializeArray();
		var ob = {};
		_.each(da, function(v, k) {
			var name = $.trim(htmlEncodeJQ(v.name));
			var value = $.trim(htmlEncodeJQ(v.value));

			if (_.isUndefined(ob[name])) {
				ob[name] = value;
			} else {
				if (typeof ob[name] === "array") {
					ob[name].push(value);
				} else {
					if (!(value === "")) {
						var arr = [ ob[name], value ];
						ob[name] = arr;
					}
				}
			}
		});
		return ob;
	};

    $.fn.serializeObject1 = function()
    {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(htmlEncodeJQ(this.value) || '');
            } else {
                o[this.name] = htmlEncodeJQ(this.value) || '';
            }
        });
        return o;
    };

	$.fn.resetOption = function(data, valueName, textName, flag) {
		return this.each(function() {
			var thiz = $(this);
			if (thiz.is('select')) {
				thiz.empty();
				if (!flag) {
					thiz.append($('<option value="">-- 请选择 --</option>'));
				}
				$.each(data, function(k, v) {
					var value = valueName ? v[valueName] : k;
					var text = textName ? v[textName] : v;
					thiz.append($('<option value="' + value + '">' + text
							+ '</option>'));
				});
			}
		});
	};

	$.fn.fillByName = function(data, keyfn, valuefn) {
		var dom = this;
		data = data || {};
		$.each(data, function(k, v) {
			var tk = keyfn ? keyfn(k) : k;
			var tv = valuefn ? valuefn(v) : v;
			var it = dom.find("[name='" + tk + "']");
			if (it.length > 0) {
				if (it.is('input') || it.is('select')) {
					it.val(tv);
				} else {
					var text = $.et.cache(tk + tv) || tv;
					it.html((!_.isUndefined(text)) ? text : '&nbsp;');
				}
			}
		});
		return dom;
	};

	$.fn.attrs = function() {
		var dom = this[0] || {
			attributes : []
		};
		var __attrs = dom.attributes;
		var res = {};
		$.each(__attrs, function(k, v) {
			res[v.name] = v.value;
		});
		return res;
	};

})(jQuery);

// -----------------------------------------------------------------------------

(function($) {

	var byId = function(id) {
		return document.getElementById(id);
	};
	var byId$ = function(id) {
		return $(document.getElementById(id));
	};

	var formItem = function(name, type, def, data) {
		var item = "";
		var div = $("<div />");

		if (type == "text") {
			item = "<input id='" + name
					+ "' type='text' style='width: 100%' name='" + name + "'";
			if (!_.isUndefined(def)) {
				item += " value='" + def + "'";
			}
			item += " />";
		} else if (type == "textarea") {

		} else if (type == "select") {
			item = "<select id='" + name + "'  style='width: 100%' name='"
					+ name + "'";
			if (!_.isUndefined(def)) {
				item += " value='" + def + "'";
			}
			item += "> <option value=''>-- 请选择 --</option>";
			if (data) {
				$.each(data, function(k, v) {
					item += "<option value='" + k + "'>" + v + "</option>";
				});
			}
			item += "</select>";

		} else if (type == "datepicker") {
			item = "<input id='"
					+ name
					+ "' type='text' style='width: 50%' onclick='WdatePicker()' class='Wdate' readonly='readonly' name='"
					+ name + "'";
			if (!_.isUndefined(def)) {
				item += " value='" + def + "'";
			}
			item += " />";
		} else if (type == "span") {

		}
		div.append($(item));
		return div.html();
	};

	var __cache = {};

	var cache = function(arg, kn, vn) {
		if (typeof arg === 'object') {
			if (kn === undefined) {
				__cache = $.extend(__cache, arg);
			} else {
				$.each(arg, function(k, v) {
					var key = v[kn];
					var text = v[vn];
					__cache[key] = text;
				});
			}
		} else {
			return __cache[arg];
		}
	};

	var selectCache = function() {
		$("select").each(function(k, v) {
			var select = $(v);
			var name = select.attr('name');
			if (name) {
				select.find('option').each(function(k, v) {
					var option = $(v);
					var key = option.val();
					var value = option.html();
					if (key && value) {
						__cache[name + key] = value;
					}
				});
			}
		});
	};

	var openWindow = function(url) {
		var screenWidth = screen.availWidth - 8;
		var screenHeight = screen.availHeight;
		var args = "width="
				+ screenWidth
				+ ",height="
				+ screenHeight
				+ ",top=0,left=0,titlebar=no,alwaysRaised=no,location=no,Resizable=no,scrollbars=yes";
		window.open(url, "showDetail", args);
	};

	var defaults = {
		DATAGRID_OPTIONS_EASY_QUERY : {
			data:[],
			queryParams : {},
			method : 'post',
			resizeHandle : 'both',
			autoRowHeight : true,
			striped : true,
			nowrap : true,
			fitColumns : true,
			pagination : true,
			pagePosition : 'bottom',
			pageNumber : 1,
			pageSize : 10,
			pageList : [10],
			rownumbers : true,
			singleSelect : true,
			checkOnSelect : false,
			selectOnCheck : true,
			sortName : null,
			sortOrder : 'asc',
			multiSort : false,
			remoteSort : false,
			loadMsg : 'Please wait...',
			showHeader : true,
			showFooter : true,
			scrollbarSize : 18
		},
		DATAGRID_OPTIONS_EASY_MULTIPLE_SELECT_QUERY : {
			data:[],
			queryParams : {},
			method : 'post',
			resizeHandle : 'both',
			autoRowHeight : true,
			striped : true,
			nowrap : true,
			fitColumns : false,
			rownumbers : true,
			singleSelect : false,
			checkOnSelect : false,
			selectOnCheck : true,
			sortName : null,
			sortOrder : 'asc',
			multiSort : false,
			remoteSort : false,
			loadMsg : 'Please wait...',
			showHeader : true,
			showFooter : true,
			scrollbarSize : 18
		},
		DATAGRID_OPTIONS_EASY_FORM_TABLE : {
			queryParams : {},
			method : 'post',
			resizeHandle : 'both',
			autoRowHeight : true,
			striped : true,
			nowrap : true,
			fitColumns : false,
			pagination : false,
			pagePosition : 'bottom',
			pageNumber : 1,
			pageSize : 1,
			pageList : [10],
			rownumbers : true,
			singleSelect : true,
			checkOnSelect : false,
			selectOnCheck : true,
			sortName : null,
			sortOrder : 'asc',
			multiSort : false,
			remoteSort : false,
			loadMsg : 'Please wait...',
			showHeader : true,
			showFooter : false,
			scrollbarSize : 18,
			onSelect : function(rowIndex, rowData) {
				$(this).datagrid('unselectRow', rowIndex);
			}
		},
		DATAGRID_COLUMN_DEFAULT_OPTIONS : {
			field : "",
			fuzzy_condition : "text",
			title : "",
			width : 100,
			sortable : true,
			headalign : 'center',
			align : 'center'
		}

	};

	var __grid_props_map = {
		columns : 'columns',
		editors : 'editors',
		fitcolumns : 'fitColumns',
		frozencolumns : 'frozenColumns',
		idfield : 'idField',
		loadfilter : 'loadFilter',
		loadmsg : 'loadMsg',
		method : 'method',
		nowrap : 'nowrap',
		onafteredit : 'onAfterEdit',
		onbeforeedit : 'onBeforeEdit',
		onbeforeload : 'onBeforeLoad',
		oncanceledit : 'onCancelEdit',
		onclickcell : 'onClickCell',
		onclickrow : 'onClickRow',
		ondblclickcell : 'onDblClickCell',
		ondblclickrow : 'onDblClickRow',
		onheadercontextmenu : 'onHeaderContextMenu',
		onloaderror : 'onLoadError',
		onloadsuccess : 'onLoadSuccess',
		onresizecolumn : 'onResizeColumn',
		onrowcontextmenu : 'onRowContextMenu',
		onselect : 'onSelect',
		onselectall : 'onSelectAll',
		onsortcolumn : 'onSortColumn',
		onunselect : 'onUnselect',
		onunselectall : 'onUnselectAll',
		pagelist : 'pageList',
		pagenumber : 'pageNumber',
		pagesize : 'pageSize',
		pagination : 'pagination',
		queryparams : 'queryParams',
		remotesort : 'remoteSort',
		rownumbers : 'rownumbers',
		rowstyler : 'rowStyler',
		showfooter : 'showFooter',
		singleselect : 'singleSelect',
		sortname : 'sortName',
		sortorder : 'sortOrder',
		striped : 'striped',
		url : 'url',
		view : 'view'
	};

	var attrs_to_grid_options = function(attrs) {
		var options = {};
		$.each(attrs, function(k, v) {
			var __k = __grid_props_map[k] || k;
			options[__k] = v;
		});
		return options;
	};

	var req_common = function(data, scb, fcb) {
		$.ajax({
			type : "post",
			url : (ctx || '') + "/et/commonAction.action?" + (sessInfo || ''),
			data : data,
			dataType : "json",
			success : scb,
			error : fcb || function() {
				alert('发生错误，请与管理员联系！');
			}
		});
	};
	var req_common_sync = function(data) {
		var res;
		$.ajax({
			type : "post",
			url : (ctx || '') + "/et/commonAction.action?" + (sessInfo || ''),
			data : data,
			async : false,
			dataType : "json",
			success : function(result) {
				res = result;
			},
			error : function() {
				alert('发生错误，请与管理员联系！');
				res = {};
			}
		});
		return res;
	};

	var req_list_meta_sync = function(rid) {
		var res;
		$.ajax({
			type : "post",
			url : (ctx || '') + "/et/listQueryAction_meta.action?"
					+ (sessInfo || ''),
			data : {
				rid : rid
			},
			async : false,
			dataType : "json",
			success : function(result) {
				res = result;
			},
			error : function() {
				alert('发生错误，请与管理员联系！');
				res = {};
			}
		});
		return res;
	};

	var formGrid = function(select, options) {
		return grid(select, options, defaults.DATAGRID_OPTIONS_EASY_FORM_TABLE);
	};

	var multipleSelectGrid = function(select, options) {
		return grid(select, options,
				defaults.DATAGRID_OPTIONS_EASY_MULTIPLE_SELECT_QUERY);
	};

	var grid = function(select, options, defOptions) {

		var grid;
		if (select instanceof jQuery) {
			grid = select;
		} else {
			grid = $(select);
		}

		var ready = false;
		var gridOptions = _.extend({}, defOptions
				|| defaults.DATAGRID_OPTIONS_EASY_QUERY, options);
		var onLoadSuccess = gridOptions.onLoadSuccess;
		var onLoadError = gridOptions.onLoadError;
		var onBeforeLoad = gridOptions.onBeforeLoad;
		var onClickRow = gridOptions.onClickRow;
		var onDblClickRow = gridOptions.onDblClickRow;
		var onClickCell = gridOptions.onClickCell;
		var onDblClickCell = gridOptions.onDblClickCell;
		var onSelect = gridOptions.onSelect;
		var onUnselect = gridOptions.onUnselect;

		gridOptions = $.extend(gridOptions, {
			onLoadSuccess : function(data) {
				if (typeof onLoadSuccess === "function") {
					onLoadSuccess(data);
				}
				grid.trigger("onLoadSuccess", arguments);
			},
			onLoadError : function() {
				if (typeof onLoadError === "function") {
					onLoadError();
				}
				grid.trigger("onLoadError", arguments);
			},
			onBeforeLoad : function(param) {
				if (typeof onBeforeLoad === "function") {
					onBeforeLoad(param);
				}
				grid.trigger("onBeforeLoad", arguments);
			},
			onClickRow : function(rowIndex, rowData) {
				if (typeof onClickRow === "function") {
					onClickRow(rowIndex, rowData);
				}
				grid.trigger("onClickRow", arguments);
			},
			onDblClickRow : function(rowIndex, rowData) {
				if (typeof onDblClickRow === "function") {
					onDblClickRow(rowIndex, rowData);
				}
				grid.trigger("onDblClickRow", arguments);
			},
			onClickCell : function(rowIndex, field, value) {
				if (typeof onClickCell === "function") {
					onClickCell(rowIndex, field, value);
				}
				grid.trigger("onClickCell", arguments);
			},
			onDblClickCell : function(rowIndex, field, value) {
				if (typeof onDblClickCell === "function") {
					onDblClickCell(rowIndex, field, value);
				}
				grid.trigger("onDblClickCell", arguments);
			},
			onSelect : function(rowIndex, rowData) {
				if (typeof onSelect === "function") {
					onSelect(rowIndex, rowData);
				}
				grid.trigger("onSelect", arguments);
			},
			onUnselect : function(rowIndex, rowData) {
				if (typeof onUnselect === "function") {
					onUnselect(rowIndex, rowData);
				}
				grid.trigger("onUnselect", arguments);
			}
		});

		grid.setQueryParams = function(options) {
			gridOptions.queryParams = $.extend({}, options);
			return grid;
		};
		grid.refresh = function() {
			grid.datagrid(gridOptions);
			afterCommonQueryInit();
			ready = true;
			return grid;
		};
		grid.query = function(options) {
			grid.setQueryParams(options);
			if (ready) {
				grid.datagrid('load', gridOptions.queryParams);
			} else {
				grid.refresh();
			}

			return grid;
		};
		grid.loadData = function(data) {
			if (data) {
				gridOptions.loadData = data;
			}

			// console.log(gridOptions.loadData);
			if (!ready) {
				grid.refresh();
			}
			grid.datagrid('loadData', gridOptions.loadData);
			return grid;
		};

		return grid;
	};

	function __idName(column, suffix) {
		var idName = "";
		suffix = suffix || "";
		if (column.condition_id) {
			idName += (" id='" + column.condition_id + suffix + "_cnd' ");
		}

		if (column.condition_name) {
			idName += (" id='" + column.condition_name + suffix + "_cnd' ");
		} else {
			idName += (" id='" + column.field + suffix + "_cnd' ");
		}
		return idName;
	}

	function __buildSelect(column, suffix) {

		var select = "<select " + __idName(column, suffix)
				+ " style='width:100%;' />";
		var data = column.condition_data || {};
		var opt = column.condition_options || {};
		if (_.isFunction(data)) {
			data = data() || {};
		}
		data = data[suffix] || data;

		var res = $(select).resetOption(data, opt.listKey, opt.listValue,
				opt.flag);

		// console.log(res);

		return $("<div />").append(res).html();
	}

	function __addSearchFieldToTitle(column) {

		var condition_item = "";

		switch (column.fuzzy_condition) {
		case "text":
			condition_item = "<input " + __idName(column)
					+ " type='text' class='layui-input' style='width:100%;' />";
			break;
		case "text_inteval":
			condition_item = "<input " + __idName(column, "_FROM")
					+ " type='text' class='layui-input' style='width:40%;' /> - <input "
					+ __idName(column, "_TO")
					+ " type='text' class='layui-input' style='width:40%;' />";
			break;
		case "date":
			condition_item = "<input "
					+ __idName(column)
					+ " onclick='WdatePicker()' class='Wdate' readonly='readonly' type='text' style='width:100%;' />";
			break;
		case "exact_date":
			condition_item = "<input "
				+ __idName(column)
				+ " onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\" class='Wdate' readonly='readonly' type='text' style='width:100%;' />";
			break;
		case "date_inteval":
			condition_item = "<input "
					+ __idName(column, "_FROM")
					+ " onclick=WdatePicker() class='Wdate' readonly='readonly' type='text' style='width:45%;' /> - <input "
					+ __idName(column, "_TO")
					+ " onclick='WdatePicker()' class='Wdate' readonly='readonly' type='text' style='width:45%;' />";
			break;
		case "ref":
			condition_item = column.condition_ref ? $(column.condition_ref)
					.html() : "&nbsp;";
			break;
		case "select":
			condition_item = __buildSelect(column);
			break;
		default:
			if (_.isFunction(column.fuzzy_condition)) {
				var v = column.fuzzy_condition();
				if (typeof v === "string") {
					condition_item = v;
				} else {
					condition_item = "&nbsp;";
				}
			} else {
				if (typeof column.fuzzy_condition === "string") {
					condition_item = column.fuzzy_condition;
					if(""===column.fuzzy_condition){
						var title = "<div  style='height:15px;'>"
							+ "<div class='et-common-query-title'>"
							+ column.title
							+ " <span class='datagrid-sort-icon et-common-sort-icon' /> </div>"
							+ "</div>";
					return title;
					 }
				} else {
					condition_item = "&nbsp;";
				}
			}
			break;
		}

		var title = "<div>"
				+ "<div class='et-common-query-title'>"
				+ column.title
				+ " <span class='datagrid-sort-icon et-common-sort-icon' /> </div>"
				+ "<div class='et-common-query-condition'>" + condition_item
				+ "</div>" + "</div>";
		return title;
	}
	;

	var commonQuery = function(select, options) {
		if (!options.no_fuzzy_condition) {
			var columns = _.flatten(options.columns);
			$.each(columns, function(k, v) {
				var x = _.clone(v);
				$.extend(v,$.et.defaults.DATAGRID_COLUMN_DEFAULT_OPTIONS,x);
				// if(v.fuzzy_condition){
				if (v.fc) {
					v.fuzzy_condition = v.fc;
				}
				if (v.fc_ref) {
					v.condition_ref = v.fc_ref;
				}
				if (v.fc_data) {
					v.condition_data = v.fc_data;
				}
				if (v.fc_opt) {
					v.condition_options = v.fc_opt;
				}
				v.title = __addSearchFieldToTitle(v);
				// }
			});
			$(select).addClass("et-common-query");
		}
		return grid(select, options);
	};

	var commonForm = function(select) {
		var form = $(select);

		form.data = function() {
			return form.serializeObject();
		};

		form.val = function(name, value) {
			var res = form.serializeObject();
			if (name === undefined && value === undefined) {

			} else if (value === undefined) {
				res = res[name];
			} else {
				var fields = form.find("[name='"+name+"']");
				if(fields.length>0){
					$(fields[0]).val(value);
				}
			}

			return res;
		};

		return form;
	};

	var commonQueryForm = function(select, options) {
		var form = commonForm(select);

		if (!form.tbid) {
			form.tbid = 'tb' + _.now();
			var tb = $("<table id=" + form.tbid + " />");
			form.append(tb);
		}

		var grid = commonQuery("#" + form.tbid, options);

		form.grid = grid;

		form.query = function(opts) {
			var data = $.extend({}, form.serializeProps(), opts);
			grid.query(data);
			return form;
		};

		return form;
	};

	var afterCommonQueryInit = function() {
		$(".et-common-query-condition").unbind('click').click(function() {
			return false;
		});
		$(".et-common-query").parent("div.datagrid-view").addClass(
				"et-common-query-datagrid-view");
	};

	var form = function(select, options) {
		var form = $(select);

		return form;
	};

	var box = function(select, options) {

	};

	var showButton = function(tpid) {
		var url = ctx + "/example/codeExampleAction_addEnterprise.action?"
				+ sessInfo + "&condition.TP_ID=" + tpid;
		$.post(url, null, function(dd) {
			var data = dd.rows;
			$.each(data, function(i) {
				var id = data[i].TP_ID + "_" + data[i].SOURCE_NAME;
				if ("1" == data[i].SOURCE_TYPE) {
					$("#" + id).attr({
						disabled : 'true'
					});
				} else if ("2" == data[i].SOURCE_TYPE) {
					$("#" + id).hide();
				} else {
					$("#" + id).removeAttr("disabled");
					$("#" + id).css("visibility", "visible");
				}
			});
		}, "json");
	};

	var stmp = {};

	var __ran = {};

	var ready = function(f, args) {
		$(runOnce(f, args));
	};
	var runOnce = function(func) {
		var memo;
		if (_.isFunction(func)) {
			return function() {
				if (!__ran[func]) {
					__ran[func] = _.now();
					memo = func.apply(this, arguments);
				}
				func = null;
				return memo;
			};
		}
	};

	var __evalProps = function(obj) {
		var res = {};
		$.each(obj, function(k, v) {
			if (typeof v == "string") {
				try {
					res[k] = eval("(" + v + ")");
				} catch (e) {
					res[k] = v;
				}
			} else {
				res[k] = v;
			}
		});
		return res;
	};

	var __components = {};

	var get = function(id) {
		return __components[id];
	};

	var set = function(id, component) {
		__components[id] = component;
	};

	$.et = {
		commonQuery : commonQuery,
		commonQueryForm : commonQueryForm,
		afterCommonQueryInit : afterCommonQueryInit,
		multipleSelectGrid : multipleSelectGrid,
		formGrid : formGrid,
		grid : grid,

		form : form,
		box : box,

		evalProps : __evalProps,

		cache : cache,
		selectCache : selectCache,

		req_common : req_common,
		req_common_sync : req_common_sync,
		req_list_meta_sync : req_list_meta_sync,

		byId$ : byId$,
		byId : byId,

		formItem : formItem,

		openWindow : openWindow,

		defaults : defaults,

		showButton : showButton,

		stmp : stmp,
		runOnce : runOnce,
		ready : ready,

		attrs_to_grid_options : attrs_to_grid_options,

		get : get,
		set : set
	};

})(jQuery);

$.et.ready(function() {

	$("et-list").each(function(k, dom) {
		(function(dom) {
			var jqDom = dom instanceof $ ? dom : $(dom);
			var DEF_COL_OPT = $.et.defaults.DATAGRID_COLUMN_DEFAULT_OPTIONS;

			var etList = $.et.evalProps(jqDom.attrs());

			var etId = etList["et_id"];
			var context = $.et.get(etId);

			if (!context) {
				var gridOptions = $.et.attrs_to_grid_options(etList);

				var columnsOptions = [];

				jqDom.find("et-field").each(function(k, v) {
					var options = $.et.evalProps($(v).attrs());
					columnsOptions.push($.extend({}, DEF_COL_OPT, options));
				});

				var listOptions = {};

				listOptions = $.extend({}, {
					columns : [ columnsOptions ]
				}, gridOptions);

				var id = new Date().getTime();
				var formId = 'FORM_' + id;

				var forms = jqDom.find("form");
				var form;
				if (forms.length > 0) {
					form = $(forms[0]);
				} else {
					form = $("<form id='" + formId + "' />");
					jqDom.append(form);
				}

				var listForm = $.et.commonQueryForm(form, listOptions);
				context = listForm;

				$.et.set(etId, context);

			}
			;
		}(dom));

	});
	

});