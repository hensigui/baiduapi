package com.yuanyue.interceptor;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AddHeaderInterceptor extends AbstractPhaseInterceptor<SoapMessage>{

	private String userName;
    private String password;
 
    public AddHeaderInterceptor(String userName, String password) {
        super(Phase.PREPARE_SEND); // 发送SOAP消息之前调用拦截器
        this.userName=userName;
        this.password=password;
    }
 
    public void handleMessage(SoapMessage message) throws Fault {
        List<Header> headers=message.getHeaders();
         
        Document doc=DOMUtils.createDocument();
        Element ele=doc.createElement("authHeader");
        Element idElement=doc.createElement("userName");
        idElement.setTextContent(userName);
        Element passElement=doc.createElement("password");
        passElement.setTextContent(password);
         
        ele.appendChild(idElement);
        ele.appendChild(passElement);
         
        headers.add(new Header(new QName("teshuQ"),ele));
         
         
    }

}
