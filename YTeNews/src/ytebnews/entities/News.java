/**
 * Copyright(C) 2019 Tran Thanh Viet
 * News.java, Oct 9, 2019 VietTT
 */
package ytebnews.entities;

/**
 * @author VietTT
 *
 */
public class News {

	private long newsId;
	private int categoryId;
	private String categoryName;
	private String newsName;
	private String description;
	private String content;
	private String image;
	private String datePost;
	private String userName;
	private int userId;
	private long view;
	private int approve;
	
	


	/**
	 * @return the approve
	 */
	public int getApprove() {
		return approve;
	}

	/**
	 * @param approve the approve to set
	 */
	public void setApprove(int approve) {
		this.approve = approve;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the newsId
	 */
	public long getNewsId() {
		return newsId;
	}

	/**
	 * @param newsId the newsId to set
	 */
	public void setNewsId(long newsId) {
		this.newsId = newsId;
	}

	/**
	 * @return the categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the newsName
	 */
	public String getNewsName() {
		return newsName;
	}

	/**
	 * @param newsName the newsName to set
	 */
	public void setNewsName(String newsName) {
		this.newsName = newsName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String contend) {
		this.content = contend;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the datePost
	 */
	public String getDatePost() {
		return datePost;
	}

	/**
	 * @param datePost the datePost to set
	 */
	public void setDatePost(String datePost) {
		this.datePost = datePost;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the view
	 */
	public long getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(long view) {
		this.view = view;
	}

}
