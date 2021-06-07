package lsq.Bryan.dao;

import java.util.List;

import lsq.Bryan.domain.Privilege;
import lsq.Bryan.domain.User;

public interface UserDao {

	void add(User user);

	User find(String id);

	User find(String username, String password);
	
	//�õ��û�������Ȩ��
	public List<Privilege> getAllPrivilege(String userId);
	public List<Privilege> getAllPrivilege(User user);
}
