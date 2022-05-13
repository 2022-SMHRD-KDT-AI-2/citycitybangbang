package citycitybangbang.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.ConnectDB3;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ConnectDB3 connectDB3 = ConnectDB3.getInstance3();

		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		/* String returns = id+"/"+pw; */

		String returns = connectDB3.connectionDB3(id, pw);


		System.out.println(returns);
		
		
		PrintWriter out = response.getWriter();
		

		// 안드로이드로 전송
		out.print(returns);

	}

}
