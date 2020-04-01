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
import BitStore.ui.user.UserUI;

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

		if (!userListFile.exists()) { // userList.txt가 없을시 생성
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			ObjectOutputStream oos = null;
			try {
				UserVO initUserVO = new UserVO();
				userListFile.createNewFile();
				fos = new FileOutputStream(userListFile); // admin 계정을 default로
															// 추가
				bos = new BufferedOutputStream(fos);
				oos = new ObjectOutputStream(fos);
				initUserVO.setID("admin");
				initUserVO.setPwd("admin");
				initUserVO.setUserName("admin");
				initUserVO.setUserPhone("010-0000-0000");
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
		productListFile = new File("productList.txt"); // 상품 파일
		if (!productListFile.exists()) { // productList.txt가 없을시 생성
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			ObjectOutputStream oos = null;
			try {
				ProductVO initProductVO = new ProductVO();
				productListFile.createNewFile();
				fos = new FileOutputStream(productListFile); // snack 상품을
																// default로 추가
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
		discardProductListFile = new File("discardProductList.txt"); // 폐기상품 파일
		if (!discardProductListFile.exists()) { // discardProductList.txt가 없을시
												// 생성
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			ObjectOutputStream oos = null;
			try {
				DiscardProductVO initDiscardProductVO = new DiscardProductVO();
				discardProductListFile.createNewFile();
				fos = new FileOutputStream(discardProductListFile); // beverage
																	// 상품을
																	// default로
																	// 추가
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
		boardListFile = new File("boardList.txt"); // 게시판 목록 파일
		if (!boardListFile.exists()) { // boardList.txt가 없을시 생성
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			ObjectOutputStream oos = null;
			try {
				BoardVO initBoardVO = new BoardVO();
				boardListFile.createNewFile();
				fos = new FileOutputStream(boardListFile); // 첫번째 게시글을 default로
															// 추가
				bos = new BufferedOutputStream(fos);
				oos = new ObjectOutputStream(fos);
				initBoardVO.setBoardNo(1);
				initBoardVO.setBoardTitle("제목 테스트");
				initBoardVO.setBoardContent("내용 테스트");
				initBoardVO.setBoardWriter("admin");
				initBoardVO.setBoardDate("2020-03-26");
				boardList.put(1, initBoardVO);
				oos.writeObject(boardList);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		readUserList(); // 회원목록 파일 불러오기
		readProductList(); // 상품목록 파일 불러오기
		readDiscardProductList(); // 폐기상품목록 파일 불러오기
		readBoardList(); // 게시판목록 파일 불러오기
		sc = new Scanner(System.in);
	}

	public void join() {
		user = new UserVO();
		System.out.println("● Bit 편의점에 사용할 회원 ID를 입력해주세요 : ");
		System.out.println("(5~15자 사이의 영문 과 숫자를 조합해주세요.)");
		checkID();
		System.out.println("● Bit 편의점에 사용할 회원 비밀번호를 입력해주세요 : ");
		System.out.println("(8~20자 사이의 영문 과 숫자,특수문자를 조합해주세요.)");
		checkPwd();
		System.out.println("● Bit 편의점에 사용할 회원 이름을 입력해주세요 : ");
		user.setUserName(sc.nextLine().trim());
		System.out.println("● Bit 편의점에 사용할 회원 핸드폰번호를 입력해주세요 : ");
		System.out.println("(010-0000-0000 형식으로 입력해 주세요.)");
		checkPhone();
		System.out.println("● 물건구매에 사용할 금액을 충전해주세요 : ");
		user.setMoney(Integer.parseInt(sc.nextLine().trim()));
		user.setUserPoint(1000);
		System.out.println("● 할인카드를 소지하셨다면 1번을, 없으시다면 0번을 입력해 주세요 : ");
		checkDC();
		userList.put(user.getID(), user);
		writeUserList();
		System.out.println("※※※  Bit 편의점 회원이 되신 것을 환영합니다 ※※※");
		System.out.println("※※※  구매에 사용 가능한 포인트를 지급해 드렸습니다 ※※※");

		// 회원목록
		System.out.println("[회원목록 조회]");
		Iterator<String> mapIter = BitStore.userList.keySet().iterator();
		while (mapIter.hasNext()) {
			String key = mapIter.next();
			UserVO value = BitStore.userList.get(key);
			System.out.println(value + " / ");
		}

	}

	public void login() {
		while (true) {
			System.out.println("※※※ Bit 편의점 로그인 ※※※ ");
			System.out.println("● 아이디를 입력해주세요 : ");
			String userID = sc.nextLine().trim();

			if (!userList.containsKey(userID)) {
				System.out.println("["+userID+"] 에 해당하는 ID가 없습니다");
				System.out.println("xxx 회원가입을 하시거나 ID 찾기를 이용해 주세요 xxx");
				break;
			} else if (userList.containsKey(userID)) {
				System.out.println("● 비밀번호를 입력해주세요 : ");
				String userPwd = sc.nextLine().trim();
				if (userList.get(userID).getPwd().equals(userPwd)) {
					System.out.println("※※※ Bit 편의점에 로그인 되었습니다 ※※※ ");
					System.out.println("["+userList.get(userID).getUserName()+"] 님 환영합니다.");
					if (userID.equals("admin")) { // admin이면 adminUI 페이지 ㄱ고싱
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
								break;
							}
						}
						lu.user();
					}
					break;
				} else {
					System.out.println("xxxxx Password가 일치하지 않습니다. 다시한번 확인해주세요 xxxxx");
					break;
				}
			}
		}

	}

	private String userID;

	public void checkID() {
		userID = sc.nextLine().trim();
		Set<String> keys = userList.keySet();
		while (true) {
			Pattern idPattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[^!@#$%^*+=-])(?=.*[^가-�R])(?=.*[0-9]).{5,15}$");
			Matcher matcher1 = idPattern.matcher(userID);
			for (String key : keys) {
				if (key.equals(userID)) {
					System.out.println("xxx 이미 사용중인 아이디입니다. 다시 입력해 주세요 xxx");
					checkID();
				}
			}
			if (!matcher1.matches()) {
				System.out.println("xxxxxxxx 잘못 입력하셨습니다 xxxxxxxx");
				System.out.println("xxx 5~15자 영문과 숫자를 조합하여 다시 입력해 주세요 xxx");
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
				System.out.println("xxxxxxxx 잘못 입력하셨습니다 xxxxxxxx");
				System.out.println("xxx 8~20자 사이의 영문 과 숫자,특수문자를 조합해 다시 입력해 주세요 xxx");
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
			Pattern phonePattern = Pattern.compile("^01([0|1|0]?)-?([0-9]{4})-?([0-9]{4})$");
			Matcher matcher1 = phonePattern.matcher(userPhone);
			if (!matcher1.matches()) {
				System.out.println("xxxxxxxx 잘못 입력하셨습니다 xxxxxxxx");
				System.out.println("xxx 010-0000-0000 형식으로 입력해 주세요 xxx");
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
				System.out.println("xxxxxxxx 잘못 입력하셨습니다 xxxxxxxx");
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
			System.out.println("xxxxx 회원목록 저장에 실패하였습니다 xxxxx");
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
		UserUI userUI = new UserUI();
		currentLoginUser = null;
		System.out.println("※※※ 이용해 주셔서 감사합니다 ※※※");
		userUI.service();
	}

	public void findID() {
		Set<String> keys = userList.keySet();
		boolean flag = false;

		System.out.println("● Bit 편의점에 가입한 회원 이름을 입력해주세요 : ");
		String userName = sc.nextLine().trim();
		for (String key : keys) {
			UserVO value = userList.get(key);
			if (value.getUserName().equals(userName)) {
				flag = true;
				break;
			}
		}
		if (flag == false) {
			System.out.println("[" + userName + "] 이름의 회원이 존재하지 않습니다. ");
			System.out.println("xxxxxx 회원가입후 이용해 주세요 xxxxxx");
			return;
		}
		flag = false;
		System.out.println("● Bit 편의점에 가입한 회원 핸드폰번호 뒷 4자리를 입력해주세요 : ");
		String userPhone = sc.nextLine().trim();
		for (String key : keys) {
			UserVO value = userList.get(key);
			if (value.getUserName().equals(userName) && value.getUserPhone().contains(userPhone)) {
				System.out.println("[" + userName + "] 님의 ID 는 [" + value.getID() + "] 입니다.");
				flag = true;
				break;
			}
		}
		if (flag == false) {
			System.out.println("xxx 이름과 핸드폰번호가 일치하지 않습니다. 다시 시도해 주세요 xxx");
		}
	}

	/*
	 * public void findID() { Iterator<String> mapIter =
	 * userList.keySet().iterator(); System.out.println("찾고 싶은 ID의 이름을 입력해주세요");
	 * String userName = sc.nextLine().trim(); boolean flag = false;
	 * System.out.println("찾고 싶은 ID의 핸드폰번호를 입력해주세요"); String userPhone =
	 * sc.nextLine().trim(); while (mapIter.hasNext()) { String key =
	 * mapIter.next(); UserVO value = userList.get(key); if
	 * (value.getUserName().equals(userName) &&
	 * value.getUserPhone().equals(userPhone)) {
	 * System.out.println("["+userName+"] 님의 ID는 ["+value.getID()+"] 입니다.");
	 * flag = true; break; } } if (flag == false) { System.out.println(
	 * "찾는 아이디가 없습니다."); } }
	 */
	public void findPwd() {
		Set<String> keys = userList.keySet();
		boolean flag = false;

		System.out.println("● Bit 편의점에 가입한 회원 ID 를 입력해주세요 : ");
		String userID = sc.nextLine().trim();
		for (String key : keys) {
			UserVO value = userList.get(key);
			if (value.getID().equals(userID)) {
				flag = true;
				break;
			}
		}
		if (flag == false) {
			System.out.println("[" + userID + "] ID가 존재하지 않습니다. ");
			System.out.println("xxx 회원가입을 하시거나 ID 찾기를 이용해 주세요 xxx");
			return;
		}
		flag = false;
		System.out.println("● Bit 편의점에 가입한 회원 이름을 입력해주세요 : ");
		String userName = sc.nextLine().trim();
		for (String key : keys) {
			UserVO value = userList.get(key);
			if (value.getID().equals(userID) && value.getUserName().contains(userName)) {
				System.out.println("[" + userID + "] 님의 비밀번호는 [" + value.getPwd() + "] 입니다.");
				flag = true;
				break;
			}
		}
		if (flag == false) {
			System.out.println("xxx ID와 이름이 일치하지 않습니다. 다시 시도해 주세요 xxx");
		}
	}
	/*
	 * public void findPwd() { Iterator<String> mapIter =
	 * userList.keySet().iterator(); System.out.println("찾고 싶은 비밀번호의 ID를 입력해주세요"
	 * ); String userID = sc.nextLine().trim(); boolean flag = false;
	 * System.out.println("찾고 싶은 비밀번호의 이름을 입력해주세요"); String userName =
	 * sc.nextLine().trim(); while (mapIter.hasNext()) { String key =
	 * mapIter.next(); UserVO value = userList.get(key); if
	 * (value.getID().equals(userID) && value.getUserName().equals(userName)) {
	 * System.out.println("["+userID+"] 님의 비밀번호는 ["+value.getPwd()+"] 입니다.");
	 * flag = true; break; } } if (flag == false) { System.out.println(
	 * "찾는 아이디가 없습니다."); } }
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
			System.out.println("xxxxx 회원목록을 불러오는데 실패하였습니다 xxxxx");
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
			System.out.println("xxxxx 상품목록을 불러오는데 실패하였습니다 xxxxx");
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
			System.out.println("xxxxx 폐기상품목록을 불러오는데 실패하였습니다 xxxxx");
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
			System.out.println("xxxxx 게시판목록을 불러오는데 실패하였습니다 xxxxx");
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
