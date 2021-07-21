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
	text-decoration: underline;
	text-underline-position: under;
}

button {
	background: black;
	color: white;
}

div.left {
	border: none;
	width: 50%;
	float: left;
	box-sizing: border-box;
}

div.right {
	margin-top: none;
	border: none;
	width: 50%;
	float: right;
	box-sizing: border-box;
}
</style>

<script>
	function cloth_list() {
		var category = document.getElementsByName("category");
		var userid = document.getElementsByName("userid");

		for (var i = 0; i < category.length; i++) {
			if (category[i].checked) {
				var category = category[i].value;
				console.log(category);
			}
		}
		var param = category;
		console.log(param);
		$.ajax({
			url : "${path}/cloth/cloth_list.do",
			type : "post",
			data : "category=" + category,
			success : function(result) {
				$("#list").html(result);
			}

		});

	}

	function delete_board() {
		var no = document.getElementsByName("no");
		console.log(no);
		
		var n=new Array();
		for(i=0; i<no.length; i++){
			if(no[i].checked){
			n[i]=no[i].value;
			}
		}
		console.log(n);
		
	
		if ($("input:checkbox[name=no]:checked").length == 0) {
			alert("삭제할 게시글을 선택해주세요.");
		} else {
			var check = confirm("삭제하시겠습니까?");
			if (check) { 
				location.href = "${path}/board/delete_board.do?no="+n;
			} 
		}

	}

</script>
</head>
<body>
<%@ include file = "../include/menu.jsp" %>

	<h1 class="a">
		<a style = "color: #FFDDA6;"href="${path}/member/main.do">오늘의 랜덤 스타일</a>
	</h1>
	<h3>나의 정보</h3>
	<div id="div1" class="left">
		<table
			style="border: 1px solid green; border-collapse: collapse; width: 100%;">
			<tr align="center"
				style="background: #FFDDA6;; border: 1 px solid #ffbe00;">
				<td>No.</td>
				<td>글제목.</td>
				<td>내용.</td>
				<td>좋아요.</td>
				<td>댓글.</td>
				<td>조회수.</td>
				<td></td>
			</tr>
			<c:forEach var="row" items="${list}">
				<tr align="center">
					<td>${row.no}</td>

					<td><a href="${path}/board/detail.do?no=${row.no}">${row.title}</a></td>
					<td>${row.contents}</td>
					<td>${row.likes}</td>
					<td>${row.comments}</td>
					<td><fmt:formatNumber value="${row.views}" pattern="#,###" /></td>
					<td><input type="checkbox" name="no" value="${row.no}"></td>
				</tr>
			</c:forEach>
			<tr align="right">
				<td colspan="7">
					<button type="button"
						onclick="location.href = '${path}/board/insert_board.do'">ADD</button>
					<button type="button" onclick="delete_board()">DELETE</button>
				</td>
			</tr>



		</table>
	</div>
	<h4 style="text-align: center; margin-bottom: 5px;">나의 아이템</h4>
	<br>
	<div id="div2" class="right">
		<table style="width: 100%;">
			<tr align="center">
				<td><input type="radio" name="category" value="outer" 
					onclick="cloth_list()">OUTER</td>
				<td><input type="radio" name="category" value="top"
					onclick="cloth_list()">TOP</td>
				<td><input type="radio" name="category" value="bottom"
					onclick="cloth_list()">BOTTOM</td>
				<td><input type="radio" name="category" value="shoes"
					onclick="cloth_list()">SHOES</td>
			</tr>

		</table>

		<table  style="width: 100%;">

			<tr align="center">
				<td>
					<div style="border:none;" id="list"></div>
			</tr>
			<tr align="center">
				<td>
					<button type="button"
						onclick="location.href = '${path}/cloth/cloth.do'">ADD</button>
			</tr>
		</table>
	</div>
</body>
</html>