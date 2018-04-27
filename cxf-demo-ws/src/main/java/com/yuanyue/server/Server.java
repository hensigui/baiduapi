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
	 * CXFʵ�ֵ�webservice����
	 * @param implementor
	 * @param address
	 */
	private static void pushServerByCXF(HelloWorld implementor, String address) {
		JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();
        factoryBean.setAddress(address); // ���ñ�¶��ַ
        factoryBean.setServiceClass(HelloWorld.class); // �ӿ���
        factoryBean.setServiceBean(implementor); // ����ʵ����
        factoryBean.getInInterceptors().add(new LoggingInInterceptor()); // ���in������ ��־������
        factoryBean.getOutInterceptors().add(new LoggingOutInterceptor()); // ���out������
        factoryBean.getInInterceptors().add(new MyInterceptor());//���in�Զ���������
        factoryBean.create();
	}
	
	/**
	 * JDKʵ�ֵ�webservice����
	 * @param implementor
	 * @param address
	 */
	@SuppressWarnings("unused")
	private static void pushServerByJDK(HelloWorld implementor, String address) {
		Endpoint.publish(address, implementor);  // JDKʵ��
	}
}
