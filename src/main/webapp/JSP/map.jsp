<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=LIBRARY"></script>
<!-- services ���̺귯�� �ҷ����� -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services"></script>
<!-- services�� clusterer, drawing ���̺귯�� �ҷ����� -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services,clusterer,drawing"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9982fb8589d4aeda9ccac9ef44b2f2cf"></script>
<link href="../CSS/map.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="map">
	</div>
	
	<script>
	var map = new kakao.maps.Map(document.getElementById('map'), { // ������ ǥ���� div
        center : new kakao.maps.LatLng(36.2683, 127.6358), // ������ �߽���ǥ
        level : 14 // ������ Ȯ�� ����
    });

    // ��Ŀ Ŭ�����ͷ��� �����մϴ�
    // ��Ŀ Ŭ�����ͷ��� ������ �� disableClickZoom ���� true�� �������� ���� ���
    // Ŭ������ ��Ŀ�� Ŭ������ �� Ŭ������ ��ü�� �����ϴ� ��Ŀ���� ��� �� ���̵��� ������ ������ ������ �����մϴ�
    // �� ���������� disableClickZoom ���� true�� �����Ͽ� �⺻ Ŭ�� ������ ����
    // Ŭ������ ��Ŀ�� Ŭ������ �� Ŭ���� Ŭ������ ��Ŀ�� ��ġ�� �������� ������ 1������ Ȯ���մϴ�
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // ��Ŀ���� Ŭ�����ͷ� �����ϰ� ǥ���� ���� ��ü
        averageCenter: true, // Ŭ�����Ϳ� ���Ե� ��Ŀ���� ��� ��ġ�� Ŭ������ ��Ŀ ��ġ�� ����
        minLevel: 10, // Ŭ������ �� �ּ� ���� ����
        disableClickZoom: true // Ŭ������ ��Ŀ�� Ŭ������ �� ������ Ȯ����� �ʵ��� �����Ѵ�
    });

    // �����͸� �������� ���� jQuery�� ����մϴ�
    // �����͸� ������ ��Ŀ�� �����ϰ� Ŭ�����ͷ� ��ü�� �Ѱ��ݴϴ�
    $.get("�����͸� �ҷ��;���...DB����", function(data) {
        // �����Ϳ��� ��ǥ ���� ������ ��Ŀ�� ǥ���մϴ�
        // ��Ŀ Ŭ�����ͷ��� ������ ��Ŀ ��ü�� ������ �� ���� ��ü�� �������� �ʽ��ϴ�
        var markers = $(data.positions).map(function(i, position) {
            return new kakao.maps.Marker({
                position : new kakao.maps.LatLng(position.lat, position.lng)
            });
        });

        // Ŭ�����ͷ��� ��Ŀ���� �߰��մϴ�
        clusterer.addMarkers(markers);
    });

    // ��Ŀ Ŭ�����ͷ��� Ŭ���̺�Ʈ�� ����մϴ�
    // ��Ŀ Ŭ�����ͷ��� ������ �� disableClickZoom�� true�� �������� ���� ���
    // �̺�Ʈ ��鷯�� cluster ��ü�� �Ѿ���� ���� ���� �ֽ��ϴ�
    kakao.maps.event.addListener(clusterer, 'clusterclick', function(cluster) {

        // ���� ���� �������� 1���� Ȯ���� ����
        var level = map.getLevel()-1;

        // ������ Ŭ���� Ŭ�������� ��Ŀ�� ��ġ�� �������� Ȯ���մϴ�
        map.setLevel(level, {anchor: cluster.getCenter()});
    });
	</script>
	
</body>
</html>