package BitStore.ui.admin;

import java.util.Scanner;

import BitStore.ui.BoardUI;
import BitStore.ui.LoginUI;
import BitStore.ui.user.BitStoreUI;
import BitStore.ui.user.UserUI;

public class MemberUI {
	private Scanner sc = new Scanner(System.in);

	// 관리자일 경우
	public void admin() {
		while (true) {
			switch (adminMenu()) {
			case 1: // 회원목록 조회, User클래스의 selectUser() : Map<String, User> 호출

				break;
			case 2: // 회원상세 조회 User클래스의 selectUserByID(String) : User 호출

				break;
			case 3: // 회원정보 삭제 User클래스의 deleteUser(String) : boolean 호출
				
				break;
			case 4: // 돌아가기
				LoginUI loginUI = new LoginUI();
				loginUI.admin();
				break;
			case 0:
				quit();
			default:
				System.out.println("번호를 잘못 입력하셨습니다.");
			}
		}
	}

	private void quit() {
		System.out.println("안녕히 가세요^^!");
		System.exit(0);
	}

	private int adminMenu() {
		System.out.println("-----------------");
		System.out.println("1. 회원목록조회");
		System.out.println("2. 회원상세 조회");
		System.out.println("3. 회원정보 삭제");
		System.out.println("4. 돌아가기");
		System.out.println("0. 종료");
		System.out.println("-----------------");
		System.out.print("메뉴 중 처리할 항목을 선택하세요 : ");
		return Integer.parseInt(sc.nextLine());
	}
}
