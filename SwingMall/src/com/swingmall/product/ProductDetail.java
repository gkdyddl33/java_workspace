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

	public JPanel p_content;	// 상세내용을 담게 될 패널
	public JPanel p_can;		// 큰 상품이미지 그려질 패널
	JPanel p_option;		// 옵션 선택 영역
	
	JLabel la_brand;	// 상품 브랜드
	JLabel la_product_name;	// 상품명 라벨
	JLabel la_price;	// 상품가격
	
	Choice ch_color;
	Choice ch_size;
	
	JButton bt_buy;	// 구매하기
	JButton bt_cart;	// 장바구니	
	
	private ProductVO vo;
	private Image img;
	
	// 상세페이지 호출 시 상품 1개의 정보는 VO에 그려질 이미지는 img로 전달받자.
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
		bt_buy = new JButton("구매하기");
		bt_cart = new JButton("장바구니");
		
		// 스타일
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
		
		// 조립
		p_content.setLayout(new GridLayout(1,2));
		// p_content 구성하기
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
		
		// 1)장바구니 페이지 열기
		bt_cart.addActionListener((e)->{
			// 2..) 장바구니에 상품 추가하기
			registCart();
			
			// 2) 장바구니에 정보가 담겼다고 알려주고, 장바구니 이동 여부를 확인해야 함
			int ans =JOptionPane.showConfirmDialog(ProductDetail.this, "장바구니에 상품이 담겼습니다.\n장바구니로 이동하시겠어요?");
			if(ans==JOptionPane.OK_OPTION) {
				getShopMain().showPage(ShopMain.CART);	//1)	
			}
				
		});
	}

	// 상세페이지가 보여질 때 데이터를 채워넣는 메서드(생성자에서 하면 디자인 처리에 타이밍적인 제한이 많다)
	public void init(ProductVO vo,Image img) {
		this.vo=vo;		// 멤버변수에 현재 보고있는 상품 vo를 주입!! 안그러면 null됨
		la_brand.setText(vo.getBrand());
		la_product_name.setText(vo.getProduct_name());
		la_price.setText(Integer.toString(vo.getPrice()));
		this.img=img;
		this.img = this.img.getScaledInstance(500, 550, Image.SCALE_SMOOTH);
				
		p_can.repaint();
	}
	
	// 2..) 장바구니에 등록 - DB로 보관하지 않고, 오직 메모리상으로 저장할 예정
	public void registCart() {
		Cart cartPage = (Cart)getShopMain().getPage()[ShopMain.CART]; // 장바구니 페이지에 접근
		
		CartVO cartVo = new CartVO();	// vo 생성
		cartVo.setProduct_id(vo.getProduct_id());	// 해당클래스에서 바라보고 있는 productVO에서 가져오기
		cartVo.setBrand(vo.getBrand());
		cartVo.setProduct_name(vo.getProduct_name());
		cartVo.setPrice(vo.getPrice());
		cartVo.setFilename(vo.getFilename());
		cartVo.setDetail(vo.getDetail());
		
		cartVo.setColor(ch_color.getSelectedItem());	// 선택한 색상
		cartVo.setSize(ch_size.getSelectedItem());		// 선택한 사이즈
		cartVo.setEa(1);// 장바구니에 담을때는 기본이 1개임...
		
		cartPage.addCart(cartVo); 	// 장바구니에 상품 1건 추가하기 - Cart.java
		cartPage.getCartList(); 	// 장바구니 목록 구성하기		
		
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
