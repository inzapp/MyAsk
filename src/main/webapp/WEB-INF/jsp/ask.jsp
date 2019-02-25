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
<link rel="stylesheet" href="/css/ask.css">
<title>MyAsk::질문하기</title>
</head>
<body style="background-color:rgb(60, 60, 60)">
	<div style="text-align: center; margin-top: 50px;">
		<div id="header">
			<img src="/images/myasklogo.png" style="height:80px; margin-right: 20px"/>
			<div style="margin-top: 30px; color: snow; font-size: 130%; font-weight: bold">
				${ userVO.name } 님께 익명으로 질문해보세요!
			</div>
			<c:set scope="session" var="ask_delay" value="${ sessionScope.ask_delay }"/>
			<c:choose>
				<c:when test="${ ask_delay eq null }">
					<div class="askBox" style="margin-top: 20px">
						<form action="/ask/${ userVO.id }" method="post">
							<textarea name="ask" rows="3" maxlength="100" class="inputText"></textarea>
							<input type="submit" value="질문하기" class="askButton" style="margin-top: 20px"/>
						</form>
					</div>
				</c:when>
				<c:otherwise>
					<div class="delayAlert" style="margin-top: 20px">
						질문 등록이 완료되었습니다!<br/>
						답변이 완료된다면 해당 질문이 타임라인에 게시됩니다.<br/>
						연속된 질문을 방지하기 위해 10초 후 재질문이 가능합니다.<br/><br/>
						
						질문지를 만들어 친구들과 공유해보세요!<br/>
						<a href="/signup">질문지 만들기</a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<div id="content">
			<div style="margin-top: 30px;">
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