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

	JPanel p_west;			// tree ����(����)
	JPanel p_center;
	JTree tree;
	JTable table;
	JScrollPane s1,s2;
	JButton bt_regist;

	ArrayList<String> topList;
	ArrayList<ArrayList> subList = new ArrayList<>();	// �ֻ����� ���������� ����� ����ī�װ�
	
	ProductModel model;
	RegistForm registForm;
	
	public Product(AdminMain adminMain) {
		super(adminMain);
		
		// 2) �α��� ���� ���� �������� - ī�װ� ��������		
		getTopList();	// top,down,accessay,shoes
		for(String name : topList) {
			getSubList(name);
		}
		
		// 2) '��ǰ���' �̶�� ������ �ֻ��� ��� �����ϱ�
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("��ǰ���");
		for(int i=0;i<topList.size();i++) {
			top.add(getCreatedNode(topList.get(i),subList.get(i)));			
		}
		
		
		// ����
		p_west = new JPanel();
		p_center = new JPanel();
		tree = new JTree(top);			// �ֻ��� ��带 ���� ����..
		table = new JTable(model = new ProductModel());
		s1 =  new JScrollPane(tree);
		s2 =  new JScrollPane(table);
		bt_regist = new JButton("����ϱ�");
		// 4) ����� ����(��Ϲ�ư)
		registForm = new RegistForm(this);
				
		// ��Ÿ��
		s1.setPreferredSize(new Dimension(200,AdminMain.HEIGHT-100));
		p_west.setBackground(Color.WHITE);
		s2.setPreferredSize(new Dimension(AdminMain.WIDTH-200, AdminMain.HEIGHT-200));
		
		// ����
		setLayout(new BorderLayout());
		
		p_west.add(s1);				// ���� �гο� Ʈ�� ��ũ�� ����
		p_center.add(s2);				// ���� �гο� ���̺� ��ũ�� ����
		p_center.add(bt_regist);	// ���� �гο� ��ư ����
		
		add(p_west,BorderLayout.WEST);
		//add(p_center);
		add(registForm);
		
		
		getProductList(null);
		// 3) tree�� �̺�Ʈ�� ������ ���� ex. tree ����� Ŭ������ ��..
		tree.addTreeSelectionListener((e)->{
			DefaultMutableTreeNode selectedNode=(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
			
			if(selectedNode.toString().equals("��ǰ���")) {
				getProductList(null);//��� ��ǰ ��������
			}else {
				getProductList(selectedNode.toString());//��� ��ǰ ��������				
			}
		});
		
		// 5) ������� ��������
		bt_regist.addActionListener((e)->{
			addRemoveContent(p_center, registForm);
		});
		
	}
	// 2) ����, ���� ī�װ� ��������
	public void getTopList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		String sql = "select * from topcategory";
		try {
			// private �� ������ getter �̿� - Page���� ����
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
			while(rs.next()) {// �������� ��´�
				list.add(rs.getString("name"));
			}
			// ������ �������� �ѱ���.
			subList.add(list);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getAdminMain().getDbManager().close(pstmt, rs);
		}
		return subList;
	}
	
	// 3) ��ǰ ��������
	public void getProductList(String name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// �ȳѾ���� ��� ��ǰ ��������
		String sql =null;
		if(name==null) {// name �Ű������� null�̸� ��� ��ǰ ��������
			sql = "select * from product";			
		}else {// name ���� �Ѿ���� ���� ���� ����
			sql ="select * from product where subcategory_id=(select subcategory_id from subcategory where name='"+name+"')";

		}
		try {
			pstmt = getAdminMain().getCon().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			// ��Ÿ������ �̿��Ͽ� ProductModel�� column ArrayList�� ä����.
			ResultSetMetaData meta = rs.getMetaData();			
			ArrayList<String> columnNames = new ArrayList();
			for(int i=1;i<=meta.getColumnCount();i++) {					
				String colName = meta.getColumnName(i);
				columnNames.add(colName);
			}
			
			// rs �� ���ڵ带 ProductModel �� record ArrayList�� ä����.
			ArrayList<ProductVO> productList = new ArrayList<>();
			while(rs.next()) {
				ProductVO vo = new ProductVO();	// ����ִ� vo�� �����ؼ� rs�� ������ ä�� �ֱ� ����
				
				vo.setProduct_id(rs.getInt("product_id"));
				vo.setSubcategory_id(rs.getInt("subcategory_id"));
				vo.setProduct_name(rs.getString("product_name"));
				vo.setBrand(rs.getString("brand"));
				vo.setPrice(rs.getInt("price"));
				vo.setFilename(rs.getString("filename"));
				vo.setDetail(rs.getString("detail"));
				productList.add(vo);	// vo�� ArrayList�� �߰�����.
			}
			model = new ProductModel();
			model.column=columnNames;	// �÷����� ����
			model.record=productList;			// ���ڵ����� ����
			table.setModel(model);
			table.updateUI();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			getAdminMain().getDbManager().close(pstmt, rs);
		}
	}
	
	// 1) Ʈ�� ��� �����ϱ�
	public DefaultMutableTreeNode getCreatedNode(String parentName,ArrayList childName) {
		// �θ��� �����ϱ�
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentName);
		
		// �Ѱܹ��� �Ű������� ArrayList ��ŭ �ݺ��Ͽ� �θ��忡 �ڽĳ�� ����!		
		for(int i=0;i<childName.size();i++) {
			parent.add(new DefaultMutableTreeNode(childName.get(i)));
		}		
		return parent;
	} 
	
	// 5) ������ ����Ʈ�� ������ ����Ʈ�� �����ϴ¸޼���
	public void addRemoveContent(Component removeObj, Component addObj) {
		this.remove(removeObj);// ���ŵ� �� - �μ��� ����
		this.add(addObj);// ������ ��
		((JPanel)addObj).updateUI();
	}
}
