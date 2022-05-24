package citycitybangbang.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import citycitybangbang.model.ReportDAO;
import citycitybangbang.model.ReportVO;

/**
 * Servlet implementation class AddressController
 */
@WebServlet("/address.do")
public class AddressController extends HttpServlet { 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReportDAO dao=new ReportDAO();
		List<ReportVO> list=dao.postionList();
		Gson g=new Gson();
		String json=g.toJson(list);
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.print(json);
	}

}
