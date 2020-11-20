package com.swingmall.home;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.swingmall.admin.product.ProductVO;
import com.swingmall.main.Page;
import com.swingmall.main.ShopMain;
import com.swingmall.product.ProductDetail;

public class Home extends Page{
	
	JPanel p_content;	// 상품 리스트를 담게 될 패널, 추후 상세보기로 전환시 이 패널 자체를 들어내버릴꺼임
	ArrayList<ProductItem> itemList;	// 생성된 상품 아이템들을 담게 될 리스트..
										// productItem클래스 내에서 이벤트를 구현하면 너무 많은 정보가 넘어간다.
										// 페이지도 아니므로 호율성 저하
	
	public Home(ShopMain shopMain) {
		super(shopMain);
		p_content = new JPanel();
		
		// 스타일
		p_content.setPreferredSize(new Dimension(ShopMain.WIDTH-40,ShopMain.HEIGHT-150));
				
		add(p_content);
		
		getProductList();
		p_content.updateUI();
		
		// 생성된 아이템들에 대해서 마우스 리스너 연결하기
		for(ProductItem item : itemList) {
			item.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					//System.out.println("나 선택했어?");
					ProductDetail productDetail = (ProductDetail) getShopMain().getPage()[ShopMain.PRODUCT_DETAIL];
					productDetail.init(item.vo,item.img);// item 이 productDetail을 보유하고 잇어서 productVo전달
																		// 정보 + 이미지 나오게 하기
					productDetail.p_can.repaint();
					productDetail.updateUI();
										
					getShopMain().showPage(ShopMain.PRODUCT_DETAIL);// 보여주고 싶은 페이지	 
					
				}
			});
		}
	}
	// 2) 모든 상품 가져오기 - productItem으로 구현해서 home에 띄우기 한 후...
	public void getProductList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		String sql = "select * from product";	
		
		try {
			pstmt = getShopMain().getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			itemList = new ArrayList<>();	// 상품 아이템 생성할때마다 리스트에 담기 위함
			
			while(rs.next()) {
				// vo 하나를 생성한 후, rs 의 데이터를 vo 옮긴다.
				ProductVO vo = new ProductVO();	// empty 상태
				vo.setProduct_id(rs.getInt("product_id"));
				vo.setProduct_name(rs.getString("product_name"));
				vo.setBrand(rs.getString("brand"));
				vo.setPrice(rs.getInt("price"));
				vo.setFilename(rs.getString("filename"));
				vo.setDetail(rs.getString("detail"));
				
				itemList.add(getCreateItem(vo)); // 완성된 것을 담기
				
				// 완성된 vo를 이용하여  createItem() 호출
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getShopMain().getDbManager().close(pstmt, rs);
		}
		
	}	
	
	// 1) 상품 아이템 카드 생성하기
	public ProductItem getCreateItem(ProductVO vo) {	// rs에 데이터를 담으면 알아서 크기는 여기서 조절해줌	
		ProductItem item = new ProductItem(this,vo,200, 180);	
		p_content.add(item);
		
		return item; // 생성후 반환
	}
}
