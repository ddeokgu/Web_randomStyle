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

h3 {
	text-align: center;
    color: black;
    text-underline-position: under;	
} 


button {
padding: 0;
border: none;
background: none;
}

 table {
   	margin-left:auto; 
   	margin-right:auto;
   	border-radius: 10px;
   	
  } 
  .button {
	background: black;
	color: white;
	
}
</style>
<script>


$(function(){
	
 count_comments();
 likes_check();
 
 function likes_check() {
		var b_no = $("#no").val();
		var userid = $("#sessionId").val();
		var img1 = document.getElementById("heart"); 
		 
		$.ajax({
			url: "${path}/board/likes_check.do?",
			type: "post",
			data: "b_no="+ b_no +"&userid="+userid,
			success : function(result) {
				if (result == 0) { 
					img1.src = "${path}/images/heart1.jpeg";
				} else {
					img1.src = "${path}/images/heart2.jpeg";
				}
			}
		});
		
	 
 }
 function likes(){
	$.ajax({
		url: "${path}/board/likes.do",    
		type: "post",
		data: "no=" + $("#no").val() +"&userid=" + $("#userid").val(),
		success: function(result) { 
				count_likes();
		}
		
	});
 }
 
 function likes_min(){
		$.ajax({ 
			url: "${path}/board/likes_min.do",    
			type: "post",
			data: "no=" + $("#no").val() +"&userid=" + $("#userid").val(),
			success: function(result) { 
					count_likes();
			}
			
		});
	 }

 function likes_check_insert(){
	 	
		var b_no = $("#no").val(); 
		var userid = $("#sessionId").val();
		$.ajax({
			url: "${path}/board/likes_check_insert.do",
			type: "post",
			data: "b_no=" + $("#no").val() +"&userid=" + $("#sessionId").val(),
			success: function(result) {
						likes();
			
			}
		});
		
	}
 function likes_check_delete(){ 
		var b_no = $("#no").val();  
		var userid = $("#sessionId").val();
		$.ajax({
			url: "${path}/board/likes_check_delete.do",
			type: "post",
			data: "b_no=" + $("#no").val() +"&userid=" + $("#sessionId").val(),
			success: function(result) {
				likes_min();
			}
		});
		
	}
 
 $("#btnLikes").click(function(){  
	 var img1 = document.getElementById("heart"); 
	 var url = "";
	if (img1.src == "http://localhost:8080/randomStyle/images/heart1.jpeg"){
		img1.src = "${path}/images/heart2.jpeg";
		likes_check_insert();
	} else {
		img1.src ="${path}/images/heart1.jpeg";
		likes_check_delete();
	}
	
 });
 	
function count_likes(){
	$.ajax({ 
		type : "post",
		url : "${path}/board/likes_count.do",
		data : "no=" + $("#no").val(),
		success : function(result){
			$("#div1").html(result);
		}
	});
}	

function count_comments(){
	$.ajax({
		type : "post",
		url : "${path}/comments/comments_count.do",
		data : "b_no=" + $("#no").val(),
		success : function(result){
			$("#div2").html(result);
		}
	});
}	



$("#btnComments").click(function(){
	if($("#comments_div").css("display") == "none") {
	$("#comments_div").show();
	$.ajax({
		type: "post", 
		url : "${path}/comments/comments_list.do",
		data: "b_no=" + $("#no").val(),
		success:  function(result){
			$("#comments").val("");
			 	$("#list_div").html(result);
			 	console.log(result);
			 	
			}
		});
	} else {
		$("#comments_div").hide();
	}
	});
	
$("#btnShare").click(function btnShare(){

	var url = '';
	var textarea = document.createElement("textarea");
	document.body.appendChild(textarea);
	url = window.document.location.href;
	textarea.value = url;
	textarea.select();
	document.execCommand("copy");
	document.body.removeChild(textarea);
	alert("URL이 복사되었습니다.")
});
 
}); 
	 
	

</script>
</head>
<body>
<%@ include file = "../include/menu.jsp" %>
<% String userid = (String)session.getAttribute("userid"); %>
<input type = "hidden" id = "sessionId" value = <%=userid%>>
<h1 class = "a"><a style = "color:#FFDDA6;"href = "${path}/member/main.do">오늘의 랜덤 스타일</a></h1>
<input type ="hidden" id ="userid" value = "${dto.userid}">
<input id = "no" name = "no" type = "hidden" value = "${dto.no}">
<table style="border:1px solid gray;">

	<tr>
		<td colspan ="5" style="border-bottom: none;"><h4 style="text-align: center; margin-top: 0px; margin-bottom: 0px;">${dto.title}</h4></td>
		
	</tr>
	<tr> 
		<td colspan ="5" style="border-top: none; border-bottom: none;"><img src="<spring:url value = '/board/${dto.photo1_url}'/>" 
		width="300px" height="300px"/></td> 
	</tr>
	<tr>
		<td style="width: 30px;">
		<button type="button" id = "btnLikes">
				<img id="heart" src ="${path}/images/heart1.jpeg" style="width:30px; height:30px;"></button></td>
		<td style="width: 30px;">
			<div style="border-left:none;" id = "div1">${dto.likes}</div></td>
		<td style="width: 40px;">
			<button type="button" id = "btnComments">
				<img src="${path}/images/comments.png" style="width:30px; height:30px;"></button></td>
			<td style="width: 30px;">
		<div id = "div2">${dto.comments}</div></td>
			<td>
		<button type="button" id = "btnShare" >
				<img src="${path}/images/share.png" style="width:25px; height:25px;"></button></td>
	</tr>
	<tr>
		<td colspan="5"><span style="font-weight: bold;">${dto.userid}</span><div style="font-size:15px; margin-top:10px;">${dto.contents}</div></td>
	</tr>
</table>



<div id = "comments_div" style="display: none; ">
<form style="text-align:center;" name = "form1" method = "post" action = "${path}/comments/comments_write.do">
<input id = "b_no" name = "b_no" type = "hidden" value = "${dto.no}">
<table style="text-align: center; margin:auto; margin-top:5px;"> 
	<tr style = "background-color:#FFDDA6;">
		<td> <input type = "hidden" name="userid" value = "${userid}">${userid}</td>
	</tr>
	<tr>
		<td style="padding-top: 5px;
    padding-bottom: 5px;"><input type = "text" name = "comments" id = "comments"></td>
	</tr>
	<tr>
		<td><input class="button" type = "submit" value = "ADD"></td>
	</tr>
</table> 
</form>
<div style ="margin-top:10px;" id = "list_div"></div>
</div>

</body>
</html>