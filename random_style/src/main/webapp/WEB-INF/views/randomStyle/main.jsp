<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<link rel = "stylesheet" href = "${path}/include/style.css">
<style>
	 
 .b {
  	font-size: 15px;
  	margin-right: 10px;
  	
}
</style>
<script>

	function category_check(check){ 
	if(check.checked== true) {
		var category = check.value;
		console.log(category);
		$.ajax({
			url: "${path}/random/random_check.do",
			type: "post",
			data: "category=" + category, 
			success: function(result) {
				console.log(result);
				
				var arr=result.split("-");
				console.log(arr);
				if(arr[0] == 0) {
					alert("등록된 아이템이 없습니다."+"\n"+"(아이템을 먼저 등록해주세요.)");
					
					
				}
			}
		});
		
	} 
}
	
function random() {
	var category = $("input:checkbox[name='category']:checked").val();
	console.log(category);
	if(category == null) {
		alert("카테고리를 선택해주세요"); 
		return false;
	} else {
		
	document.form1.action = "${path}/random/random.do";
	document.form1.submit();
	return true;
	}
}


</script>
</head>

<body> 
<%@ include file = "../include/menu.jsp" %>
<h1 class = "a"><a style= "color:#FFDDA6;" href = "${path}/member/main.do">오늘의 랜덤 스타일</a></h1>
<div style="text-align:center; height: 50px;">
 
<button class = "b" id = "btnBoard" onclick="location.href='${path}/board/list.do'">BOARD</button>
<button class = "b" id = "btnShop">SHOP</button>
<button style = "font-size: 15px; text-transform:uppercase;"id = "btnUser" onclick="location.href='${path}/board/list_userid.do?userid=${userid}'">${userid}</button>
</div>

<div style="text-align:center">
<form id = "form1" name = "form1" method = "post" onsubmit="return random()">
<input type = "checkbox" name = "category" onclick = "category_check(this)" value = "outer">OUTER
<input type = "checkbox" name = "category" onclick = "category_check(this)" value = "top">TOP
<input type = "checkbox" name = "category" onclick = "category_check(this)" value = "bottom">BOTTOM
<input type = "checkbox" name = "category" onclick = "category_check(this)" value = "shoes">SHOES<br>
<input type = "image" style="height:500px; weight:500px;" 
 src = "${path}/images/dice3.png">
</form>
</div>



</body>
</html>