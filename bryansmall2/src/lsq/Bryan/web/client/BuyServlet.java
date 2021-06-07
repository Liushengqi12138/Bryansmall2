package lsq.Bryan.web.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lsq.Bryan.domain.Cart;
import lsq.Bryan.domain.Good;
import lsq.Bryan.domain.User;
import lsq.Bryan.service.BusinessService;
import lsq.Bryan.service.impl.BusinessServiceImpl;

@WebServlet("/client/BuyServlet")
public class BuyServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		BusinessService service = new BusinessServiceImpl();
		Good good = service.findGood(id);
		
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			request.setAttribute("message", "�����ȵ�¼��");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		String userid=user.getId();
		service.updatescore1(userid, good.getId());
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		cart.add(good);
		
		response.sendRedirect(request.getContextPath() + "/client/listcart.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
