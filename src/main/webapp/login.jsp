<%@page import="com.db.ConnectDB3"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ConnectDB3 connectDB3 = ConnectDB3.getInstance3();
	
   String id = request.getParameter("id");
   String pw = request.getParameter("pw");
   
   /* String returns = id + "/" + pw; */

   String returns = connectDB3.connectionDB3(id, pw); 
   
   response.setContentType("text/html; charset=utf-8");
   
   System.out.println(returns);

   // 안드로이드로 전송
   out.print(returns);
%>