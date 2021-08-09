<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<link rel = "stylesheet" href = "${path}/include/style.css">

<script>

$(function(){
	$("#btnWrite").click(function(){
		location.href = "${path}/board/insert_board.do";
		
	});
});

</script>
<style>
h3 {
	text-align: center;
    color: black;
    text-decoration : underline;
    text-underline-position: under;
	
} 
  .button {
  	margin-top: 10px;
	background: black;
	color: white;
}
</style>
</head>
<body>
<%@ include file = "../include/menu.jsp" %>

<h1  class = "a"><a style = "color:#FFDDA6;" href = "${path}/member/main.do">오늘의 랜덤 스타일</a></h1>

<h3>게시판</h3>

<table style="margin-left:auto; margin-right:auto; border:1px solid black; border-radius: 10px;">
	<tr align = "center">
		<th>No.</th>
		<th>ID</th>
		<th>글제목</th>
		<th>내용</th>
		<th>좋아요</th>
		<th>댓글</th>
		<th>조회수</th>
	</tr>
<c:forEach var = "row" items = "${list}">
	<tr align = "center">
	<td>${row.no}</td>
	<td><input type = "hidden" id = "userid" value = "${row.userid}">${row.userid}</td>
	<td><a href = "${path}/board/detail.do?no=${row.no}">${row.title}</a></td>
	<td>${row.contents}</td>
	<td>${row.likes}</td>
	<td>${row.comments}</td>
	<td><fmt:formatNumber value = "${row.views}" pattern = "#,###"/></td>
	</tr>
	</c:forEach>
	
</table>
<div style = "text-align:center;">
<input class="button"; style = "width:100px;" type = "button" id = "btnWrite" value ="WRITE">
</div>
</body>
</html>