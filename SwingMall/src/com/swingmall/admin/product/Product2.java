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
/*��ǰ���� ������*/
public class Product2 extends Page{

	JPanel p_west;
	JPanel p_center;
	JTree tree;
	JTable table;
	JScrollPane s1,s2;
	JButton bt_regist;
	
	// ����, ���� ī�װ��� ���� �迭�� �־�� �Ѵ�.
	ArrayList<String> topList;
	ArrayList<ArrayList> subList = new ArrayList<>();
	
	// table ǥ�� ���� Ŭ����
	ProductModel model;
	
	public Product2(AdminMain adminMain) {
		super(adminMain);

		getTopList();	// �α��� ���ڸ���
		for(String name : topList) {
			getSubList(name);	// ������ �ݺ��ϸ鼭 ������ ��������
		}
		
		// Ʈ�� �θ� �ֻ��� ��� �����ϱ�
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("��ǰ���");
		for(int i =0;i<topList.size();i++) {
			// ���� ī�װ�
			top.add(getCreatedNode(topList.get(i),subList.get(i))); // ��ǰ��� - �θ� - �ڽ�
		}
		
		p_west = new JPanel();
		p_center = new JPanel();
		tree = new JTree();			// �ֻ��� ��带 ���� ����..
		table = new JTable(model = new ProductModel());
		s1 =  new JScrollPane(tree);
		s2 =  new JScrollPane(table);
		bt_regist = new JButton("����ϱ�");
		
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

	// ����
	public void getTopList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from topcategory";
		try {
			pstmt = getAdminMain().getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			topList = new ArrayList<>();	// �迭�� �޸𸮿� �ø���.
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
			
			ArrayList list = new ArrayList(); // ������ ���� �迭
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
	// ��ǰ�� �⺻���� �����ͼ� table�� ���̰� �ϱ�
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
			
			// ��Ÿ���� �̿� column ArrayList�� ä����.
			ResultSetMetaData meta = rs.getMetaData();
			
			ArrayList<String> columnNames = new ArrayList<>();
			for(int i=1;i<=meta.getColumnCount();i++) {// �ȿ� ���μ� �����ִ� �÷��� ��
				String colName = meta.getColumnName(i);
				columnNames.add(colName);
			}
			// ������ �÷� �Ʒ��� ���ڵ�!!!!!!
			// rs �� ���ڵ带 ProductModel �� record ArrayList�� ä����. = ���� ������
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
			// ���̺��� ������ �ִ� ��
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
	// Ʈ�� ��� ����
	public DefaultMutableTreeNode getCreatedNode(String parentName,ArrayList childName) {
		// �θ� ������ �ڽ��� ����..
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentName);
		for(int i=0;i<childName.size();i++) {
			parent.add(new DefaultMutableTreeNode(childName.get(i)));
		}// �ڽ��� �θ� ���..		
		return parent;
	}
	
}
