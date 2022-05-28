<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%pageContext.setAttribute("context", request.getContextPath());%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/citycitybangbang/CSS/list.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<<<<<<< HEAD
<script src="../JS/list.js" charset="UTF-8" ></script>

=======
<script src="/citycitybangbang/JS/list.js"></script>
>>>>>>> branch 'main' of https://github.com/2022-SMHRD-KDT-AI-2/citycitybangbang.git
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
                   	<option value="대구">대구</option>
                   	<option value="인천">인천</option>
                   	<option value="대전">대전</option>
                   	<option value="울산">울산</option>
                   	<option value="세종">세종</option>
                   	<option value="경기">경기</option>
                   	<option value="강원">강원</option>
                   	<option value="충북">충북</option>
                   	<option value="충남">충남</option>
                   	<option value="전북">전북</option>
                   	<option value="전남">전남</option>
                   	<option value="경북">경북</option>
                   	<option value="경남">경남</option>
                   	<option value="제주도">제주도</option>
                   	</select>
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
                	<td id="tdl" class="btn" >상세보기</td>
            	</tr>
               </thead>
               
               
               <tbody>
               
               
               </tbody>
               
               
         
        </table>
    </div>
    <div id="detailbox">
        <table id="detailtable">

        </table>
    </div>
</body>
</html>