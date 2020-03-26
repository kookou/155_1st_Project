package BitStore.util;

import java.util.Iterator;
import java.util.Scanner;

import BitStore.domain.UserVO;

public class User {

	Scanner sc = new Scanner(System.in);

	public void selectUser() {
		Iterator<String> mapIter = BitStore.userList.keySet().iterator();
		while (mapIter.hasNext()) {
			String key = mapIter.next();
			UserVO value = BitStore.userList.get(key);
			System.out.println(key + value);
		}
	}
	public void selectUserByID() {

	}

	public void updateUser() {
		BitStore bs = new BitStore();
		BitStore.user = BitStore.currentLoginUser;
		System.out.println("�ءء� ȸ������ �����Դϴ� �ءء� ");
		System.out.println("�� ������ ��й�ȣ�� �Է����ּ��� : ");
		bs.checkPwd();
		System.out.println("�� ������ �̸��� �Է��� �ּ��� : ");
		BitStore.user.setUserName(sc.nextLine().trim());
		System.out.println("�� ������ �ڵ��� ��ȣ�� �Է��� �ּ��� : ");
		System.out.println("(010-0000-0000 �������� �Է��� �ּ���.)");
		bs.checkPhone();
		/*
		while (true) {
			String userPwd = sc.nextLine().trim();
			Pattern pwPattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,20}$");
			Matcher matcher1 = pwPattern.matcher(userPwd);
			if (!matcher1.matches()) {
				System.out.println("xxxxxxxx �߸� �Է��ϼ̽��ϴ� xxxxxxxx");
				System.out.println("xxx 8~20�� ������ ���� �� ����,Ư�����ڸ� ������ �ٽ� �Է��� �ּ��� xxx");
			} else {
				BitStore.user.setPwd(userPwd);
				break;
			}
		}
		System.out.println("�� ������ �̸��� �Է��� �ּ��� : ");
		BitStore.user.setUserName(sc.nextLine().trim());
		System.out.println("�� ������ �ڵ��� ��ȣ�� �Է��� �ּ��� : ");
		System.out.println("(010-0000-0000 �������� �Է��� �ּ���.)");
		while (true) {
			String userPhone = sc.nextLine().trim();
			Pattern phonePattern = Pattern.compile("^01([0|1|0]?)-?([0-9]{3,4})-?([0-9]{4})$");
			Matcher matcher1 = phonePattern.matcher(userPhone);
			if (!matcher1.matches()) {
				System.out.println("xxxxxxxx �߸� �Է��ϼ̽��ϴ� xxxxxxxx");
				System.out.println("xxx 010-0000-0000 �������� �Է��� �ּ��� xxx");
			} else {
				BitStore.user.setUserPhone(userPhone);
				break;
			}
		}
		BitStore.user.setID(BitStore.currentLoginUser.getID());
//		BitStore.user.setUserPoint(BitStore.currentLoginUser.getUserPoint());
//		BitStore.user.setMoney(BitStore.currentLoginUser.getMoney());
		BitStore.userList.put(BitStore.user.getID(),BitStore.user);
		 */
		BitStore.writeUserList();
//		System.out.println("currentLoginUser"+BitStore.currentLoginUser);
//		System.out.println("user"+BitStore.user);
	}

	public void deleteUser() {

	}

}
