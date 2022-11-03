<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="./temp/boot.jsp"></c:import>
</head>
<body>
	<h1>Index page</h1>
	<a href="/qna/list" style="width:30px">qna</a>
	<a href="/qna/add" style="width:30px">qna 쓰자쓰자</a>

	
	<!-- 로그인 성공 -->
		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="Principal" var="user"/>
			<h3>ID : ${user.id} </h3>
			<h3>Name : ${user.name}</h3>
				<a href="./member/logout">logout</a>
				<sec:authorize access="hasRole('ADMIN')">
					<a href="/admin">Go Admin</a>
				</sec:authorize>
				
				<sec:authorize access="hasAnyRole('ADMIN','MANAGER')">
					<a href="/admin">Go Manager</a>
				</sec:authorize>
		</sec:authorize>
		<sec:authorize access="!isAuthenticated()">
				<!-- 로그인 전 -->
				<a href="./member/login">login</a>
				<a href="./member/join">Join</a>
			</sec:authorize>
</body>
</html>