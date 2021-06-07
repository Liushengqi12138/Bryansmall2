package lsq.Bryan.web.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lsq.Bryan.domain.User;
import lsq.Bryan.service.BusinessService;
import lsq.Bryan.service.impl.BusinessServiceImpl;

@WebServlet("/client/LoginServlet")
public class LoginServlet extends HttpServlet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BusinessService service = new BusinessServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		BusinessService service = new BusinessServiceImpl();
		User user = service.findUser(username, password);
		if (user == null) {
			request.setAttribute("message", "用户名或者密码错误！！");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		String ipAddr=getIpAddr(request);
		service.saveIp(ipAddr,user.getId(),true);
		request.getSession().setAttribute("user", user);
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
       }

}
