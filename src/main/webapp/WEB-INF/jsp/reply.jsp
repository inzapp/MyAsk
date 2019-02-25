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
<link rel="stylesheet" href="/css/reply.css">
<title>MyAsk::답변</title>
</head>
<body style="background-color:rgb(60, 60, 60)">
	<div id="content" style="margin-top: 50px; text-align: center">
		<img src="/images/myasklogo.png" style="height:80px; margin-right: 20px"/>
		<div class="replyBox" style="margin-top: 30px">
			<div id="askContent" style="font-weight: bold; font-size: 120%">
				${ askVO.ask }
			</div>
			<form action="/mypage/${ myId }/${ ask_code }" method="post">
				<div id="replyInputText" style="margin-top: 30px">
					<textarea name="reply" rows="3"  maxlength="100" class="inputText"></textarea>
				</div>
				<div style="margin-top: 15px">
					<input type="submit" value="답변" class="replyButton"/>
				</div>
			</form>
		</div>
	</div>
</body>
</html>