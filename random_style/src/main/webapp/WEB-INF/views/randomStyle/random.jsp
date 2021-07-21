<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file = "../include/header.jsp" %>

<link rel = "stylesheet" href = "${path}/include/style.css">
</head>
<body>
<%@ include file = "../include/menu.jsp" %>
<h1 class = "a"><a style= "color:#FFDDA6;" href = "${path}/member/main.do">오늘의 랜덤 스타일</a></h1>
<div align = "center"><span style = "color:red;">*스타일링이 마음에 드신다면 스크린샷을 찍어주세요.</span></div>

 <div style="text-align:center;">
<c:if test = "${map.containsKey('outer')== true}">
<div id = "outer"><img style ="width: 150px; height: 150px;"
			 src="<spring:url value = '/items/${map.outer}'/>"/></div>
 </c:if>  

 
 <c:if test = "${map.containsKey('top')== true}">
<div id = "top"><img style ="width: 150px; height: 150px;" 
			src="<spring:url value = '/items/${map.top}'/>" /></div><br>
</c:if> 

<c:if test = "${map.containsKey('bottom')== true}">
<div id = "bottom"><img style ="width: 150px; height: 150px;"
		src="<spring:url value = '/items/${map.bottom}'/>" /></div><br>
</c:if> 

<c:if test = "${map.containsKey('shoes')== true}">
<div id = "shoes"><img style ="width: 150px; height: 150px;" 
		src="<spring:url value = '/items/${map.shoes}'/>" /></div>
</c:if>
</div>

</body>
</html>