package com.yuanyue.server;

import javax.xml.ws.Endpoint;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import com.yuanyue.interceptor.MyInterceptor;
import com.yuanyue.webservice.HelloWorld;
import com.yuanyue.webservice.impl.HelloWorldImpl;

public class Server {
	
	public static void main(String[] args) {
		System.out.println("web service start");  
        HelloWorld implementor = new HelloWorldImpl();  
        String address = "http://localhost/helloWorld";  
        //pushServerByJDK(implementor, address);
        pushServerByCXF(implementor, address);      
        System.out.println("web service started");  
	}
	/**
	 * CXF实现的webservice推送
	 * @param implementor
	 * @param address
	 */
	private static void pushServerByCXF(HelloWorld implementor, String address) {
		JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();
        factoryBean.setAddress(address); // 设置暴露地址
        factoryBean.setServiceClass(HelloWorld.class); // 接口类
        factoryBean.setServiceBean(implementor); // 设置实现类
        factoryBean.getInInterceptors().add(new LoggingInInterceptor()); // 添加in拦截器 日志拦截器
        factoryBean.getOutInterceptors().add(new LoggingOutInterceptor()); // 添加out拦截器
        factoryBean.getInInterceptors().add(new MyInterceptor());//添加in自定义拦截器
        factoryBean.create();
	}
	
	/**
	 * JDK实现的webservice推送
	 * @param implementor
	 * @param address
	 */
	@SuppressWarnings("unused")
	private static void pushServerByJDK(HelloWorld implementor, String address) {
		Endpoint.publish(address, implementor);  // JDK实现
	}
}
