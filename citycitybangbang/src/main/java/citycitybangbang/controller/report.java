package citycitybangbang.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.ConnectDB5;

@WebServlet("/report")
public class report extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ConnectDB5 connectDB5 = ConnectDB5.getInstance5();

		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("id");
		String acc_date = request.getParameter("acc_date");
		String acc_place = request.getParameter("acc_place");
		String re_comment = request.getParameter("re_comment");
		
		/* String returns = id; */
		
		 System.out.println(id);

		String returns = connectDB5.connectionDB5(id, acc_date, acc_place, re_comment);

		System.out.println(returns);
		
		PrintWriter out = response.getWriter();

		// 안드로이드로 전송
		out.print(returns);

	}

}
