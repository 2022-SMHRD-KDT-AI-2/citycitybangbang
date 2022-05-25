<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.List"%>
<%@page import="citycitybangbang.model.ReportVO"%>
<%@page import="java.util.ArrayList"%>
<%
	List<ReportVO> list=(List<ReportVO>)request.getAttribute("list");
%>

<%
	pageContext.setAttribute("context", request.getContextPath());
%>
<%@page import="citycitybangbang.model.MemberVO"%>
<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
	MemberVO vo = (MemberVO)request.getAttribute("mbVO"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>신고 관리</title>
<link href="/citycitybangbang/CSS/menu.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
   	function goOut(){
  		location.href="/citycitybangbang/webLogout.do";
  	}  	
</script>  	
   	
   	
   	
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9982fb8589d4aeda9ccac9ef44b2f2cf&libraries=services,clusterer,drawing"></script>
<link href="/citycitybangbang/CSS/map.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/citycitybangbang/JS/map.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap" rel="stylesheet">
</head>

<body>
<div class="sbaner">
            <table class="bar_manager">
            <tr>
                <td class="tdb"><img class="appimg2" src="/citycitybangbang/IMG/icon.png" alt="어플이미지"></td>
            </tr>
            <tr>
                <td class="tdb">
                    <div class="managername">
                        <span>${mbVO.mem_id}</span>
                    </div>
                </td>
            </tr>    
            <tr>
                <td class="tdb"><button id="logout" onclick="goOut()">로그아웃</button></td>
            </tr>
            </table>
            <table class="barmenu" id="report" onclick="location.href='http://localhost:8081/citycitybangbang/area.do'">
                <tr>
                    <td><img class="appimg" src="/citycitybangbang/IMG/point.png" alt="어플이미지" ></td>
                </tr>
                <tr>
                    <td class="tdb" style="color: black;">신고위치</td>
                </tr>
            </table>
            <table  class="barmenu" id="chart" onclick="location.href='http://localhost:8081/citycitybangbang/JSP/graph.jsp'">
                <tr>
                    <td class="tdb"><img class="appimg" src="/citycitybangbang/IMG/chart.png" alt="어플이미지"></td>
                </tr>
                <tr>
                    <td class="tdb" style="color: black;">통계</td>
                </tr>
            </table>
            <table class="barmenu"  id="list" onclick="location.href='http://localhost:8081/citycitybangbang/JSP/list.jsp'">
                <tr>
                    <td class="tdb"><img class="appimg" src="/citycitybangbang/IMG/리스트.png" alt="어플이미지"></td>
                </tr>
                <tr>
                    <td class="tdb" style="color: black;">리스트</td>
                </tr>
            </table>     
        </div>
        
        
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
var mapContainer = document.getElementById('map') // 지도를 표시할 div 
 mapOption = {
     center: new kakao.maps.LatLng(36.49334, 127.27856), // 지도의 중심좌표
     level: 11, // 지도의 확대 레벨
     mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
 }; 
 
	// 지도를 생성한다 
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	      
	   // 마커 클러스터러를 생성합니다 
	    var clusterer = new kakao.maps.MarkerClusterer({
	        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
	        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
	        minLevel: 6 // 클러스터 할 최소 지도 레벨 
	    });
	    
	   // 지도 타입 변경 컨트롤을 생성한다
	   var mapTypeControl = new kakao.maps.MapTypeControl();

	   // 지도의 상단 우측에 지도 타입 변경 컨트롤을 추가한다
	   map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);   

	   // 지도에 확대 축소 컨트롤을 생성한다
	   var zoomControl = new kakao.maps.ZoomControl();

	   // 지도의 우측에 확대 축소 컨트롤을 추가한다
	   map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
	   
	   // 주소-좌표 변환 객체를 생성합니다
	   var geocoder = new kakao.maps.services.Geocoder();
	
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
	      	markers = [];
	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords
	        });

	       
	     	// 생성된 마커를 마커 저장하는 변수에 넣음
	        markers.push(marker);

	     	// 클러스터러에 마커들을 추가합니다
	        clusterer.addMarkers(markers);
	    } 
	    
	    
	});
	}
	
	



</script>
</body>
</html>