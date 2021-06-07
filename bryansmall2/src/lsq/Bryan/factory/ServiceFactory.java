package lsq.Bryan.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import lsq.Bryan.domain.Privilege;
import lsq.Bryan.domain.User;
import lsq.Bryan.service.BusinessService;
import lsq.Bryan.service.impl.BusinessServiceImpl;
import lsq.Bryan.utils.Permission;
import lsq.Bryan.utils.SecurityException;

public class ServiceFactory {

	private ServiceFactory() {}
	private static ServiceFactory instance = new ServiceFactory();
	public static ServiceFactory getInstance() {
		return instance;
	}
	
	//BusinessServiceProxy.addCategory(Category c)    or   BusinessServiceProxy.addGood(Good b)
	public BusinessService createService(User user) {
		BusinessService service = new BusinessServiceImpl();
		
		return (BusinessService) Proxy.newProxyInstance(ServiceFactory.class.getClassLoader(), service.getClass().getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				
				//�õ�web����õķ������ƣ�����addCategory
				String methodName = method.getName();//addCategory
				
				//�������ʵ���������Ӧ�ķ����������ʵ����ķ�������û��Ȩ��ע��                                
				/*
                 * ��ʵ�������Ӧ�ķ����ʹ���������Ӧ�ķ������й�ͬ����Ϊ��
                 * ���������ķ�����ʲô��������ʵ�������Ӧ�ķ����Ͼ���ʲô������
                 * 
                 * Ҳ����˵��������ķ�����Method����ʲô���Ҿ͵õ���ʵ���������Ӧ�ķ�����Method��
                 */                                                          
				                                                             //method.getParameterTypes()���õ��������Ĳ������ͣ�����Category.class
				Method realMethod = service.getClass().getMethod(methodName, method.getParameterTypes());
				
				//����ʵ���������Ӧ�ķ�������û��@Permissionע��
				Permission permission = realMethod.getAnnotation(Permission.class);
				if (permission == null) {//��ζ�Ÿ÷�����û�м�Ȩ�ޱ�ǩ�������ʸ÷�������ҪȨ��
					return method.invoke(service, args);
				}
				
				//��ζ����ʵ�������Ӧ�ķ�������Ȩ��ע�⣬��õ����ʸ÷�����Ҫ��Ȩ��
				Privilege p = new Privilege(permission.value());//�õ�������Ҫ��Ȩ��
				
				//����Ҫ����û��Ƿ���Ȩ�ޣ����ڼ��֮ǰ����Ҫ�ж��û���û�е�¼��
				if (user == null) {
					//����˵Ӧ����һ���û�û�е�¼���쳣��ȥ�������ǲ�����ô�鷳��
					throw new SecurityException("��û�е�¼!");
				}
				
				//����û��Ƿ���Ȩ��
				List<Privilege> list = service.getUserAllPrivilege(user);//�õ��û�ӵ�е�����Ȩ��
				if (list.contains(p)) {
					return method.invoke(service, args);
				}
				//��ζ���û�û��Ȩ�ޣ���Ҫ��һ���쳣��ȥ����һ������ʱ�쳣��ȥ��ϣ��web��������쳣�����û�һ���Ѻ���ʾ��˵��û��Ȩ�ޣ�
				throw new SecurityException("��û��Ȩ��!");
			}
		});
	}
}
