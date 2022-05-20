<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("context", request.getContextPath());
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>산고 관리</title>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script> 
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>  
   
    <script src="../JS/piechart.js"></script>
    <script src="../JS/barchart.js"></script>    
    <link rel="stylesheet" href="../CSS/graph.css">
</head>
<body>
<jsp:include page="menu.jsp"/>
<div class="tbaner"><p>신고 통계</p></div>    
<div id="columnchart_material" class="chart1" ></div>  
<input type="date" class="yymmdd">  
<div id="donutchart" class="chart2" style="width: 1200px; height: 800px;"></div>            
</body>
</html>     