package com.swingmall.admin.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import com.swingmall.admin.AdminMain;
import com.swingmall.admin.Page;
/*상품관리 페이지*/
public class Product2 extends Page{

	JPanel p_west;
	JPanel p_center;
	JTree tree;
	JTable table;
	JScrollPane s1,s2;
	JButton bt_regist;
	
	// 상위, 하위 카테고리를 담을 배열도 있어야 한다.
	ArrayList<String> topList;
	ArrayList<ArrayList> subList = new ArrayList<>();
	
	// table 표를 담을 클래스
	ProductModel model;
	
	public Product2(AdminMain adminMain) {
		super(adminMain);

		getTopList();	// 로그인 하자마자
		for(String name : topList) {
			getSubList(name);	// 상위를 반복하면서 하위를 가져오기
		}
		
		// 트리 부모 최상위 노드 생성하기
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("상품목록");
		for(int i =0;i<topList.size();i++) {
			// 상위 카테고리
			top.add(getCreatedNode(topList.get(i),subList.get(i))); // 상품목록 - 부모 - 자식
		}
		
		p_west = new JPanel();
		p_center = new JPanel();
		tree = new JTree();			// 최상위 노드를 넣을 예정..
		table = new JTable(model = new ProductModel());
		s1 =  new JScrollPane(tree);
		s2 =  new JScrollPane(table);
		bt_regist = new JButton("등록하기");
		
		s1.setPreferredSize(new Dimension(200,AdminMain.HEIGHT-100));
		p_west.setBackground(Color.WHITE);
		s2.setPreferredSize(new Dimension(AdminMain.WIDTH-200, AdminMain.HEIGHT-200));
		
		setLayout(new BorderLayout());
		p_west.add(s1);
		p_center.add(s2);
		p_center.add(bt_regist);
		
		add(p_west,BorderLayout.WEST);
		add(p_center);
	}

	// 상위
	public void getTopList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from topcategory";
		try {
			pstmt = getAdminMain().getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			topList = new ArrayList<>();	// 배열을 메모리에 올리다.
			while(rs.next()) {
				topList.add(rs.getString("name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getAdminMain().getDbManager().close(pstmt, rs);
		}
		
	}
	public List getSubList(String name) {
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		
		String sql = "select * from subcategory where topcategory_id=(";
		sql += " select topcategory_id from topcategory where name='"+name+"')";
		
		try {
			pstmt =getAdminMain().getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			ArrayList list = new ArrayList(); // 내용을 담을 배열
			while(rs.next()) {
				list.add(rs.getString("name"));
			}
			subList.add(list);			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			getAdminMain().getDbManager().close(pstmt, rs);
		}
		return subList;
	}
	// 상품을 기본으로 가져와서 table에 보이게 하기
	public void getProductList(String name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = null;
		if(name==null) {
			sql = "select * from product";
		}else {
			sql ="select * from product where subcategory_id=(select subcategory_id from subcategory where name='"+name+"')";
		}
		
		try {
			pstmt = getAdminMain().getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			// 메타정보 이용 column ArrayList를 채우자.
			ResultSetMetaData meta = rs.getMetaData();
			
			ArrayList<String> columnNames = new ArrayList<>();
			for(int i=1;i<=meta.getColumnCount();i++) {// 안에 감싸서 숨어있는 컬럼의 수
				String colName = meta.getColumnName(i);
				columnNames.add(colName);
			}
			// 위에는 컬럼 아래는 레코드!!!!!!
			// rs 의 레코드를 ProductModel 의 record ArrayList에 채우자. = 행을 만들자
			ArrayList<ProductVO> productList = new ArrayList<>();
			while(rs.next()) {
				ProductVO vo = new ProductVO();
				vo.setProduct_id(rs.getInt("product_id"));
				vo.setSubcategory_id(rs.getInt("subcategory_id"));
				vo.setProduct_name(rs.getString("product_name"));
				vo.setBrand(rs.getString("brand"));
				vo.setPrice(rs.getInt("price"));
				vo.setFilename(rs.getString("filename"));
				vo.setDetail(rs.getString("detail"));
				productList.add(vo);
			}
			// 테이블을 가지고 있는 모델
			model = new ProductModel();
			model.column=columnNames;
			model.record=productList;
			table.setModel(model);
			table.updateUI();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getAdminMain().getDbManager().close(pstmt, rs);
		}
	}
	// 트리 노드 생성
	public DefaultMutableTreeNode getCreatedNode(String parentName,ArrayList childName) {
		// 부모를 만들어야 자식이 생성..
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentName);
		for(int i=0;i<childName.size();i++) {
			parent.add(new DefaultMutableTreeNode(childName.get(i)));
		}// 자식을 부모에 담다..		
		return parent;
	}
	
}
