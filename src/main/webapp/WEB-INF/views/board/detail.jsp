<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<c:import url="../temp/boot.jsp"></c:import>

</head>
<body>
	<h1>Detail Page</h1>
	<table class="table table-striped-columns">
      <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">title</th>
      <th scope="col">writer</th>
      <th scope="col">contents</th>
      <th scope="col">hit</th>
      <th scope="col">regDate</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">${qnaVO.num}</th>
      <td>${qnaVO.title}</td>
      <td>${qnaVO.writer}</td>
      <td>${qnaVO.contents}</td>
      <td>${qnaVO.hit}</td>
      <td>${qnaVO.regDate}</td>
    </tr>
    </tbody>
    </table>
     <c:forEach items="${qnaVO.qnaFilesVO}" var="qna">
    <img src="/file/qna/${qna.fileName}">
    </c:forEach>
    
</body>
</html>