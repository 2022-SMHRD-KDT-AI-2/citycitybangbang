<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%pageContext.setAttribute("context", request.getContextPath());%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../CSS/list.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../JS/list.js"></script>
</head>
<body>
<div class="tbaner"><p>접수된 신고</p></div>
<jsp:include page="menu.jsp"/>
    <div id="daypst">
            <tr>
               	<input type="date" class="yymmdd2" name="date" onchange="loadList()" style="margin-right: 20px;">
               	<select class="lctlist" name="area" onchange="loadList()" style="margin-right: 20px;">
                   	<option value="광주">광주</option>
                   	<option value="부산">부산</option>
                   	<option value="서울">서울</option>
                   	<option value="포항">포항</option></select>
               	<select class="lctlist" name="check" onchange="loadList()">
                    <option value="전체">전체</option>
                   	<option value="처리">처리</option>
                   	<option value="미처리">미처리</option>
               	</select>
            </tr>
        </table >
    </div>
    
    <div id="reportscroll">        
        <table id="listtable">    
               
               <thead>
                <tr>
                	<td id="tdl" class="day">날짜</td>
                	<td id="tdl" class="list">지역</td>
                	<td id="tdl" class="check" >처리여부</td>
            	</tr>
               </thead>
               
               
               <tbody>
               
               
               </tbody>
               
               
         
        </table>
    </div>
    <div id="detailbox">
        <table id="detailtable">
            <tr>
                <td id="tdl" class="reporter">신고자 : asd</td>
                <td id="tdl" style="text-align: center;">처리 여부</td>
                <td id="tdl" class="lctlist" >훼손 여부</td>
            </tr>
            <tr>
                <td id="tdl" class="reporter">신고 위치 : asd</td>
                <td id="tdl" class="lctlist"> 심각함</td>
                <td id="tdl"><select name="처리 여부"class="lctlist" style="margin-left:3px ;">
                    <option value="처리">처리</option>
                    <option value="미처리">미처리</option></td>
            </tr>
            <tr>
                <td id="tdl" colspan="3" style="text-align: center;">
                    첨부 사진
                </td>
            </tr>
            <tr>
                <td id="tdl" colspan="3">
                    <img src="../IMG/chart.png" class="rptimg" alt="이미지">
                </td>
            </tr>
        </table>
    </div>

</script>
</body>
</html>