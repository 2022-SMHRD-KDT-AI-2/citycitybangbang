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

import com.db.ConnectDB7;
import com.google.gson.Gson;

import citycitybangbang.model.ReportVO;



@WebServlet("/webList")
public class webList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String date = request.getParameter("date");
		
		System.out.println("webList : "+ date);
		ConnectDB7 connectDB7 = ConnectDB7.getInstance7();
		List<ReportVO> list = connectDB7.connectionDB7(date);
		System.out.println("webList : "+ list);	
		
		
		// List<ReportVO> ==> JSON( JavaScript Object Notation)
		// JSON --> String타입으로 저장
		// Gson 라이브러리
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
		System.out.println(json);
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		// ajax에 응답할때는 out.print이용
		out.print(json);
		
		
		
	}

}
