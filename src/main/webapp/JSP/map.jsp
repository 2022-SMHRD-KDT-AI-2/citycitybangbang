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
</head>

<body>
<jsp:include page="menu.jsp"/>
<div class="full">
	<div>
		<button onclick="seoul()">서울</button>
		<button onclick="busan()">부산</button>
		<button onclick="daegu()">대구</button>
		<button onclick="incheon()">인천</button>
		<button onclick="gwangju()">광주</button>
		<button onclick="daejeon()">대전</button>
		<button onclick="ulsan()">울산</button>
		<button onclick="sejong()">세종</button>
		<button onclick="gyeonggi()">경기</button>
		<button onclick="gangwon()">강원</button>
		<button onclick="chungcheonbuk()">충북</button>
		<button onclick="chungcheonnam()">충남</button>
		<button onclick="jeollakbuk()">전북</button>
		<button onclick="jeollanam()">전남</button>
		<button onclick="gyeongsangbuk()">경북</button>
		<button onclick="gyeongsangnam()">경남</button>
		<button onclick="jeju()">제주도</button>
	</div>
	<div id="map"></div>
</div>
	
   <script>

   //지도를 초기화 시키는 함수
   var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(36.49334, 127.27856), // 지도의 중심좌표
        level: 12, // 지도의 확대 레벨
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

	// 다중 마커 생성
	var data = [
		[36.511513, 127.258927, '<div style="padding:5px;">주소</div>'],
		[36.514043, 127.263682, '<div style="padding:5px;">주소 놓자</div>'],
		[36.514494, 127.254835, '<div style="padding:5px;">주소 놓지</div>'],
		[36.511590, 127.262530, '<div style="padding:5px;">주소 놓지하지</div>']
	];

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