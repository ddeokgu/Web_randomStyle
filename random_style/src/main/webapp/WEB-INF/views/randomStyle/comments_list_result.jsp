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
  table {
   	margin-left:auto; 
   	margin-right:auto;
    border-top: 1px solid #444444;
    border-collapse: collapse;
  }
  tr, td {
    border-bottom: 1px solid #444444;
    padding: 10px;
  }
</style>
<script>

	function del(c_no){
		var check = confirm("삭제하시겠습니까?");
		if(check){
		location.href = "${path}/comments/comments_delete.do?c_no="+c_no;
		}
		
	}



</script>
</head>
<body>

	<table border = "1" style="text-align:center; margin:auto; border:2px solid green;">
		<tr style = "background-color:#FFDDA6;">
			<td colspan = "3">아이디</td>
			<td>댓글내용</td>
			<td colspan ="4">작성시각</td>
		</tr>
		
		<c:forEach var="row" items="${list}">
	
			<tr>
				<td colspan = "3">${row.userid}</td>
				<td>${row.comments}</td>
				<td>${row.write_date}</td>
				<td colspan = "2"> 
		<c:if test = "${row.userid == sessionScope.userid}">
		<button style="background-color:red; color:white;" onclick="del('${row.c_no}')">DELETE</button>
		
		</c:if>
		</td>
			</tr>
		</c:forEach>
		
	</table>

</body>
</html>