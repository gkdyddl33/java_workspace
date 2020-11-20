package com.swingmall.product;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.swingmall.admin.product.ProductVO;
import com.swingmall.cart.Cart;
import com.swingmall.cart.CartVO;
import com.swingmall.main.Page;
import com.swingmall.main.ShopMain;

public class ProductDetail extends Page{

	public JPanel p_content;	// �󼼳����� ��� �� �г�
	public JPanel p_can;		// ū ��ǰ�̹��� �׷��� �г�
	JPanel p_option;		// �ɼ� ���� ����
	
	JLabel la_brand;	// ��ǰ �귣��
	JLabel la_product_name;	// ��ǰ�� ��
	JLabel la_price;	// ��ǰ����
	
	Choice ch_color;
	Choice ch_size;
	
	JButton bt_buy;	// �����ϱ�
	JButton bt_cart;	// ��ٱ���	
	
	private ProductVO vo;
	private Image img;
	
	// �������� ȣ�� �� ��ǰ 1���� ������ VO�� �׷��� �̹����� img�� ���޹���.
	public ProductDetail(ShopMain shopMain) {
		super(shopMain);
		
		p_content = new JPanel();
		p_can = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, p_can);
			}
		};
		
		p_option = new JPanel();
		la_brand = new JLabel();
		la_product_name = new JLabel();
		la_price = new JLabel();
		
		ch_color = new Choice();
		ch_size = new Choice();
		bt_buy = new JButton("�����ϱ�");
		bt_cart = new JButton("��ٱ���");
		
		// ��Ÿ��
		ch_color.add("red");
		ch_color.add("black");
		ch_color.add("white");
		
		ch_size.add("S");
		ch_size.add("M");
		ch_size.add("L");
		
		p_content.setPreferredSize(new Dimension(ShopMain.WIDTH-100,ShopMain.HEIGHT-200));
//		p_content.setBackground(Color.YELLOW);
//		p_option.setPreferredSize(new Dimension((ShopMain.WIDTH-100)/2,ShopMain.HEIGHT-400));
//		p_option.setBackground(Color.PINK);
		
		Dimension d = new Dimension(new Dimension((ShopMain.WIDTH-100)/3,30));
		la_brand.setPreferredSize(d);
		la_product_name.setPreferredSize(d);
		la_price.setPreferredSize(d);		
		bt_buy.setPreferredSize(new Dimension(200,30));
		bt_cart.setPreferredSize(new Dimension(200,30));
		
		ch_color.setPreferredSize(new Dimension((ShopMain.WIDTH-100)/3,30));
		ch_size.setPreferredSize(new Dimension((ShopMain.WIDTH-100)/3,30));
		
		// ����
		p_content.setLayout(new GridLayout(1,2));
		// p_content �����ϱ�
		p_option.add(la_brand);
		p_option.add(la_product_name);
		p_option.add(la_price);
		p_option.add(ch_color);
		p_option.add(ch_size);
		p_option.add(bt_buy);
		p_option.add(bt_cart);
		
		p_content.add(p_can);
		p_content.add(p_option);
		
		add(p_content);
		
		// 1)��ٱ��� ������ ����
		bt_cart.addActionListener((e)->{
			// 2..) ��ٱ��Ͽ� ��ǰ �߰��ϱ�
			registCart();
			
			// 2) ��ٱ��Ͽ� ������ ���ٰ� �˷��ְ�, ��ٱ��� �̵� ���θ� Ȯ���ؾ� ��
			int ans =JOptionPane.showConfirmDialog(ProductDetail.this, "��ٱ��Ͽ� ��ǰ�� �����ϴ�.\n��ٱ��Ϸ� �̵��Ͻðھ��?");
			if(ans==JOptionPane.OK_OPTION) {
				getShopMain().showPage(ShopMain.CART);	//1)	
			}
				
		});
	}

	// ���������� ������ �� �����͸� ä���ִ� �޼���(�����ڿ��� �ϸ� ������ ó���� Ÿ�̹����� ������ ����)
	public void init(ProductVO vo,Image img) {
		this.vo=vo;		// ��������� ���� �����ִ� ��ǰ vo�� ����!! �ȱ׷��� null��
		la_brand.setText(vo.getBrand());
		la_product_name.setText(vo.getProduct_name());
		la_price.setText(Integer.toString(vo.getPrice()));
		this.img=img;
		this.img = this.img.getScaledInstance(500, 550, Image.SCALE_SMOOTH);
				
		p_can.repaint();
	}
	
	// 2..) ��ٱ��Ͽ� ��� - DB�� �������� �ʰ�, ���� �޸𸮻����� ������ ����
	public void registCart() {
		Cart cartPage = (Cart)getShopMain().getPage()[ShopMain.CART]; // ��ٱ��� �������� ����
		
		CartVO cartVo = new CartVO();	// vo ����
		cartVo.setProduct_id(vo.getProduct_id());	// �ش�Ŭ�������� �ٶ󺸰� �ִ� productVO���� ��������
		cartVo.setBrand(vo.getBrand());
		cartVo.setProduct_name(vo.getProduct_name());
		cartVo.setPrice(vo.getPrice());
		cartVo.setFilename(vo.getFilename());
		cartVo.setDetail(vo.getDetail());
		
		cartVo.setColor(ch_color.getSelectedItem());	// ������ ����
		cartVo.setSize(ch_size.getSelectedItem());		// ������ ������
		cartVo.setEa(1);// ��ٱ��Ͽ� �������� �⺻�� 1����...
		
		cartPage.addCart(cartVo); 	// ��ٱ��Ͽ� ��ǰ 1�� �߰��ϱ� - Cart.java
		cartPage.getCartList(); 	// ��ٱ��� ��� �����ϱ�		
		
	}
	
	public ProductVO getVo() {
		return vo;
	}

	public void setVo(ProductVO vo) {
		this.vo = vo;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
	
	
}
