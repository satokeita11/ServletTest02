package jp.co.aforce.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.aforce.beans.Tweet;
import jp.co.aforce.dao.TweetDAO;

@WebServlet("/tweet_list")
public class TweetListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String author = request.getParameter("author"); // リクエストから投稿者名を取得

		TweetDAO tweetDAO = new TweetDAO();
		try {
			List<Tweet> tweets;
			if (author != null && !author.isEmpty()) {
				// 投稿者名が指定されている場合はその投稿者のツイートのみ取得
				tweets = tweetDAO.getTweetsByAuthor(author);
			} else {
				// 投稿者名が指定されていない場合はすべてのツイートを取得
				tweets = tweetDAO.getAllTweets();
			}
			request.setAttribute("tweets", tweets);
		} catch (Exception e) {
			e.printStackTrace();
			// エラー処理を追加する
		}
		request.getRequestDispatcher("tweet_list.jsp").forward(request, response);
	}
}
