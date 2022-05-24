package citycitybangbang.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import citycitybangbang.model.ReportDAO;
import citycitybangbang.model.ReportVO;

public class webAddress extends HttpServlet {
	   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      
	      ReportDAO dao = new ReportDAO();
	      List<ReportVO> list =dao.selectAll();
	      
	      request.setAttribute("list", list);
	      RequestDispatcher rd=request.getRequestDispatcher("JSP/map.jsp");
	      rd.forward(request, response);
	      }

	}