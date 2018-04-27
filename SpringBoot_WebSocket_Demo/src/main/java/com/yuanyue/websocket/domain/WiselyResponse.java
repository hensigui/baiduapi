package com.yuanyue.websocket.domain;
/**
 * 服务器消息实体
 * @author yuanyue
 *
 */
public class WiselyResponse {
	
	private String responseMessage;

	public WiselyResponse(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	
}
