<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file = "../include/header.jsp" %>
<link rel="stylesheet" href="${path}/include/style.css">
<script>
function cloth_delete(){
	var no = $("#no").val();
	console.log(no);
	var check = confirm("삭제하시겠습니까?");
		if(check){
			location.href = "${path}/cloth/cloth_delete.do?no="+no;
			} 
}


</script>


</head>
<body>
<%@ include file = "../include/menu.jsp" %>

	<h1 class="a">
		<a style = "color: #FFDDA6;"href="${path}/member/main.do">오늘의 랜덤 스타일</a>
	</h1>
	<table style="margin-left:auto; margin-right:auto; border:1px solid red;">
	<tr>
		<td><input type="hidden" id="no" value="${dto.no}">
	</tr>
	
	<tr>
	<td><img src="<spring:url value = '/items/${dto.photo_url}'/>" 
		width="300px" height="300px"/></td>
		</tr>
	</table>
<div style="text-align:center; margin-top:20px;">
<button type = "button" onclick = "cloth_delete()">DELETE</button></div>
	
	
	
		
</body>
</html>