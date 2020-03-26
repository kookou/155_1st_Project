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

		if (!userListFile.exists()) { // userList.txt�� ������ ����
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			ObjectOutputStream oos = null;
			try {
				UserVO initUserVO = new UserVO();
				userListFile.createNewFile();
				fos = new FileOutputStream(userListFile); // admin ������ default��
															// �߰�
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
		productListFile = new File("productList.txt"); // ��ǰ ����
		if (!productListFile.exists()) { // productList.txt�� ������ ����
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			ObjectOutputStream oos = null;
			try {
				ProductVO initProductVO = new ProductVO();
				productListFile.createNewFile();
				fos = new FileOutputStream(productListFile); // snack ��ǰ��
																// default�� �߰�
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
		discardProductListFile = new File("discardProductList.txt"); // ����ǰ ����
		if (!discardProductListFile.exists()) { // discardProductList.txt�� ������
												// ����
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			ObjectOutputStream oos = null;
			try {
				DiscardProductVO initDiscardProductVO = new DiscardProductVO();
				discardProductListFile.createNewFile();
				fos = new FileOutputStream(discardProductListFile); // beverage
																	// ��ǰ��
																	// default��
																	// �߰�
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
		boardListFile = new File("boardList.txt"); // �Խ��� ��� ����
		if (!boardListFile.exists()) { // boardList.txt�� ������ ����
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			ObjectOutputStream oos = null;
			try {
				BoardVO initBoardVO = new BoardVO();
				boardListFile.createNewFile();
				fos = new FileOutputStream(boardListFile); // ù��° �Խñ��� default��
															// �߰�
				bos = new BufferedOutputStream(fos);
				oos = new ObjectOutputStream(fos);
				initBoardVO.setBoardNo(1);
				initBoardVO.setBoardTitle("���� �׽�Ʈ");
				initBoardVO.setBoardContent("���� �׽�Ʈ");
				initBoardVO.setBoardWriter("admin");
				initBoardVO.setBoardDate("2020-03-26");
				boardList.put(1, initBoardVO);
				oos.writeObject(boardList);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		readUserList(); // ȸ����� ���� �ҷ�����
		readProductList(); // ��ǰ��� ���� �ҷ�����
		readDiscardProductList(); // ����ǰ��� ���� �ҷ�����
		readBoardList(); // �Խ��Ǹ�� ���� �ҷ�����
		sc = new Scanner(System.in);
	}

	public void join() {
		user = new UserVO();
		System.out.println("�ءء�  Bit ������ ȸ�������� ȯ�� �մϴ� �ءء�");
		System.out.println("�� Bit �������� ����� ȸ�� ID�� �Է����ּ��� : ");
		System.out.println("(5~15�� ������ ���� �� ���ڸ� �������ּ���.)");
		checkID();
		System.out.println("�� Bit �������� ����� ȸ�� ��й�ȣ�� �Է����ּ��� : ");
		System.out.println("(8~20�� ������ ���� �� ����,Ư�����ڸ� �������ּ���.)");
		checkPwd();
		System.out.println("�� Bit �������� ����� ȸ�� �̸��� �Է����ּ��� : ");
		user.setUserName(sc.nextLine().trim());
		System.out.println("�� Bit �������� ����� ȸ�� �ڵ�����ȣ�� �Է����ּ��� : ");
		System.out.println("(010-0000-0000 �������� �Է��� �ּ���.)");
		checkPhone();
		System.out.println("�� ���Ǳ��ſ� ����� �ݾ��� �������ּ��� : ");
		user.setMoney(Integer.parseInt(sc.nextLine().trim()));
		user.setUserPoint(1000);
		System.out.println("�� ����ī�带 �����ϼ̴ٸ� 1����, �����ôٸ� 0���� �Է��� �ּ��� : ");
		checkDC();
		userList.put(user.getID(), user);
		writeUserList();
		System.out.println("�ءء�  Bit ������ ȸ���� �ǽ� ���� ���ϵ帳�ϴ� �ءء�");
		System.out.println("�ءء�  ���ſ� ��� ������ ����Ʈ�� ������ ��Ƚ��ϴ� �ءء�");

		// ȸ�����
		System.out.println("[ȸ����� ��ȸ]");
		Iterator<String> mapIter = BitStore.userList.keySet().iterator();
		while (mapIter.hasNext()) {
			String key = mapIter.next();
			UserVO value = BitStore.userList.get(key);
			System.out.println(value + " / ");
		}

	}

	public void login() {
		while (true) {
			System.out.println("�ءء� Bit ������ �α��� �ءء� ");
			System.out.println("�� ���̵� �Է����ּ��� : ");
			String userID = sc.nextLine().trim();

			if (!userList.containsKey(userID)) {
				System.out.println("xxxxx �ش��ϴ� ID�� �����ϴ� xxxxx");
				login();
			} else if (userList.containsKey(userID)) {
				System.out.println("�� ��й�ȣ�� �Է����ּ��� : ");
				String userPwd = sc.nextLine().trim();
				if (userList.get(userID).getPwd().equals(userPwd)) {
					System.out.print("�ءء� Bit �������� �α��� �Ǿ����ϴ� �ءء� ");
					if (userID.equals("admin")) { // admin�̸� adminUI ������ �����
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
					System.out.println("Password�� Ȯ���� �ּ���.");
				}
			}
		}

	}

	private String userID;

	public void checkID() {
		userID = sc.nextLine().trim();
		Set<String> keys = userList.keySet();
		while (true) {
			Pattern idPattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[^!@#$%^*+=-])(?=.*[^��-�R])(?=.*[0-9]).{5,15}$");
			Matcher matcher1 = idPattern.matcher(userID);
			for (String key : keys) {
				if (key.equals(userID)) {
					System.out.println("xxx �̹� ������� ���̵��Դϴ�. �ٽ� �Է��� �ּ��� xxx");
					checkID();
				}
			}
			if (!matcher1.matches()) {
				System.out.println("�Է� �� : " + userID);
				System.out.println("xxxxxxxx �߸� �Է��ϼ̽��ϴ� xxxxxxxx");
				System.out.println("xxx 5~15�� ������ ���ڸ� �����Ͽ� �ٽ� �Է��� �ּ��� xxx");
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
				System.out.println("xxxxxxxx �߸� �Է��ϼ̽��ϴ� xxxxxxxx");
				System.out.println("xxx 8~20�� ������ ���� �� ����,Ư�����ڸ� ������ �ٽ� �Է��� �ּ��� xxx");
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
				System.out.println("xxxxxxxx �߸� �Է��ϼ̽��ϴ� xxxxxxxx");
				System.out.println("xxx 010-0000-0000 �������� �Է��� �ּ��� xxx");
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
				System.out.println("xxxxxxxx �߸� �Է��ϼ̽��ϴ� xxxxxxxx");
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
			System.out.println("xxxxx ȸ����� ���忡 �����Ͽ����ϴ� xxxxx");
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

		System.out.println("�� Bit �������� ������ ȸ�� �̸��� �Է����ּ��� : ");
		String userName = sc.nextLine().trim();
		for (String key : keys) {
			UserVO value = userList.get(key);
			if (value.getUserName().equals(userName)) {
				flag = true;
				break;
			}
		}
		if (flag == false) {
			System.out.println("[" + userName + "] �̸��� ȸ���� �������� �ʽ��ϴ�. ");
			System.out.println("xxxxxx ȸ�������� �̿��� �ּ��� xxxxxx");
			return;
		}
		flag = false;
		System.out.println("�� Bit �������� ������ ȸ�� �ڵ��� ��ȣ�� �Է����ּ��� : ");
		String userPhone = sc.nextLine().trim();
		for (String key : keys) {
			UserVO value = userList.get(key);
			if (value.getUserName().equals(userName) && value.getUserPhone().equals(userPhone)) {
				System.out.println("[" + userName + "] ���� ��й�ȣ�� [" + value.getID() + "] �Դϴ�.");
				flag = true;
				break;
			}
		}
		if (flag == false) {
			System.out.println("xxx �̸��� �ڵ�����ȣ�� ��ġ���� �ʽ��ϴ�. �ٽ� �õ��� �ּ��� xxx");
		}
	}

	/*
	 * public void findID() { Iterator<String> mapIter =
	 * userList.keySet().iterator(); System.out.println("ã�� ���� ID�� �̸��� �Է����ּ���");
	 * String userName = sc.nextLine().trim(); boolean flag = false;
	 * System.out.println("ã�� ���� ID�� �ڵ�����ȣ�� �Է����ּ���"); String userPhone =
	 * sc.nextLine().trim(); while (mapIter.hasNext()) { String key =
	 * mapIter.next(); UserVO value = userList.get(key); if
	 * (value.getUserName().equals(userName) &&
	 * value.getUserPhone().equals(userPhone)) {
	 * System.out.println("["+userName+"] ���� ID�� ["+value.getID()+"] �Դϴ�.");
	 * flag = true; break; } } if (flag == false) { System.out.println(
	 * "ã�� ���̵� �����ϴ�."); } }
	 */
	public void findPwd() {
		Set<String> keys = userList.keySet();
		boolean flag = false;

		System.out.println("�� Bit �������� ������ ȸ�� ID �� �Է����ּ��� : ");
		String userID = sc.nextLine().trim();
		for (String key : keys) {
			UserVO value = userList.get(key);
			if (value.getID().equals(userID)) {
				flag = true;
				break;
			}
		}
		if (flag == false) {
			System.out.println("[" + userID + "] ID�� �������� �ʽ��ϴ�. ");
			System.out.println("xxx ȸ�������� �Ͻðų� ID ã�⸦ �̿��� �ּ��� xxx");
			return;
		}
		flag = false;
		System.out.println("�� Bit �������� ������ ȸ�� �̸��� �Է����ּ��� : ");
		String userName = sc.nextLine().trim();
		for (String key : keys) {
			UserVO value = userList.get(key);
			if (value.getID().equals(userID) && value.getUserName().equals(userName)) {
				System.out.println("[" + userID + "] ���� ��й�ȣ�� [" + value.getPwd() + "] �Դϴ�.");
				flag = true;
				break;
			}
		}
		if (flag == false) {
			System.out.println("xxx ID�� �̸��� ��ġ���� �ʽ��ϴ�. �ٽ� �õ��� �ּ��� xxx");
		}
	}
	/*
	 * public void findPwd() { Iterator<String> mapIter =
	 * userList.keySet().iterator(); System.out.println("ã�� ���� ��й�ȣ�� ID�� �Է����ּ���"
	 * ); String userID = sc.nextLine().trim(); boolean flag = false;
	 * System.out.println("ã�� ���� ��й�ȣ�� �̸��� �Է����ּ���"); String userName =
	 * sc.nextLine().trim(); while (mapIter.hasNext()) { String key =
	 * mapIter.next(); UserVO value = userList.get(key); if
	 * (value.getID().equals(userID) && value.getUserName().equals(userName)) {
	 * System.out.println("["+userID+"] ���� ��й�ȣ�� ["+value.getPwd()+"] �Դϴ�.");
	 * flag = true; break; } } if (flag == false) { System.out.println(
	 * "ã�� ���̵� �����ϴ�."); } }
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
			System.out.println("xxxxx ȸ������� �ҷ����µ� �����Ͽ����ϴ� xxxxx");
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
			System.out.println("xxxxx ��ǰ����� �ҷ����µ� �����Ͽ����ϴ� xxxxx");
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
			System.out.println("xxxxx ����ǰ����� �ҷ����µ� �����Ͽ����ϴ� xxxxx");
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
			System.out.println("xxxxx �Խ��Ǹ���� �ҷ����µ� �����Ͽ����ϴ� xxxxx");
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
