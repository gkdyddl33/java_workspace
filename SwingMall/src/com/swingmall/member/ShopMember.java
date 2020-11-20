package com.swingmall.member;

public class ShopMember {
	private int member_id;
	private static String mid;
	private static String pass;
	private static String name;
	private static String phone;
	private static String email;
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public static String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public static String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public static String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public static String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
