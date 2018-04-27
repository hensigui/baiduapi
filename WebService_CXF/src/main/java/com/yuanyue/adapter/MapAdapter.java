package com.yuanyue.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.yuanyue.entity.Role;
/**
 * ����cxf����map��������
 * @author yuanyue
 *
 */
public class MapAdapter extends XmlAdapter<UserInfo[], Map<String,List<Role>>>{
	/**
	 * ���Զ��������������ת��Ϊmap
	 */
	@Override
	public Map<String, List<Role>> unmarshal(UserInfo[] v) throws Exception {
		 Map<String, List<Role>> map=new HashMap<String,List<Role>>();
	        for(int i=0;i<v.length;i++){
	        	UserInfo r=v[i];
	            map.put(r.getKey(), r.getValue());
	        }
	     return map;
	}
	/**
	 * ��mapת��Ϊ�Զ��������������
	 */
	@Override
	public UserInfo[] marshal(Map<String, List<Role>> v) throws Exception {
		UserInfo[] userInfos = new UserInfo[v.size()];
		int i=0;
        for(String key:v.keySet()){
        	userInfos[i]=new UserInfo();
        	userInfos[i].setKey(key);
        	userInfos[i].setValue(v.get(key));
            i++;
        }
        return userInfos;
	}

}
