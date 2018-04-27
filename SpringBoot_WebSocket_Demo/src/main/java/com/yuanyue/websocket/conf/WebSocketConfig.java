package com.yuanyue.websocket.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	/**
	 * 注册STOMP协议节点endpoint,并映射指定url
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/endpointWisely").withSockJS();//注册一个endpoint并指定适用Socket协议
		registry.addEndpoint("/endpointChat").withSockJS();//1
	}
	
	/**
	 * 配置消息代理
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/queue","/topic");//增加一个广播式、一个点对点消息代理
	}

	
}
