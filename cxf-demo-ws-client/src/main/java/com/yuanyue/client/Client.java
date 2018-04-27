package com.yuanyue.client;

import java.util.List;

import com.yuanyue.interceptor.AddHeaderInterceptor;
import com.yuanyue.webservice.HelloWorld;
import com.yuanyue.webservice.HelloWorldService;
import com.yuanyue.webservice.Role;
import com.yuanyue.webservice.User;
import com.yuanyue.webservice.UserInfo;
import com.yuanyue.webservice.UserInfoArray;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;

public class Client {
	public static void main(String[] args) {
		HelloWorldService service=new HelloWorldService();
        HelloWorld helloWorld=service.getHelloWorldPort();
        
        org.apache.cxf.endpoint.Client client=ClientProxy.getClient(helloWorld);
        client.getInInterceptors().add(new LoggingInInterceptor()); // ���in������ ��־������
        client.getOutInterceptors().add(new LoggingOutInterceptor()); // ���out������
        
        client.getOutInterceptors().add(new AddHeaderInterceptor("yuanyue", "123"));// ���out header������֤��Ϣ������
        
        //System.out.println(helloWorld.say("����Ա"));
        //testJavaBean(helloWorld);
        testMap(helloWorld);
	}
	/**
	 * CXF����Map�ȸ�������
	 * @param helloWorld
	 */
	private static void testMap(HelloWorld helloWorld) {
		UserInfoArray infoArray = helloWorld.getAllUserInfo();
        List<UserInfo> userInfoList = infoArray.getItem();
        for (UserInfo userInfo : userInfoList) {
			System.out.print(userInfo.getKey()+":");
			for(Role role : userInfo.getValue()) {
				System.out.print(role.getId() + "," + role.getRoleName() + " ");
			}
			System.out.println();
		}
	}
	/**
	 * CXF����javabean
	 * @param helloWorld
	 */
	@SuppressWarnings("unused")
	private static void testJavaBean(HelloWorld helloWorld) {
		User user=new User();
        user.setUserName("yuanyue");
        user.setPassword("123");
        List<Role> roleList=helloWorld.getRoleByUser(user);
        for(Role role:roleList){
            System.out.println(role.getId()+","+role.getRoleName());
        }
	}
}
