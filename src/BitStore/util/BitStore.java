package BitStore.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import BitStore.domain.BoardVO;
import BitStore.domain.DiscardProductVO;
import BitStore.domain.ProductVO;
import BitStore.domain.UserVO;
import BitStore.ui.LoginUI;

public class BitStore {
	public static Map<String, UserVO> userList = new HashMap();
	public static Map<Integer, ProductVO> productList = new HashMap();
	public static Map<Integer, DiscardProductVO> discardProductList = new HashMap();
	public static Map<Integer, BoardVO> boardList = new HashMap();
	public static UserVO currentLoginUser = new UserVO();
	private Scanner sc;
	private File userListFile;
	private File productListFile;
	private File discardProductListFile;
	private File boardListFile;
	public static UserVO user;

	public BitStore() {
		userListFile = new File("userList.txt");

		if (!userListFile.exists()) { // userList.txt°¡ ¾øÀ»½Ã »ý¼º
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			ObjectOutputStream oos = null;
			try {
				UserVO initUserVO = new UserVO();
				userListFile.createNewFile();
				fos = new FileOutputStream(userListFile); // admin °èÁ¤À» default·Î
															// Ãß°¡
				bos = new BufferedOutputStream(fos);
				oos = new ObjectOutputStream(fos);
				initUserVO.setID("admin");
				initUserVO.setPwd("admin");
				initUserVO.setUserName("admin");
				initUserVO.setUserPhone("000-0000-0000");
				initUserVO.setMoney(0);
				initUserVO.setUserPoint(0);
				initUserVO.setDiscount(false);
				initUserVO.setOrderList(null);
				userList.put("admin", initUserVO);
				oos.writeObject(userList);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		productListFile = new File("productList.txt"); // »óÇ° ÆÄÀÏ
		if (!productListFile.exists()) { // productList.txt°¡ ¾øÀ»½Ã »ý¼º
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			ObjectOutputStream oos = null;
			try {
				ProductVO initProductVO = new ProductVO();
				productListFile.createNewFile();
				fos = new FileOutputStream(productListFile); // snack »óÇ°À»
																// default·Î Ãß°¡
				bos = new BufferedOutputStream(fos);
				oos = new ObjectOutputStream(fos);
				initProductVO.setProductNo(880100000);
				initProductVO.setProductName("Snack");
				initProductVO.setStock(50);
				initProductVO.setExprtDate("2020-04-26");
				initProductVO.setPrice(500);
				productList.put(880100000, initProductVO);
				oos.writeObject(productList);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		discardProductListFile = new File("discardProductList.txt"); // Æó±â»óÇ° ÆÄÀÏ
		if (!discardProductListFile.exists()) { // discardProductList.txt°¡ ¾øÀ»½Ã
												// »ý¼º
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			ObjectOutputStream oos = null;
			try {
				DiscardProductVO initDiscardProductVO = new DiscardProductVO();
				discardProductListFile.createNewFile();
				fos = new FileOutputStream(discardProductListFile); // beverage
																	// »óÇ°À»
																	// default·Î
																	// Ãß°¡
				bos = new BufferedOutputStream(fos);
				oos = new ObjectOutputStream(fos);
				initDiscardProductVO.setDiscardProductNo(880200000);
				initDiscardProductVO.setDiscardProductName("Beverage");
				initDiscardProductVO.setDiscardExprtDate("2020-03-25");
				initDiscardProductVO.setDiscardPrice(500);
				discardProductList.put(880200000, initDiscardProductVO);
				oos.writeObject(discardProductList);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		boardListFile = new File("boardList.txt"); // °Ô½ÃÆÇ ¸ñ·Ï ÆÄÀÏ
		if (!boardListFile.exists()) { // boardList.txt°¡ ¾øÀ»½Ã »ý¼º
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			ObjectOutputStream oos = null;
			try {
				BoardVO initBoardVO = new BoardVO();
				boardListFile.createNewFile();
				fos = new FileOutputStream(boardListFile); // Ã¹¹øÂ° °Ô½Ã±ÛÀ» default·Î
															// Ãß°¡
				bos = new BufferedOutputStream(fos);
				oos = new ObjectOutputStream(fos);
				initBoardVO.setBoardNo(1);
				initBoardVO.setBoardTitle("Á¦¸ñ Å×½ºÆ®");
				initBoardVO.setBoardContent("³»¿ë Å×½ºÆ®");
				initBoardVO.setBoardWriter("admin");
				initBoardVO.setBoardDate("2020-03-26");
				boardList.put(1, initBoardVO);
				oos.writeObject(boardList);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		readUserList(); // È¸¿ø¸ñ·Ï ÆÄÀÏ ºÒ·¯¿À±â
		readProductList(); // »óÇ°¸ñ·Ï ÆÄÀÏ ºÒ·¯¿À±â
		readDiscardProductList(); // Æó±â»óÇ°¸ñ·Ï ÆÄÀÏ ºÒ·¯¿À±â
		readBoardList(); // °Ô½ÃÆÇ¸ñ·Ï ÆÄÀÏ ºÒ·¯¿À±â
		sc = new Scanner(System.in);
	}

	public void join() {
		user = new UserVO();
		System.out.println("¡Ø¡Ø¡Ø  Bit ÆíÀÇÁ¡ È¸¿ø°¡ÀÔÀ» È¯¿µ ÇÕ´Ï´Ù ¡Ø¡Ø¡Ø");
		System.out.println("¡Ü Bit ÆíÀÇÁ¡¿¡ »ç¿ëÇÒ È¸¿ø ID¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä : ");
		System.out.println("(5~15ÀÚ »çÀÌÀÇ ¿µ¹® °ú ¼ýÀÚ¸¦ Á¶ÇÕÇØÁÖ¼¼¿ä.)");
		checkID();
		System.out.println("¡Ü Bit ÆíÀÇÁ¡¿¡ »ç¿ëÇÒ È¸¿ø ºñ¹Ð¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä : ");
		System.out.println("(8~20ÀÚ »çÀÌÀÇ ¿µ¹® °ú ¼ýÀÚ,Æ¯¼ö¹®ÀÚ¸¦ Á¶ÇÕÇØÁÖ¼¼¿ä.)");
		checkPwd();
		System.out.println("¡Ü Bit ÆíÀÇÁ¡¿¡ »ç¿ëÇÒ È¸¿ø ÀÌ¸§À» ÀÔ·ÂÇØÁÖ¼¼¿ä : ");
		user.setUserName(sc.nextLine().trim());
		System.out.println("¡Ü Bit ÆíÀÇÁ¡¿¡ »ç¿ëÇÒ È¸¿ø ÇÚµåÆù¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä : ");
		System.out.println("(010-0000-0000 Çü½ÄÀ¸·Î ÀÔ·ÂÇØ ÁÖ¼¼¿ä.)");
		checkPhone();
		System.out.println("¡Ü ¹°°Ç±¸¸Å¿¡ »ç¿ëÇÒ ±Ý¾×À» ÃæÀüÇØÁÖ¼¼¿ä : ");
		user.setMoney(Integer.parseInt(sc.nextLine().trim()));
		user.setUserPoint(1000);
		System.out.println("¡Ü ÇÒÀÎÄ«µå¸¦ ¼ÒÁöÇÏ¼Ì´Ù¸é 1¹øÀ», ¾øÀ¸½Ã´Ù¸é 0¹øÀ» ÀÔ·ÂÇØ ÁÖ¼¼¿ä : ");
		checkDC();
		userList.put(user.getID(), user);
		writeUserList();
		System.out.println("¡Ø¡Ø¡Ø  Bit ÆíÀÇÁ¡ È¸¿øÀÌ µÇ½Å °ÍÀ» ÃàÇÏµå¸³´Ï´Ù ¡Ø¡Ø¡Ø");
		System.out.println("¡Ø¡Ø¡Ø  ±¸¸Å¿¡ »ç¿ë °¡´ÉÇÑ Æ÷ÀÎÆ®¸¦ Áö±ÞÇØ µå·È½À´Ï´Ù ¡Ø¡Ø¡Ø");

		// È¸¿ø¸ñ·Ï
		System.out.println("[È¸¿ø¸ñ·Ï Á¶È¸]");
		Iterator<String> mapIter = BitStore.userList.keySet().iterator();
		while (mapIter.hasNext()) {
			String key = mapIter.next();
			UserVO value = BitStore.userList.get(key);
			System.out.println(value + " / ");
		}

	}

	public void login() {
		while (true) {
			System.out.println("¡Ø¡Ø¡Ø Bit ÆíÀÇÁ¡ ·Î±×ÀÎ ¡Ø¡Ø¡Ø ");
			System.out.println("¡Ü ¾ÆÀÌµð¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä : ");
			String userID = sc.nextLine().trim();

			if (!userList.containsKey(userID)) {
				System.out.println("xxxxx ÇØ´çÇÏ´Â ID°¡ ¾ø½À´Ï´Ù xxxxx");
				login();
			} else if (userList.containsKey(userID)) {
				System.out.println("¡Ü ºñ¹Ð¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä : ");
				String userPwd = sc.nextLine().trim();
				if (userList.get(userID).getPwd().equals(userPwd)) {
					System.out.print("¡Ø¡Ø¡Ø Bit ÆíÀÇÁ¡¿¡ ·Î±×ÀÎ µÇ¾ú½À´Ï´Ù ¡Ø¡Ø¡Ø ");
					if (userID.equals("admin")) { // adminÀÌ¸é adminUI ÆäÀÌÁö ¤¡°í½Ì
						LoginUI lu = new LoginUI();
						Iterator<String> mapIter = userList.keySet().iterator();
						while (mapIter.hasNext()) {
							String key = mapIter.next();
							UserVO value = userList.get(key);
							if (userID.equals(value.getID())) {
								currentLoginUser = value;
								System.out.println("admin" + currentLoginUser.toString());
								break;
							}
						}
						lu.admin();
					} else {
						LoginUI lu = new LoginUI();
						Iterator<String> mapIter = userList.keySet().iterator();
						while (mapIter.hasNext()) {
							String key = mapIter.next();
							UserVO value = userList.get(key);
							if (userID.equals(value.getID())) {
								currentLoginUser = value;
								System.out.println("user" + currentLoginUser.toString());
								break;
							}
						}
						lu.user();
					}
					break;
				} else {
					System.out.println("Password¸¦ È®ÀÎÇØ ÁÖ¼¼¿ä.");
				}
			}
		}

	}

	private String userID;

	public void checkID() {
		userID = sc.nextLine().trim();
		Set<String> keys = userList.keySet();
		while (true) {
			Pattern idPattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[^!@#$%^*+=-])(?=.*[^°¡-ÆR])(?=.*[0-9]).{5,15}$");
			Matcher matcher1 = idPattern.matcher(userID);
			for (String key : keys) {
				if (key.equals(userID)) {
					System.out.println("xxx ÀÌ¹Ì »ç¿ëÁßÀÎ ¾ÆÀÌµðÀÔ´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä xxx");
					checkID();
				}
			}
			if (!matcher1.matches()) {
				System.out.println("ÀÔ·Â °ª : " + userID);
				System.out.println("xxxxxxxx Àß¸ø ÀÔ·ÂÇÏ¼Ì½À´Ï´Ù xxxxxxxx");
				System.out.println("xxx 5~15ÀÚ ¿µ¹®°ú ¼ýÀÚ¸¦ Á¶ÇÕÇÏ¿© ´Ù½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä xxx");
				checkID();
			} else {
				user.setID(userID);
				return;
			}
		}
	}

	private String userPwd;

	public void checkPwd() {
		userPwd = sc.nextLine().trim();
		while (true) {
			Pattern pwPattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$");
			Matcher matcher1 = pwPattern.matcher(userPwd);
			if (!matcher1.matches()) {
				System.out.println("xxxxxxxx Àß¸ø ÀÔ·ÂÇÏ¼Ì½À´Ï´Ù xxxxxxxx");
				System.out.println("xxx 8~20ÀÚ »çÀÌÀÇ ¿µ¹® °ú ¼ýÀÚ,Æ¯¼ö¹®ÀÚ¸¦ Á¶ÇÕÇØ ´Ù½Ã ÀÔ·ÂÇØ ÁÖ¼¼¿ä xxx");
				checkPwd();
			} else {
				user.setPwd(userPwd);
				return;
			}
		}
	}

	String userPhone;

	public void checkPhone() {
		userPhone = sc.nextLine().trim();
		while (true) {
			Pattern phonePattern = Pattern.compile("^01([0|1|0]?)-?([0-9]{3,4})-?([0-9]{4})$");
			Matcher matcher1 = phonePattern.matcher(userPhone);
			if (!matcher1.matches()) {
				System.out.println("xxxxxxxx Àß¸ø ÀÔ·ÂÇÏ¼Ì½À´Ï´Ù xxxxxxxx");
				System.out.println("xxx 010-0000-0000 Çü½ÄÀ¸·Î ÀÔ·ÂÇØ ÁÖ¼¼¿ä xxx");
				checkPhone();
			} else {
				user.setUserPhone(userPhone);
				return;
			}
		}

	}

	int userDC;

	public void checkDC() {
		userDC = (Integer.parseInt(sc.nextLine().trim()));
		while (true) {
			if (userDC == 1) {
				user.setDiscount(true);
				break;
			} else if (userDC == 0) {
				user.setDiscount(false);
				break;
			} else {
				System.out.println("xxxxxxxx Àß¸ø ÀÔ·ÂÇÏ¼Ì½À´Ï´Ù xxxxxxxx");
				checkDC();
			}
		}
	}

	static public void writeUserList() {
		File file = new File("userList.txt");
		// file.delete();
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(userList);
		} catch (Exception e) {
			System.out.println("xxxxx È¸¿ø¸ñ·Ï ÀúÀå¿¡ ½ÇÆÐÇÏ¿´½À´Ï´Ù xxxxx");
			e.printStackTrace();
		} finally {
			try {
				oos.close();
				bos.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void logout() {

	}

	public void findID() {
		Set<String> keys = userList.keySet();
		boolean flag = false;

		System.out.println("¡Ü Bit ÆíÀÇÁ¡¿¡ °¡ÀÔÇÑ È¸¿ø ÀÌ¸§À» ÀÔ·ÂÇØÁÖ¼¼¿ä : ");
		String userName = sc.nextLine().trim();
		for (String key : keys) {
			UserVO value = userList.get(key);
			if (value.getUserName().equals(userName)) {
				flag = true;
				break;
			}
		}
		if (flag == false) {
			System.out.println("[" + userName + "] ÀÌ¸§ÀÇ È¸¿øÀÌ Á¸ÀçÇÏÁö ¾Ê½À´Ï´Ù. ");
			System.out.println("xxxxxx È¸¿ø°¡ÀÔÈÄ ÀÌ¿ëÇØ ÁÖ¼¼¿ä xxxxxx");
			return;
		}
		flag = false;
		System.out.println("¡Ü Bit ÆíÀÇÁ¡¿¡ °¡ÀÔÇÑ È¸¿ø ÇÚµåÆù ¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä : ");
		String userPhone = sc.nextLine().trim();
		for (String key : keys) {
			UserVO value = userList.get(key);
			if (value.getUserName().equals(userName) && value.getUserPhone().equals(userPhone)) {
				System.out.println("[" + userName + "] ´ÔÀÇ ºñ¹Ð¹øÈ£´Â [" + value.getID() + "] ÀÔ´Ï´Ù.");
				flag = true;
				break;
			}
		}
		if (flag == false) {
			System.out.println("xxx ÀÌ¸§°ú ÇÚµåÆù¹øÈ£°¡ ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù. ´Ù½Ã ½ÃµµÇØ ÁÖ¼¼¿ä xxx");
		}
	}

	/*
	 * public void findID() { Iterator<String> mapIter =
	 * userList.keySet().iterator(); System.out.println("Ã£°í ½ÍÀº IDÀÇ ÀÌ¸§À» ÀÔ·ÂÇØÁÖ¼¼¿ä");
	 * String userName = sc.nextLine().trim(); boolean flag = false;
	 * System.out.println("Ã£°í ½ÍÀº IDÀÇ ÇÚµåÆù¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä"); String userPhone =
	 * sc.nextLine().trim(); while (mapIter.hasNext()) { String key =
	 * mapIter.next(); UserVO value = userList.get(key); if
	 * (value.getUserName().equals(userName) &&
	 * value.getUserPhone().equals(userPhone)) {
	 * System.out.println("["+userName+"] ´ÔÀÇ ID´Â ["+value.getID()+"] ÀÔ´Ï´Ù.");
	 * flag = true; break; } } if (flag == false) { System.out.println(
	 * "Ã£´Â ¾ÆÀÌµð°¡ ¾ø½À´Ï´Ù."); } }
	 */
	public void findPwd() {
		Set<String> keys = userList.keySet();
		boolean flag = false;

		System.out.println("¡Ü Bit ÆíÀÇÁ¡¿¡ °¡ÀÔÇÑ È¸¿ø ID ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä : ");
		String userID = sc.nextLine().trim();
		for (String key : keys) {
			UserVO value = userList.get(key);
			if (value.getID().equals(userID)) {
				flag = true;
				break;
			}
		}
		if (flag == false) {
			System.out.println("[" + userID + "] ID°¡ Á¸ÀçÇÏÁö ¾Ê½À´Ï´Ù. ");
			System.out.println("xxx È¸¿ø°¡ÀÔÀ» ÇÏ½Ã°Å³ª ID Ã£±â¸¦ ÀÌ¿ëÇØ ÁÖ¼¼¿ä xxx");
			return;
		}
		flag = false;
		System.out.println("¡Ü Bit ÆíÀÇÁ¡¿¡ °¡ÀÔÇÑ È¸¿ø ÀÌ¸§À» ÀÔ·ÂÇØÁÖ¼¼¿ä : ");
		String userName = sc.nextLine().trim();
		for (String key : keys) {
			UserVO value = userList.get(key);
			if (value.getID().equals(userID) && value.getUserName().equals(userName)) {
				System.out.println("[" + userID + "] ´ÔÀÇ ºñ¹Ð¹øÈ£´Â [" + value.getPwd() + "] ÀÔ´Ï´Ù.");
				flag = true;
				break;
			}
		}
		if (flag == false) {
			System.out.println("xxx ID¿Í ÀÌ¸§ÀÌ ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù. ´Ù½Ã ½ÃµµÇØ ÁÖ¼¼¿ä xxx");
		}
	}
	/*
	 * public void findPwd() { Iterator<String> mapIter =
	 * userList.keySet().iterator(); System.out.println("Ã£°í ½ÍÀº ºñ¹Ð¹øÈ£ÀÇ ID¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä"
	 * ); String userID = sc.nextLine().trim(); boolean flag = false;
	 * System.out.println("Ã£°í ½ÍÀº ºñ¹Ð¹øÈ£ÀÇ ÀÌ¸§À» ÀÔ·ÂÇØÁÖ¼¼¿ä"); String userName =
	 * sc.nextLine().trim(); while (mapIter.hasNext()) { String key =
	 * mapIter.next(); UserVO value = userList.get(key); if
	 * (value.getID().equals(userID) && value.getUserName().equals(userName)) {
	 * System.out.println("["+userID+"] ´ÔÀÇ ºñ¹Ð¹øÈ£´Â ["+value.getPwd()+"] ÀÔ´Ï´Ù.");
	 * flag = true; break; } } if (flag == false) { System.out.println(
	 * "Ã£´Â ¾ÆÀÌµð°¡ ¾ø½À´Ï´Ù."); } }
	 */

	public void readUserList() {
		File file = new File("userList.txt");
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);
			userList = (HashMap) ois.readObject();
		} catch (Exception e) {
			System.out.println("xxxxx È¸¿ø¸ñ·ÏÀ» ºÒ·¯¿À´Âµ¥ ½ÇÆÐÇÏ¿´½À´Ï´Ù xxxxx");
			e.printStackTrace();
		} finally {
			this.close(fis, bis, ois);
		}
	}

	public void readProductList() {
		File file = new File("productList.txt");
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);
			productList = (HashMap) ois.readObject();
		} catch (Exception e) {
			System.out.println("xxxxx »óÇ°¸ñ·ÏÀ» ºÒ·¯¿À´Âµ¥ ½ÇÆÐÇÏ¿´½À´Ï´Ù xxxxx");
			e.printStackTrace();
		} finally {
			this.close(fis, bis, ois);
		}
	}

	public void readDiscardProductList() {
		File file = new File("discardProductList.txt");
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);
			discardProductList = (HashMap) ois.readObject();
		} catch (Exception e) {
			System.out.println("xxxxx Æó±â»óÇ°¸ñ·ÏÀ» ºÒ·¯¿À´Âµ¥ ½ÇÆÐÇÏ¿´½À´Ï´Ù xxxxx");
			e.printStackTrace();
		} finally {
			this.close(fis, bis, ois);
		}
	}

	public void readBoardList() {
		File file = new File("boardList.txt");
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			ois = new ObjectInputStream(bis);
			boardList = (HashMap) ois.readObject();
		} catch (Exception e) {
			System.out.println("xxxxx °Ô½ÃÆÇ¸ñ·ÏÀ» ºÒ·¯¿À´Âµ¥ ½ÇÆÐÇÏ¿´½À´Ï´Ù xxxxx");
			e.printStackTrace();
		} finally {
			this.close(fis, bis, ois);
		}
	}

	public void close(FileInputStream fis, BufferedInputStream bis, ObjectInputStream ois) {
		if (fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (bis != null) {
			try {
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (ois != null) {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
