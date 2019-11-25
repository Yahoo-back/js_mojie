init=function(){
	
	//alert(123);
	//获得当前的from标签
	$("form").each(function(o){			
		//text 输入项验证
		var formid="#"+$(this).attr("id")+" input[type=text]";
		$(formid).each(function(obj){
			checkText($(this));
		});
		
		//下拉框的验证
		formid="#"+$(this).attr("id")+" select";
		$(formid).each(function(obj){
			checkSelect($(this));
		});
		
		//checkbox的验证
		formid="#"+$(this).attr("id")+" checkbox";
		$(formid).each(function(obj){
			checkCheckbox($(this));
		});
	});
};

var numVal = 0; //上一次的数值
var signNumVal = 0; //上一次的数值
var tepVal = ''; //上一次的电话号码数值
var postalCodeVal = ''; //上一次的邮政编码数值

formatterinit=function(id){
	//text 输入项验证
	$("#"+id).each(function(obj){
		checkText($(this));
	});
};

//不为空的验证
formatterIsNull=function(id){
	
	$("#"+id).each(function(obj){
		if(isUndefined($(this),"isNull")){
			if(isTrueFalse($(this),"isNull")){
				isNullRed($(this));
			}	
		}
	});
	
};

//验证表单提交
submit=function(formname){
		//提交时验证必填项
		var formid="#"+formname+" [isNull=true]";
		//alert($(formid).length);
		var b=true;
		$(formid).each(function(obj){
		
			validate:if("true"==$(this).attr("isNull")){
				//alert($(this).attr("id")+"  "+$(this).val());
				//replace(/^\s+|\s+$/g,"")替换前后空格
				if(""==$(this).val().replace(/^\s+|\s+$/g,"")){
					b=false;
					
					//提示信息
					if(isUndefined($(this),"errInfo")){
						alert($(this).attr("errInfo"));
					}
					
					//日期空间不能得到焦点
					if(isUndefined($(this),"class")){
						if("Wdate"!=$(this).attr("class")){
							$(this).focus();
						}
					}else{
						$(this).focus();
					}
					
					$(this).css("background-color","red");
					
					return false;
					//break  validate;
				}else{
					if($(this).css("background-color") == 'rgb(255, 0, 0)'){
						$(this).css("background-color","");
					}
				}
			}
		});
		if(b){
			return true;
		}
		return false;
};

//输入框数据验证
checkText=function(text){
	
	var validateTwo=0;
	
	if(isUndefined(text,"isNum")){
		validateTwo++;
	}
	
	if(isUndefined(text,"isSignNum")){
		validateTwo++;
	}
	
	if(isUndefined(text,"isPhone")){
		validateTwo++;
	}
	
	if(isUndefined(text,"isDate")){
		validateTwo++;
	}
	
	if(isUndefined(text,"isPostalCode")){
		validateTwo++;
	}
	
	
	//验证输入框只能存在一种数据校验
	if(validateTwo>1){
		//text.attr("style","background-color:#FF0000");
		text.css("background-color","red");
		text.val("输入框属性设置两种格式验证");
		text.focus();
		//alert("input 参数设置错误");
		return false;
	}

	//alert(text.attr("name"));
	//数值验证
	if(isUndefined(text,"isNum")){
		//等于true进行验证
		if(isTrueFalse(text,"isNum")){
			//验证是否有格式要求,没有格式要求是整数 
			var arry=numSizeArray(text,"numsize");
			//IE浏览器
			if($.browser.msie){
				
				
				if(arry[1]==0){
					/* propertychange在IE8、IE9下出现bug
					text.bind("propertychange",function(event){
						return isIntegerChange(this,this.value,arry[0]);//数字
					});
					*/
					//绑定粘贴 的事件
					text.bind('paste',function(event){
						return isNumberPaste(event,clipboardData.getData("Text"),this,this.value,arry[0],0);//数字
			   		});
					//绑定鼠标拖入的事件
					text.bind('drop',function(event){
						//禁用拖拽功能
						return false;
			   		});
					//绑定键盘按下的事件
					text.keydown(function(event){
						return isNumberDown(event,this,this.value,arry[0],0);//数字
			   		});
					//绑定键盘弹起的事件
					text.keyup(function(event){
						return isNumberUp(event,this,this.value,arry[0],0);
			   		});
					
				}else{
					/* propertychange在IE8、IE9下出现bug
					text.bind("propertychange",function(event){
						return isNumberChange(this,this.value,arry[0],arry[1]);//数字
					});
					*/
					//绑定粘贴 的事件
					text.bind('paste',function(event){
						return isNumberPaste(event,clipboardData.getData("Text"),this,this.value,arry[0],arry[1]);//数字
			   		});
					//绑定鼠标拖入的事件
					text.bind('drop',function(event){
						//禁用拖拽功能
						return false;
			   		});
					//绑定键盘按下的事件
					text.keydown(function(event){
						return isNumberDown(event,this,this.value,arry[0],arry[1]);//数字
			   		});
					//绑定键盘弹起的事件
					text.keyup(function(event){
						return isNumberUp(event,this,this.value,arry[0],arry[1]);
			   		});
				}
				
				
			}else{
				
				if(arry[1]==0){
					for(var i=0;i<text.length;i++){
						text.get(i).addEventListener("input",function(o){
							return isIntegerChange(this,this.value,arry[0]);//数字
					   });
					}
				}else{
					for(var i=0;i<text.length;i++){
						text.get(i).addEventListener("input",function(o){
							return isNumberChange(this,this.value,arry[0],arry[1]);//数字
					   });
					}
				}
				
			}
			
			
		}
	} 
	
	/**判断是否带符号的数字 weifeng.tao 2013.1.15*/
	if(isUndefined(text,"isSignNum")){
		//等于true进行验证
		if(isTrueFalse(text,"isSignNum")){
			//验证是否有格式要求,没有格式要求是整数 
			var arry=numSizeArray(text,"numsize");
			//IE浏览器
			if($.browser.msie){
				
				
				if(arry[1]==0){
					/* propertychange在IE8、IE9下出现bug
					text.bind("propertychange",function(event){
						return isIntegerChange(this,this.value,arry[0]);//数字
					});
					*/
					//绑定粘贴 的事件
					text.bind('paste',function(event){
						return isSignNumberPaste(event,clipboardData.getData("Text"),this,this.value,arry[0],0);//数字
			   		});
					//绑定鼠标拖入的事件
					text.bind('drop',function(event){
						//禁用拖拽功能
						return false;
			   		});
					//绑定键盘按下的事件
					text.keydown(function(event){
						return isSignNumberDown(event,this,this.value,arry[0],0);//数字
			   		});
					//绑定键盘弹起的事件
					text.keyup(function(event){
						return isSignNumberUp(event,this,this.value,arry[0],0);
			   		});
					
				}else{
					/* propertychange在IE8、IE9下出现bug
					text.bind("propertychange",function(event){
						return isNumberChange(this,this.value,arry[0],arry[1]);//数字
					});
					*/
					//绑定粘贴 的事件
					text.bind('paste',function(event){
						return isSignNumberPaste(event,clipboardData.getData("Text"),this,this.value,arry[0],arry[1]);//数字
			   		});
					//绑定鼠标拖入的事件
					text.bind('drop',function(event){
						//禁用拖拽功能
						return false;
			   		});
					//绑定键盘按下的事件
					text.keydown(function(event){
						return isSignNumberDown(event,this,this.value,arry[0],arry[1]);//数字
			   		});
					//绑定键盘弹起的事件
					text.keyup(function(event){
						return isSignNumberUp(event,this,this.value,arry[0],arry[1]);
			   		});
				}
				
				
			}else{
				
				if(arry[1]==0){
					for(var i=0;i<text.length;i++){
						text.get(i).addEventListener("input",function(o){
							return isSignIntegerChange(this,this.value,arry[0]);//数字
					   });
					}
				}else{
					for(var i=0;i<text.length;i++){
						text.get(i).addEventListener("input",function(o){
							return isSignNumberChange(this,this.value,arry[0],arry[1]);//数字
					   });
					}
				}
				
			}	
		}
	} 
	
	
	
	if(isUndefined(text,"isNull")){
		if(isTrueFalse(text,"isNull")){
			isNullRed(text);
		}	
	};


	
	
	//电话号码验证
	if(isUndefined(text,"isPhone")){
		//等于true进行验证
		if(isTrueFalse(text,"isPhone")){
			var arry=numSizeArray(text,"numsize");//分隔符前面字符长度，分隔符后面长度 
			if($.browser.msie){
				/* propertychange在IE8、IE9下出现bug
				text.bind("propertychange",function(event){
					return isPhoneChange(this,this.value,arry[0],arry[1]);//数字
				});	
				*/
				//绑定粘贴 的事件
				text.bind('paste',function(event){
					return isPhonePaste(event,clipboardData.getData("Text"),this,this.value,arry[0],arry[1]);//数字
		   		});
				//绑定鼠标拖入的事件
				text.bind('drop',function(event){
					//禁用拖拽功能
					return false;
		   		});
				//绑定键盘按下的事件
				text.keydown(function(event){
					return isPhoneDown(event,this,this.value,arry[0],arry[1]);//数字
		   		});
				//绑定键盘弹起的事件
				text.keyup(function(event){
					return isPhoneUp(event,this,this.value,arry[0],arry[1]);
		   		});
			}else{
				for(var i=0;i<text.length;i++){
					text.get(i).addEventListener("input",function(o){
						return isPhoneChange(this,this.value,arry[0],arry[1]);//数字
					});	
				}
				
			}
								
		}
	};
	
	//邮政编码验证
	if(isUndefined(text,"isPostalCode")){
		//等于true进行验证
		if(isTrueFalse(text,"isPostalCode")){
			var arry=numSizeArray(text,"numsize");//分隔符前面字符长度，分隔符后面长度 
			if($.browser.msie){
				//绑定粘贴 的事件
				text.bind('paste',function(event){
					return isPostalCodePaste(event,clipboardData.getData("Text"),this,this.value,arry[0],arry[1]);//数字
		   		});
				//绑定鼠标拖入的事件
				text.bind('drop',function(event){
					//禁用拖拽功能
					return false;
		   		});
				//绑定键盘按下的事件
				text.keydown(function(event){
					return isPostalCodeDown(event,this,this.value,arry[0],arry[1]);//数字
		   		});
				//绑定键盘弹起的事件
				text.keyup(function(event){
					return isPostalCodeUp(event,this,this.value,arry[0],arry[1]);
		   		});
			}else{
				for(var i=0;i<text.length;i++){
					text.get(i).addEventListener("input",function(o){
						return isPostalCodeChange(this,this.value,arry[0],arry[1]);//数字
					});	
				}
			}
		}
	};
	
	
	//验证日期
	if(isUndefined(text,"isDate")){
		//等于true进行验证
		if(isTrueFalse(text,"isDate")){
            text.bind("change",function(event){
				return isvaliDate(text);//数字
			});		
		}
	};
	
};

//为空的数据显示红色
isNullRed=function(text){

	text.bind("blur",function(event){
		if(""==text.val()){
			text.css("background-color","red");
		}else{
			text.css("background-color","#FFFFFF");
		}
	});	

};

//验证下拉框
checkSelect=function(select){
	
	if(isUndefined(select,"isNull")){
		//等于true进行验证
		if(isTrueFalse(select,"isNull")){
			select.bind("change",function(event){
            	if(""==select.val()){
					select.css("background-color","red");
				}else{
					select.css("background-color","#FFFFFF");
				}
			});		
		}
	};
	
};

checkCheckbox=function(checkbox){
	
	if(isUndefined(select,"isNull")){
		//等于true进行验证
		if(isTrueFalse(select,"isNull")){
			checkbox.bind("checked",function(event){
            	if(""==select.val()){
					select.css("background-color","red");
				}else{
					select.css("background-color","#FFFFFF");
				}
			});		
		}
	};
	
};



//obj这个对象，num输入框的值，len 横杠前参数，preLen 横杠后参数
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
				 $(obj).css("background-color","#FFFFFF");
				return true;
		   }else{
			   values=values.substr(0,values.length-1);
			   $(obj).val(values);
				return false;
		   }
		}	
};

//判断整数
isIntegerChange=function(obj,num,len){
	
	 //两种类型
	   var patrn = new RegExp("^\\d{0,"+len+"}$"); 
	   	if(num.length>0){
		   if(num.search(patrn) != -1 ){ //匹配
			   $(obj).css("background-color","#FFFFFF");
			   return true;
		   }else{
			   num=num.substr(0,num.length-1);
			   
			   if(num.substr(0,2)=='00'){
		   			return false;
		   		}			   
			   $(obj).val(num);
				return false;
		   }
	   	}
	
};

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
		   if(num.search(patrn) != -1 && num.search(patrn2) == -1 && num.search(patrn3) == -1){ //匹配
			   $(obj).css("background-color","#FFFFFF");
			   return true;
		   }else{
			   num=num.substr(0,num.length-1);
			   
			   if(num.substr(0,2)=='00'){
		   			return false;
		   		}			   
			   $(obj).val(num);
				return false;
		   }
	   	}
	   	return false;

};

//判断有符号整数
isSignIntegerChange=function(obj,num,len){
	
	 //两种类型
	   var patrn = new RegExp("^\\-?\\d{0,"+len+"}$"); 
	   	if(num.length>0){
		   if(num.search(patrn) != -1 ){ //匹配
			   $(obj).css("background-color","#FFFFFF");
			   return true;
		   }else{
			   num=num.substr(0,num.length-1);
			   
			   if(num.substr(0,2)=='00'||num.substr(0,3)=='-00'){
		   			return false;
		   		}			   
			   $(obj).val(num);
				return false;
		   }
	   	}
	
};

//判断有符号数字
isSignNumberChange=function(obj,num,len,preLen) {
	   //两种类型
	   var patrn = new RegExp("^\\-?\\d+(\\.?\\d*)$"); 
	   var patrn2 = new RegExp("^\\-?[0]\\d+$");
	   var patrn3 = new RegExp("^\\-?[0]\\d+$");
	   //存在小数点,分配小数点后面的长度时
	   if(0 != preLen && num.indexOf('.') != -1){
	   		patrn = new RegExp("^\\-?\\d{0,"+len+"}(\\.\\d{0,"+preLen+"})$");
	   		patrn2 = new RegExp("^\\-?[0]\\d+\\.\\d*$");
	   		patrn3 = new RegExp("^\\.\\d*$"); 
	   //不存在小数点时
	   }else if (num.indexOf('.') == -1){
	   		patrn = new RegExp("^\\-?\\d{0,"+len+"}$");
	   		patrn2 = new RegExp("^\\-?[0]\\d+$");
	   		patrn3 = new RegExp("^\\-?[0]\\d+$");
	   }
	 
	   	if(num.length>0){
		   if(num.search(patrn) != -1 && num.search(patrn2) == -1 && num.search(patrn3) == -1){ //匹配
			   $(obj).css("background-color","#FFFFFF");
			   return true;
		   }else{
			   num=num.substr(0,num.length-1);
			   
			   if(num.substr(0,2)=='00'||num.substr(0,3)=='-00'){
		   			return false;
		   		}			   
			   $(obj).val(num);
				return false;
		   }
	   	}
	   	return false;

};

//验证日期
isvaliDate=function(obj){

	var date_ymd=/^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/;
	
	if(obj.val().search(date_ymd)!=0){
		obj.css("background-color","red");
		return false;
	}

	//obj.css("border-bottom","#0066CC");
	obj.css("background-color","#FFFFFF");
	//obj.attr("style","background-color:#FFFFFF; border-bottom:#0066CC;");
	return true;	

};

//遍历分隔符
numSizeArray=function(obj,str){
	
	var arry=new Array();
	//验证是否有值,没有格式要求是整数 
	if(isUndefined(obj,"numsize")){
		arry=obj.attr(str).split(",");//解析数组
		//分隔符前面字符长度，分隔符后面长度 
		if(arry.length==2){
			arry[2]=(parseInt(arry[0])+parseInt(arry[1])+1);//1代表分隔符
		}else if(arry.length==1){
			arry[2]=arry[0];
			arry[1]=0;
		}
	}else{
		arry[0]=12;
		arry[1]=0;
		arry[2]=12;
	}
	
	//alert(arry[2]);
	obj.attr("maxlength",arry[2]);
	return arry;
	
};

//验证属性对象是都存在
isUndefined=function(obj,str){

	  if(undefined==obj.attr(str)){
			return false;			
		}else{
			if(""==obj.attr(str)){
				return false;
			}
		
		}

		return true;
};

//验证是否需要进行验证
isTrueFalse=function(obj,str){

	if("true".toLowerCase()==obj.attr(str).toLowerCase())
		return true;

	return false;
};

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
 * 电话验证
 * */
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
/*
 * 输入框只能输入整型、小数类型
 * 数字框键按下时触发的事件
 * 调用方法:onkeydown="javascript:return isNumberDown(event,value);"
 * */
isNumberDown = function (e,obj,num,len,preLen) {
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
				              (code == 8) || (code == 229)) {
						numVal = num; //备份值
						$(obj).css("background-color","#FFFFFF");
						return true;	
				   } else {  
				        return false;
				   }
			   //已经存在数据时
			   }else{
					if (((code > 47) && (code < 58)) || ((code > 95) && (code < 106)) || 
				              (code == 8) || (code == 37) || (code == 39) || 
				              (code == 46)||(code == 110)||(code == 190) || (code == 229)) {
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
			    $(obj).css("background-color","#FFFFFF");
				return true;
		   }else{
				$(obj).val(numVal); //控件重置为备份值
				return false;
		   }
	   }
};

/**
 * 电话输入框
 * 输入框框键按下时触发的事件
 * */
isPhoneDown = function (e,obj,num,len,preLen) {
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
	   //alert("code is: " + code);
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
				              (code == 8) || (code == 229)) {
						tepVal = num; //备份值
						$(obj).css("background-color","#FFFFFF");
						return true;	
				   } else {  
				        return false;
				   }
			   //已经存在数据时
			   }else{
					if (((code > 47) && (code < 58)) || ((code > 95) && (code < 106)) || 
				              (code == 8) || (code == 37) || (code == 39) || 
				              (code == 109)||(code == 189) || (code == 229)) {
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
			    $(obj).css("background-color","#FFFFFF");
				return true;
		   }else{
				$(obj).val(tepVal); //控件重置为备份值
				return false;
		   }
	   }
};

/**
* 数字粘贴方法 weifeng.tao 2012.10.24
*/
isNumberPaste=function(e,pasteVal,obj,orgValue,len,preLen) {
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
				   $(obj).css("background-color","#FFFFFF");
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
isPhonePaste=function(e,pasteVal,obj,orgValue,len,preLen) {
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
					$(obj).css("background-color","#FFFFFF");
					return true;
			    }else{
				   /*values=values.substr(0,values.length-1);
				   $(obj).val(values);*/
				   return false;
			    }
			 }
			 return false;
};

/**
 * 邮政编码输入框
 * 输入框框键按下时触发的事件
 * */
isPostalCodeDown = function (e,obj,num,len,preLen) {
	   var patrn = new RegExp("^\\d{0,"+len+"}$");
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
				   if ( ((code > 47) && (code < 58)) ||  ((code > 95) && (code < 106))|| 
				              (code == 8)) {
						postalCodeVal = num; //备份值
						$(obj).css("background-color","#FFFFFF");
						return true;	
				   } else {  
				        return false;
				   }
			   //已经存在数据时
			   }else{
					if (((code > 47) && (code < 58)) || ((code > 95) && (code < 106))|| 
				              (code == 8)) {
						if(num.search(patrn) != -1){
							postalCodeVal = num;//备份值
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
 * 邮政编码只能输入整型
 * 邮政编码键弹起时触发的事件
 * */
function isPostalCodeUp(e,obj,num,len,preLen) {
	   var patrn = new RegExp("^\\d{0,"+len+"}$");
	   evt=window.event||e;
	   code=evt.keyCode||evt.which;
	  
	   if(null!=num&&(""!=num)){
		   if(num.search(patrn) != -1){ //匹配
			    $(obj).css("background-color","#FFFFFF");
				return true;
		   }else{
				$(obj).val(postalCodeVal); //控件重置为备份值
				return false;
		   }
	   }
};

/**
* 邮政编码粘贴方法 weifeng.tao
*/
isPostalCodePaste=function(e,pasteVal,obj,orgValue,len,preLen) {
		   //获取鼠标位置、选中值
		   var range = document.selection.createRange();
		   var rangeText = range.text;
		   range.moveStart("character",-orgValue.length);
	       //获取粘贴后的拼接的字符串值
		   var values = '';
		   values = values.concat(orgValue.substr(0,range.text.length-rangeText.length),pasteVal,orgValue.substr(range.text.length,orgValue.length));
		   var patrn = new RegExp("^\\d{0,"+len+"}$");
			 
			 if(values.length>0){
				if(values.search(patrn) != -1){ //匹配
					$(obj).css("background-color","#FFFFFF");
					return true;
			    }else{
				   /*values=values.substr(0,values.length-1);
				   $(obj).val(values);*/
				   return false;
			    }
			 }
			 return false;
};

isPostalCodeChange=function(obj,values,len,preLen){
	 var patrn = new RegExp("^\\d{0,"+len+"}$"); 
	 
	if(values.length>0){
		if(values.search(patrn) != -1){ //匹配
			 $(obj).css("background-color","#FFFFFF");
			return true;
	   }else{
		   values=values.substr(0,values.length-1);
		   $(obj).val(values);
			return false;
	   }
	}	
};

/*
 * 输入框只能有符号输入整型、小数类型
 * 数字框键按下时触发的事件
 * 调用方法:onkeydown="javascript:return isNumberDown(event,value);"
 * */
isSignNumberDown = function (e,obj,num,len,preLen) {
		//两种类型
	   //var partn = /^\d+(\.?\d*)$/;
	   //var patrn = new RegExp("^\\d+(\\.?\\d*)$"); 
	   var patrn = new RegExp("^\\-?\\d+(\\.?\\d*)$"); 
	   //存在小数点,分配小数点后面的长度时
	   if(0 != preLen && num.indexOf('.') != -1){
	   		patrn = new RegExp("^\\-?\\d{0,"+len+"}(\\.\\d{0,"+preLen+"})$");
	   //不存在小数点时
	   }else if (num.indexOf('.') == -1){
	   		patrn = new RegExp("^\\-?\\d{0,"+len+"}$");
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
				              (code == 8) || (code == 229)|| (code == 189)) {
						signNumVal = num; //备份值
						$(obj).css("background-color","#FFFFFF");
						return true;	
				   } else {  
				        return false;
				   }
			   //已经存在数据时
			   }else{
					if (((code > 47) && (code < 58)) || ((code > 95) && (code < 106)) || 
				              (code == 8) || (code == 37) || (code == 39) || 
				              (code == 46)||(code == 110)||(code == 190) || (code == 229)) {
						if(num.search(patrn) != -1){
							signNumVal = num;//备份值
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
 * 输入框只能输入有符号整型、小数类型
 * 数字框键弹起时触发的事件
 * */
function isSignNumberUp(e,obj,num,len,preLen) {
	   //两种类型
	   //var partn = /^\d+(\.?\d*)$/;
	   //var patrn = new RegExp("^\\d+(\\.?\\d*)$"); 
	   var patrn = new RegExp("^\\-?\\d+(\\.?\\d*)$"); 
	   var patrn2 = new RegExp("^\\-?[0]\\d+$");
	   var patrn3 = new RegExp("^\\-?[0]\\d+$");
	   //存在小数点,分配小数点后面的长度时
	   if(0 != preLen && num.indexOf('.') != -1){
	   		patrn = new RegExp("^\\-?\\d{0,"+len+"}(\\.\\d{0,"+preLen+"})$");
	   		patrn2 = new RegExp("^\\-?[0]\\d+\\.\\d*$");
	   		patrn3 = new RegExp("^\\.\\d*$"); 
	   //不存在小数点时
	   }else if (num.indexOf('.') == -1){
	   		patrn = new RegExp("^\\-?\\d{0,"+len+"}$");
	   		patrn2 = new RegExp("^\\-?[0]\\d+$");
	   		patrn3 = new RegExp("^\\-?[0]\\d+$");
	   }
	   evt=window.event||e;
	   code=evt.keyCode||evt.which;
	  
	   if(null!=num&&(""!=num)){
		   if(num.search(patrn) != -1 && num.search(patrn2) == -1 && num.search(patrn3) == -1){ //匹配
			    $(obj).css("background-color","#FFFFFF");
				return true;
		   }else{
				$(obj).val(signNumVal); //控件重置为备份值
				return false;
		   }
	   }
};

/**
* 有符号数字粘贴方法
*/
isSignNumberPaste=function(e,pasteVal,obj,orgValue,len,preLen) {
		   //获取鼠标位置、选中值
		   var range = document.selection.createRange();
		   var rangeText = range.text;
		   range.moveStart("character",-orgValue.length);
	       //获取粘贴后的拼接的字符串值
		   var num = '';
		   num = num.concat(orgValue.substr(0,range.text.length-rangeText.length),pasteVal,orgValue.substr(range.text.length,orgValue.length));
		   //两种类型
		   var patrn = new RegExp("^\\-?\\d+(\\.?\\d*)$"); 
		   var patrn2 = new RegExp("^\\-?[0]\\d+$");
		   var patrn3 = new RegExp("^\\-?[0]\\d+$");
		   //存在小数点,分配小数点后面的长度时
		   if(0 != preLen && num.indexOf('.') != -1){
		   		patrn = new RegExp("^\\-?\\d{0,"+len+"}(\\.\\d{0,"+preLen+"})$");
		   		patrn2 = new RegExp("^\\-?[0]\\d+\\.\\d*$");
		   		patrn3 = new RegExp("^\\.\\d*$"); 
		   		
		   //不存在小数点时
		   }else if (num.indexOf('.') == -1){
		   		patrn = new RegExp("^\\-?\\d{0,"+len+"}$");
		   		patrn2 = new RegExp("^\\-?[0]\\d+$");
		   		patrn3 = new RegExp("^\\-?[0]\\d+$");
		   }
		   
		   	if(num.length>0){
		   		//alert(num.substr(0,2));		   	
		   		//num.search(patrn2) == -1 &&
			   if(num.search(patrn) != -1 && num.search(patrn2) == -1 && num.search(patrn3) == -1){ //匹配
				   $(obj).css("background-color","#FFFFFF");
				   return true;
			   }else{
				    num=num.substr(0,num.length-1);
				    
				    if(num.substr(0,2)=='00'||num.substr(0,3)=='-00'){
			   			return false;
			   		}/*else if(num.substr(0,1)=="."){
			   			return false;
			   		} */
				    
					return false; 		  
			   }
		   	}
		   	return false;
};
