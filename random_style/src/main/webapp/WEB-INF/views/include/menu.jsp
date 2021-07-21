<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<div style = "text-align:right;">
<c:choose>
	<c:when test = "${sessionScope.userid == null }">
		<a href = "${path}/member/login.do">로그인</a>
		<%-- <a href = "${path}/admin/login.do">관리자 로그인</a> --%>
	</c:when>
	
	<c:otherwise>
		<a href = "${path}/member/logout.do">로그아웃</a>
		<%-- <a href = "${path}/admin/logout.do">로그아웃(관리자)</a> --%>
	</c:otherwise>
</c:choose>

	</div>
	
	