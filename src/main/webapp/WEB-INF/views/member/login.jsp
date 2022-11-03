<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="login" method="post" id="joinForm">
					<div class="row">
						<label for="ipId" class="form-label"><b>아이디</b></label>
					</div>
					<div class="row">
						<div class="col-6">
							<input type="text" name="id" class="form-control border-success border-opacity-25" id="ipId" placeholder="아이디를 입력해주세요">
						</div>
					</div>
					<div class="my-4">
						<label for="ipPw" class="form-label"><b>비밀번호</b></label>
						<input type="text" name="pw" class="form-control border-success border-opacity-25" id="ipPw" placeholder="비밀번호를 입력해주세요">
					</div>
					<input type="submit" value="로그인"/>
	</form>

</body>
</html>