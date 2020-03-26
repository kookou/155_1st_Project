package BitStore.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import BitStore.domain.ProductVO;

public class PurChase {
	private Scanner in = new Scanner(System.in);
	public static List<ProductVO> basket;
	
	public void selectPoint() {
		System.out.print("현재 남은 포인트 : ");
		System.out.println(BitStore.currentLoginUser.getUserPoint());
	}
	
	public void discountProduct() {
	
	}
	
	public void insertBasket() {
		while(true) {
			System.out.println("장바구니에 담을 상품번호를 입력하세요,");	
			int productNo = in.nextInt();
			//	public static Map<Integer, ProductVO> productList = new HashMap();
			// 여기에서 key 값으로 바교해서 ProductVO객체 뽑은 다음 리스트에 넣기~
			
		}
		
		
	}
	
}
