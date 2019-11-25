/**
 * 表单序列化 2 object。
 * use $("#you form idd").serializeObject();
 */
(function($){
       $.fn.extend({
              serializeObject:function(){
                     if(this.length>1){
                            return false;
                     }
                     var arr=this.serializeArray();
                     var obj=new Object;
                     $.each(arr,function(k,v){
                            obj[v.name]=v.value;
                     });
                     return obj;
              }
       });
})(jQuery);

$.extend($.fn.form.methods, {
	setValues : function(myself, data) {
		var form = $(myself);
		var opts = $.data(form[0], "form").options;
		var cols = "," + data.items + ",";
		for ( var name in data.row) {
			if (cols.indexOf(name) >= 0) {
				var cellData = data.row[name];
				var type = form.find("input[name=" + name + "]").attr("type");
				var titleName =$("#"+name+"_title").attr("title");
				if(type == "checkbox"){ // 判断是否是 checkbox
					// 如果值相等，则false 
					if(form.find("input[name=" + name + "]").val() == cellData){
						form.find("input[name=" + name + "]").attr("checked",true);
					} else {
						form.find("input[name=" + name + "]").attr("checked",false);
					}
				}else if(titleName=="combotree"){ //选择树
					 $("#"+name+"_title").combotree('setValue',cellData);
				}else if(titleName=="combobox"){//下拉选择单选
					 $("#"+name+"_title").combobox('setValue',cellData);
				}else if(titleName=="textbox"){//多行编辑框
					$("#"+name+"_title").textbox('setValue',cellData);
				}else {
					form.find("input[name=" + name + "]").val(cellData);
				}
			}
		}
		opts.onLoadSuccess.call(form, data);
		form.form("validate");
	},
	setInnerTextValues : function(myself, data) {
		var form = $(myself);
		var opts = $.data(form[0], "form").options;
		var cols = "," + data.items + ",";
		for ( var name in data.row) {
			if (cols.indexOf(name) >= 0) {
				var val = data.row[name];
				form.find(data.lable + "[class=" + name + "]").text(val);
			}
		}
		opts.onLoadSuccess.call(form, data);
	}
});

 

