<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/css/bad_access.css">
<title>MyAsk::잘못된 접근</title>
</head>
<body style="background-color:rgb(60, 60, 60)">
	<div id="content" style="margin-top: 50px; text-align: center">
		<img src="/images/myasklogo.png" style="height:80px; margin-right: 20px"/>
		<div id="alert" style="margin-top: 50px">
			<span style="color: snow; font-size: 220%">잘못된 접근입니다.</span>
		</div>
		<div id="homeButton" style="margin-top: 10px">
			<input type="button" value="홈으로" class="homeButton" onclick="location.href='/mypage/${ myId }'"/>
		</div>
	</div>
</body>
</html>