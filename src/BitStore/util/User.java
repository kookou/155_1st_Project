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

//		BitStore.user.setID(BitStore.currentLoginUser.getID());
//		BitStore.user.setUserPoint(BitStore.currentLoginUser.getUserPoint());
//		BitStore.user.setMoney(BitStore.currentLoginUser.getMoney());
		

		BitStore.userList.put(BitStore.user.getID(),BitStore.user);
		BitStore.writeUserList();
		System.out.println("currentLoginUser"+BitStore.currentLoginUser);
		System.out.println("user"+BitStore.user);
	}

	public void deleteUser() {

	}

}
