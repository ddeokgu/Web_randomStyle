<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file = "../include/header.jsp" %>
<link rel = "stylesheet" href = "${path}/include/style.css">
<style>


h3 {
	text-align: center;
    color: black;
    text-decoration : underline;
    text-underline-position: under;
	
} 

</style>
<script>
<%
String userid = (String)session.getAttribute("userid");
%>

function board_write(){
		
		if(document.form1.title.value == ""){
			alert("제목을 입력하세요.");
			document.form1.title.focus();
			return;
		}
		 if(document.form1.contents.value == ""){
			alert("내용을 입력하세요.")
			document.form1.contents.focus();
			return;
		}
		
		if(document.getElementById("file1").files.length == 0){
		    alert("인증사진을 첨부해주세요.")
		    return;
		
		}
		if(document.getElementById("file2").files.length == 0){
			alert("스크린샷을 첨부해주세요.")
			return; 
			
		} 
		
		
		
		document.form1.action = "${path}/board/board_write.do";
		document.form1.submit();
	}
	
</script>
</head>
<body>
<%@ include file = "../include/menu.jsp" %>
<h1 class = "a"><a style="color:#FFDDA6;" href = "${path}/member/main.do">오늘의 랜덤 스타일</a></h1>
<h3>글쓰기</h3>

<div style = "text-align:center;">
<form id = "form1" name = "form1" method = "post" enctype = "multipart/form-data">

	<table align = "center"border="1" bordercolor="green">
		<tr>
		
			<td>아이디</td>
			<td><input type = "hidden" name = "userid" value = "<%=userid%>"><%=userid%></td>
		</tr>
		<tr>
		
			<td>제목</td>
			<td><input style = "width:500px;" type = "text" name = "title"></td>
		</tr>

		<tr>
			<td>인증사진</td>
			
			<td> <input type="file" id="file1" name = "file1"></td> 
			
		
			<tr>
			<td>스크린샷</td>
			<td><input type = "file" id = "file2" name = "file2"></td>	
			
		<tr>
			<td>내용</td>
			<td><textarea style = "width:500px; height:300px;" name = "contents" id = "contents"></textarea></td>
		</tr>
		<tr>
			<td colspan = "2" align = "center">
			<input type = "button" value = "등록" onclick = "board_write()">
			<input type = "button" value = "목록" onclick = "location.href='${path}/board/list.do';">
			</td>
		</tr>
	</table>
	
</form>

</div>

</body>
</html>