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
<link rel="stylesheet" href="/css/signup.css">
<title>MyAsk::Sign Up</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
/* 	$(function(){
		$('#signUpBt').click(function(){
			$.ajax({
				url: '', 
				type: 'post',
				data: {
					signUpId: $('#id').val();
				}
			}).done(function(data) {
				
				var pw1 = $('#password').val();
				var pw2 = $('#password2').val();
				
				if(pw1 === pw2){
					var id = $('#id').val();
				}
				else{
					alert('비밀번호가 일치하지 않습니다.');
				}
			}).fail(function(data){
				
			});
		});
	}); */
</script>
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
					<input type="text" name="id" id="id" class="roundInput" size=20 placeholder="아이디">
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
					<input type="submit" id="signUpBt" value="회원가입" class="invisibleButton">
				</div>
			</form>
			<div style="margin-top: 50px; text-align:center">
				<span style="color: rgb(115, 115, 115)">@Copyright by Inz</span>
			</div>
		</div>
	</div>
</body>
</html>