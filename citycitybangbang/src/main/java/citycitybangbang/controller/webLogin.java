package citycitybangbang.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import citycitybangbang.model.MemberDAO;
import citycitybangbang.model.MemberVO;

@WebServlet("/webLogin.do")
public class webLogin extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String mem_id = request.getParameter("mem_id");
		String mem_pwd = request.getParameter("mem_pwd");
		
		MemberVO vo = new MemberVO();
		
		vo.setMem_id(mem_id);
		vo.setMem_pwd(mem_pwd);
		
		MemberDAO dao = new MemberDAO();
		MemberVO mbVO = dao.checkLogin(vo);
		
		if(mbVO != null) {
			HttpSession session=request.getSession();
			session.setAttribute("mbVO", mbVO);		
			response.sendRedirect("/citycitybangbang/area.do");
		}else {
			response.sendRedirect("JSP/first.jsp");
		}
		
		
	}
}
