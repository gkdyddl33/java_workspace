/*
 	장바구니 페이지를 정의한다.
 	
*/
package com.swingmall.cart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.swingmall.main.Page;
import com.swingmall.main.ShopMain;

public class Cart extends Page{

	JPanel bt_container;	// 버튼을 묶어줄 컨테이너
	JButton bt_pay;	// 결제단계로가기
	JButton bt_del;	// 장바구니 비우기
	
	// 장바구니 역할을 컬렉션 프레임 웍 객체를 선언
	Map<Integer,CartVO> cartList;
	JPanel p_content;		// cart에 직접붙이지 말고, 아이템들을 붙일 컨테이너 준비한다.
		
	public Cart(ShopMain shopMain) {
		super(shopMain);

		cartList = new HashMap<Integer,CartVO>();
		
		bt_container = new JPanel();
		bt_pay = new JButton("결제하기");
		bt_del = new JButton("장바구니 비우기");
		
		// 스타일
		bt_container.setPreferredSize(new Dimension(ShopMain.WIDTH,100));
		bt_container.setBackground(Color.CYAN);
		
		getCartList();
		
		bt_container.add(bt_pay);
		bt_container.add(bt_del);
		add(bt_container,BorderLayout.SOUTH);
	}
	// 2) 장바구니 에 넣기
	// CartVO 는 상속을 받아 더 많은 정보를 가지고 있다.
	public void addCart(CartVO vo) {// 상품 1건을 장바구니에 추가하기
		cartList.put(vo.getProduct_id(), vo);	// key와 value 을 저장
	}
	
	// 장바구니 삭제하기
	public void removeCart(int product_id) { // 상품 1건을 장바구니에서 제거하기
		cartList.remove(product_id);	// 해당상품의 프라이머리키
	}
	
	// 장바구니 비우기
	public void removeAll() {// 모든 상품을 장바구니에서 제거
		
	}
	
	// 장바구니 변경
	public void updateCart(CartVO vo) {
		// 해시맵에 들어있는 객체 중 해당 객체를 찾아내어, vo 교체!!
		CartVO obj =cartList.get(vo.getProduct_id());	// 검색 키값을 찾아낸다. 
		obj = vo;	// 기존 해시맵이 가지고 있던 vo를 찾아내어 주소 변경!
	}
	
	// 1 -> 2) 장바구니 목록 출력하기
	public void getCartList() {
		Set<Integer> set = cartList.keySet();	// key들을 set으로 반환받는다. 일렬로 먼저 하는게 아니라 키값을 먼저 가져 와야 함.
		Iterator<Integer> it = set.iterator();	// 순서를 가지게 된다.
		
		// add() 하기 전에 기존에 붙어있던 모든 컴포넌트는 제거
		int count = 0;
		if(p_content!=null) {
			//this.자식요소들 없애기();
			this.remove(p_content);
			this.revalidate();
 			this.updateUI();
			this.repaint();
		}
		
		// 동적으로 새로 생성
		p_content = new JPanel();
		p_content.setPreferredSize(new Dimension(ShopMain.WIDTH-350,600));
		
		while(it.hasNext()) {
			int key =it.next();	// 요소 추출
			CartVO vo = cartList.get(key);
			
			// 디자인을 표현하는 CartItem에 CartVO 의 정보를 채워넣자!
			CartItem item = new CartItem(vo);
			
			item.bt_del.addActionListener((e)->{
				if(JOptionPane.showConfirmDialog(Cart.this, "장바구니에서 삭제하시겠어요?")==JOptionPane.OK_OPTION) {
					removeCart(vo.getProduct_id());	// 지금 내가 선택한 아이템의 pk				
					getCartList();
				}
			});
			item.bt_update.addActionListener((e)->{
				if(JOptionPane.showConfirmDialog(Cart.this, "장바구니를 수정하시겠어요?")==JOptionPane.OK_OPTION) {
					int ea = Integer.parseInt(item.t_ea.getText());	// 수정한 갯수!
					vo.setEa(ea);  // 변경된 갯수를 vo에 반영한 후에 전달..
					updateCart(vo);				
					getCartList();
				}
			});
			
			p_content.add(item);
			count++;
		}
		add(p_content);
		this.updateUI();
		
	}
	
}
