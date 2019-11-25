function pic(id,obj){
	//判断HTML里面是否有文件：
	$.each(obj,function(i){
		var html = '';
		var selected0 = "";
		var selected1 = "";
		var selected2 = "";
		var selected3 = "";
		var selected4 = "";
		var selected5 = "";
		var selected6 = "";
		var selected7 = "";
		var selected8 = "";
		var selected9 = "";
		var selected10 = "";
		var selected11 = "";
		var selected12 = "";
		var selected13 = "";
		var selected14 = "";
		var selected15 = "";
		var selected16 = "";
		var selected17 = "";
		var atm = "selected";
		if(undefined != obj[i].file_name){
			switch(obj[i].file_name){
			case "0":
				selected0 = atm;break;
			case "1":
				selected1 = atm;break;
			case "2":
				selected2 = atm;break;
			case "3":
				selected3 = atm;break;
			case "4":
				selected4 = atm;break;
			case "5":
				selected5 = atm;break;
			case "6":
				selected6 = atm;break;
			case "7":
				selected7 = atm;break;
			case "8":
				selected8 = atm;break;
			case "9":
				selected9 = atm;break;
			case "10":
				selected10 = atm;break;
			case "11":
				selected11 = atm;break;
			case "12":
				selected12 = atm;break;
			case "13":
				selected13 = atm;break;
			case "14":
				selected14 = atm;break;
			case "15":
				selected15 = atm;break;
			case "16":
				selected16 = atm;break;
			case "17":
				selected17 = atm;break;
			}
		}
		var loan_product_tmp = $("#loan_product_tmp").val();
		var loan_product = $("#loan_product").val();
		/*var select = "";
		if((loan_product_tmp == '' && loan_product == '2') || loan_product_tmp == '2'){
			select = "<td id='TD"+obj[i].id+"'><select id=file_name name=file_name style='width:130px'>";
			select += "<option value=0 "+selected0+">身份证正面照</option>";
			select += "<option value=1 "+selected1+">身份证反面照</option>";
			select += "<option value=2 "+selected2+">本人手持身份证照</option>";
			select += "<option value=3 "+selected3+">央行征信报告</option>";
			select += "<option value=4 "+selected4+">社保截图</option>";
			select += "<option value=5 "+selected5+">劳务合同</option>";
			select += "<option value=6 "+selected6+">收支流水（3-6个月工资流水）</option>";
			select += "<option value=7 "+selected7+">有力资产证明</option>";
			select += "<option value=8 "+selected8+">水电费物业费发票（其一）</option>";
			select += "<option value=9 "+selected9+">房屋租赁合同</option>";
			select += "<option value=12 "+selected12+">手持消费合同、身份证与面签员合影</option>";
			select += "<option value=13 "+selected13+">现场填写合同按手印照片</option>";
			select += "<option value=10 "+selected10+">收货证明</option>";
			select += "</select><img src="+ctx+"/static/images/del.png style=height:15px title=删除图片 onclick=delPic('TD"+obj[i].id+"')><br>";
		}else if((loan_product_tmp == '' && loan_product == '7') || loan_product_tmp == '7'){
			select = "<td id='TD"+obj[i].id+"'><select id=file_name name=file_name style='width:130px'>";
			select += "<option value=0 "+selected0+">身份证正面照</option>";
			select += "<option value=1 "+selected1+">身份证反面照</option>";
			select += "<option value=2 "+selected2+">本人手持身份证照</option>";
			select += "<option value=3 "+selected3+">身份证正面+消费服务合同</option>";
			select += "<option value=4 "+selected4+">收货确认书</option>";
			select += "<option value=5 "+selected5+">现场填写合同按手印照片</option>";
			select += "</select><img src="+ctx+"/static/images/del.png style=height:15px title=删除图片 onclick=delPic('TD"+obj[i].id+"')><br>";
		}else if((loan_product_tmp == '' && loan_product == '6') || loan_product_tmp == '6'){
			select = "<td id='TD"+obj[i].id+"'><select id=file_name name=file_name style='width:130px'>";
			select += "<option value=0 "+selected0+">身份证正面照</option>";
			select += "<option value=1 "+selected1+">身份证反面照</option>";
			select += "<option value=2 "+selected2+">身份证正面+消费服务合同</option>";
			select += "<option value=3 "+selected3+">房屋租赁合同（第一页）</option>";
			select += "<option value=4 "+selected4+">房屋租赁合同（末页）</option>";
			select += "<option value=5 "+selected5+">租客手持合同照片</option>";
			select += "</select><img src="+ctx+"/static/images/del.png style=height:15px title=删除图片 onclick=delPic('TD"+obj[i].id+"')><br>";
		}else if((loan_product_tmp == '' && loan_product == '4') || loan_product_tmp == '4'){
			select = "<td id='TD"+obj[i].id+"'><select id=file_name name=file_name style='width:130px'>";
			select += "<option value=0 "+selected0+">身份证正面照</option>";
			select += "<option value=1 "+selected1+">身份证反面照</option>";
			select += "<option value=2 "+selected2+">手持身份证</option>";
			select += "<option value=3 "+selected3+">手持消费合同、身份证与办单员合照</option>";
			select += "<option value=5 "+selected5+">（银行）悦分期申请表</option>";
			select += "<option value=6 "+selected6+">现场填写合同按手印照片</option>";
			select += "<option value=8 "+selected8+">（银行）银行借款凭证</option>";
			select += "<option value=9 "+selected9+">（银行）身份证正面与学生证</option>";
			select += "<option value=10 "+selected10+">（银行）悦分期借款合同</option>";
			select += "<option value=11 "+selected11+">（银行）手持校悦汇分期消费服务合同</option>";
			select += "<option value=12 "+selected12+">悦才分期消费服务合同</option>";
			select += "<option value=13 "+selected13+">（新生）学费缴费单</option>";
			select += "<option value=14 "+selected14+">（新生）录取通知书</option>";
			select += "<option value=15 "+selected15+">（新生）教务网截图</option>";
			select += "<option value=16 "+selected16+">（新生）学校录取信息查询网页截图</option>";
			select += "<option value=17 "+selected17+">收货确认书</option>";
			select += "</select><img src="+ctx+"/static/images/del.png style=height:15px title=删除图片 onclick=delPic('TD"+obj[i].id+"')><br>";
		}else if((loan_product_tmp == '' && loan_product == '9') || loan_product_tmp == '9'){
			select = "<td id='TD"+obj[i].id+"'><select id=file_name name=file_name style='width:130px'>";
			select += "<option value=0 "+selected0+">身份证正面照</option>";
			select += "<option value=1 "+selected1+">身份证反面照</option>";
			select += "<option value=2 "+selected2+">手持身份证正面与工作人员合影照片</option>";
			select += "<option value=12 "+selected12+">0元购购物合同照片</option>";
			select += "<option value=17 "+selected17+">产品交付确认单照片</option>";
			select += "<option value=3 "+selected3+">客户手持产品交付确认单照片</option>";
			select += "</select><img src="+ctx+"/static/images/del.png style=height:15px title=删除图片 onclick=delPic('TD"+obj[i].id+"')><br>";
		}else if((loan_product_tmp == '' && loan_product == '11') || loan_product_tmp == '11'){
			select = "<td id='TD"+obj[i].id+"'><select id=file_name name=file_name style='width:130px'>";
			select += "<option value=0 "+selected0+">身份证正面照</option>";
			select += "<option value=1 "+selected1+">身份证反面照</option>";
			select += "<option value=2 "+selected2+">手持身份证正面与工作人员合影照片</option>";
			select += "<option value=12 "+selected12+">话费分期合同照片</option>";
			select += "<option value=17 "+selected17+">合同按手印照片</option>";
			select += "</select><img src="+ctx+"/static/images/del.png style=height:15px title=删除图片 onclick=delPic('TD"+obj[i].id+"')><br>";
		}else if((loan_product_tmp == '' && loan_product == '13') || loan_product_tmp == '13'){
			select = "<td id='TD"+obj[i].id+"'><select id=file_name name=file_name style='width:130px'>";
			select += "<option value=0 "+selected0+">身份证正面</option>";
			select += "<option value=1 "+selected1+">身份证反面</option>";
			select += "<option value=2 "+selected2+">手持身份证照</option>";
			select += "<option value=4 "+selected4+">术前术后照</option>";
			select += "<option value=3 "+selected3+">手持消费合同照片</option>";
			select += "<option value=6 "+selected6+">现场填写合同按手印照片</option>";
			select += "<option value=11 "+selected11+">手持消费合同、身份证与面单员照片</option>";
			select += "<option value=12 "+selected12+">悦才掌柜消费服务合同</option>";
			select += "</select><img src="+ctx+"/static/images/del.png style=height:15px title=删除图片 onclick=delPic('TD"+obj[i].id+"')><br>";
		}else if((loan_product_tmp == '' && loan_product == '14') || loan_product_tmp == '14'){
			select = "<td id='TD"+obj[i].id+"'><select id=file_name name=file_name style='width:130px'>";
			select += "<option value=0 "+selected0+">身份证正面照</option>";
			select += "<option value=1 "+selected1+">身份证反面照</option>";
			select += "<option value=2 "+selected2+">手持分期合同与身份证</option>";
			select += "<option value=3 "+selected3+">工作证明（快递资格证、所在工作单位APP个人资料截图、入职证明照片，三选一）</option>";
			select += "<option value=12 "+selected12+">消费分期合同</option>";
			select += "<option value=17 "+selected17+">产品交付确认单照片</option>";
			select += "</select><img src="+ctx+"/static/images/del.png style=height:15px title=删除图片 onclick=delPic('TD"+obj[i].id+"')><br>";
		}else if((loan_product_tmp == '' && loan_product == '12') || loan_product_tmp == '12'){
			select = "<td id='TD"+obj[i].id+"'><select id=file_name name=file_name style='width:130px'><option value=17 "+selected17+">收货确认书</option><option value=0 "+selected0+">身份证正面照</option><option value=1 "+selected1+">身份证反面照</option><option value=2 "+selected2+">手持身份证</option><option value=12 "+selected12+">悦才分期消费服务合同</option><option value=3 "+selected3+">手持消费合同、身份证与办单员合照</option><option value=6 "+selected6+">现场填写合同按手印照片</option><option value=7 "+selected7+">学信网截图</option><option value=13 "+selected13+">征信报告（1）</option><option value=14 "+selected14+">征信报告（2）</option><option value=15 "+selected15+">征信报告（3）</option><option value=16 "+selected16+">征信报告（4）</option><option value=5 "+selected5+">征信报告（5）</option><option value=8 "+selected8+">家访照片（1）</option><option value=9 "+selected9+">家访照片（2）</option></select><img src="+ctx+"/static/images/del.png style=height:15px title=删除图片 onclick=delPic('TD"+obj[i].id+"')><br>";
		}else{
			select = "<td id='TD"+obj[i].id+"'><select id=file_name name=file_name style='width:130px'><option value=17 "+selected17+">收货确认书</option><option value=0 "+selected0+">身份证正面照</option><option value=1 "+selected1+">身份证反面照</option><option value=2 "+selected2+">手持身份证</option><option value=12 "+selected12+">悦才分期消费服务合同</option><option value=3 "+selected3+">手持消费合同、身份证与办单员合照</option><option value=6 "+selected6+">现场填写合同按手印照片</option><option value=7 "+selected7+">学信网截图</option><option value=13 "+selected13+">（新生）学费缴费单</option><option value=14 "+selected14+">（新生）录取通知书</option><option value=15 "+selected15+">（新生）教务网截图</option><option value=16 "+selected16+">（新生）学校录取信息查询网页截图</option><option value=5 "+selected5+">（银行）悦分期申请表</option><option value=8 "+selected8+">（银行）银行借款凭证</option><option value=9 "+selected9+">（银行）身份证正面和学生证</option><option value=10 "+selected10+">（银行）悦分期借款合同</option><option value=11 "+selected11+">（银行）手持校悦汇分期消费服务合同</option></select><img src="+ctx+"/static/images/del.png style=height:15px title=删除图片 onclick=delPic('TD"+obj[i].id+"')><br>";
		}*/
		html += " "+select+"<input name=file_uri type=hidden id=file_uri value='"+obj[i].file_uri+"'>";
		/*if((loan_product_tmp == '' && loan_product == '4') || loan_product_tmp == '4'){
			html += "<a href=### onclick=opens('"+(obj[i].file_uri)+"') ><img src='"+(obj[i].file_uri)+"' style='width:150px;height:100px' /></a></td>";
		}else{
			html += "<a href=### onclick=opens('"+(st.webSite+obj[i].file_uri)+"') ><img src='"+(st.webSite+obj[i].file_uri)+"' style='width:150px;height:100px' /></a></td>";
		}*/
		var surl = obj[i].file_uri;
		surl = surl.indexOf("http://") >= 0 ? surl : st.webSite+obj[i].file_uri;
		html += "<a href=### onclick=opens('"+(surl)+"') ><img src='"+(surl)+"' style='width:150px;height:100px' /></a></td>";
		paintTab(id,html);
	});
	/*var objs=[document.getElementsByName("file_uri")];
	$.each(objs,function(){
		alert(objs[key]);
	});
		
	
	
	alert(objs.innerHTML()+"ssss");
	var old = $("#"+id).html();
	$("#"+id).html(old+html);
	//initRebox();
*/}
function delPic(id){
	$("#"+id).remove();
}
function callBackFunction(data){
	var fes = data.fes;
	var obj = jQuery.parseJSON(fes);
	pic("zmwj",obj);
}

function opensnew(url){
	window.open(url,"_blank");
}

/**
 * 通过table的id去绘画4*4格式的表格。
 * @param tableid
 * @param uri
 */
function paintTab(tableid,trHtml){
	// 查询该table下的所有的tr
	var f = false;  // 判断现有的table中tr下的td是不是都满4个td
	var is_user = false;
	$("#"+tableid).find("tr").each(function(){
		if(is_user){
			return;
		}
		//计算每个tr下有几个td
		var tdLen =  $(this).find("td").length;
		if(tdLen < 5){
			f = true;
			if(tdLen == 0){
				$(this).append(trHtml);
				is_user = true;
			}else{
				$(this).find("td:eq("+(tdLen-1)+")").after(trHtml);
				is_user = true;
			}
		}
	});
	//若现在有tr都满格，那么就先要添加一个tr
	if(!f){
		$("#"+tableid+"").append('<tr>'+trHtml+'</tr>');
	}
}