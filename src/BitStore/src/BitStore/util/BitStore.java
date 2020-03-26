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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import BitStore.domain.BoardVO;
import BitStore.domain.DiscardProductVO;
import BitStore.domain.ProductVO;
import BitStore.domain.UserVO;

public class BitStore {
	public static Map<String, UserVO> userList = new HashMap();
	public static Map<Integer, ProductVO> productList = new HashMap();
	public static Map<Integer, DiscardProductVO> discardProductList = new HashMap();
	public static Map<Integer, BoardVO> boardList = new HashMap();
	public static UserVO currentLoginUser = new UserVO(null, null, null, null, 0, 0, false, null);
	Scanner in;
	File userListFile;
	File productListFile;
	File discardProductListFile;
	File boardListFile;

	public BitStore() { 
		userListFile = new File("userList.txt"); 

		if (!userListFile.exists()) { // userList.txt�� ������ ����
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			ObjectOutputStream oos = null;
			try {
				userListFile.createNewFile();
				fos = new FileOutputStream(userListFile); // admin ������ default�� �߰�
				bos = new BufferedOutputStream(fos);
				oos = new ObjectOutputStream(fos);
				userList.put("admin", new UserVO("admin", "admin", "admin", "010-0000-0000", 0, 0, false, null));
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
				productListFile.createNewFile();
				fos = new FileOutputStream(productListFile); // snack ��ǰ�� default�� �߰�
				bos = new BufferedOutputStream(fos);
				oos = new ObjectOutputStream(fos);
				productList.put(880123456, new ProductVO(880123456, "Snack", 80, "2020-04-25", 3000));
				oos.writeObject(productList);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		discardProductListFile = new File("discardProductList.txt"); // ����ǰ ����
		if (!discardProductListFile.exists()) { // discardProductList.txt�� ������ ����
			FileOutputStream fos = null;
			BufferedOutputStream bos = null;
			ObjectOutputStream oos = null;
			try {
				discardProductListFile.createNewFile();
				fos = new FileOutputStream(discardProductListFile); // beverage ��ǰ�� default�� �߰�
				bos = new BufferedOutputStream(fos);
				oos = new ObjectOutputStream(fos);
				discardProductList.put(880123456, new DiscardProductVO(880123456, "beverage", "2020-03-25", 1000));
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
				boardListFile.createNewFile();
				fos = new FileOutputStream(boardListFile); // ù��° �Խñ��� default�� �߰�
				bos = new BufferedOutputStream(fos);
				oos = new ObjectOutputStream(fos);
				boardList.put(1, new BoardVO(1, "���� �׽�Ʈ", "���� �׽�Ʈ", "admin", "2020-03-25"));
				oos.writeObject(boardList);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		readUserList(); // ȸ����� ���� �ҷ�����
		readProductList(); // ��ǰ��� ���� �ҷ�����
		readDiscardProductList(); // ����ǰ��� ���� �ҷ�����
		readBoardList(); // �Խ��Ǹ�� ���� �ҷ�����
		in = new Scanner(System.in);
	}

	public void join() {
		
	}

	public void login() {
		
	}

	public void checkID() {
	}

	public void checkPwd() {
	}

	public void checkPhone() {
	}

	static public void writeUserList() {
		File file = new File("userList.txt");
//		file.delete();
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(userList);
		} catch (Exception e) {
			System.out.println("ȸ����� ���忡 �����Ͽ����ϴ�.");
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

	}

	public void findPwd() {

	}

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
			System.out.println("ȸ������� �ҷ����µ� �����Ͽ����ϴ�.");
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
			System.out.println("��ǰ����� �ҷ����µ� �����Ͽ����ϴ�.");
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
			System.out.println("����ǰ����� �ҷ����µ� �����Ͽ����ϴ�.");
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
			System.out.println("�Խ��Ǹ���� �ҷ����µ� �����Ͽ����ϴ�.");
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