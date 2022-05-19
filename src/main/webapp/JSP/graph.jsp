<%@ page language="java" contentType="text/html; charset=EUC-KR"
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
   
    <script src="../js/barchart.js"></script>
    <script src="../js/piechart.js"></script>
    <link rel="stylesheet" href="../css/graph.css">
</head>
<body>
    <div class="tbaner"><p>신고 통계</p></div>
    <div class="sbaner">
            <table class="bar_manager">
            <tr>
                <td><img class="appimg" src="../img/리스트.png" alt="어플이미지"></td>
            </tr>
            <tr>
                <td>
                    <div class="managername">
                        <span>진짜 머리아프네</span>
                    </div>
                </td>
            </tr>    
            <tr>
                <td><button id="logout" >로그아웃</button></td>
            </tr>
            </table>
            <table class="barmenu" onclick="location.href='http://localhost:8081/citycitybangbang/JSP/map.jsp'">
                <tr>
                    <td><img class="appimg" src="../img/마크.png" alt="어플이미지" ></td>
                </tr>
                <tr>
                    <td>신고위치</td>
                </tr>
            </table>
            <table  class="barmenu"  id="select" onclick="location.href='http://localhost:8081/citycitybangbang/JSP/graph.jsp'">
                <tr>
                    <td><img class="appimg" src="../img/pngwing.com (1).png" alt="어플이미지"></td>
                </tr>
                <tr>
                    <td>통계</td>
                </tr>
            </table>
            <table class="barmenu"  onclick="location.href='http://localhost:8081/citycitybangbang/JSP/list.jsp'">
                <tr>
                    <td ><img class="appimg" src="../img/리스트.png" alt="어플이미지"></td>
                </tr>
                <tr>
                    <td>리스트</td>
                </tr>
            </table>
            <div id="columnchart_material" class="chart1" ></div>    
            <div id="employee_piechart" class="chart2" style="width: 900px; height: 500px;"></div>          
        </div>
    </div>
    

</body>
</html>     