<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
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
    <script type="text/javascript">
   	function goMap(){
  		location.href="/citycitybangbang/JSP/map.jsp";
  	}
  </script>
</head>
<body class="bdbg">
	<div class="imgbox">
		<img src="../IMG/v206_1522.png" alt="img" class="mainimg1">
		<p></p>
		<img src="../IMG/logo.png" alt="img" class="mainimg2">
	</div>
	<form class="form-inline" action="/citycitybangbang/webLogin.do" method="post">
	<div class="loginbox">
    <input type="text" placeholder="아이디 입력" id="mem_id" class="account" name="mem_id">
    <p></p>
    <input type="password" placeholder="비밀번호 입력" id="mem_pwd" class="account" name="mem_pwd">
        <button type="submit" id="btnlogin">
             <span>로그인</span>
        </button>
    </div>
	</form>
</body>
</body>
</html>