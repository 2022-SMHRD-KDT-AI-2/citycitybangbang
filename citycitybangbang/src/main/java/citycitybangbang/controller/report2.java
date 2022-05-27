package citycitybangbang.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import java.io.*;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import com.db.ConnectDB5;

// self신고용
@WebServlet("/report2")
public class report2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ConnectDB5 connectDB5 = ConnectDB5.getInstance5();

		response.setContentType("text/html; charset=UTF-8");
		
		String folderTypePath = "C:/Users/smhrd/Desktop/pic";
		String name = new String();
		String fileName = new String();
		int sizeLimit = 5 * 1024 * 1024; // 5메가까지 제한 넘어서면 예외발생
		DefaultFileRenamePolicy policy= new DefaultFileRenamePolicy();
		try {
			MultipartRequest multi = new MultipartRequest(request, folderTypePath, sizeLimit,"euc-kr",
					policy);
			Enumeration files = multi.getFileNames();

			//파일 정보가 있다면
			if (files.hasMoreElements()) {
				name = (String) files.nextElement();
				fileName = multi.getFilesystemName(name);
			}
			System.out.println("이미지를 저장하였습니다. : " + fileName);
		} catch (IOException e) {
			System.out.println("안드로이드로부터 이미지를 받아옵니다.");
		}
		
		String id = request.getParameter("id");
		String acc_date = request.getParameter("acc_date");
		String acc_place = request.getParameter("acc_place");
		String re_comment = request.getParameter("re_comment");
		String image_file = fileName;

		/* String returns = id; */
		
		 System.out.println(id);
		 
		 String returns = connectDB5.connectionDB5(id, acc_date, acc_place, re_comment, image_file);
		
		PrintWriter out = response.getWriter();

		// 안드로이드 출력
		out.print(returns);

	}

}