
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%pageContext.setAttribute("context", request.getContextPath());%>
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
    <style>
    	#submitbut{
    		z-index: 2;
    	}
    </style>
</head>
<body onload="loadPieChart(); loadBarChart();">

<jsp:include page="menu.jsp"/>
<div class="tbaner"><p>신고 통계</p></div>    
<div id="columnchart_material" class="chart1" ></div>  
<input type="date" class="yymmdd" name="date" onchange="loadData()">


<div id="donutchart" class="chart2" style="width: 1200px; height: 800px;"></div>         

<script type="text/javascript">
	function loadData(){
		
		// #id
		// .class
		// 태그이름[속성명=속성값]
		// .html() => 닫는태그 있는 경우
		// .val() => 닫는태그 없는 경우
		let date = $('input[name=date]').val()
		console.log(date);
		
		// ajax 요청
		$.ajax({
			// /ContextPath/urlMapping
			url : '/citycitybangbang/webList', //어디로 요청할건지
			type : 'get', //Get? Post?
			data : {
				// 보내주는 data
				// "name" : value
				"date" : date
			},
			dataType : 'json',
			success : function(res){
				// 요청이 성공하면 실행
				reloadPie(res)
			},
			error : function(){alert("error!");}
		})
		$.ajax({
			// /ContextPath/urlMapping
			url : '/citycitybangbang/webList', //어디로 요청할건지
			type : 'get', //Get? Post?
			data : {
				// 보내주는 data
				// "name" : value
				"date" : date
			},
			dataType : 'json',
			success : function(res){
				// 요청이 성공하면 실행
				reloadBar(res)
			},
			error : function(){alert("error!");}
		})
		
		
	}
	
</script>
   
</body>
</html>     