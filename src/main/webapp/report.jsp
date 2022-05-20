<%@page import="com.db.ConnectDB5"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ConnectDB5 connectDB5 = ConnectDB5.getInstance5();
	
   String id = request.getParameter("id");
   String acc_date = request.getParameter("acc_date");
   String acc_place = request.getParameter("acc_place");
   String re_comment = request.getParameter("re_comment");
   
   /* String returns = id+"/"+acc_date+"/"+acc_place+"/"+re_comment;  */

   System.out.print(id);
   
 String returns = connectDB5.connectionDB5(id, acc_date, acc_place, re_comment); 
   
   System.out.println(returns);

   // 안드로이드로 전송
   out.print(returns);
%>