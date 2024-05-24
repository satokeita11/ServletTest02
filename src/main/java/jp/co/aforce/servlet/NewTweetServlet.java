package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.aforce.dao.TweetDAO;

@WebServlet("/new_tweet")
public class NewTweetServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String content = request.getParameter("content");
		String author = request.getParameter("author");

		// 投稿者名の文字数が255文字を超える場合は投稿失敗とする
		if (author != null && author.length() > 255) {
			request.setAttribute("message", "投稿に失敗しました。");

			// ツイート一覧を取得して設定
			TweetDAO tweetDAO = new TweetDAO();
			try {
				request.setAttribute("tweets", tweetDAO.getAllTweets());
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("message", "ツイート一覧の取得に失敗しました");
			}

			request.getRequestDispatcher("tweet_list.jsp").forward(request, response);
			return;
		}

		TweetDAO tweetDAO = new TweetDAO();

		try {
			tweetDAO.addTweet(content, author);
			request.setAttribute("message", "投稿が成功しました");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "投稿に失敗しました: " + e.getMessage());
		}

		// ツイート一覧を取得して設定
		try {
			request.setAttribute("tweets", tweetDAO.getAllTweets());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "ツイート一覧の取得に失敗しました");
		}

		request.getRequestDispatcher("tweet_list.jsp").forward(request, response);
	}
}
