<%@page import="com.db.ConnectDB2"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ConnectDB2 connectDB2 = ConnectDB2.getInstance2();
	
   String id = request.getParameter("id");
   
   /* String returns = id; */

    String returns = connectDB2.connectionDB2(id); 
   System.out.println(returns);

   // 안드로이드로 전송
   out.println(returns);
%>