package citycitybangbang.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.ConnectDB6;

@WebServlet("/reSuccess")
public class reSuccess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			ConnectDB6 connectDB6 = ConnectDB6.getInstance6();
			
			response.setContentType("text/html; charset=UTF-8");
			
		   String id = request.getParameter("id");
		   
		   /* String returns = id; */
		   
		   List returns = connectDB6.connectionDB6(id); 
		   
		   System.out.println(returns);
		   
		   PrintWriter out = response.getWriter();

		   // 안드로이드로 전송
		   out.print(returns);
	}

}
