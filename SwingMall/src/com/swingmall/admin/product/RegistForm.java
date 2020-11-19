
/*상품등록 폼을 정의한다.(등록하기 버튼 구현)*/
package com.swingmall.admin.product;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.swingmall.admin.AdminMain;

public class RegistForm extends JPanel {
	Product product;

	JPanel p_container; // 그리드 적용 예정(7,2)
	String[] title = { "상위카테고리", "하위카테고리", "상품명", "브랜드", "가격", "이미지", "상세설명" };
	JLabel[] la_title = new JLabel[title.length];

	Choice ch_top; // 최상위,하위 카테고리
	Choice ch_sub;
	// 상품명, 브랜드, 가격, 이미지, 상세설명
	JTextField t_product_name;
	JTextField t_brand;
	JTextField t_price;
	JTextField t_filename;
	JTextField t_detail;
	JScrollPane s1; // 상세설명 스크롤
	JButton bt_regist;
	JButton bt_list;
	
	public RegistForm(Product product) {
		this.product = product;
		
		// 생성
		p_container = new JPanel();
		for (int i = 0; i < la_title.length; i++) {
			la_title[i] = new JLabel(title[i]);
		}

		ch_top = new Choice();
		ch_sub = new Choice();
		t_product_name = new JTextField();
		t_brand = new JTextField();
		t_price = new JTextField();
		t_filename = new JTextField();
		t_detail = new JTextField();
		s1 = new JScrollPane(t_detail);
		bt_regist = new JButton("등록");
		bt_list = new JButton("목록");
		
		// 6) 최상위 카테고리 채우기(DB연동 X, 기존 데이터 재사용하자)
		for(String name : product.topList) {
			ch_top.add(name);
		}
		
		// 스타일
		Dimension d = new Dimension(320, 25);

		p_container.setBackground(Color.WHITE);
		p_container.setPreferredSize(new Dimension(AdminMain.WIDTH - 500, AdminMain.HEIGHT - 400));
		for (int i = 0; i < title.length; i++) {
			la_title[i].setPreferredSize(d);
		}
		ch_top.setPreferredSize(d);
		ch_sub.setPreferredSize(d);
		t_product_name.setPreferredSize(d);
		t_brand.setPreferredSize(d);
		t_price.setPreferredSize(d);
		t_filename.setPreferredSize(d);
		t_detail.setPreferredSize(new Dimension(320, 300));
		bt_regist.setPreferredSize(new Dimension(300, 40));
		bt_list.setPreferredSize(new Dimension(300, 40));

		// 조립

		p_container.add(la_title[0]);
		p_container.add(ch_top);
		p_container.add(la_title[1]);
		p_container.add(ch_sub);
		p_container.add(la_title[2]);
		p_container.add(t_product_name);
		p_container.add(la_title[3]);
		p_container.add(t_brand);
		p_container.add(la_title[4]);
		p_container.add(t_price);
		p_container.add(la_title[5]);
		p_container.add(t_filename);
		p_container.add(la_title[6]);
		p_container.add(t_detail);
		p_container.add(s1);

		this.add(p_container);
		this.add(bt_regist);
		this.add(bt_list);

		// 5) 화면전환(페이지) + 7) 등록버튼 구현 추가
		bt_regist.addActionListener((e)->{
			if(regist()==0) {
				JOptionPane.showMessageDialog(RegistForm.this, "등록실패");
			}else {
				JOptionPane.showMessageDialog(RegistForm.this, "등록성공");
				product.getProductList(null);
			}
		});
		
		bt_list.addActionListener((e)->{// 목록으로 돌아가기
			product.addRemoveContent(this, product.p_center); // 내가 제거, 다시 돌아오기
		});
		
		ch_top.addItemListener((e)->{// 6) 하위카테고리 가져오기
			//System.out.println("나 바꿧어?");			
			// 선택한 번호를 알면? 몇번째를 넣을건지 알 수가 잇다.			
			getSubCategory(ch_top.getSelectedIndex());
		});
	}
	// 6) 지금 유저가 선택한 최상위 카테고리에 소속된 하위카테고리만 가져오기!
	public void getSubCategory(int index) {
		ArrayList<String>  list = product.subList.get(index);
		ch_sub.removeAll(); // 채워넣기 전에, 기존 아이템들은 삭제..
		for(String item: list) {
			ch_sub.add(item);
		}
	}
	// 7) 유저가 선택한 아이템으로 부터 pk를 가져오기 - subcategory
	public int getSubId(String name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int subcategory_id=0;
		String sql = "select * from subcategory where name=?";
		try {
			pstmt = product.getAdminMain().getCon().prepareStatement(sql);
			pstmt.setString(1, name); // 매개변수 인수로 전달된 아이템을 바인드 변수에 대입
			rs=pstmt.executeQuery();
			if(rs.next()) {
				subcategory_id = rs.getInt("subcategory_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			product.getAdminMain().getDbManager().close(pstmt, rs);
		}
		
		return subcategory_id;
	}
	
	public int regist() {// 7) 성공 실패를 알 수 있는 반환형 = int
		PreparedStatement pstmt = null;
		int result = 0;	// DML 수행이 성공했는지 여부를 알 수 있는 변수
		
		String sql = "insert into product(product_id,subcategory_id,product_name";
		sql += ",brand,price,filename,detail) values(seq_product.nextval,?,?,?,?,?,?)";
		
		try {
			pstmt=product.getAdminMain().getCon().prepareStatement(sql);
			// 선택한 아이템의 pk를 구하여 바인드 변수에 대입
			pstmt.setInt(1, getSubId(ch_sub.getSelectedItem()));
			pstmt.setString(2, t_product_name.getText());
			pstmt.setString(3, t_brand.getText());
			pstmt.setInt(4, Integer.parseInt(t_price.getText()));
			pstmt.setString(5, t_filename.getText()); // 파일 url
			pstmt.setString(6, t_detail.getText());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			product.getAdminMain().getDbManager().close(pstmt, null);
		}
		
		return result;
	}
}
