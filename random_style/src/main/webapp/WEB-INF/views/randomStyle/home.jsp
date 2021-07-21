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
	$("#btnLogin").click(function(){
		var userid = $("#userid").val();
		var passwd = $("#passwd").val();
		if(userid ==""){
			alert("아이디를 입력하세요.");
			$("#userid").focus();
			return;
		}
		if(passwd ==""){
			alert("비밀번호를 입력하세요.");
			$("#passwd").focus();
			return;
		}
		document.form1.action = "${path}/member/login_check.do";
		document.form1.submit();
		
	});
});
</script>

</head>
<body>
<h1 class= "a">오늘의 랜덤 스타일</h1>
<img src = "${path}/images/main2.png">
<form name = "form1" method = "post">
<fieldset style = "width:150px; margin:auto;" >
<legend>로그인</legend>
ID<input type = "text" id = "userid" name = "userid"><br>
PASSWD<input type = "password" id = "passwd" name = "passwd"><br>
<button type = "button" id = "btnLogin">로그인</button>
<input type = "button" value = "회원가입"
		 onclick = "location.href = '${path}/member/join.do'"><br>
		 <c:if test = "${message == 'error'}">
	 		<div style = "color:red; text-align:center;">
	 			아이디 또는 비밀번호가 일치하지 않습니다.
	 		</div>
	 	</c:if>
	 	<c:if test = "${message == 'logout' }">
	 		<div style = "color:red; text-align:center;">
	 			로그아웃되었습니다.
	 		</div>
	 	</c:if>
</fieldset> 
</form>
</body>
</html>