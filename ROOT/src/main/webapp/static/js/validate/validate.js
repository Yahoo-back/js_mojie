$.extend($.fn.validatebox.defaults.rules, {
            idcard: {// 验证身份证
                validator: function (value) {
                    return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
                },
                message: '身份证号码格式不正确'
            },
            minLength: {
                validator: function (value, param) {
                    return value.length >= param[0];
                },
                message: '请输入至少（2）个字符.'
            },
            length: { validator: function (value, param) {
                var len = $.trim(value).length;
                return len >= param[0] && len <= param[1];
            },
                message: "输入内容长度必须介于{0}和{1}之间."
            },
            phone: {// 验证电话号码
                validator: function (value) {
                    return /^((\d{2,3})|(\d{3}\-))?(0\d{2,3}|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
                },
                message: '格式不正确,请使用下面格式:020-88888888'
            },
            mobile: {// 验证手机号码
                validator: function (value) {
                    return /^(13|15|17|18)\d{9}$/i.test(value);
                },
                message: '手机号码格式不正确'
            },
			dict: {// 验证参数值/^\+?[1-9]\d*$/
				validator: function (value) {
					return /^[a-zA-z0-9_]*$/i.test(value);
				},
				message: '只能输入大小写字母、数字及下划线'
			},
			valihtml : {
					validator: function (value) {
						if(/<("[^"]*"|'[^']*'|[^'">])*>/i.test(value)) {
							return false;
						} else {
                            return true;
						}
				},
				message: '不能输入html语言'
			},
            intOrFloat: {// 验证整数或小数
                validator: function (value) {
                    return /^\d+(\.\d+)?$/i.test(value);
                },
                message: '请输入数字，并确保格式正确'
            },
            currency: {// 验证货币
                validator: function (value) {
                    return /^\d+(\.\d+)?$/i.test(value);
                },
                message: '货币格式不正确'
            },
            qq: {// 验证QQ,从10000开始
                validator: function (value) {
                    return /^[1-9]\d{4,9}$/i.test(value);
                },
                message: 'QQ号码格式不正确'
            },
            integer: {// 验证整数 可正负数
                validator: function (value) {
                    //return /^[+]?[1-9]+\d*$/i.test(value);

                    return /^([0-9]+)$/.test(value);  
                },
                message: '请输入整数'
            },
            integerLength: {// 验证整数 可正负数
                validator: function (value) {
                    return /^(?:[0-9][0-9]?|0[01][0-9]|100)$/i.test(value);
                },
                message: '请输入0-100的正整数'
            },
            age: {// 验证年龄
                validator: function (value) {
                    return /^(?:[1-9][0-9]?|1[01][0-9]|120)$/i.test(value);
                },
                message: '年龄必须是0到120之间的整数'
            },
            activation: {// 激活次数
            	validator: function (value) {
            		return /^(?:[0-9][0-9]?|1[01][0-9]|20)$/i.test(value);
            	},
            	message: '激活次数必须是0到20之间的整数'
            },
            
            daysOneM: {// 天数一个月
            	validator: function (value) {
            		return /^(?:[1-9][0-9]?|1[01][0-9]|31)$/i.test(value);
            	},
            	message: '天数必须是0到31之间的整数'
            },
            
            monthOneY: {// 月数一年
            	validator: function (value) {
            		return /^(?:[1-9][0-9]?|1[01][0-9]|12)$/i.test(value);
            	},
            	message: '月数必须是0到12之间的整数'
            },

            chinese: {// 验证中文
                validator: function (value) {
                    return /^[\Α-\￥]+$/i.test(value);
                },
                message: '请输入中文'
            },
            english: {// 验证英语
                validator: function (value) {
                    return /^[A-Za-z]+$/i.test(value);
                },
                message: '请输入英文'
            },
            unnormal: {// 验证是否包含空格和非法字符
                validator: function (value) {
                    return /.+/i.test(value);
                },
                message: '输入值不能为空和包含其他非法字符'
            },
            username: {// 验证用户名
                validator: function (value) {
                    return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
                },
                message: '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）'
            },
            faxno: {// 验证传真
                validator: function (value) {
                    //            return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/i.test(value);
                    return /^((\d{2,3})|(\d{3}\-))?(0\d{2,3}|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
                },
                message: '传真号码不正确'
            },
            zip: {// 验证邮政编码
                validator: function (value) {
                    return /^[1-9]\d{5}$/i.test(value);
                },
                message: '邮政编码格式不正确'
            },
            ip: {// 验证IP地址
                validator: function (value) {
                    return /d+.d+.d+.d+/i.test(value);
                },
                message: 'IP地址格式不正确'
            },
            name: {// 验证姓名，可以是中文或英文
                validator: function (value) {
                    return /^[\Α-\￥]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
                },
                message: '请输入姓名'
            },
            date: {// 验证姓名，可以是中文或英文
                validator: function (value) {
                    //格式yyyy-MM-dd或yyyy-M-d
                    return /^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\2(?:29))$/i.test(value);
                },
                message: '清输入合适的日期格式'
            },
            msn: {
                validator: function (value) {
                    return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
                },
                message: '请输入有效的msn账号(例：abc@hotnail(msn/live).com)'
            },
            same: {
                validator: function (value, param) {
                    if ($("#" + param[0]).val() != "" && value != "") {
                        return $("#" + param[0]).val() == value;
                    } else {
                        return true;
                    }
                },
                message: '两次输入的密码不一致！'
            },
        	nonAroundSpaceText:{
        		validator:function (value) {
        			var reg = new RegExp(/(^\s+)|(\s+$)/g);
        			return !reg.test(value);
        		},
        		message:'请输入非前后空格文本'
        	},
        	positiveIntegerNumber:{
        		validator:function (value) {
        			var reg = new RegExp(/^[1-9]\d*$/);
        			return reg.test(value);
        		},
        		message:'请输入正整数'
        	},
        	nonNegativeNumber:{
        		validator:function (value) {
        			var reg = new RegExp(/^\d+(\.\d+)?$/);
        			return reg.test(value);
        		},
        		message:'请输入非负数'
        	},
        	percentNumber:{
        		validator:function (value) {
        			var reg = new RegExp(/^((([0-9]|[1-9][0-9])(\.\d{1,2})?)|100|100.0|100.00)$/);
        			return reg.test(value);
        		},
        		message:'请输入 0~100 的百分比'
        	}
        });

//检验form表单字段
//form的名字
//needArray必须输入的字段数组
//numberArray数字验证字段数组
function validateForm(formName,needArray,numberArray)
{ 	 
  //replace(/^\s+|\s+$/g,"")去除前后空格
	//   alert(formName + "      " + evtm);
  var needAdd = needArray;
  for (var i = 0; i < needAdd.length; i++)
  {
    //var procodedesc=needAdd[i];
    //var procode=procodedesc.substr(0,procodedesc.indexOf("@"));
    //var prodesc=procodedesc.substr(procodedesc.indexOf("@")+1);
	var procode=needAdd[i];
	if(null==formName||""==formName){
		if (document.elements[procode].length==0||document.elements[procode].value.replace(/^\s+|\s+$/g,"")==""||document.elements[procode].value=="0.00")
	    {
	      //$.messager.alert("操作信息",prodesc+"，请重新输入！","info");
	      try{
	    	  document.elements[procode].focus();
	    	 }catch(e){}
	      return(false);
	    }
	}else{
		//alert("document.forms[formName].elements[procode].length = " + procode);
		//alert(document.forms[formName].elements[procode]);
		if(undefined != document.forms[formName].elements[procode] || null != document.forms[formName].elements[procode])
		{
			if (document.forms[formName].elements[procode].length==0||document.forms[formName].elements[procode].value.replace(/^\s+|\s+$/g,"")==""||document.forms[formName].elements[procode].value=="0.00")
		    {
		      //$.messager.alert("操作信息",prodesc+"，请重新输入！","info");
		      try{
		    	  document.forms[formName].elements[procode].focus();
		    	 }catch(e){}
		      return(false);
		    }
		}
	}
  }
  
  return true;
}

/*
 * 输入框只能输入整型、小数类型
 * 调用方法:onkeypress="javascript:return isNumber(event,value);"
 * */
isNumber = function (e,num,len,preLen) {
   //两种类型
   //var partn = /^\d+(\.?\d*)$/;
   //var patrn = new RegExp("^\\d+(\\.?\\d*)$"); 
   var patrn = new RegExp("^\\d+(\\.?\\d*)$"); 
   //存在小数点,分配小数点后面的长度时
   if(0 != preLen && num.indexOf('.') != -1){
   		patrn = new RegExp("^\\d{0,"+len+"}(\\.\\d{0,"+preLen+"})$");
   //不存在小数点时
   }else if (num.indexOf('.') == -1){
   		patrn = new RegExp("^\\d{0,"+len+"}$");
   }
   evt=window.event||e;
   code=evt.keyCode||evt.which;
   //未填数据时
   if(num==null||num==""){
	   if ( ((code > 47) && (code < 58)) ||   
	              (code == 8)) { 
	        return true; 
	   } else {  
	       return false;
	   }
   //已经存在数据时
   }else{
   	   if ( ((code > 47) && (code < 58)) ||   
	              (code == 8) || 
	              (code == 46)) {
	            if(num.search(patrn) != -1){
	            	//如果首位为0，第二位输入的只有为小数点时，才可以通过
            		if(num.toString().indexOf('0')==0&&num.toString().length==1){
            			if(code!=46){
	            			return false;
            			}
	            	}
	            	
	            	//数据中存在小数点
	            	if(num.indexOf('.') != -1){
	            		//code==46 按下小数点
	            		if(code==46){
	            			return false;
	            		}else{
	            			//当数值长度超出分配的长度时
	            			if(num.toString().length >= num.toString().indexOf('.') + preLen + 1){
	            				return false;
		            		}else{
		            			return true;
		            		}
	            		}
	            	}else{
	            		if(num.toString().length >= len){
	            			if(code==46){
	            				if(0 == preLen){
	            					return false;
	            				}else{
	            					return true;
	            				}
	            			}
	            			return false;
	            		}else{
	            			return true;
	            		}
	            	}
	        	} else{
	        		return false;
	        	}   
	   } else {   
	       return false;   
	   }
   } 
};


/*
 * 输入框只能输入整型、小数类型（包括可以输入负数）
 * 调用方法:onkeypress="javascript:return isNumber(event,value);"
 * */
isNumber2 = function (e,num,len,preLen) {
   //两种类型
   //var partn = /^\d+(\.?\d*)$/;
   //var patrn = new RegExp("^\\d+(\\.?\\d*)$"); 
   var patrn = new RegExp("^-?\\d+(\\.?\\d*)$"); 
   //存在小数点,分配小数点后面的长度时
   if(0 != preLen && num.indexOf('.') != -1){
   		patrn = new RegExp("^-?\\d{0,"+len+"}(\\.\\d{0,"+preLen+"})$");
   //不存在小数点时
   }else if (num.indexOf('.') == -1){
   		patrn = new RegExp("^-?\\d{0,"+len+"}$");
   }
   evt=window.event||e;
   code=evt.keyCode||evt.which;
   
   //未填数据时
   if(num==null||num==""){
	   if ( ((code > 47) && (code < 58)) ||   
	              (code == 8) || (code == 45)) { 
	        return true; 
	   } else {  
	       return false;
	   }
   //已经存在数据时
   }else{
	   
   	   if ( ((code > 47) && (code < 58)) ||   
	              (code == 8) || 
	              (code == 46)) {
   		   
	            if(num.search(patrn) != -1){
	            	
	            	//如果首位为0，第二位输入的只有为小数点时，才可以通过
            		if(num.toString().indexOf('0')==0&&num.toString().length==1){
            			if(code!=46){
	            			return false;
            			}
	            	}
	            	
	            	//数据中存在小数点
	            	if(num.indexOf('.') != -1){
	            		//code==46 按下小数点
	            		if(code==46){
	            			return false;
	            		}else{
	            			//当数值长度超出分配的长度时
	            			if(num.toString().length >= num.toString().indexOf('.') + preLen + 1){
	            				return false;
		            		}else{
		            			return true;
		            		}
	            		}
	            	}else{
	            		if(num.toString().length >= len){
	            			if(code==46){
	            				if(0 == preLen){
	            					return false;
	            				}else{
	            					return true;
	            				}
	            			}
	            			return false;
	            		}else{
	            			return true;
	            		}
	            	}
	        	} else{
	        		return false;
	        	}   
	   } else {   
	       return false;   
	   }
   } 
};



var numVal = 0; //上一次的数值
var tepVal = ''; //上一次的电话号码数值

/*
 * 输入框只能输入整型、小数类型
 * 数字框键按下时触发的事件
 * 调用方法:onkeydown="javascript:return isNumberDown(event,value);"
 * */
isNumberDown = function (e,num,len,preLen) {
		//两种类型
	   //var partn = /^\d+(\.?\d*)$/;
	   //var patrn = new RegExp("^\\d+(\\.?\\d*)$"); 
	   var patrn = new RegExp("^\\d+(\\.?\\d*)$"); 
	   //存在小数点,分配小数点后面的长度时
	   if(0 != preLen && num.indexOf('.') != -1){
	   		patrn = new RegExp("^\\d{0,"+len+"}(\\.\\d{0,"+preLen+"})$");
	   //不存在小数点时
	   }else if (num.indexOf('.') == -1){
	   		patrn = new RegExp("^\\d{0,"+len+"}$");
	   }
	   evt=window.event||e;
	   code=evt.keyCode||evt.which;
	   //可以使用Ctrl键，但是不能使用shift、alt键
	   if(evt.ctrlKey==false){
		   //直接按TAB键可以调整到下一个元素
		   if(code==9){
			   return true;
		   }
		   if(evt.shiftKey==true||evt.altKey==true){
			   return false;
		   }else{
			   //未填数据时,code为48-57大键盘0-9,code为96-105为小键盘0-9,code为8为回车键
			   if(num==null||num==""){
				   if ( ((code > 47) && (code < 58)) ||  ((code > 95) && (code < 106)) || 
				              (code == 8)) {
						numVal = num; //备份值
						return true;	
				   } else {  
				        return false;
				   }
			   //已经存在数据时
			   }else{
					if (((code > 47) && (code < 58)) || ((code > 95) && (code < 106)) || 
				              (code == 8) || (code == 37) || (code == 39) || 
				              (code == 46)||(code == 110)||(code == 190)) {
						if(num.search(patrn) != -1){
							numVal = num;//备份值
							//如果首位为0，第二位输入的只有为小数点时，才可以通过
		            		/*if(num.toString().indexOf('0')==0&&num.toString().length==1){
		            			if(code != 110 && code != 190){
			            			return false;
		            			}
			            	}*/
							return true;
						}else{
							return false;
						}
				   }else{
						return false;
				   }
			   } 
		   }
	   }else{
		   return true;
	   }
};

/*
 * 输入框只能输入整型、小数类型
 * 数字框键弹起时触发的事件
 * */
function isNumberUp(e,obj,num,len,preLen) {
	   //两种类型
	   //var partn = /^\d+(\.?\d*)$/;
	   //var patrn = new RegExp("^\\d+(\\.?\\d*)$"); 
	   var patrn = new RegExp("^\\d+(\\.?\\d*)$"); 
	   var patrn2 = new RegExp("^[0]\\d+$");
	   var patrn3 = new RegExp("^[0]\\d+$");
	   //存在小数点,分配小数点后面的长度时
	   if(0 != preLen && num.indexOf('.') != -1){
	   		patrn = new RegExp("^\\d{0,"+len+"}(\\.\\d{0,"+preLen+"})$");
	   		patrn2 = new RegExp("^[0]\\d+\\.\\d*$");
	   		patrn3 = new RegExp("^\\.\\d*$"); 
	   //不存在小数点时
	   }else if (num.indexOf('.') == -1){
	   		patrn = new RegExp("^\\d{0,"+len+"}$");
	   		patrn2 = new RegExp("^[0]\\d+$");
	   		patrn3 = new RegExp("^[0]\\d+$");
	   }
	   evt=window.event||e;
	   code=evt.keyCode||evt.which;
	  
	   if(null!=num&&(""!=num)){
		   if(num.search(patrn) != -1 && num.search(patrn2) == -1 && num.search(patrn3) == -1){ //匹配
				return true;
		   }else{
				$(obj).val(numVal); //控件重置为备份值
				return false;
		   }
	   }
};

/*
 * 输入框只能输入整型、小数类型
 * 数字框失去焦点时触发的事件
 * */
function isNumberBlur(e,obj,num,len,preLen) {
	   //两种类型
	   //var partn = /^\d+(\.?\d*)$/;
	   //var patrn = new RegExp("^\\d+(\\.?\\d*)$"); 
	   var patrn = new RegExp("^\\d+(\\.?\\d*)$"); 
	   var patrn2 = new RegExp("^[0]\\d+$");
	   var patrn3 = new RegExp("^[0]\\d+$");
	   //存在小数点,分配小数点后面的长度时
	   if(0 != preLen && num.indexOf('.') != -1){
	   		patrn = new RegExp("^\\d{0,"+len+"}(\\.\\d{0,"+preLen+"})$");
	   		patrn2 = new RegExp("^[0]\\d+\\.\\d*$");
	   		patrn3 = new RegExp("^\\.\\d*$"); 
	   //不存在小数点时
	   }else if (num.indexOf('.') == -1){
	   		patrn = new RegExp("^\\d{0,"+len+"}$");
	   		patrn2 = new RegExp("^[0]\\d+$");
	   		patrn3 = new RegExp("^[0]\\d+$");
	   }
	   evt=window.event||e;
	   code=evt.keyCode||evt.which;
	  
	   if(null!=num&&(""!=num)){
		   if(num.search(patrn) != -1 && num.search(patrn2) == -1 && num.search(patrn3) == -1){ //匹配
				return true;
		   }else{
				$(obj).val(numVal); //控件重置为备份值
				return false;
		   }
	   }
};

isPhone = function (e,num,len,preLen) {
	   //两种类型
	   //var partn = /^\d+(\.?\d*)$/;
	   //var patrn = new RegExp("^\\d+(\\.?\\d*)$"); 
	   var patrn = new RegExp("^\\d+(\\-?\\d*)$"); 
	   //存在小数点,分配小数点后面的长度时
	   if(0 != preLen && num.indexOf('-') != -1){
	   		patrn = new RegExp("^\\d{0,"+len+"}(\\-\\d{0,"+preLen+"})$");
	   //不存在小数点时
	   }else if (num.indexOf('-') == -1){
	   		patrn = new RegExp("^\\d{0,"+len+"}$");
	   }
	   evt=window.event||e;
	   code=evt.keyCode||evt.which;
	   //未填数据时
	   if(num==null||num==""){
		   if ( ((code > 47) && (code < 58)) ||   
		              (code == 8)) { 
		        return true; 
		   } else {  
		       return false;
		   }
	   //已经存在数据时
	   }else{
	   	   if ( ((code > 47) && (code < 58)) ||   
		              (code == 8) || 
		              (code == 45)) {
		            if(num.search(patrn) != -1){
		            	//数据中存在小数点
		            	if(num.indexOf('-') != -1){
		            		//code==45 按下-
		            		if(code==45){
		            			return false;
		            		}else{
		            			//当数值长度超出分配的长度时
		            			if(num.toString().length >= num.toString().indexOf('-') + preLen + 1){
		            				return false;
			            		}else{
			            			return true;
			            		}
		            		}
		            	}else{
		            		if(num.toString().length >= len){
		            			if(code==45){
		            				if(0 == preLen){
		            					return false;
		            				}else{
		            					return true;
		            				}
		            			}
		            			return false;
		            		}else{
		            			return true;
		            		}
		            	}
		        	} else{
		        		return false;
		        	}   
		   } else {   
		       return false;   
		   }
	   } 
	};
	
/**
 * 电话输入框
 * 输入框框键按下时触发的事件
 * */
isPhoneDown = function (e,num,len,preLen) {
		//两种类型
	   //var partn = /^\d+(\.?\d*)$/;
	   //var patrn = new RegExp("^\\d+(\\.?\\d*)$"); 
	   var patrn = new RegExp("^\\d+(\\-?\\d*)$"); 
	   //存在小数点,分配小数点后面的长度时
	   if(0 != preLen && num.indexOf('-') != -1){
	   		patrn = new RegExp("^\\d{0,"+len+"}(\\-\\d{0,"+preLen+"})$");
	   //不存在小数点时
	   }else if (num.indexOf('-') == -1){
	   		patrn = new RegExp("^\\d{0,"+len+"}$");
	   }
	   evt=window.event||e;
	   code=evt.keyCode||evt.which;
	   //可以使用Ctrl键，但是不能使用shift、alt键
	   if(evt.ctrlKey==false){
		   //直接按TAB键可以调整到下一个元素
		   if(code==9){
			   return true;
		   }
		   if(evt.shiftKey==true||evt.altKey==true){
			   return false;
		   }else{
			   //未填数据时,code为48-57大键盘0-9,code为96-105为小键盘0-9
			   if(num==null||num==""){
				   if ( ((code > 47) && (code < 58)) ||  ((code > 95) && (code < 106)) || 
				              (code == 8)) {
						tepVal = num; //备份值
						return true;	
				   } else {  
				        return false;
				   }
			   //已经存在数据时
			   }else{
					if (((code > 47) && (code < 58)) || ((code > 95) && (code < 106)) || 
				              (code == 8) || (code == 37) || (code == 39) || 
				              (code == 109)||(code == 189)) {
						if(num.search(patrn) != -1){
							tepVal = num;//备份值
							return true;
						}else{
							return false;
						}
				   }else{
						return false;
				   }
			   }
		  }
	   }else{
		   return true;
	   }
};

/**
 * 电话框只能输入整型、小数类型
 * 电话框键弹起时触发的事件
 * */
function isPhoneUp(e,obj,num,len,preLen) {
	   //两种类型
	   //var partn = /^\d+(\.?\d*)$/;
	   //var patrn = new RegExp("^\\d+(\\.?\\d*)$"); 
	   var patrn = new RegExp("^\\d+(\\-?\\d*)$"); 
	   var patrn2 = new RegExp("^\\d+\\-\\-$"); //随意写的一个验证，主要是带"-"的时候验证,不带"-"这个验证一定不通过
	   //存在小数点,分配小数点后面的长度时
	   if(0 != preLen && num.indexOf('-') != -1){
	   		patrn = new RegExp("^\\d{0,"+len+"}(\\-\\d{0,"+preLen+"})$");
	   		patrn2 = new RegExp("^\\-\\d*$"); 
	   //不存在小数点时
	   }else if (num.indexOf('-') == -1){
	   		patrn = new RegExp("^\\d{0,"+len+"}$");
	   		patrn2 = new RegExp("^\\d+\\-\\-$");
	   }
	   evt=window.event||e;
	   code=evt.keyCode||evt.which;
	  
	   if(null!=num&&(""!=num)){
		   if(num.search(patrn) != -1 && num.search(patrn2) == -1){ //匹配
				return true;
		   }else{
				$(obj).val(tepVal); //控件重置为备份值
				return false;
		   }
	   }
};

/**
 * 电话框只能输入整型、小数类型
 * 电话框失去焦点时触发的事件
 * */
function isPhoneBlur(e,obj,num,len,preLen) {
	   //两种类型
	   //var partn = /^\d+(\.?\d*)$/;
	   //var patrn = new RegExp("^\\d+(\\.?\\d*)$"); 
	   var patrn = new RegExp("^\\d+(\\-?\\d*)$"); 
	   var patrn2 = new RegExp("^\\d+\\-\\-$"); //随意写的一个验证，主要是带"-"的时候验证,不带"-"这个验证一定不通过
	   //存在小数点,分配小数点后面的长度时
	   if(0 != preLen && num.indexOf('-') != -1){
	   		patrn = new RegExp("^\\d{0,"+len+"}(\\-\\d{0,"+preLen+"})$");
	   		patrn2 = new RegExp("^\\-\\d*$"); 
	   //不存在小数点时
	   }else if (num.indexOf('-') == -1){
	   		patrn = new RegExp("^\\d{0,"+len+"}$");
	   		patrn2 = new RegExp("^\\d+\\-\\-$");
	   }
	   evt=window.event||e;
	   code=evt.keyCode||evt.which;
	  
	   if(null!=num&&(""!=num)){
		   if(num.search(patrn) != -1 && num.search(patrn2) == -1){ //匹配
				return true;
		   }else{
				$(obj).val(tepVal); //控件重置为备份值
				return false;
		   }
	   }
};
	
/**
* 电话改变方法，在IE8、IE9下会存在bug，火狐下正常 weifeng.tao 2012.10.24
* obj这个对象，num输入框的值，len 横杠前参数，preLen 横杠后参数
*/
isPhoneChange=function(obj,values,len,preLen){
	 var patrn = new RegExp("^\\d+(\\-?\\d*)$"); 
	//随意写的一个验证，主要是带"-"的时候验证,不带"-"这个验证一定不通过
	 var patrn2 = new RegExp("^\\d+\\-\\-$"); 
	 //电话号码带横杠
	 if(0 != preLen && values.indexOf('-') != -1){
	   		patrn = new RegExp("^\\d{0,"+len+"}(\\-\\d{0,"+preLen+"})$");
	   		patrn2 = new RegExp("^\\-\\d*$"); 
	 //不带横杠
	 }else if (values.indexOf('-') == -1){
	   		patrn = new RegExp("^\\d{0,"+len+"}$");
	   		patrn2 = new RegExp("^\\d+\\-\\-$");
	 }
	 
	 if(values.length>0){
		if(values.search(patrn) != -1 && values.search(patrn2) == -1){ //匹配
			return true;
	    }else{
		   values=values.substr(0,values.length-1);
		   $(obj).val(values);
		   return false;
	    }
	 }
};
	
/**
* 数字改变方法，在IE8、IE9下会存在bug，火狐下正常 weifeng.tao 2012.10.24
*/
isNumberChange=function(obj,num,len,preLen) {
		   //两种类型
		   var patrn = new RegExp("^\\d+(\\.?\\d*)$"); 
		   var patrn2 = new RegExp("^[0]\\d+$");
		   var patrn3 = new RegExp("^[0]\\d+$");
		   //存在小数点,分配小数点后面的长度时
		   if(0 != preLen && num.indexOf('.') != -1){
		   		patrn = new RegExp("^\\d{0,"+len+"}(\\.\\d{0,"+preLen+"})$");
		   		patrn2 = new RegExp("^[0]\\d+\\.\\d*$");
		   		patrn3 = new RegExp("^\\.\\d*$"); 
		   //不存在小数点时
		   }else if (num.indexOf('.') == -1){
		   		patrn = new RegExp("^\\d{0,"+len+"}$");
		   		patrn2 = new RegExp("^[0]\\d+$");
		   		patrn3 = new RegExp("^[0]\\d+$");
		   }
		  
		   	if(num.length>0){
		   		//alert(num.substr(0,2));		   	
		   		//num.search(patrn2) == -1 &&
			   if(num.search(patrn) != -1 && num.search(patrn2) == -1 && num.search(patrn3) == -1){ //匹配
				   numVal = num;
				   return true;
			   }else{
				    num=num.substr(0,num.length-1);
				    
				    if(num.substr(0,2)=='00'){
			   			return false;
			   		}/*else if(num.substr(0,1)=="."){
			   			return false;
			   		} */
				    
					$(obj).val(numVal);
					return false; 		  
			   }
		   	}
		   	return false;
};

/**
* 数字粘贴方法 weifeng.tao 2012.10.24
*/
isNumberPaste=function(obj,pasteVal,orgValue,len,preLen) {
		   //获取鼠标位置、选中值
		   var range = document.selection.createRange();
		   var rangeText = range.text;
		   range.moveStart("character",-orgValue.length);
	       //获取粘贴后的拼接的字符串值
		   var num = '';
		   num = num.concat(orgValue.substr(0,range.text.length-rangeText.length),pasteVal,orgValue.substr(range.text.length,orgValue.length));
		   //两种类型
		   var patrn = new RegExp("^\\d+(\\.?\\d*)$"); 
		   var patrn2 = new RegExp("^[0]\\d+$");
		   var patrn3 = new RegExp("^[0]\\d+$");
		   //存在小数点,分配小数点后面的长度时
		   if(0 != preLen && num.indexOf('.') != -1){
		   		patrn = new RegExp("^\\d{0,"+len+"}(\\.\\d{0,"+preLen+"})$");
		   		patrn2 = new RegExp("^[0]\\d+\\.\\d*$");
		   		patrn3 = new RegExp("^\\.\\d*$"); 
		   		
		   //不存在小数点时
		   }else if (num.indexOf('.') == -1){
		   		patrn = new RegExp("^\\d{0,"+len+"}$");
		   		patrn2 = new RegExp("^[0]\\d+$");
		   		patrn3 = new RegExp("^[0]\\d+$");
		   }
		   
		   	if(num.length>0){
		   		//alert(num.substr(0,2));		   	
		   		//num.search(patrn2) == -1 &&
			   if(num.search(patrn) != -1 && num.search(patrn2) == -1 && num.search(patrn3) == -1){ //匹配
				   return true;
			   }else{
				    num=num.substr(0,num.length-1);
				    
				    if(num.substr(0,2)=='00'){
			   			return false;
			   		}/*else if(num.substr(0,1)=="."){
			   			return false;
			   		} */
				    
					return false; 		  
			   }
		   	}
		   	return false;
};

/**
* 电话粘贴方法 weifeng.tao 2012.10.24
*/
isPhonePaste=function(obj,pasteVal,orgValue,len,preLen) {
		   //获取鼠标位置、选中值
		   var range = document.selection.createRange();
		   var rangeText = range.text;
		   range.moveStart("character",-orgValue.length);
	       //获取粘贴后的拼接的字符串值
		   var values = '';
		   values = values.concat(orgValue.substr(0,range.text.length-rangeText.length),pasteVal,orgValue.substr(range.text.length,orgValue.length));
		   var patrn = new RegExp("^\\d+(\\-?\\d*)$"); 
		   //随意写的一个验证，主要是带"-"的时候验证,不带"-"这个验证一定不通过
		   var patrn2 = new RegExp("^\\d+\\-\\-$"); 
			 //电话号码带横杠
			 if(0 != preLen && values.indexOf('-') != -1){
			   		patrn = new RegExp("^\\d{0,"+len+"}(\\-\\d{0,"+preLen+"})$");
			   		patrn2 = new RegExp("^\\-\\d*$"); 
			 //不带横杠
			 }else if (values.indexOf('-') == -1){
			   		patrn = new RegExp("^\\d{0,"+len+"}$");
			   		patrn2 = new RegExp("^\\d+\\-\\-$");
			 }
			 
			 if(values.length>0){
				if(values.search(patrn) != -1 && values.search(patrn2) == -1){ //匹配
					return true;
			    }else{
				   /*values=values.substr(0,values.length-1);
				   $(obj).val(values);*/
				   return false;
			    }
			 }
			 return false;
};
	
//屏蔽指定字段的粘帖功能
function controlInputNumber(fields){

	for(var i=0;i<fields.length;i++){
		
		//获取指定字段数组信息name 18,2
		var fieldArray = document.getElementsByName(fields[i][0]);
		//循环对指定的字段进行
		for(var j=0;j<fieldArray.length;j++){
			//粘帖屏蔽
			/*fieldArray[j].onpaste = function(){
   				return false;
   			};*/
   			//fieldArray[j].style.imeMode = "disabled"; js方式
		}
		//jquery方式屏蔽输入法
		$("input[name='"+fields[i][0]+"']").each(function(){
			$(this).css("ime-mode","disabled");
		});

			//鼠标按下事件
			$("input[name='"+fields[i][0]+"']").each(function(){
				
				var len = fields[i][1];
				var preLen = fields[i][2];
				var type = fields[i][3];
				
				if($.browser.msie){//IE
					//绑定粘贴 的事件
					$(this).bind('paste',function(event){
						if(0 == type){
							return isNumberPaste(event,clipboardData.getData("Text"),this.value,len,preLen);//数字
						}else if(1 == type){
							return isPhonePaste(event,clipboardData.getData("Text"),this.value,len,preLen);//电话
						}
			   		});
					//绑定键盘弹起的事件
					$(this).bind('blur',function(event){
						if(0 == type){
							return isNumberBlur(event,this,this.value,len,preLen);
						}else if(1 == type){
							return isPhoneBlur(event,this,this.value,len,preLen);
						}
			   		});
					//绑定鼠标拖入的事件
					$(this).bind('drop',function(event){
						/////////没有找到获取drop内容的方法
						//禁用拖拽功能
						return false;
			   		});
					//绑定键盘按下的事件
					$(this).keydown(function(event){
						if(0 == type){
							return isNumberDown(event,this.value,len,preLen);//数字
						}else if(1 == type){
							return isPhoneDown(event,this.value,len,preLen);//电话
						}	
			   		});
					//绑定键盘弹起的事件
					$(this).keyup(function(event){
						if(0 == type){
							return isNumberUp(event,this,this.value,len,preLen);
						}else if(1 == type){
							return isPhoneUp(event,this,this.value,len,preLen);
						}
			   		});
					/* 不用propertychange事件，在IE8、IE9中会出问题
					if(0 == type){
						$(this).bind("propertychange",function(event){
							return isNumberChange(this,this.value,len,preLen);//数字
						});
					}else if(1 == type){
						$(this).bind("propertychange",function(event){
							return isPhoneChange(this,this.value,len,preLen);//电话	
						});
					}
					*/
				}else{//火狐
					var obj=$(this);
					if(0 == type){
						for(var j=0;j<obj.length;j++){
							obj.get(j).addEventListener("input",function(o){
								return isNumberChange(this,this.value,len,preLen);//数字
							 });
						}
					}else if(1 == type){
						for(var j=0;j<obj.length;j++){
							obj.get(j).addEventListener("input",function(o){
								return isPhoneChange(this,this.value,len,preLen);//电话	
							 });
						}
					}
				}
			});
	}
}

