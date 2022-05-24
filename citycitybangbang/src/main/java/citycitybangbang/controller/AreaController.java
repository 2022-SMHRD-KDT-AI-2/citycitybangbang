package citycitybangbang.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import citycitybangbang.model.ReportDAO;
import citycitybangbang.model.ReportVO;

/**
 * Servlet implementation class AreaController
 */
@WebServlet("/area.do")
public class AreaController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReportDAO dao = new ReportDAO();
		List<ReportVO> list=dao.postionList();
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("/JSP/NewFile.jsp");
		rd.forward(request, response);
	}

}
