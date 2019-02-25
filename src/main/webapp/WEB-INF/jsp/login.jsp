<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/css/login.css">
<title>MyAsk</title>
</head>
<body style="background-color:rgb(60, 60, 60)">
	<div class="center" style="text-align: center">
		<div class="container">
			<img src="/images/myasklogo.png" style="height:80px; margin-right: 20px"/>
			<form action="/login" method="POST">
				<div style="text-align: center; margin-top: 20px">
					<input type="text" name="id" class="roundInput" size=20 placeholder="아이디">
				</div>
				<div style="text-align: center; margin-top: 10px">
					<input type="password" name="password" class="roundInput" size=20 placeholder="비밀번호">
				</div>
				<div style="margin-top:10px">
					<input type="submit" value="로그인" class="invisibleButton">
				</div>
				<div>
					<a href="/signup" style="color:snow; font-size:110%">회원가입</a>
				</div>
			</form>
			<div style="margin-top: 50px; text-align:center">
				<span style="color: rgb(115, 115, 115)">@Copyright by Inz</span>
			</div>
		</div>
	</div>
</body>
</html>