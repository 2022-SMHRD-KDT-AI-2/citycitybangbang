<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("context", request.getContextPath());
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>main</title>
    <link href="../CSS/first.css" rel="stylesheet" type="text/css">
</head>
<body class="bdbg">
	<div class="imgbox">
		<img src="../IMG/v206_1522.png" alt="img" class="mainimg1">
		<p></p>
		<img src="../IMG/logo.png" alt="img" class="mainimg2">
	</div>
	<div class="loginbox">
    <input type="text" placeholder="아이디 입력" id="id" class="account">
    <p></p>
    <input type="password" placeholder="비밀번호 입력" id="pw" class="account">
    <input  type="button" value="로그인" id="login" class="account" onclick="location.href='http://localhost:8081/citycitybangbang/JSP/map.jsp'" >
	</div>
</body>
</body>
</html>