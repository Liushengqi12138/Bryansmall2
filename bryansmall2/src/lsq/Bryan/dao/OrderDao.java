package lsq.Bryan.dao;

import java.util.List;

import lsq.Bryan.domain.Order;

public interface OrderDao {

	void add(Order o);
	//Ҫ��Order����Ļ�����Ϣ�һ�������Ҫ���û�����Ϣ�һ�������Ҫ�Ѷ�����������Ϣ�һ������ܹ�Ҫ����4�ű�
	Order find(String id);
	List<Order> getAll(boolean status);//�鿴������û����
	List<Order> getAllbyUsername(String username);
	public void update(String id, boolean status);//���¶���״̬
}
