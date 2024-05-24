<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規ツイート投稿</title>
<link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
	<div class="container">
		<h1>新規ツイート投稿</h1>
		<form action="new_tweet" method="post">
			<div>
				<label for="content">内容:</label><br>
				<textarea id="content" name="content" rows="4" cols="50" required></textarea>
			</div>
			<div>
				<label for="author">投稿者:</label><br> <input type="text"
					id="author" name="author" required>
			</div>
			<div>
				<input type="submit" value="投稿する">
			</div>
		</form>
		<a href="tweet_list">一覧に戻る</a>
	</div>
</body>
</html>
