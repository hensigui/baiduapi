
package com.yuanyue.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.yuanyue.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAllUserInfo_QNAME = new QName("http://webservice.yuanyue.com/", "getAllUserInfo");
    private final static QName _GetAllUserInfoResponse_QNAME = new QName("http://webservice.yuanyue.com/", "getAllUserInfoResponse");
    private final static QName _GetRoleByUser_QNAME = new QName("http://webservice.yuanyue.com/", "getRoleByUser");
    private final static QName _GetRoleByUserResponse_QNAME = new QName("http://webservice.yuanyue.com/", "getRoleByUserResponse");
    private final static QName _Say_QNAME = new QName("http://webservice.yuanyue.com/", "say");
    private final static QName _SayResponse_QNAME = new QName("http://webservice.yuanyue.com/", "sayResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.yuanyue.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllUserInfo }
     * 
     */
    public GetAllUserInfo createGetAllUserInfo() {
        return new GetAllUserInfo();
    }

    /**
     * Create an instance of {@link GetAllUserInfoResponse }
     * 
     */
    public GetAllUserInfoResponse createGetAllUserInfoResponse() {
        return new GetAllUserInfoResponse();
    }

    /**
     * Create an instance of {@link GetRoleByUser }
     * 
     */
    public GetRoleByUser createGetRoleByUser() {
        return new GetRoleByUser();
    }

    /**
     * Create an instance of {@link GetRoleByUserResponse }
     * 
     */
    public GetRoleByUserResponse createGetRoleByUserResponse() {
        return new GetRoleByUserResponse();
    }

    /**
     * Create an instance of {@link Say }
     * 
     */
    public Say createSay() {
        return new Say();
    }

    /**
     * Create an instance of {@link SayResponse }
     * 
     */
    public SayResponse createSayResponse() {
        return new SayResponse();
    }

    /**
     * Create an instance of {@link UserInfo }
     * 
     */
    public UserInfo createUserInfo() {
        return new UserInfo();
    }

    /**
     * Create an instance of {@link Role }
     * 
     */
    public Role createRole() {
        return new Role();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link UserInfoArray }
     * 
     */
    public UserInfoArray createUserInfoArray() {
        return new UserInfoArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllUserInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.yuanyue.com/", name = "getAllUserInfo")
    public JAXBElement<GetAllUserInfo> createGetAllUserInfo(GetAllUserInfo value) {
        return new JAXBElement<GetAllUserInfo>(_GetAllUserInfo_QNAME, GetAllUserInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllUserInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.yuanyue.com/", name = "getAllUserInfoResponse")
    public JAXBElement<GetAllUserInfoResponse> createGetAllUserInfoResponse(GetAllUserInfoResponse value) {
        return new JAXBElement<GetAllUserInfoResponse>(_GetAllUserInfoResponse_QNAME, GetAllUserInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRoleByUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.yuanyue.com/", name = "getRoleByUser")
    public JAXBElement<GetRoleByUser> createGetRoleByUser(GetRoleByUser value) {
        return new JAXBElement<GetRoleByUser>(_GetRoleByUser_QNAME, GetRoleByUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRoleByUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.yuanyue.com/", name = "getRoleByUserResponse")
    public JAXBElement<GetRoleByUserResponse> createGetRoleByUserResponse(GetRoleByUserResponse value) {
        return new JAXBElement<GetRoleByUserResponse>(_GetRoleByUserResponse_QNAME, GetRoleByUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Say }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.yuanyue.com/", name = "say")
    public JAXBElement<Say> createSay(Say value) {
        return new JAXBElement<Say>(_Say_QNAME, Say.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.yuanyue.com/", name = "sayResponse")
    public JAXBElement<SayResponse> createSayResponse(SayResponse value) {
        return new JAXBElement<SayResponse>(_SayResponse_QNAME, SayResponse.class, null, value);
    }

}
