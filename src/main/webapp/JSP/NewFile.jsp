<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@page import="java.util.List"%>
<%@page import="citycitybangbang.model.ReportVO"%>
<%@page import="java.util.ArrayList"%>
    <%
	List<ReportVO> list=(List<ReportVO>)request.getAttribute("list");
System.out.println("----------------");
/* System.out.println("�ʸ���Ʈ Ȯ�� : " + list.get(1).getAcc_place()); */

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9982fb8589d4aeda9ccac9ef44b2f2cf&libraries=services,clusterer,drawing"></script>
<script type="text/javascript" src="../JS/map.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap" rel="stylesheet">
</head>

<body>
<div class="full">
	<div class="area">
		<button class= "carea" onclick="seoul()">����</button>
		<button class= "carea" onclick="busan()">�λ�</button>
		<button class= "carea" onclick="daegu()">�뱸</button>
		<button class= "carea" onclick="incheon()">��õ</button>
		<button class= "carea" onclick="gwangju()">����</button>
		<button class= "carea" onclick="daejeon()">����</button>
		<button class= "carea" onclick="ulsan()">���</button>
		<button class= "carea" onclick="sejong()">����</button>
		<button class= "carea" onclick="gyeonggi()">���</button>
		<button class= "carea" onclick="gangwon()">����</button>
		<button class= "carea" onclick="chungcheonbuk()">���</button>
		<button class= "carea" onclick="chungcheonnam()">�泲</button>
		<button class= "carea" onclick="jeollakbuk()">����</button>
		<button class= "carea" onclick="jeollanam()">����</button>
		<button class= "carea" onclick="gyeongsangbuk()">���</button>
		<button class= "carea" onclick="gyeongsangnam()">�泲</button>
		<button class= "carea" onclick="jeju()">���ֵ�</button>
	</div>
	<div id="map" style="width: 100%; height: 350px;"></div>

</div>
<script>


//������ �ʱ�ȭ ��Ű�� �Լ�
var mapContainer = document.getElementById('map'), // ������ ǥ���� div 
 mapOption = {
     center: new kakao.maps.LatLng(36.49334, 127.27856), // ������ �߽���ǥ
     level: 11, // ������ Ȯ�� ����
     mapTypeId : kakao.maps.MapTypeId.ROADMAP // ��������
 }; 
 
	// ������ �����Ѵ� 
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	let addr = [];
	<%for (int i = 0; i < list.size(); i++) {%>
	addr.push("<%=list.get(i).getAcc_place()%>");
	<%}%>
	console.log(addr)
	
	//�ּ�-��ǥ ��ȯ ��ü�� �����մϴ�
	var geocoder = new kakao.maps.services.Geocoder();
	for(let i =0; i<addr.length; i++){
	geocoder.addressSearch(addr[i], function(result, status) {

	   console.log(result)
	    // ���������� �˻��� �Ϸ������ 
	     if (status === kakao.maps.services.Status.OK) {

	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	      
	        // ��������� ���� ��ġ�� ��Ŀ�� ǥ���մϴ�
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords
	        });

	        /* // ����������� ��ҿ� ���� ������ ǥ���մϴ�
	        var infowindow = new kakao.maps.InfoWindow({
	            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+dataNum2[i]+'</div>'
	        });
	        infowindow.open(map, marker); */

	        // ������ �߽��� ��������� ���� ��ġ�� �̵���ŵ�ϴ�
	        map.setCenter(coords);
	    } 
	    
	    
	});
	}


</script>
</body>
</html>