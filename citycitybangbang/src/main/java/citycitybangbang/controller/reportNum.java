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

import com.db.ConnectDB9;

@WebServlet("/reportNum")
public class reportNum extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			ConnectDB9 connectDB9 = ConnectDB9.getInstance9();
			
			response.setContentType("text/html; charset=UTF-8");
		   
		   List returns = connectDB9.connectionDB9(); 
		   
		   System.out.println(returns);
		   
		   PrintWriter out = response.getWriter();

		   // 안드로이드로 전송
		   out.print(returns);
	}

}
