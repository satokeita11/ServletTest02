package jp.co.aforce.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.aforce.dao.TweetDAO;

@WebServlet("/delete_tweet")
public class DeleteTweetServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータから削除するツイートのIDを取得
		String tweetIdStr = request.getParameter("tweet_id");
		if (tweetIdStr != null && !tweetIdStr.isEmpty()) {
			int tweetId = Integer.parseInt(tweetIdStr);

			// ツイートDAOを使ってツイートを削除
			TweetDAO tweetDAO = new TweetDAO();
			try {
				tweetDAO.deleteTweet(tweetId);
				request.setAttribute("message", "ツイートの削除が成功しました");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("message", "ツイートの削除に失敗しました: " + e.getMessage());
			}
		}

		// ツイート一覧を再取得してツイート一覧ページにリダイレクト
		response.sendRedirect(request.getContextPath() + "/tweet_list.jsp");
	}
}
