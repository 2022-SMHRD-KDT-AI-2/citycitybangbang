<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="../CSS/menu.css">
      <script type="text/javascript">
   	function goOut(){
  		location.href="/citycitybangbang/webLogout.do";
  	}
   	</script>
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
                        <span>${mbVO.mem_id}</span>
                    </div>
                </td>
            </tr>    
            <tr>
                <td><button id="logout" onclick="goOut()">로그아웃</button></td>
            </tr>
            </table>
            <table class="barmenu" id="report" onclick="location.href='http://localhost:8081/citycitybangbang/JSP/map.jsp'">
                <tr>
                    <td><img class="appimg" src="../IMG/마크.png" alt="어플이미지" ></td>
                </tr>
                <tr>
                    <td>신고위치</td>
                </tr>
            </table>
            <table  class="barmenu" id="chart" onclick="location.href='http://localhost:8081/citycitybangbang/JSP/graph.jsp'">
                <tr>
                    <td><img class="appimg" src="../IMG/pngwing.com (1).png" alt="어플이미지"></td>
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