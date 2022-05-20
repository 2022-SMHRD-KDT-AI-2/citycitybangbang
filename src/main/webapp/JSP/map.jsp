<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9982fb8589d4aeda9ccac9ef44b2f2cf&libraries=services,clusterer,drawing"></script>
<link href="../CSS/map.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="map">
	</div>
	
	<script>
	
	var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center : new kakao.maps.LatLng(36.2683, 127.6358), // 지도의 중심좌표
        level : 14 // 지도의 확대 레벨
    });

    // 마커 클러스터러를 생성합니다
    // 마커 클러스터러를 생성할 때 disableClickZoom 값을 true로 지정하지 않은 경우
    // 클러스터 마커를 클릭했을 때 클러스터 객체가 포함하는 마커들이 모두 잘 보이도록 지도의 레벨과 영역을 변경합니다
    // 이 예제에서는 disableClickZoom 값을 true로 설정하여 기본 클릭 동작을 막고
    // 클러스터 마커를 클릭했을 때 클릭된 클러스터 마커의 위치를 기준으로 지도를 1레벨씩 확대합니다
   
    
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 10, // 클러스터 할 최소 지도 레벨
        disableClickZoom: true // 클러스터 마커를 클릭했을 때 지도가 확대되지 않도록 설정한다
    });
   
	// 데이터 넣기!
	var data = {
	  "positions": [
	    {
	      "lat": 37.27943075229118,
	      "lng": 127.01763998406159,
	      "context" : "이게 뭘까"
	    },
	    {
	      "lat": 37.55915668706214,
	      "lng": 126.92536526611102,
	      "context" : "이게 뭘까"
	    },
	    {
	      "lat": 35.13854258261161,
	      "lng": 129.1014781294671,
	      "context" : "이게 뭘까"
	    },
	    {
	      "lat": 37.55518388656961,
	      "lng": 126.92926237742505,
	      "context" : "이게 뭘까"
	    }
	  ]	    /* ...... */
	};
	
	var markers = data.positions.map(function(position) {
	    return new kakao.maps.Marker({
	        position : new kakao.maps.LatLng(position.lat, position.lng) 
	    });
	});
    
	var iwContent = data.positions.map(function(position)){
		iwPosition = new kakao.maps.LatLng(position.lat, position.lng)
	};
	
	
	
    // 인포윈도우
	var iwContent = '<div style="padding:5px;">Hello World! <br><a href="https://map.kakao.com/link/map/Hello World!,33.450701,126.570667" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/Hello World!,33.450701,126.570667" style="color:blue" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    

	// 인포윈도우를 생성합니다
	var infowindow = new kakao.maps.InfoWindow({
	    position : iwPosition, 
	    content : iwContent 
	});
	  
	
    // 마커에 클릭이벤트를 등록합니다 
	kakao.maps.event.addListener(marker, 'click', function() { 
		// 마커 위에 인포윈도우를 표시합니다 
		infowindow.open(map, marker); });
    
   
    // 클러스터러에 마커들을 추가합니다
	clusterer.addMarkers(markers);


    // 마커 클러스터러에 클릭이벤트를 등록합니다
    // 마커 클러스터러를 생성할 때 disableClickZoom을 true로 설정하지 않은 경우
    // 이벤트 헨들러로 cluster 객체가 넘어오지 않을 수도 있습니다
    kakao.maps.event.addListener(clusterer, 'clusterclick', function(cluster) {

        // 현재 지도 레벨에서 1레벨 확대한 레벨
        var level = map.getLevel()-1;

        // 지도를 클릭된 클러스터의 마커의 위치를 기준으로 확대합니다
        map.setLevel(level, {anchor: cluster.getCenter()});
    });
	</script>
</body>
</html>