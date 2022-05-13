package citycitybangbang.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.ConnectDB1;

@WebServlet("/androidDB")
public class androidDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ConnectDB1 connectDB1 = ConnectDB1.getInstance1();

		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String sex = request.getParameter("sex");

		/* String returns = id+"/"+pw+"/"+name+"/"+email+"/"+sex; */

		String returns = connectDB1.connectionDB1(id, pw, name, email, sex);


		System.out.println(returns);
		
		
		PrintWriter out = response.getWriter();
		

		// 안드로이드로 전송
		out.print(returns);

	}

}
