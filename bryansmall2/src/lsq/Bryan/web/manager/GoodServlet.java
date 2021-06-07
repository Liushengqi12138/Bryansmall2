package lsq.Bryan.web.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lsq.Bryan.domain.Category;
import lsq.Bryan.domain.Good;
import lsq.Bryan.domain.User;
import lsq.Bryan.service.BusinessService;
import lsq.Bryan.service.impl.BusinessServiceImpl;
import lsq.Bryan.utils.WebUtils;

@WebServlet("/manager/GoodServlet")
public class GoodServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BusinessService service = new BusinessServiceImpl();
	private static String changeid="0";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if ("forAddUI".equals(method)) {
			forAddUI(request, response);
		}
		if ("forAddUIb".equals(method)) {
			forAddUIb(request, response);
		}
		if ("add".equals(method)) {
			add(request, response);
		}
		if ("list".equals(method)) {
			list(request, response);
		}
		if ("listbyUserid".equals(method)) {
			listbyUserid(request, response);
		}
		if ("dele".equals(method)) {
			dele(request, response);
		}
		if ("change".equals(method)) {
			change(request, response);
		}
		if ("changec".equals(method)) {
			changec(request, response);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Good> list = service.getAllGood();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/manager/listgood.jsp").forward(request, response);
	}
	private void listbyUserid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String userid=user.getId();
		List<Good> list = service.getGoodbyUserid(userid);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/manager/listgood.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Good good = WebUtils.upload(request, this.getServletContext().getRealPath("/images"));
			service.addGood(good);
			String op="addgood";
			saveOp(request,response,op);
			request.setAttribute("message", "��ӳɹ�����");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "���ʧ�ܣ���");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	
	private void dele(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			service.deleteGood(id);
			String op="deletegood";
			saveOp(request,response,op);
			request.setAttribute("message", "ɾ���ɹ�����");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "ɾ��ʧ�ܣ���");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	
	private void change(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			changeid=id;
			service.deleteGood(id);
			response.sendRedirect(request.getContextPath() + "/manager/GoodServlet?method=forAddUIb");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "�޸�ʧ�ܣ���");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	
	private void changec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Good good = WebUtils.upload(request, this.getServletContext().getRealPath("/images"));
			good.setId(changeid);
			service.addGood(good);
			String op="changegood";
			saveOp(request,response,op);
			request.setAttribute("message", "�޸ĳɹ�����");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "�޸�ʧ�ܣ���");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	private void forAddUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> categories = service.getAllCategory();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/manager/addgood.jsp").forward(request, response);
	}
	
	private void forAddUIb(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> categories = service.getAllCategory();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/manager/changegood.jsp").forward(request, response);
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
