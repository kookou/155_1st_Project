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
		System.out.println("※※※ 회원정보 수정입니다 ※※※ ");
		System.out.println("● 수정할 비밀번호를 입력해주세요 : ");
		bs.checkPwd();
		System.out.println("● 수정할 이름을 입력해 주세요 : ");
		BitStore.user.setUserName(sc.nextLine().trim());
		System.out.println("● 수정할 핸드폰 번호를 입력해 주세요 : ");
		System.out.println("(010-0000-0000 형식으로 입력해 주세요.)");
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
