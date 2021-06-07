package lsq.Bryan.service;

import java.util.List;

import lsq.Bryan.domain.Cart;
import lsq.Bryan.domain.Category;
import lsq.Bryan.domain.Good;
import lsq.Bryan.domain.Order;
import lsq.Bryan.domain.PageBean;
import lsq.Bryan.domain.Privilege;
import lsq.Bryan.domain.QueryInfo;
import lsq.Bryan.domain.User;

public interface BusinessService {

	/********************************************
	 * 分类相关的服务
	 ********************************************/
	void addCategory(Category c);

	Category findCategory(String id);

	List<Category> getAllCategory();
	List<Category> getAllCategory(String userid);
	
	void deleteCategory(String id);

	/********************************************
	 * 商品相关的服务
	 ********************************************/
	void addGood(Good good);

	Good findGood(String id);

	PageBean goodPageQuery(QueryInfo info);
	PageBean goodPageQuery2(QueryInfo info,List<Category> categories);
	public List<Good> getAllGood();
	public List<Good> getGoodbyUserid(String userid);
	
	void deleteGood(String id);

	/********************************************
	 * 用户相关的服务
	 ********************************************/
	void addUser(User user);

	User findUser(String username, String password);

	User findUser(String id);

	/********************************************
	 * 订单相关的服务
	 ********************************************/
	//使用用户的购物车来生成一个订单，然后存入数据库中
	void saveOrder(Cart cart, User user);

	Order findOrder(String id);

	List<Order> getOrderByStatus(boolean status);
	List<Order> getOrderByUsername(String username);
	
	//更新订单状态
	public void updatOrder(String id, boolean status);
	
	//得到用户的所有权限
	List<Privilege> getUserAllPrivilege(String userId);
	List<Privilege> getUserAllPrivilege(User user);
	//删除指定购物车中的购物项
	public void deleteCartItem(String id, Cart cart);
	
	// 清空购物车
	public void clearCart(Cart cart) ;

	// 改变购物车某购物项的数量
	public void changeItemQuantity(String id, String quantity, Cart cart);
	
	public void saveIp(String addr, String userId, Boolean status);
	
	public void saveOp(String userid, String op);
	
	public void updatescore1(String userid,String categoryid);
	public void updatescore2(String userid,String categoryid);
	public void createscore(String userid);
}
