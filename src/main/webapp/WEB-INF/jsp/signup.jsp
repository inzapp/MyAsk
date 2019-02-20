<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<title>MyAsk::Sign Up</title>
<style>
	.center {
    position: absolute;
    top: 50%;
    left: 50%;
    -moz-transform: translateX(-50%) translateY(-50%);
    -webkit-transform: translateX(-50%) translateY(-50%);
    transform: translateX(-50%) translateY(-50%);
	}

	.roundInput {
		border: none;
		border-radius: 10px;
		text-align: center;
		font-size: 110%;
		padding: 7px;
		background-color: white;
	}
	
	.comment {
		color: snow;
		margin-top: 3px;
	}
	
	.error {
		margin-top: 10px;
		color: red;
		font-size: 110%;
	}
	
	.invisibleButton {
		padding: 10px;
		width: 100px;
		font-size: 110%;
		color: snow;
		border-radius: 10px; 
		border:solid 2px snow; 
		background-color: rgb(60, 60, 60);
		font-weight: bold;
	}
	
	.signup {
		color: snow;
		text-align: center;
		font-size: 170%;
		font-weight: bold;
	}
</style>
</head>
<body style="background-color:rgb(60, 60, 60)">
	<div class="center" style="text-align: center">
		<div class="container">
			<img src="/images/myasklogo.png" style="height:80px; margin-right: 20px"/>
			<div class="signup" style="margin-top: 10px">
				회원 가입
			</div>
			<form action="/signup" method="POST">
				<div style="text-align: center; margin-top: 20px">
					<input type="text" name="id" class="roundInput" size=20 placeholder="아이디">
					<div class="comment">
						4 ~ 13글자 영문, 숫자 조합 가능
					</div>
				</div>
				<div style="text-align: center; margin-top: 15px">
					<input type="password" name="password" class="roundInput" size=20 placeholder="비밀번호 : 6 ~ 16자리">
					<div class="comment">
						6 ~ 16글자 영문, 숫자, 특수문자 조합 가능
					</div>
				</div>
				<div style="text-align: center; margin-top: 15px">
					<input type="password" name="password2" class="roundInput" size=20 placeholder="비밀번호 확인">
					<div class="comment">
						비밀번호 확인
					</div>
				</div>
				<div style="text-align: center; margin-top: 15px">
					<input type="text" name="name" class="roundInput" size=20 placeholder="이름">
					<div class="comment">
						1 ~ 10글자
					</div>
				</div>
				<c:set scope="session" var="badAccount" value="${sessionScope.badAccount}"/>
				<c:if test="${ badAccount ne null }">
					<div class="error">
						계정 형식에 맞지 않습니다.
					</div>
				</c:if>
				<c:set scope="session" var="idAlreadyExist" value="${sessionScope.idAlreadyExist}"/>
				<c:if test="${ idAlreadyExist ne null }">
					<div class="error">
						이미 존재하는 아이디 입니다.
					</div>
				</c:if>
				<c:set scope="session" var="pwIncorrect" value="${sessionScope.pwIncorrect}"/>
				<c:if test="${ pwIncorrect ne null }">
					<div class="error">
						비밀번호가 일치하지 않습니다.
					</div>
				</c:if>
				<div style="margin-top:20px">
					<input type="submit" value="회원가입" class="invisibleButton">
				</div>
			</form>
			<div style="margin-top: 50px; text-align:center">
				<span style="color: rgb(115, 115, 115)">@Copyright by Inz</span>
			</div>
		</div>
	</div>
</body>
</html>