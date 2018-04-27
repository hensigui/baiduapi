package com.yuanyue.webservice;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.yuanyue.adapter.MapAdapter;
import com.yuanyue.entity.Role;
import com.yuanyue.entity.User;

@WebService
public interface HelloWorld {
	String say(String str);
	/**
	 * �����û������Ӧ��ɫ
	 * @param user
	 * @return
	 */
	List<Role> getRoleByUser(User user);
	/**
	 * ��ȡȫ���û���Ϣ,����������map����������
	 * @return
	 */
	@XmlJavaTypeAdapter(MapAdapter.class)
	Map<String,List<Role>> getAllUserInfo();
}
