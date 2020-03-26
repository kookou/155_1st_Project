package BitStore.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import BitStore.domain.UserVO;

public class User {

	Scanner in = new Scanner(System.in);

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

	}

	public void deleteUser() {

	}

}
