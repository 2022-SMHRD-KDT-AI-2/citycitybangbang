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
    <link rel="stylesheet" href="../CSS/menu.css">
</head>
<body>
    <div class="sbaner">
            <table class="bar_manager">
            <tr>
                <td><img class="appimg" src="../IMG/리스트.png" alt="어플이미지"></td>
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
            <table class="barmenu" id="report" onclick="location.href='http://localhost:8081/citycitybangbang/JSP/map.jsp'">
                <tr>
                    <td><img class="appimg" src="../IMG/chart.png" alt="어플이미지" ></td>
                </tr>
                <tr>
                    <td>신고위치</td>
                </tr>
            </table>
            <table  class="barmenu" id="chart" onclick="location.href='http://localhost:8081/citycitybangbang/JSP/graph.jsp'">
                <tr>
                    <td><img class="appimg" src="../IMG/mark.png" alt="어플이미지"></td>
                </tr>
                <tr>
                    <td>통계</td>
                </tr>
            </table>
            <table class="barmenu"  id="list" onclick="location.href='http://localhost:8081/citycitybangbang/JSP/list.jsp'">
                <tr>
                    <td ><img class="appimg" src="../IMG/리스트.png" alt="어플이미지"></td>
                </tr>
                <tr>
                    <td>리스트</td>
                </tr>
            </table>     
        </div>
   
    

</body>
</html>     