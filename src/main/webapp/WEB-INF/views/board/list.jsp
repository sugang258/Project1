<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/boot.jsp"></c:import>
</head>
<body>

	<h1 class="text-center">List Page</h1>
	<form class="row g-3" action="/qna/list">
	  <div class="col-auto" style="margin-top:20px; margin-left:650px;">
	    <label for="inputPassword2" class="visually-hidden">search</label>
	    <input type="text" id="search" name="search" value="">
	  </div>
	  <div class="col-auto">
	    <button type="submit" class="btn btn-primary mb-3">Search</button>
	  </div>
	</form>
	
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
	<c:forEach items="${ar}" var="ar">
  <tbody>
    <tr>
      <th scope="row">${ar.num}</th>
      <td><a href="./detail?num=${ar.num}">${ar.title}</a></td>
      <td>${ar.writer}</td>
      <td>${ar.contents}</td>
      <td>${ar.hit}</td>
      <td>${ar.regDate}</td>
    </tr>
    
  </tbody>
  </c:forEach>

  </table>
  
  <div class="container" id="pager" style="margin-left:700px;">
		<nav aria-label="Page navigation example" class="justify-content-center">
			<ul class="pagination">
				<c:if test="${pager.pre}">
					<li class="page-item"><a class="page-link" href="./List?page=${pager.startNum-1}">Previous</a></li>
				</c:if>
				<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
					<li class="page-item"><a class="page-link" href="./List?page=${i}">${i}</a></li>
				</c:forEach>
							
				<li class="page-item ${pager.next?'':'disabled'}">
				<a class="page-link" href="./List?page=${pager.lastNum+1}">Next</a></li>
			</ul>
		</nav>
 </div>
</body>
</html>