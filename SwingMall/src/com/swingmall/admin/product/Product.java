package com.swingmall.admin.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Connection;
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
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.swingmall.admin.AdminMain;
import com.swingmall.admin.Page;
import com.swingmall.admin.board.Board;


public class Product extends Page{

	JPanel p_west;			// tree 영역(서쪽)
	JPanel p_center;
	JTree tree;
	JTable table;
	JScrollPane s1,s2;
	JButton bt_regist;

	ArrayList<String> topList;
	ArrayList<ArrayList> subList = new ArrayList<>();	// 최상위를 선택했을때 생기는 하위카테고리
	
	ProductModel model;
	RegistForm registForm;
	
	public Product(AdminMain adminMain) {
		super(adminMain);
		
		// 2) 로그인 하자 마자 가져오기 - 카테고리 가져오기		
		getTopList();	// top,down,accessay,shoes
		for(String name : topList) {
			getSubList(name);
		}
		
		// 2) '상품목록' 이라는 제목의 최상위 노드 생성하기
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("상품목록");
		for(int i=0;i<topList.size();i++) {
			top.add(getCreatedNode(topList.get(i),subList.get(i)));			
		}
		
		
		// 생성
		p_west = new JPanel();
		p_center = new JPanel();
		tree = new JTree(top);			// 최상위 노드를 넣을 예정..
		table = new JTable(model = new ProductModel());
		s1 =  new JScrollPane(tree);
		s2 =  new JScrollPane(table);
		bt_regist = new JButton("등록하기");
		// 4) 등록폼 생성(등록버튼)
		registForm = new RegistForm(this);
				
		// 스타일
		s1.setPreferredSize(new Dimension(200,AdminMain.HEIGHT-100));
		p_west.setBackground(Color.WHITE);
		s2.setPreferredSize(new Dimension(AdminMain.WIDTH-200, AdminMain.HEIGHT-200));
		
		// 조립
		setLayout(new BorderLayout());
		
		p_west.add(s1);				// 서쪽 패널에 트리 스크롤 부착
		p_center.add(s2);				// 센터 패널에 테이블 스크롤 부착
		p_center.add(bt_regist);	// 센터 패널에 버튼 부착
		
		add(p_west,BorderLayout.WEST);
		//add(p_center);
		add(registForm);
		
		
		getProductList(null);
		// 3) tree는 이벤트가 별도로 지원 ex. tree 목록을 클릭했을 때..
		tree.addTreeSelectionListener((e)->{
			DefaultMutableTreeNode selectedNode=(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
			
			if(selectedNode.toString().equals("상품목록")) {
				getProductList(null);//모든 상품 가져오기
			}else {
				getProductList(selectedNode.toString());//모든 상품 가져오기				
			}
		});
		
		// 5) 사라지고 보여지고
		bt_regist.addActionListener((e)->{
			addRemoveContent(p_center, registForm);
		});
		
	}
	// 2) 상위, 하위 카테고리 가져오기
	public void getTopList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		String sql = "select * from topcategory";
		try {
			// private 로 선언후 getter 이용 - Page에도 선언
			pstmt = getAdminMain().getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			topList = new ArrayList();
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
				
		String sql = "select * from subcategory where topcategory_id =(";
		sql += " select topcategory_id from topcategory where name='"+name+"')";
		
		try {
			pstmt =getAdminMain().getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			ArrayList list = new ArrayList();
			while(rs.next()) {// 일차원에 담는다
				list.add(rs.getString("name"));
			}
			// 담은걸 이차원에 넘기자.
			subList.add(list);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getAdminMain().getDbManager().close(pstmt, rs);
		}
		return subList;
	}
	
	// 3) 상품 가져오기
	public void getProductList(String name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 안넘어오면 모든 상품 가져오기
		String sql =null;
		if(name==null) {// name 매개변수가 null이면 모든 상품 가져오기
			sql = "select * from product";			
		}else {// name 값이 넘어오면 조건 쿼리 수행
			sql ="select * from product where subcategory_id=(select subcategory_id from subcategory where name='"+name+"')";

		}
		try {
			pstmt = getAdminMain().getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			// 메타정보를 이용하여 ProductModel의 column ArrayList를 채우자.
			ResultSetMetaData meta = rs.getMetaData();			
			ArrayList<String> columnNames = new ArrayList();
			for(int i=1;i<=meta.getColumnCount();i++) {					
				String colName = meta.getColumnName(i);
				columnNames.add(colName);
			}
			
			// rs 의 레코드를 ProductModel 의 record ArrayList에 채우자.
			ArrayList<ProductVO> productList = new ArrayList<>();
			while(rs.next()) {
				ProductVO vo = new ProductVO();	// 비어있는 vo를 생성해서 rs에 값들을 채워 넣기 위해
				
				vo.setProduct_id(rs.getInt("product_id"));
				vo.setSubcategory_id(rs.getInt("subcategory_id"));
				vo.setProduct_name(rs.getString("product_name"));
				vo.setBrand(rs.getString("brand"));
				vo.setPrice(rs.getInt("price"));
				vo.setFilename(rs.getString("filename"));
				vo.setDetail(rs.getString("detail"));
				productList.add(vo);	// vo를 ArrayList에 추가하자.
			}
			model = new ProductModel();
			model.column=columnNames;	// 컬럼정보 대입
			model.record=productList;			// 레코드정보 대입
			table.setModel(model);
			table.updateUI();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getAdminMain().getDbManager().close(pstmt, rs);
		}
	}
	
	// 1) 트리 노드 생성하기
	public DefaultMutableTreeNode getCreatedNode(String parentName,ArrayList childName) {
		// 부모노드 생성하기
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentName);
		
		// 넘겨받은 매개변수인 ArrayList 만큼 반복하여 부모노드에 자식노드 부착!		
		for(int i=0;i<childName.size();i++) {
			parent.add(new DefaultMutableTreeNode(childName.get(i)));
		}		
		return parent;
	} 
	
	// 5) 보여질 컨텐트와 가려질 컨텐트를 제어하는메서드
	public void addRemoveContent(Component removeObj, Component addObj) {
		this.remove(removeObj);// 제거될 자 - 인수로 받자
		this.add(addObj);// 부착될 자
		((JPanel)addObj).updateUI();
	}
}
