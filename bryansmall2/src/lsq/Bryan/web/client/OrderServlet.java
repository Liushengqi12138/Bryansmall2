package lsq.Bryan.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lsq.Bryan.domain.Cart;
import lsq.Bryan.domain.User;
import lsq.Bryan.service.BusinessService;
import lsq.Bryan.service.impl.BusinessServiceImpl;

@WebServlet("/client/OrderServlet")
public class OrderServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("message", "�����ȵ�¼��");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		String username=user.getUsername();
		String useremail=user.getEmail();
		String content="�𾴵Ŀͻ�"+username+"�����ã�";
		try {
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			BusinessService service = new BusinessServiceImpl();
			service.saveOrder(cart, user);
			service.clearCart(cart);
			EmailServlet.SendMail(useremail, "�����ѳɹ�����", content+"���ո���Bryansmallƽ̨������һ���¶�����");
			request.setAttribute("message", content+"���Ķ������ɳɹ�����ȴ��ջ���");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", content+"���Ķ�������ʧ�ܣ������¹���");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}


