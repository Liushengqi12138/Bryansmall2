package lsq.Bryan.web.client;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lsq.Bryan.domain.User;
import lsq.Bryan.service.BusinessService;
import lsq.Bryan.service.impl.BusinessServiceImpl;
import lsq.Bryan.utils.WebUtils;

@WebServlet("/client/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = WebUtils.request2Bean(request, User.class);
			user.setId(UUID.randomUUID().toString());
			
			BusinessService service = new BusinessServiceImpl();
			service.addUser(user);
			service.createscore(user.getId());
			request.setAttribute("message", "ע���û��ɹ�������");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "ע���û�ʧ�ܻ��û��Ѵ��ڣ�������ע�ᣡ����");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
