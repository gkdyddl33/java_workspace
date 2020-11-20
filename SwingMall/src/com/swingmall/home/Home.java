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
	
	JPanel p_content;	// ��ǰ ����Ʈ�� ��� �� �г�, ���� �󼼺���� ��ȯ�� �� �г� ��ü�� ����������
	ArrayList<ProductItem> itemList;	// ������ ��ǰ �����۵��� ��� �� ����Ʈ..
										// productItemŬ���� ������ �̺�Ʈ�� �����ϸ� �ʹ� ���� ������ �Ѿ��.
										// �������� �ƴϹǷ� ȣ���� ����
	
	public Home(ShopMain shopMain) {
		super(shopMain);
		p_content = new JPanel();
		
		// ��Ÿ��
		p_content.setPreferredSize(new Dimension(ShopMain.WIDTH-40,ShopMain.HEIGHT-150));
				
		add(p_content);
		
		getProductList();
		p_content.updateUI();
		
		// ������ �����۵鿡 ���ؼ� ���콺 ������ �����ϱ�
		for(ProductItem item : itemList) {
			item.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					//System.out.println("�� �����߾�?");
					ProductDetail productDetail = (ProductDetail) getShopMain().getPage()[ShopMain.PRODUCT_DETAIL];
					productDetail.init(item.vo,item.img);// item �� productDetail�� �����ϰ� �վ productVo����
																		// ���� + �̹��� ������ �ϱ�
					productDetail.p_can.repaint();
					productDetail.updateUI();
										
					getShopMain().showPage(ShopMain.PRODUCT_DETAIL);// �����ְ� ���� ������	 
					
				}
			});
		}
	}
	// 2) ��� ��ǰ �������� - productItem���� �����ؼ� home�� ���� �� ��...
	public void getProductList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		String sql = "select * from product";	
		
		try {
			pstmt = getShopMain().getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			itemList = new ArrayList<>();	// ��ǰ ������ �����Ҷ����� ����Ʈ�� ��� ����
			
			while(rs.next()) {
				// vo �ϳ��� ������ ��, rs �� �����͸� vo �ű��.
				ProductVO vo = new ProductVO();	// empty ����
				vo.setProduct_id(rs.getInt("product_id"));
				vo.setProduct_name(rs.getString("product_name"));
				vo.setBrand(rs.getString("brand"));
				vo.setPrice(rs.getInt("price"));
				vo.setFilename(rs.getString("filename"));
				vo.setDetail(rs.getString("detail"));
				
				itemList.add(getCreateItem(vo)); // �ϼ��� ���� ���
				
				// �ϼ��� vo�� �̿��Ͽ�  createItem() ȣ��
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getShopMain().getDbManager().close(pstmt, rs);
		}
		
	}	
	
	// 1) ��ǰ ������ ī�� �����ϱ�
	public ProductItem getCreateItem(ProductVO vo) {	// rs�� �����͸� ������ �˾Ƽ� ũ��� ���⼭ ��������	
		ProductItem item = new ProductItem(this,vo,200, 180);	
		p_content.add(item);
		
		return item; // ������ ��ȯ
	}
}
