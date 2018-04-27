package com.yuanyue.interceptor;

import java.util.List;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class MyInterceptor extends AbstractPhaseInterceptor<SoapMessage>{

	public MyInterceptor(){
        // 在调用方法之前调用拦截器
        super(Phase.PRE_INVOKE);
    }
 
    /**
     * 拦截获取消息
     */
    public void handleMessage(SoapMessage message) throws Fault {
        List<Header> headers=message.getHeaders();
        if(headers==null || headers.size()==0){
            throw new Fault(new IllegalArgumentException("没有Header,拦截器实施拦截"));
        }
        Header firstHeader=headers.get(0);
        Element ele=(Element) firstHeader.getObject();
        NodeList userIds=ele.getElementsByTagName("userName");
        NodeList userPasses=ele.getElementsByTagName("password");
         
        if(userIds.getLength()!=1){
            throw new Fault(new IllegalArgumentException("用户名格式不对"));
        }
         
        if(userPasses.getLength()!=1){
            throw new Fault(new IllegalArgumentException("密码格式不对"));
        }
         
        String userId=userIds.item(0).getTextContent();
        String userPass=userPasses.item(0).getTextContent();
         
        if(!userId.equals("yuanyue") || ! userPass.equals("123")){
            throw new Fault(new IllegalArgumentException("用户名或者密码不正确"));
        }
         
    }

}
