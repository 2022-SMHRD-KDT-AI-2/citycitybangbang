<%@page import="com.db.ConnectDB4"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ConnectDB4 connectDB4 = ConnectDB4.getInstance4();
	
   String id = request.getParameter("id");
   
   /* String returns = id; */
   
   System.out.println(id);

   String returns = connectDB4.connectionDB4(id); 
   System.out.println(returns);

   // 안드로이드로 전송
   out.print(returns);
%>