package citycitybangbang.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Base64;

import javax.imageio.ImageIO;
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
		String pic = request.getParameter("pic");
		
		byte dearr[] =Base64.getDecoder().decode(pic.replace("\n", ""));
		
		try( OutputStream stream = new FileOutputStream("C:/Users/smhrd/Desktop/pic/"+id+"_"+acc_date.replace(":", "-")+".png") ) 
		{
		   stream.write(dearr);
		}
		catch (Exception e) 
		{
		   System.err.println("Couldn't write to file...");
		}
		
		/* String returns = id; */
		
		 System.out.println(id);
		 System.out.println(acc_date);

		String returns = connectDB5.connectionDB5(id, acc_date, acc_place, re_comment);

		System.out.println(returns);
		
		PrintWriter out = response.getWriter();

		// 안드로이드로 전송
		out.print(returns);

	}

}
