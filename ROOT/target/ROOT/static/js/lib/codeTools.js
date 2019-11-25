var __operatorHandlers = {};
registerOperatorHandler = function(idcard, handler) {
	__operatorHandlers[idcard] = handler;
};
opertorSwitch = function(obj, operType, idcard) {
	var handler = __operatorHandlers[idcard];
	if (typeof handler === "object") {
		var action = handler[operType];
		switch (operType) {
		case "del":
			$.messager.confirm("操作提示", "确定删除吗？", function (r) {
				if(r){
					if (typeof action === "function") {
						action();
					}
				}
			});
			break;
		case "edit":
			$.messager.confirm("操作提示", "确定修改吗？", function (r) {
				if(r){
					opt.optText(idcard);
					if (typeof action === "function") {
						action();
					}
				}
			});
			break;
		default:
			if (typeof action === "function") {
				action();
			}
			break;
		}
	}
};


var opt = {
	/**
	 * 组织模糊查询的查询条件
	 */
	fuzzy:function(arr){
		var condition = {};
		for(var s in arr){
			condition["condition."+arr[s]] = $("#"+arr[s]).val();
		}
		return condition;
	},
	/**
	 * 点击“返回”的操作
	 */
	goback:function(){
		window.close();
	},
	/**
	 * 打开表单
	 */
	optText:function(idcard){
		this.optTable(idcard,true);
		this.hideButton(idcard + "_QUERYS", true);
		this.hideButton(idcard + "_EDITS", true);
		this.hideButton(idcard + "_DELETES", true);
		this.hideButton(idcard + "_ADDS", true);
		this.hideButton(idcard + "_SAVE", false);
		this.hideButton(idcard + "_BACK", false);
		return true;
	},
	optTable:function(idcard,isEdit){
		if(isEdit){
			var id = idcard + "_INPUT";
			$("div[class='" + id + "']").css('display', 'block');
			var id = idcard + "_DIV";
			$("div[class='" + id + "']").css('display', 'none');
		}else{
			var id = idcard + "_INPUT";
			$("div[class='" + id + "']").css('display', 'none');
			var id = idcard + "_DIV";
			$("div[class='" + id + "']").css('display', 'block');
		}
	},
	/**
	 * 对按钮的隐藏等操作
	 */
	hideButton:function(id,f){
		if (f) {
			$("#" + id).css("visibility", "hidden");
		} else {
			$("#" + id).css("visibility", "visible");
		}
	},
	/**
	 * 操作保存按钮的CSS样式的变化
	 * hideOrShow:为true时则是打开保存按钮，false：隐藏保存
	 */
	optSaveCss:function(idcard,hideOrShow){
		this.optTable(idcard, hideOrShow);
		$("a[class='easyui-linkbutton l-btn']").each(function(){
			var text =  $(this).text();
			var id = $(this).attr("id");
			if(!hideOrShow){
				if(text == "保存并提交" || text == "返回"){
					$(this).css("visibility", "hidden");
					opt.optTable(idcard,false);
				}
				for(var i in buttons){
					if(id.indexOf(buttons[i]) >= 0){
						$(this).css("visibility", "visible");
					}
				}
			}else{
				if(text == "保存并提交" || text == "返回"){
					$(this).css("visibility", "visible");
				}
				for(var i in buttons){
					if(id.indexOf(buttons[i]) >= 0){
						$(this).css("visibility", "hidden");
					}
				}
			}
		});
	},
	viewEditHis:function(tpid, bizid){
		var url = ctx + "/template/templateAction_viewHIS.action?" + sessInfo+ "&tpid=" + tpid + "&bizid=" + bizid + "&flag=gotoHTML";
		window.open(url,"win_1","width=1000,height=600,top=0,left=150,titlebar=no,alwaysRaised=no,location=no,Resizable=no,scrollbars=yes");
	},
	goBack:function(type){
		if("0" == type){
			window.close();
		}else{
			window.history.back();
		}
	}
};
