<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file = "../include/header.jsp" %>
<script>
function cloth_delete() {
	var no = document.getElementsByName("cloth_no");
	console.log(no);
	
	var n=new Array();
	for(i=0; i<no.length; i++){
		if(no[i].checked){
		n[i]=no[i].value;
		}
	}
	console.log(n);
	if ($("input:checkbox[name=cloth_no]:checked").length == 0) {
		alert("삭제할 아이템을 선택해주세요.");
	} else {
		var check = confirm("삭제하시겠습니까?");
		if (check) { 
			location.href = "${path}/cloth/cloth_delete.do?no="+n;
		} 
	}

}
</script>



</head>
<body>
<table style = "border:none;">
<tr style = "background:#FFDDA6;">
	<td>번호</td>
	<td>카테고리</td>
	<td>아이템</td>
</tr>
<c:forEach var = "row" items="${list}">
	<tr>
		<td>${row.no}</td>
		<td>${row.category}</td>
		<td><a href = "${path}/cloth/cloth_detail.do?no=${row.no}">${row.photo_url}</a></td> 
	</tr>
		
</c:forEach>
<!-- <tr align = "right">
<td colspan = "4">
<button type="button"
						onclick="cloth_delete()">DELETE</button>
</tr> -->
</table>


</body>
</html>