<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
	
	
	var i=0;
	$.ajax({
		url: "../address.do",
		type : "get",
		dataType : "json",		
		success : function(json){
			// 다중 마커 생성
			   let data = [];
			
				for(let i = 0 ; i<json.length; i++){
					
					var arr=[];
					geocoder.addressSearch(json[i].acc_place, function(result, status) {				
				    // 정상적으로 검색이 완료됐으면 
						if (status === kakao.maps.services.Status.OK) {
						    var coords = new kakao.maps.LatLng(result[0].x, result[0].y);				     
						    //console.log(coords.La);//위도
					        //console.log(coords.Ma);//경도
					        //console.log(json[i].acc_place);//내용
					        arr.push(coords.La);
					        arr.push(coords.Ma);
					        arr.push(json[i].acc_place);
					        
					        for(let j = i; j < i+1;  j++){
					        	data.push(arr);
					        }
				    
				    	}				    
				  	});
					
				}
				

				console.log('data')
				console.log(data)
			
				
				
			// 마커들을 저장할 변수 생성
			var markers = [];
			for (var i = 0; i < data.length; i++ ) {			
				// 지도에 마커를 생성하고 표시한다.
				var marker = new kakao.maps.Marker({
					position: new kakao.maps.LatLng(data[i][0], data[i][1]), // 마커의 좌표
					map: map // 마커를 표시할 지도 객체
				});

				iwPosition = new kakao.maps.LatLng(data[i][0], data[i][1]),
			    iwRemoveable = false; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

				// 인포윈도우를 생성하고 지도에 표시합니다
				var infowindow = new kakao.maps.InfoWindow({
				    position : iwPosition, 
				    content : data[i][2],
				    removable : iwRemoveable
				});

				// 생성된 마커를 마커 저장하는 변수에 넣음
				markers.push(marker);

			 	// 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
			    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
			    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
			    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
			    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
			}	

			// 클러스터러에 마커들을 추가합니다
		    clusterer.addMarkers(markers);
		}
	});
	
	
	
	
 	// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
    function makeOverListener(map, marker, infowindow) {
        return function() {
            infowindow.open(map, marker);
        };
    }

    // 인포윈도우를 닫는 클로저를 만드는 함수입니다 
    function makeOutListener(infowindow) {
        return function() {
            infowindow.close();
        };
    }
   
   </script>
</body>
</html>