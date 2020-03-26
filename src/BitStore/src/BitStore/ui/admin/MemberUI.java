package BitStore.ui.admin;

import java.util.Scanner;

import BitStore.ui.BoardUI;
import BitStore.ui.LoginUI;
import BitStore.ui.user.BitStoreUI;
import BitStore.ui.user.UserUI;
import BitStore.util.User;

public class MemberUI {
	private Scanner sc = new Scanner(System.in);
	User user = new User();

	// �������� ���
	public void admin() {
		while (true) {
			switch (adminMenu()) {
			case 1: // ȸ����� ��ȸ, UserŬ������ selectUser() : Map<String, User> ȣ��
				user.selectUser();
				break;
			case 2: // ȸ���� ��ȸ UserŬ������ selectUserByID(String) : User ȣ��
				user.selectUserByID();
				break;
			case 3: // ȸ������ ���� UserŬ������ deleteUser(String) : boolean ȣ��
				user.deleteUser();
				break;
			case 4: // ���ư���
				LoginUI loginUI = new LoginUI();
				loginUI.admin();
				break;
			case 6: // ���ư���
				User us = new User();
				us.updateUser();
				break;
			case 0:
				quit();
			default:
				System.out.println("��ȣ�� �߸� �Է��ϼ̽��ϴ�.");
			}
		}
	}

	private void quit() {
		System.out.println("�ȳ��� ������^^!");
		System.exit(0);
	}

	private int adminMenu() {
		System.out.println("-----------------");
		System.out.println("1. ȸ�������ȸ");
		System.out.println("2. ȸ���� ��ȸ");
		System.out.println("3. ȸ������ ����");
		System.out.println("4. ���ư���");
		System.out.println("0. ����");
		System.out.println("-----------------");
		System.out.print("�޴� �� ó���� �׸��� �����ϼ��� : ");
		return Integer.parseInt(sc.nextLine());
	}
}