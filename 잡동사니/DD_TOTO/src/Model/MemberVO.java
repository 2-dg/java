package Model;

import java.net.Socket;

public class MemberVO {
	
	private String name;
	private String id;
	private String pw1;
	private String pw2;
	private String phoneNum1;
	private String phoneNum2;
	private String phoneNum3;
	private String bankName;
	private String accountNum;
	private String accountHolder;
	private String userMoney;
	private String userType;
	private String phoneNumber;
	private String userStatus;

	
	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public MemberVO() {
		super();
	}

	public MemberVO(String id) {
		this.id = id;
	}
	
	public MemberVO(String name, String id, String phoneNum1, String phoneNum2, String phoneNum3, String bankName,
			String accountNum, String accountHolder, String userMoney, String userType, String phoneNumber) {
		super();
		this.name = name;
		this.id = id;
		this.phoneNum1 = phoneNum1;
		this.phoneNum2 = phoneNum2;
		this.phoneNum3 = phoneNum3;
		this.bankName = bankName;
		this.accountNum = accountNum;
		this.accountHolder = accountHolder;
		this.userMoney = userMoney;
		this.userType = userType;
		this.phoneNumber = phoneNumber;
	}

	public MemberVO(String id, String name, String pw1, String pw2, String phoneNum1, String phoneNum2,
			String phoneNum3, String bankName, String accountNum, String accountHolder, String userMoney,
			String userType) {
		super();
		this.id = id;
		this.name = name;
		this.pw1 = pw1;
		this.pw2 = pw2;
		this.phoneNum1 = phoneNum1;
		this.phoneNum2 = phoneNum2;
		this.phoneNum3 = phoneNum3;
		this.bankName = bankName;
		this.accountNum = accountNum;
		this.accountHolder = accountHolder;
		this.userMoney=userMoney;
		this.userType=userType;
	}	
	
	

	public MemberVO(String id, String name, String phoneNum1, String bankName, 
			String accountNum, String accountHolder, String pw1, String pw2, 
			String phoneNum2, String phoneNum3) {
		super();
		this.name = name;
		this.id = id;
		this.pw1 = pw1;
		this.pw2 = pw2;
		this.phoneNum1 = phoneNum1;
		this.phoneNum2 = phoneNum2;
		this.phoneNum3 = phoneNum3;
		this.bankName = bankName;
		this.accountNum = accountNum;
		this.accountHolder = accountHolder;
	}

	public String getUserMoney() {
		return userMoney;
	}

	public void setUserMoney(String userMoney) {
		this.userMoney = userMoney;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw1() {
		return pw1;
	}

	public void setPw1(String pw1) {
		this.pw1 = pw1;
	}

	public String getPw2() {
		return pw2;
	}

	public void setPw2(String pw2) {
		this.pw2 = pw2;
	}

	public String getPhoneNum1() {
		return phoneNum1;
	}

	public void setPhoneNum1(String phoneNum1) {
		this.phoneNum1 = phoneNum1;
	}

	public String getPhoneNum2() {
		return phoneNum2;
	}

	public void setPhoneNum2(String phoneNum2) {
		this.phoneNum2 = phoneNum2;
	}

	public String getPhoneNum3() {
		return phoneNum3;
	}

	public void setPhoneNum3(String phoneNum3) {
		this.phoneNum3 = phoneNum3;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	@Override
	public String toString() {
		return "MemberVO [name=" + name + ", id=" + id + ", pw1=" + pw1 + ", pw2=" + pw2 + ", phoneNum1=" + phoneNum1
				+ ", phoneNum2=" + phoneNum2 + ", phoneNum3=" + phoneNum3 + ", bankName=" + bankName + ", accountNum="
				+ accountNum + ", accountHolder=" + accountHolder + ", userMoney=" + userMoney + ", userType="
				+ userType + ", phoneNumber=" + phoneNumber + ", userStatus=" + userStatus + "]";
	}

	
	
	

}