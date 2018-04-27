package com.yuanyue.websocket.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.yuanyue.websocket.domain.WiselyMessage;
import com.yuanyue.websocket.domain.WiselyResponse;

@Controller
public class WsController {
	@Autowired
	private SimpMessagingTemplate messagingTemplate;//1
	
	@MessageMapping("/welcome") //类似requestMapping
	@SendTo("/topic/getResponse") //当服务器有消息时会向订阅了该路径的浏览器推送消息
	public WiselyResponse say(WiselyMessage message)throws Exception{
		Thread.sleep(3000);
		return new WiselyResponse("Welcome,"+message.getName());
	}
	
	@MessageMapping("/chat")
	public void handleChat(Principal principal,String msg) {
		if(principal.getName().equals("wyf")) {
			messagingTemplate.convertAndSendToUser("wisely", "/queue/notifications", principal.getName()+"-send:");
		}
	}
}
