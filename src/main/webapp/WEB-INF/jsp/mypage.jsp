<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<title>MyAsk::마이페이지</title>
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
	
	.error {
		margin-top: 10px;
		color: red;
		font-size: 110%;
	}
	
	.newAskBox {
		margin: 0px auto;
		width: 500px;
		padding: 20px;
		margin-top: 30px;
		font-weight: bold;
		font-size: 120%;
		color: black;
		background-color: snow;
		border: none;
		border-radius: 10px;
	}
	
	.completedAskBox {
		margin: 0px auto;
		width: 500px;
		padding: 20px;
		margin-top: 30px;
		font-weight: bold;
		font-size: 120%;
		color: black;
		background-color: snow;
		border: none;
		border-radius: 10px;
	}
	
	.deleteSubmit {
		border: none;
		background-color: snow;
		color: crimson;
	}
	
	.logout {
		padding: 10px;
		border: solid 3px snow;
		border-radius: 15px;
		background-color: rgb(60, 60, 60);
		color: snow;
		font-size: 130%;
		font-weight: bold;
	}
	
	.myAskLink {
		width: 450px;
		margin: 0px auto;
		padding: 10px;
		border: solid 3px snow;
		border-radius: 50px;
		background-color: rgb(60, 60, 60);
		color: snow;
		font-size:150%;
		font-weight: bold;
	}
	
	.copyButton {
		width: 100px;
		font-size: 80%;
		padding: 5px;
		border: solid 2px snow;
		border-radius: 20px;
		color: snow;
		background-color: rgb(60, 60, 60);
	}
</style>
<script type="text/javascript">
	function copyToClipboard(elementId) {
		var aux = document.createElement("input");
		aux.setAttribute("value", document.getElementById(elementId).innerHTML);
		document.body.appendChild(aux);
		aux.select();
		document.execCommand("copy");
		document.body.removeChild(aux);
		alert("복사되었습니다!");
	}
</script>
</head>
<body style="background-color:rgb(60, 60, 60)">
	<div style="text-align: center; margin-top: 50px;">
		<div id="header">
			<img src="/images/myasklogo.png" style="height:80px; margin-right: 20px"/>
			<div id="logout" style="margin-top: 20px">
				<form action="/mypage/logout" method="post">
					<input type="submit" value="로그아웃" class="logout"/>
				</form>
			</div>
			<div class="myAskLink" style="margin-top: 20px">
				<div>
					나의 MyAsk 링크
				</div>
				<div>
					<a href="#" onclick="copyToClipboard('myAskLink')">
						<span id="myAskLink">${ myAskLink }</span>
					</a>
				</div>
			</div>
			<c:set var="newAskCount" value="${ newAskCount }"/>
			<c:choose>
				<c:when test="${ 0 < newAskCount }">
					<div style="margin-top: 30px; color: snow; font-size: 130%; font-weight: bold">
						${ myName }님 ${ newAskCount }개의 새 질문이 있습니다!
					</div>
				</c:when>
				<c:otherwise>
					<div style="margin-top: 30px; color: snow; font-size: 120%; font-weight: bold">
						새로운 질문이 없습니다.
					</div>
					<div style="margin-top: 3px; color: snow; font-size: 120%; font-weight: bold">
						지금 바로 상단의 링크를 공유해 친구들에게 질문을 받아보세요!
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<div id="content">
			<c:if test="${ 0 < newAskCount }">
				<div style="margin-top: 50px;">
					<c:forEach var="curNewAskVO" items="${ newAskVOList }">
						<div class="newAskBox">
							<div id="qAndTime">
								<div id="qBox">
									${ curNewAskVO.ask }
								</div>
								<div id="reg_date" style="color:grey; font-size: 70%">
									${ curNewAskVO.reg_date }
								</div>
								<div id="replyAndDeleteLink">
									<div style="display: inline-block">
										<a href="/mypage/${ myId }/${ curNewAskVO.ask_code }">답변</a>
									</div>
									<div style="display: inline-block; margin-left: 120px">
										<form:form action="/mypage/${ myId }/${ curNewAskVO.ask_code }" method="delete">
											<input type="submit" value="삭제" class="deleteSubmit"/>
										</form:form>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:if>
			<div style="margin-top: 50px;">
				<c:forEach var="curCompletedAskVO" items="${ completedAskVOList }">
					<div class="completedAskBox">
						<div id="qAndTime">
							<div id="qBox">
								${ curCompletedAskVO.ask }
							</div>
							<div id="askTime" style="color:grey; font-size: 70%">
								${ curCompletedAskVO.reg_date }
							</div>
						</div>
						<div id="arrowAndAnswerBox">
							<div id="answerAndTime" style="margin-top: 10px">
								<div id="answer">
									${ curCompletedAskVO.reply }
								</div>
								<div id="time" style="color:grey; font-size: 70%">
									${ curCompletedAskVO.reply_date }
								</div>
							</div>
						</div>
						<div id="deleteForm" style="margin-top: 5px">
							<form:form action="/mypage/${ myId }/${ curCompletedAskVO.ask_code }" method="delete">
								<input type="submit" value="삭제" class="deleteSubmit">
							</form:form>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<div style="margin-top: 50px; margin-bottom: 50px; text-align:center">
		<span style="color: rgb(115, 115, 115)">@Copyright by Inz</span>
	</div>
</body>
</html>