<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp"%>
<link rel="stylesheet" href="${path}/include/style.css">

<style>

h3 {
	text-align: center;
    color: black;
    text-decoration : underline;
    text-underline-position: under;
	
} 

</style>
<script>

function checkOnlyOne(element) {
	  
	  const checkboxes 
	      = document.getElementsByName("category");
	  
	  checkboxes.forEach((cb) => {
	    cb.checked = false;
	  })
	  
	  element.checked = true;
}

	
	


function cloth_insert(){
	  var fileCheck = document.getElementById("file1").value;
	  var category = '';
	  if(fileCheck != "")  {
		
		var category = $("input:checkbox[name='category']:checked").val();
	    document.form1.action = "${path}/cloth/cloth_insert.do";
	    document.form1.submit();
	   
	   
	  }else {
	    alert("파일을 첨부해주세요.")
	  }
	
	}
	

</script>

</head>
<body>
<%@ include file = "../include/menu.jsp" %>
<h1 class = "a"><a style ="color:#FFDDA6;" href = "${path}/member/main.do">오늘의 랜덤 스타일</a></h1>
 <% String userid = (String)session.getAttribute("userid"); %>
	<h3>아이템 추가</h3>
	<div style="text-align: center;">
		<form id="form1" name="form1" method="post" enctype="multipart/form-data">
 			<input type = "hidden" name = "userid" value = "<%=userid%>">
			<input type="checkbox" name="category" value="OUTER" onclick="checkOnlyOne(this)" checked> OUTER 
			<input type="checkbox" name="category" value="TOP" onclick='checkOnlyOne(this)'> TOP
			<input type="checkbox" name="category" value="BOTTOM" onclick='checkOnlyOne(this)'> BOTTOM
			<input type="checkbox" name="category" value="SHOES" onclick='checkOnlyOne(this)'> SHOES <br>
			<input type="file" name="file1" id="file1"><br>
			<input type ="submit" value="ADD" onclick = "cloth_insert()">
		</form>
		<!-- <div id = "div1">

</div> -->

	</div>
</body>
</html>