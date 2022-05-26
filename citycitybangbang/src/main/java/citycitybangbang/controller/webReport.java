package citycitybangbang.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.ConnectDB8;
import com.google.gson.Gson;

import citycitybangbang.model.ReportVO;



@WebServlet("/webReport")
public class webReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String date = request.getParameter("date");
		String area = request.getParameter("area");
		String check = request.getParameter("check");
		
		System.out.println("webList : "+ date);
		ConnectDB8 connectDB8 = ConnectDB8.getInstance8();
		List<ReportVO> list = connectDB8.connectionDB8(date,area,check);
		System.out.println("webList : "+ list);	
		
		
		// List<ReportVO> ==> JSON( JavaScript Object Notation)
		// JSON --> StringŸ������ ����
		// Gson ���̺귯��
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
		System.out.println(json);
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		// ajax�� �����Ҷ��� out.print�̿�
		out.print(json);
		
		
		
	}

}
