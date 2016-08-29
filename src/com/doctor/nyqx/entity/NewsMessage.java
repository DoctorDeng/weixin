package com.doctor.nyqx.entity;

import java.util.List;

/**
 * 图文消息实体类
 * @author Doctor邓
 *
 */
public class NewsMessage extends BaseMessage {
	/**
	 * 文章数量
	 */
	private int ArticleCount;
	/**
	 * 子图文集合
	 */
	private List<News> Articles;
	
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<News> getArticles() {
		return Articles;
	}
	public void setArticles(List<News> articles) {
		Articles = articles;
	}
}
