/**
 * Copyright(C) 2019 Tran Thanh Viet
 * Category.java, Oct 9, 2019 VietTT
 */
package ytebnews.entities;

/**
 * @author VietTT
 *
 */
public class Category {

	private int categoryId;
	private String categoryName;

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

}
