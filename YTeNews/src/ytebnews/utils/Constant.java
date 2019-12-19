/**
 * Copyright(C) 2019 Tran Thanh Viet
 * Constans.java, Oct 9, 2019 VietTT
 */
package ytebnews.utils;


/**
 * @author VietTT
 *
 */
public class Constant {
	
	public static final String UTF8 = "utf-8";
	public static final String DATABASE_PROP = "database.properties";
	public static final String MESS_PROP = "message.properties";
	public static final String CONFIG_PROP = "config.properties";
	public static final String IMAGES_NEWS_FOLDER = "images_news";
	
	public static final String T_LOGIN_NAME = "login_name";
	public static final String T_PASS = "pass";
	public static final String T_SALT = "salt";
	public static final String T_CATEGORY_NAME = "category_name";
	public static final String T_CATEGORY_ID = "category_id";
	public static final String T_NEWS_ID = "news_id";
	public static final String T_NEWS_NAME = "news_name";
	public static final String T_DESCRIPTION = "description";
	public static final String T_CONTENT = "content";
	public static final String T_IMAGE = "image";
	public static final String T_DATE = "date";
	public static final String T_USER_NAME = "user_name";
	public static final String T_VIEW = "view";
	public static final String T_RULE = "rule";
	public static final String T_FUll_NAME = "full_name";
	public static final String T_USER_ID = "user_id";
	//Messege or ERR
	public static final String ER016 = "ER016";
	public static final String ER001 = "ER001";
	
	//Rule
	public static final int RULE_ADMIN = 0;
	public static final int RULE_USER = 1;
	public static final int RULE_AUTHOR = 2;
	public static final int APPROVE_Y = 1;
	public static final int APPROVE_N = 0;
	public static final int APPROVE_DC = -1;
	
	//atribute
	public static final String LOGIN_NAME = "loginname";
	public static final String MESS_ERR = "err";
	public static final String PASS = "pass";
	public static final String ACTION = "action";
	public static final String KEYNAME = "keyname";
	public static final String CATEGORY_ID = "categoryid";
	public static final String CURRENT_PAGE = "currentpage";
	public static final String SEARCH = "search";
	public static final String PAGING = "paging";
	public static final String BACK = "back";
	public static final String INSERT_SUCC = "insertsuccess";
	public static final String DELE_SUCC = "deletesuccess";
	//jsp
	public static final String LOGIN_ADMIN_JSP = "/admin/login.jsp";
	public static final String LIST_CATEGORY_JSP = "/admin/manager_category.jsp";
	public static final String INDEX_ADMIN_JSP = "/admin/index.jsp";
	public static final String MANAGER_NEWS_JSP = "/admin/manager_news.jsp";
	public static final String MANAGER_NEWS_AUTHOR_JSP = "/admin/news_author.jsp";
	public static final String INSERT_NEWS_JSP = "/admin/insert_news.jsp";
	public static final String DETAIL_NEWS_ADMIN_JSP = "/admin/detail_news.jsp";
	public static final String EDIT_CATEGORY_JSP = "/admin/insert_category.jsp";
	public static final String EDIT_NEWS_JSP = "/admin/edit_news.jsp";
	public static final String MANAGER_USER_JSP = "/admin/manager_users.jsp";
	public static final String INDEX_JSP = "/jsp/index.jsp";
	public static final String CATEGORIES_JSP = "/jsp/categories.jsp";
	public static final String NEWS_SINGLE_JSP = "/jsp/news_single.jsp";
	public static final String ERROR_JSP =  "/jsp/404.jsp";
	public static final String CONTACT_JSP = "/jsp/contact.jsp";
	
	
	
	//servlet
	public static final String LOGIN_URL = "/login.do";
	public static final String LOGOUT_URL = "/logout.do";
	public static final String LIST_CATEGORY_URL = "/listcategory.do";
	public static final String LIST_NEWS_URL = "/listnews.do";
	public static final String INDEX_ADMIN_URL = "/index.do";
	public static final String LIST_NEWS_AUTHOR_URL = "/listnewsauthor.at";
	public static final String INSERT_NEWS_URL = "/insertnews.at";
	public static final String SYSTEM_ERR_URL = "/systemerror";
	public static final String VIEW_NEWS_URL = "/detailnews.do";
	public static final String APPROVE_URL = "/approvenews.do";
	public static final String DELE_NEWS_ADMIN_URL = "/deletenews.do";
	public static final String LIST_USER_URL = "/listuser.do";
	public static final String DELE_NEWS_AUTHOR_URL = "/deletenews.at";
	public static final String VIEW_NEWS_AU_URL = "/detailnews.at";
	public static final String INSERT_CATEGORY_URL = "/insertcategory.do";
	public static final String UPDATE_CATEGORY_URL = "/updatecategory.do";
	public static final String UPDATE_NEWS_URL = "/updatenews.at";
	public static final String CONTACT_URL = "/contact.html";
	
	//TIn tức servlet
	public static final String INDEX_URL = "/index.html";
	public static final String CATERGORIES_NEWS_URL = "/categories.html";
	public static final String NEWS_URL = "/news.html";
	//
	//Số bản ghi trên một page
	public static int LIMIT = Common.parseInt(ConfigProperties.getConfig("limit"), Constant.LIMIT_DEFAULT);
	public static final int LIMIT_DEFAULT = 5;
	// Số pagning tối đa
	public static final int PAGING_PAGE = Common.parseInt(ConfigProperties.getConfig("pagning_max"), Constant.PAGING_PAGE_DEFAULT);
	public static final int PAGING_PAGE_DEFAULT = 3;
	public static final int OFFSET = 0;
	//
	public static final String KEYNAME_DEFAULT = "";
	public static final int CATEGORY_ID_DEFAULT = 0;
	public static final int APPROVE_DEFAULT = -1;
	public static final int CURRENT_PAGE_DEFAULT = 1;
	public static final int USER_ID_DEFAULT = 0;
	public static final String NEXT_CHAR = ">>";
	public static final String PRE_CHAR = "<<";
	public static final String PREVIOUS = "previous";
	public static final String PREVIOUS_CHAR_AT = "previouschar";
	public static final String NEXT = "next";
	public static final String NEXT_CHAR_AT = "nextchar";
	public static final String TOTAL_NEWS = "totalnews";
	public static final String APPROVE = "approve";
	public static final long VIEW_DEFAULT = 0;
	public static final String USER_ID = "userid";
	
	
	public static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	public static final int MAX_FILE_SIZE = 1024 * 1024 * 90; // 90MB
	public static final int MAX_REQUEST_SIZE = 1024 * 1024 * 100; // 100MB
	public static final String NEWS_ID = "newsid";
	public static final int NEWS_ID_DEFAULT = 0;
	public static final String NEWS = "news";
	public static final String APPROVE_SUCC = "approvesuccess";
	public static final String INSERT = "insert";
	public static final String UPDATE_SUCC = "updatesuccess";
	public static final int MIN_LENTH_TITLE_DES = 0;
	public static final int MAX_LENTH_TITLE_DES = 500;
	public static final String ER002 = "ER002";
	public static final String ER003 = "ER003";
	public static final int NOT_VIEW_ORDER = 0;
	public static final int VIEW_ORDER = 1;
	public static final String RECENT = "recent";
	public static final String TRENDING = "trending";
	public static final String TRENDING_CATE = "TOP TRENDING";
	public static final String RECENT_NEWS = "RECENT NEWS";
	public static final String SEARCH_LIST = "SEARCH RESULT";
	public static final String ER001_USERNAME = "err001_username";
	public static final String ER001_EMAIL = "er001_email";
	public static final String FORMAT_EMAIL = ".+@{1}.+[.]{1}.*";
	public static final String ER005_EMAIL = "er005_email";
	public static final String ER001_TEL = "er001_tel";
	public static final String ER005_TEL = "er005_tel";
	public static final String FORMAT_TEL = "[0-9]*$";
	public static final String MSG006 = "MSG006";
	public static final String ER001_MESS = "err001_mess";
	public static final String MSG007 = "MSG007";
	public static final String TOTAL_USER = "totaluser";
	
	
	public static final String ER001_LOGNAME = "ER001_loginName";
	public static final String ER007_LOGIN_NAME = "ER007_loginName";
	public static final String ER003_LOGIN_NAME = "ER003_loginName";
	public static final String ER019_LOGIN_NAME = "ER019_loginName";
	public static final String ER001_FULL_NAME = "ER001_fullName";
	public static final String ER006_FULL_NAME = "ER006_fullName";
	public static final String ER006_EMAIL = "ER006_email";
	public static final String ER003_EMAIL = "ER003_email";
	public static final String ER006_TEL = "ER006_tel";
	public static final String ER001_PASS = "ER001_loginPass";
	public static final String ER008_PASSWORD = "ER008_password";
	public static final String ER007_PASSWORD = "ER007_password";
	
	public static final String ER017 = "ER017";
	
	public static final String FORMAT_LOGIN_NAME = "^[a-zA-Z_][a-zA-Z0-9_]*";
	public static final String FORMAT_PASSWORD = "[a-zA-Z0-9]*";
	public static final int MIN_LENGTH_LOGIN_NAME = 4;
	public static final int MAX_LENGTH_LOGIN_NAME = 15;
	public static final int MIN_LENGTH_PASS = 5;
	public static final int MAX_LENGTH_PASS = 15;

	
	
	

	
	
	


}
