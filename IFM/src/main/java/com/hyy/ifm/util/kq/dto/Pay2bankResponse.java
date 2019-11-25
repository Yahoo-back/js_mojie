package com.hyy.ifm.util.kq.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 报文实体
 * @author zhiwei.ma
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement  
@XmlType(name = "pay2bankResponse", propOrder = {"pay2bankHead","responseBody"})  
public class Pay2bankResponse {

	@XmlElement(name = "pay2bankHead")  
	private Pay2bankHead pay2bankHead;
	
	@XmlElement(name = "responseBody")  
	private ResponseBody responseBody;



	public Pay2bankHead getPay2bankHead() {
		return pay2bankHead;
	}

	public void setPay2bankHead(Pay2bankHead pay2bankHead) {
		this.pay2bankHead = pay2bankHead;
	}

	public ResponseBody getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(ResponseBody responseBody) {
		this.responseBody = responseBody;
	}
	
}
