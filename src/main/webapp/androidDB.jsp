<%@page import="com.db.ConnectDB1"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ConnectDB1 connectDB1 = ConnectDB1.getInstance1();
	
   String id = request.getParameter("id");
   String pw = request.getParameter("pw");
   String name = request.getParameter("name");
   String email = request.getParameter("email");
   String sex = request.getParameter("sex");
   
   /* String returns = id+"/"+pw+"/"+name+"/"+email+"/"+sex;  */

 String returns = connectDB1.connectionDB1(id, pw, name, email, sex);
   System.out.println(returns);

   // 안드로이드로 전송
   out.print(returns);
%>