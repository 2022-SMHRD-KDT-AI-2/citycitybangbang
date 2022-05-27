<%@page import="com.db.ConnectDB9"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ConnectDB9 connectDB9 = ConnectDB9.getInstance9();
	
   
   /* String returns = id; */
  
   
   String returns = connectDB9.connectionDB9(); 
   System.out.println(returns);

   // 안드로이드로 전송
   out.print(returns);
%>