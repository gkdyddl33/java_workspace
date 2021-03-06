package com.swingmall.cart;

import com.swingmall.admin.product.ProductVO;

/*하나의 상품정보를 담을 수 있으나, 사용자가 구매하기를 원하는 갯수는 productVO에
 * 담을수 없기에 현재 클래스르르 ProductVO를 상속받는 자식 클래스로 정의하되
 * ea만 추가하면 코드 재사용이 가능하다*/
public class CartVO extends ProductVO{

	private int ea;
	private String color;
	private String size;

	public int getEa() {
		return ea;
	}

	public void setEa(int ea) {
		this.ea = ea;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}	
}
