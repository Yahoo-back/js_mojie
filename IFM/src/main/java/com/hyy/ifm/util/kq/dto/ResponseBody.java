package com.hyy.ifm.util.kq.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 报文实体
 * @author zan.liang
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement  
@XmlType(name = "responseBody", propOrder = {"sealDataType","errorCode","errorMsg"})  
public class ResponseBody {
	
	@XmlElement(name = "errorCode")  
	private String errorCode;
	
	@XmlElement(name = "errorMsg")  
	private String errorMsg;
	
	@XmlElement(name = "sealDataType")  
	private SealDataType sealDataType;

	public SealDataType getSealDataType() {
		return sealDataType;
	}

	public void setSealDataType(SealDataType sealDataType) {
		this.sealDataType = sealDataType;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}


	
}
