package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.aforce.beans.Tweet;

public class TweetDAO extends DAO {

	// ツイート一覧を取得するメソッド
	public List<Tweet> getAllTweets() throws Exception {
		List<Tweet> tweets = new ArrayList<>();
		String sql = "SELECT id, content, posted_at, author FROM tweets ORDER BY posted_at DESC";
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery()) {
			while (rs.next()) {
				int id = rs.getInt("id");
				String content = rs.getString("content");
				String postedAt = rs.getString("posted_at");
				String author = rs.getString("author");
				Tweet tweet = new Tweet(id, content, postedAt, author);
				tweets.add(tweet);
			}
		}
		return tweets;
	}

	// ツイートを新規投稿するメソッド
	public void addTweet(String content, String author) throws Exception {
		String sql = "INSERT INTO tweets (content, author) VALUES (?, ?)";
		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, content);
			st.setString(2, author);
			st.executeUpdate();
		}
	}

	// ツイートを削除するメソッド
	public void deleteTweet(int tweetId) throws Exception {
		String sql = "DELETE FROM tweets WHERE id = ?";
		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, tweetId);
			st.executeUpdate();
		}
	}

	// 指定された投稿者名に対応するツイートを取得するメソッド
	public List<Tweet> getTweetsByAuthor(String author) throws Exception {
		List<Tweet> tweets = new ArrayList<>();
		String sql = "SELECT id, content, posted_at, author FROM tweets WHERE author = ?";
		try (Connection con = getConnection(); PreparedStatement st = con.prepareStatement(sql)) {
			st.setString(1, author);
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String content = rs.getString("content");
					String postedAt = rs.getString("posted_at");
					Tweet tweet = new Tweet(id, content, postedAt, author);
					tweets.add(tweet);
				}
			}
		}
		return tweets;
	}
}
