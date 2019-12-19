/**
 * Copyright(C) 2019 Tran Thanh Viet
 * Common.java, Oct 12, 2019 VietTT
 */
package ytebnews.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Chứa các hàm common của dự án
 * 
 * @author VietTT
 *
 */
public class Common {

	/**
	 * Mã hóa SHA1
	 * 
	 * @param pass mật khẩu chưa đc mã hóa
	 * @param salt muối thêm vào
	 * @return pass đã đc mã hóa
	 */
	public static String encryptPass(String pass, String salt) throws NoSuchAlgorithmException {
		byte[] saltByte = new byte[16];
		saltByte = salt.getBytes();
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			// update muối
			md.update(saltByte);
			// Kết quả mảng byte
			byte[] result = md.digest(pass.getBytes(StandardCharsets.UTF_8));
			// Chuyển sang string
			for (int i = 0; i < result.length; i++) {
				sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println(Common.class.getName() + "-" + Thread.currentThread().getStackTrace()[1].getMethodName()
					+ e.getMessage());
			throw e;
		}
		return sb.toString();
	}

	/**
	 * Lấy muối bằng thời gian hiện tại
	 * 
	 * @return salt
	 */
	public static String getSalt() {
		// Lấy time hiện tại tinh den milisecon s
		String salt = ZonedDateTime.now().toInstant().toEpochMilli() + "";
		return salt;
	}

	/**
	 * Chuyển đổi các ký tự đặc biệt sql
	 * 
	 * @param value full name
	 * @return value giá trị sau khi chuyển đổi
	 */
	public static String replaceSqlWildcard(String value) {
		if (Common.checkEmpty(value)) {
			value = value.replace("\\", "\\\\");
			value = value.replace("%", "\\%");
			value = value.replace("_", "\\_");
		}
		return value;
	}

	/**
	 * Check nhập
	 * 
	 * @param input dữ liệu nhập vào
	 * @return true có nhập, false k nhập
	 */
	public static boolean checkEmpty(String input) {
		boolean isEmpty = false;
		if (!input.trim().isEmpty()) {
			isEmpty = true;
		}
		return isEmpty;
	}

	/**
	 * Tạo chuỗi paging
	 * 
	 * @param totalNews   tổng sô user
	 * @param limit       số lượng cần hiển thị trên 1 trang
	 * @param currentPage trang hiện tại
	 * @return listPage Danh sách các trang cần hiển thị ở chuỗi paging theo trang
	 *         hiện tại
	 */
	public static List<Integer> getListPaging(int totalNews, int limit, int currentPage) {
		List<Integer> listPage = new ArrayList<Integer>();
		int maxPage = 0;
		int maxTotalPage = getTotalPage(totalNews, limit);
		if (totalNews > Constant.LIMIT) {
			// Giá trị lớn nhất trong listPaging
			maxPage = (int) (Math.ceil((double) currentPage / (double) Constant.PAGING_PAGE)) * Constant.PAGING_PAGE;
			// Lấy các phần tử còn lại add vào list
			for (int i = Constant.PAGING_PAGE - 1; i >= 0; i--) {

				if (maxPage - i > maxTotalPage) {
					break;
				}
				listPage.add(maxPage - i);

			}
		}
		return listPage;
	}

	/**
	 * Lấy số trang lớn nhất
	 * 
	 * @param totalNews tổng sô user
	 * @param limitsố   lượng cần hiển thị trên 1 trang
	 * @return số trang lớp nhất
	 */
	public static int getTotalPage(int totalNews, int limit) {
		int maxTotalPage = (int) Math.ceil((double) totalNews / (double) limit);
		return maxTotalPage;
	}

	/**
	 * Lấy vị trí data cần lấy
	 * 
	 * @param currentPage Trang hiện tại
	 * @param limit       số lượng cần hiển thị trên 1 trang
	 * @return vị trí cần lấy
	 */
	public static int getOffset(int currentPage, int limit) {
		int offSet = (currentPage - 1) * limit;
		return offSet;
	}

	/**
	 * Hàm chuỗi chuỗi String sang số
	 * 
	 * @param input        chuỗi số
	 * @param defaultValue giá trị kết quả nếu không chuyển được
	 * @return result kết quả sau khi chuyển đổi
	 */
	public static int parseInt(String input, int defaultValue) {
		// Khai báo biến kết quả
		int result = 0;
		try {
			// Chuyển chuỗi số sang số
			result = Integer.parseInt(input);

		} catch (NumberFormatException e) {
			// Kết quả chuyển ki bị lỗi
			result = defaultValue;
		}
		return result;
	}

	/**
	 * Lấy thời gian hiện tại
	 * 
	 * @return timeNow thời gian hiện tại
	 */
	public static String getTimeNow() {
		String timeNow = "";
		Calendar cal = Calendar.getInstance();
		int dayNow = cal.get(Calendar.DATE);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int yearNow = cal.get(Calendar.YEAR);
		timeNow = yearNow + "-" + monthNow + "-" + dayNow;
		return timeNow;
	}

	/**
	 * Check độ dài nhập thỏa mãn
	 * 
	 * @param min   độ dài tối thiểu
	 * @param max   độ dài tối đa
	 * @param input dữ liệu nhập
	 * @return true thỏa mãn ddkien độ dài, false ngược lại
	 */
	public static boolean checkLength(int min, int max, String input) {
		boolean isLength = false;
		if (input.trim().length() >= min && input.trim().length() <= max) {
			isLength = true;
		}
		return isLength;
	}

	public static List<String> splitN(String input) {
		String[] listN = input.split("\\n");
		List<String> listContent = Arrays.asList(listN);

		return listContent;
	}

	/**
	 * Kiểm tra thông tin nhập đúng format chưa
	 * 
	 * @param input      dữ liệu nhập từ form
	 * @param typeFormat kiểu format
	 * @return true đúng format, false ngược lại
	 */
	public static boolean checkFormat(String input, String typeFormat) {
		boolean isFormat = false;
		String regex = typeFormat;
		// kiểm tra toàn bộ String có khớp với regex
		isFormat = input.matches(regex);
		return isFormat;
	}

	/**
	 * Kiểm tra pass nhập trùng
	 * 
	 * @param pass    mật khẩu
	 * @param passCon mật khẩu nhập lại
	 * @return true trùng nhau
	 */
	public static boolean checkPassConfirm(String pass, String passCon) {
		boolean isConf = false;
		// So sánh 2 pass trùng trả về true
		if (passCon.equals(pass)) {
			isConf = true;
		}
		return isConf;
	}

	/**
	 * Add lỗi vào list khi không rỗng
	 * 
	 * @param lissErrMes danh sách lỗi
	 * @param err        lỗi
	 */
	public static void addErr(List<String> listErrMes, String err) {
		// Kiểm tra err rỗng hay không
		if (Common.checkEmpty(err)) {
			listErrMes.add(err);
		}
	}

}
