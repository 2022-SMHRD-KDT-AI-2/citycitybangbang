<%@page import="com.db.ConnectDB6"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ConnectDB6 connectDB6 = ConnectDB6.getInstance6();
	
   String id = request.getParameter("id");
   
   /* String returns = id; */

   String returns = connectDB6.connectionDB6(id); 
   System.out.println(returns);

   // 안드로이드로 전송
   out.print(returns);
%>