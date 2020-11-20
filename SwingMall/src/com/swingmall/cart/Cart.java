/*
 	��ٱ��� �������� �����Ѵ�.
 	
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

	JPanel bt_container;	// ��ư�� ������ �����̳�
	JButton bt_pay;	// �����ܰ�ΰ���
	JButton bt_del;	// ��ٱ��� ����
	
	// ��ٱ��� ������ �÷��� ������ �� ��ü�� ����
	Map<Integer,CartVO> cartList;
	JPanel p_content;		// cart�� ���������� ����, �����۵��� ���� �����̳� �غ��Ѵ�.
		
	public Cart(ShopMain shopMain) {
		super(shopMain);

		cartList = new HashMap<Integer,CartVO>();
		
		bt_container = new JPanel();
		bt_pay = new JButton("�����ϱ�");
		bt_del = new JButton("��ٱ��� ����");
		
		// ��Ÿ��
		bt_container.setPreferredSize(new Dimension(ShopMain.WIDTH,100));
		bt_container.setBackground(Color.CYAN);
		
		getCartList();
		
		bt_container.add(bt_pay);
		bt_container.add(bt_del);
		add(bt_container,BorderLayout.SOUTH);
	}
	// 2) ��ٱ��� �� �ֱ�
	// CartVO �� ����� �޾� �� ���� ������ ������ �ִ�.
	public void addCart(CartVO vo) {// ��ǰ 1���� ��ٱ��Ͽ� �߰��ϱ�
		cartList.put(vo.getProduct_id(), vo);	// key�� value �� ����
	}
	
	// ��ٱ��� �����ϱ�
	public void removeCart(int product_id) { // ��ǰ 1���� ��ٱ��Ͽ��� �����ϱ�
		cartList.remove(product_id);	// �ش��ǰ�� �����̸Ӹ�Ű
	}
	
	// ��ٱ��� ����
	public void removeAll() {// ��� ��ǰ�� ��ٱ��Ͽ��� ����
		
	}
	
	// ��ٱ��� ����
	public void updateCart(CartVO vo) {
		// �ؽøʿ� ����ִ� ��ü �� �ش� ��ü�� ã�Ƴ���, vo ��ü!!
		CartVO obj =cartList.get(vo.getProduct_id());	// �˻� Ű���� ã�Ƴ���. 
		obj = vo;	// ���� �ؽø��� ������ �ִ� vo�� ã�Ƴ��� �ּ� ����!
	}
	
	// 1 -> 2) ��ٱ��� ��� ����ϱ�
	public void getCartList() {
		Set<Integer> set = cartList.keySet();	// key���� set���� ��ȯ�޴´�. �Ϸķ� ���� �ϴ°� �ƴ϶� Ű���� ���� ���� �;� ��.
		Iterator<Integer> it = set.iterator();	// ������ ������ �ȴ�.
		
		// add() �ϱ� ���� ������ �پ��ִ� ��� ������Ʈ�� ����
		int count = 0;
		if(p_content!=null) {
			//this.�ڽĿ�ҵ� ���ֱ�();
			this.remove(p_content);
			this.revalidate();
 			this.updateUI();
			this.repaint();
		}
		
		// �������� ���� ����
		p_content = new JPanel();
		p_content.setPreferredSize(new Dimension(ShopMain.WIDTH-350,600));
		
		while(it.hasNext()) {
			int key =it.next();	// ��� ����
			CartVO vo = cartList.get(key);
			
			// �������� ǥ���ϴ� CartItem�� CartVO �� ������ ä������!
			CartItem item = new CartItem(vo);
			
			item.bt_del.addActionListener((e)->{
				if(JOptionPane.showConfirmDialog(Cart.this, "��ٱ��Ͽ��� �����Ͻðھ��?")==JOptionPane.OK_OPTION) {
					removeCart(vo.getProduct_id());	// ���� ���� ������ �������� pk				
					getCartList();
				}
			});
			item.bt_update.addActionListener((e)->{
				if(JOptionPane.showConfirmDialog(Cart.this, "��ٱ��ϸ� �����Ͻðھ��?")==JOptionPane.OK_OPTION) {
					int ea = Integer.parseInt(item.t_ea.getText());	// ������ ����!
					vo.setEa(ea);  // ����� ������ vo�� �ݿ��� �Ŀ� ����..
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
