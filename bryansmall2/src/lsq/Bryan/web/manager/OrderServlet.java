package lsq.Bryan.web.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lsq.Bryan.domain.Order;
import lsq.Bryan.domain.User;
import lsq.Bryan.service.BusinessService;
import lsq.Bryan.service.impl.BusinessServiceImpl;

@WebServlet(name = "OrderServlet1", urlPatterns = { "/manager/OrderServlet1" })
public class OrderServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BusinessService service = new BusinessServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if ("getAll".equals(method)) {
			getAll(request, response);
		}
		if ("find".equals(method)) {
			find(request, response);
		}
		if ("update".equals(method)) {
			update(request, response);
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			service.updatOrder(id, true);
			String op="updateorder";
			saveOp(request,response,op);
			request.setAttribute("message", "��������Ϊ�������뼰ʱ����!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "��������");
		}
		
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	private void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Order order = service.findOrder(id);
		request.setAttribute("order", order);
		request.getRequestDispatcher("/manager/orderdetail.jsp").forward(request, response);
	}

	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean status = Boolean.parseBoolean(request.getParameter("status"));
		List<Order> list = service.getOrderByStatus(status);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/manager/listorder.jsp").forward(request, response);
	}
	
	private void saveOp(HttpServletRequest request, HttpServletResponse response,String op) throws ServletException, IOException{
		User user=(User)request.getSession().getAttribute("user");
		String userid=user.getId();
		service.saveOp(userid,op);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
