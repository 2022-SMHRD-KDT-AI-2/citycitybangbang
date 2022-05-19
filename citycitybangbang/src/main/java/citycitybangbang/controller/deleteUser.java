package citycitybangbang.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.ConnectDB4;

@WebServlet("/deleteUSer")
public class deleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ConnectDB4 connectDB4 = ConnectDB4.getInstance4();

		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("id");
		
		/* String returns = id; */
		
		System.out.println(id);

		String returns = connectDB4.connectionDB4(id);

		System.out.println(returns);
		
	
		PrintWriter out = response.getWriter();
		

		// 안드로이드로 전송
		out.print(returns);

	}

}
