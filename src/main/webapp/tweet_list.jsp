<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ツイート一覧</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
	<div class="container">
		<h1>ツイート一覧</h1>

		<%-- メッセージの表示 --%>
		<c:if test="${not empty message}">
			<p>${message}</p>
		</c:if>

		<%-- 新規投稿 --%>
		<p>
			<a href="new_tweet.jsp">新規投稿</a>
		</p>

		<%-- 投稿者の名前検索フォーム --%>
		<form action="tweet_list" method="get">
			<input type="text" name="author" placeholder="投稿者の名前を入力してください">
			<input type="submit" value="検索">

			<%-- 元に戻すボタン --%>
			<input type="submit" name="allTweets" value="元に戻す">
		</form>

		<%-- 検索結果の表示 --%>
		<%-- 検索結果がある場合のみ表示 --%>
		<c:if test="${not empty tweets}">
			<h2>検索結果</h2>
			<ul class="tweet-list">
				<c:forEach var="tweet" items="${tweets}">
					<li>
						<div class="tweet-content">
							<p>${tweet.content}</p>
							<p class="tweet-info">投稿者: ${tweet.author} - 投稿日時:
								${tweet.postedAt}</p>
							<form action="delete_tweet" method="post">
								<input type="hidden" name="tweet_id" value="${tweet.id}">
								<input type="submit" value="削除">
							</form>
						</div>
					</li>
				</c:forEach>
			</ul>
		</c:if>

		<%-- ツイート一覧の表示 --%>
		<%-- 検索結果がない場合のみ表示 --%>
		<c:if test="${empty tweets}">
			<h2>全てのツイート</h2>
			<ul class="tweet-list">
				<c:forEach var="tweet" items="${tweets}">
					<li>
						<div class="tweet-content">
							<p>${tweet.content}</p>
							<p class="tweet-info">投稿者: ${tweet.author} - 投稿日時:
								${tweet.postedAt}</p>
							<form action="delete_tweet" method="post">
								<input type="hidden" name="tweet_id" value="${tweet.id}">
								<input type="submit" value="削除">
							</form>
						</div>
					</li>
				</c:forEach>
			</ul>
		</c:if>

	</div>
</body>
</html>
