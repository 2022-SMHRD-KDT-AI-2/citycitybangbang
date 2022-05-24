<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="citycitybangbang.model.ReportVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	List<ReportVO> list=(List<ReportVO>)request.getAttribute("list");
/* System.out.println("----------------");
System.out.println("맵리스트 확인 : " + list.get(i).getAcc_place()); */

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9982fb8589d4aeda9ccac9ef44b2f2cf&libraries=services,clusterer,drawing"></script>
<link href="../CSS/map.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../JS/map.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap" rel="stylesheet">
</head>

<body>
<jsp:include page="menu.jsp"/>
<div class="full">
	<div class="area">
		<button class= "carea" onclick="seoul()">서울</button>
		<button class= "carea" onclick="busan()">부산</button>
		<button class= "carea" onclick="daegu()">대구</button>
		<button class= "carea" onclick="incheon()">인천</button>
		<button class= "carea" onclick="gwangju()">광주</button>
		<button class= "carea" onclick="daejeon()">대전</button>
		<button class= "carea" onclick="ulsan()">울산</button>
		<button class= "carea" onclick="sejong()">세종</button>
		<button class= "carea" onclick="gyeonggi()">경기</button>
		<button class= "carea" onclick="gangwon()">강원</button>
		<button class= "carea" onclick="chungcheonbuk()">충북</button>
		<button class= "carea" onclick="chungcheonnam()">충남</button>
		<button class= "carea" onclick="jeollakbuk()">전북</button>
		<button class= "carea" onclick="jeollanam()">전남</button>
		<button class= "carea" onclick="gyeongsangbuk()">경북</button>
		<button class= "carea" onclick="gyeongsangnam()">경남</button>
		<button class= "carea" onclick="jeju()">제주도</button>
	</div>
	<div id="map"></div>
</div>
	
   <script>

   //지도를 초기화 시키는 함수
   var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(36.49334, 127.27856), // 지도의 중심좌표
        level: 11, // 지도의 확대 레벨
        mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
    }; 
    
	// 지도를 생성한다 
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	let addr = [];
	<%for (int i = 0; i < list.size(); i++) {%>
	addr.push("<%=list.get(i).getAcc_place()%>");
	<%}%>
	console.log(addr)
	
	//주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();
	for(let i =0; i<addr.length; i++){
	geocoder.addressSearch(addr[i], function(result, status) {

	   console.log(result)
	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {

	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	      
	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords
	        });

	        /* // 인포윈도우로 장소에 대한 설명을 표시합니다
	        var infowindow = new kakao.maps.InfoWindow({
	            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+dataNum2[i]+'</div>'
	        });
	        infowindow.open(map, marker); */

	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
	    } 
	    
	    
	});
	}

   
   </script>
</body>
</html>
