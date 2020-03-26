package BitStore.domain;

import java.io.Serializable;
import java.util.List;

import BitStore.util.Product;

public class UserVO implements Serializable{
	private String ID;
	private String pwd;
	private String userName;
	private String userPhone;
	private int money;
	private int userPoint;
	private boolean discount;
	private List<Product> orderList;

	public UserVO(String iD, String pwd, String userName, String userPhone, int money, int userPoint, boolean discount,
			List<Product> orderList) {
		super();
		ID = iD;
		this.pwd = pwd;
		this.userName = userName;
		this.userPhone = userPhone;
		this.money = money;
		this.userPoint = userPoint;
		this.discount = discount;
		this.orderList = orderList;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}

	public boolean isDiscount() {
		return discount;
	}

	public void setDiscount(boolean discount) {
		this.discount = discount;
	}

	public List<Product> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Product> orderList) {
		this.orderList = orderList;
	}

	@Override
	public String toString() {
		return "User [ID=" + ID + ", pwd=" + pwd + ", userName=" + userName + ", userPhone=" + userPhone + ", money="
				+ money + ", userPoint=" + userPoint + ", discount=" + discount + ", orderList=" + orderList + "]";
	}

}