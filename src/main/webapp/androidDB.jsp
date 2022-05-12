<%@page import="com.db.ConnectDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   ConnectDB connectDB = ConnectDB.getInstance();
	
   String id = request.getParameter("id");
   String pw = request.getParameter("pw");
   String name = request.getParameter("name");
   String email = request.getParameter("email");
   String sex = request.getParameter("sex");

   String returns = connectDB.connectionDB(id, pw, name, email, sex);
   

   System.out.println(returns);

   // 안드로이드로 전송
   out.println(returns);
%>