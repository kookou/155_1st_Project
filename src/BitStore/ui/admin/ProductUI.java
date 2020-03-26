package BitStore.ui.admin;

import java.util.Scanner;

import BitStore.ui.BoardUI;
import BitStore.ui.LoginUI;
import BitStore.util.Product;

public class ProductUI {
	private Scanner sc = new Scanner(System.in);
	private Product product;
	
	public ProductUI() {
		sc = new Scanner(System.in);
		product = new Product();
	}

	public void admin() {
		while (true) {
			switch (adminMenu()) {
			case 1: product.insertProduct(); // 상품정보 입력
				break;
			case 2: product.selectProduct(); // 상품목록 조회
				break;
			case 3: product.updateProduct(); // 상품정보 수정, Product클래스의 updateProduct(Product) : boolean 호출
				break;
			case 4: product.deleteProduct();// 상품정보 삭제, Product클래스의 deleteProduct(int) : boolean 호출
				break;
			case 5: // 폐기상품 등록, DiscardProduct클래스의 insertDiscardProduct(DiscardProduct) : void 호출
				break;
			case 6: // 폐기상품 조회, DiscardProduct클래스의 selectDiscardProduct(DiscardProduct) : Map<Integer, DiscardProduct> 호출
				break;
			case 7: // 돌아가기
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
		System.out.println("1. 상품정보 입력");
		System.out.println("2. 상품목록 조회");
		System.out.println("3. 상품정보 수정");
		System.out.println("4. 상품정보 삭제");
		System.out.println("5. 폐기상품 등록");
		System.out.println("6. 폐기상품 등록");
		System.out.println("7. 돌아가기");
		System.out.println("0. 종료");
		System.out.println("-----------------");
		System.out.print("메뉴 중 처리할 항목을 선택하세요 : ");
		return Integer.parseInt(sc.nextLine());
	}
}
