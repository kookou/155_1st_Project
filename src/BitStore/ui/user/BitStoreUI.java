package BitStore.ui.user;

import java.util.Scanner;

import BitStore.Interface.CashPayMentImpl;
import BitStore.Interface.PayMent;
import BitStore.Interface.PointPayMentImpl;
import BitStore.ui.LoginUI;
import BitStore.util.Product;
import BitStore.util.PurChase;

public class BitStoreUI {
	private Scanner sc = new Scanner(System.in);
	private LoginUI loginUI = new LoginUI(); //�ڿ�����
	private PurChase purchase = new PurChase();
	private Product product = new Product();
	private PayMent payment;

	public void useBitStore() {
		while (true) {
			switch (menu()) {
			case 1: // ��ٱ��� ���, PurchaseŬ������ insertBasket(List<Product>) : void
				purchase.insertBasket(); //�̿ϼ�!!!
				break;
			case 2: // ��ǰ ����, Payment pay = n
				usePurchase(); //���� ��ǰ ���� switch �̵�
				break;
			case 3: // ��ǰ��ȸ ProductŬ������ selectProduct() : Map<Integer,Product>
				product.selectProduct();
				break;
			case 4: // ����Ʈ��ȸ PurchaseŬ������ selectPoint(User) : int
				purchase.selectPoint();
				break;
			case 5: // ���ư���
				loginUI.user();
				break;
			case 0:
				quit();
			default:
				System.out.println("��ȣ�� �߸� �Է��ϼ̽��ϴ�.");
			}
		}
	}

	public void usePurchase() {
		while (true) {
			switch (purchaseMenu()) {
			case 1: // ��ǰ ����
				purchase.discountProduct();
				break;
			case 2: // ���� ���
				usePayment(); //���� ���� ���switch �̵�
				break;
			case 3: // ���ư���
				useBitStore();
				break;
			case 0:
				quit();
			default:
				System.out.println("��ȣ�� �߸� �Է��ϼ̽��ϴ�.");
			}
		}
	}
	
	public void usePayment() {
		while (true) {
			switch (paymentMenu()) {
			case 1: // ���� ����
				payment = new CashPayMentImpl();
				payment.buy();
				break;
			case 2: // ����Ʈ ����
				payment = new PointPayMentImpl();
				payment.buy();
				break;
			case 3: // ���ư���
				usePurchase();
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

	private int menu() {
		System.out.println("-----------------");
		System.out.println("1. ��ٱ��� ���");
		System.out.println("2. ��ǰ����");
		System.out.println("3. ��ǰ��ȸ");
		System.out.println("4. ����Ʈ��ȸ");
		System.out.println("5. ���ư���");
		System.out.println("0. ����");
		System.out.println("-----------------");
		System.out.print("�޴� �� ó���� �׸��� �����ϼ��� : ");
		return Integer.parseInt(sc.nextLine());
	}

	private int purchaseMenu() {
		System.out.println("-----------------");
		System.out.println("1. ��ǰ����");
		System.out.println("2. �������");
		System.out.println("3. ���ư���");
		System.out.println("0. ����");
		System.out.println("-----------------");
		System.out.print("�޴� �� ó���� �׸��� �����ϼ��� : ");
		return Integer.parseInt(sc.nextLine());
	}

	private int paymentMenu() {
		System.out.println("-----------------");
		System.out.println("1. ���� ����");
		System.out.println("2. ����Ʈ ����");
		System.out.println("3. ���ư���");
		System.out.println("0. ����");
		System.out.println("-----------------");
		System.out.print("�޴� �� ó���� �׸��� �����ϼ��� : ");
		return Integer.parseInt(sc.nextLine());
	}

}