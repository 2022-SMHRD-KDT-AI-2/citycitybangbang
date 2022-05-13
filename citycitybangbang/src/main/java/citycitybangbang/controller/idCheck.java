package citycitybangbang.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.ConnectDB2;

@WebServlet("/idCheck")
public class idCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			ConnectDB2 connectDB2 = ConnectDB2.getInstance2();
			
			response.setContentType("text/html; charset=UTF-8");
			
		   String id = request.getParameter("id");
		   
		   /* String returns = id; */

		    String returns = connectDB2.connectionDB2(id); 
		   System.out.println(returns);
		   
		   PrintWriter out = response.getWriter();

		   // 안드로이드로 전송
		   out.print(returns);
	}

}
