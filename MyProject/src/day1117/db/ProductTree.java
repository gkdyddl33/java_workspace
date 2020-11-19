package day1117.db;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class ProductTree extends JFrame{

	// ����
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "user1104";
	String password = "user1104";
	Connection con;
	
	JTree tree;
	JScrollPane scroll;
	
	// * �迭�� �ִٸ� Ʈ�� ������ �޼��尡 �˾Ƽ� ���ִ� �ڵ�..
	String[] category= {"����","����","�Ǽ��縮","�Ź�"};	// ���� ī�װ� �迭
	String[][] product = {
			{"Ƽ����","����","��Ʈ","�����"},
			{"û����","�ݹ���","�����","�ֹ���"},
			{"�Ͱ���","�����","����","����"},
			{"����","�ȭ","����Ŀ��","����"}
	};
	// top, down, accessary, shoes
	ArrayList<String> topCategory= new ArrayList<>();	// ����ī�װ�
	ArrayList<ArrayList> subCategory= new ArrayList<>();	// ����ī�װ�
	
	public ProductTree() {
		// 2) DB �����Ͽ� �迭���� �����͸� ���� DB  �����ͷ� ��������!
		getTopCategory();
		// ��� ������ �ܺ� �޼���κ��� ��������!
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("��ǰ����");
		// ����ī���� �޼��带 ����ī�װ� ����ŭ ȣ���ϸ鼭 2���� ArrayList������ ��������
		for(int i=0;i<topCategory.size();i++) {
			String name = topCategory.get(i); // top, down, accessary, shoes
//			ArrayList subList = (ArrayList)getSubCategory(name);
//			subCategory.add(subList);
			top.add(getCreatedNode(name, subCategory.get(i)));
		}
		
//		for(int i=0;i<category.length;i++) {
//			top.add(getCreatedNode(category[i], product[i]));	// �Ѱ��� ��			
//		}
		
		tree = new JTree(top);	//�ֻ�������!
		scroll = new JScrollPane(tree);
		
		add(scroll);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				disconnect();
				System.exit(0);
			}
		});
		
		// ȣ��
		connect();
		
		setSize(400,500);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	// �� �޼��带 ȣ���ϴ� �ڰ� �迭�� �ѱ�� �迭�� ���̸�ŭ ��带 �����Ͽ� ��ȯ���� ����
	// �� ���� ����!!
	public DefaultMutableTreeNode getCreatedNode(String parentName,String[] childName) {// �θ� ������ �ڽ��� ������
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentName);	// �θ����
		// �ڽ��� ����ŭ ��� ����� �θ� ����!
		if(childName != null) {
			for(int i=0;i<childName.length;i++) {
				parent.add(new DefaultMutableTreeNode(childName[i]));
			}			
		}
		
		return parent;
	}
	
	// ---- �� �޼���� �迭�� ����ȭ �Ǿ� �־� ó���ϴ� ��������, �� �޼���� �̸��� ������ List������ ó��..
	public DefaultMutableTreeNode getCreatedNode(String parentName,ArrayList childName) {// �θ� ������ �ڽ��� ������
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentName);	// �θ����
		// �ڽ��� ����ŭ ��� ����� �θ� ����!
		if(childName != null) {
			for(int i=0;i<childName.size();i++) {
				parent.add(new DefaultMutableTreeNode(childName.get(i)));
			}			
		}
		
		return parent;
	}
	 public void connect() {
		 try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			if(con == null) {
				JOptionPane.showMessageDialog(this, user+"�� ���ӿ� �����Ͽ����ϴ�.");
			}else {
				this.setTitle(user+" �α��� ��");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		}
		 
	 }
	 public void disconnect() {
		 if(con != null) {
			 try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 }
	 }
	// ����, ���� ī�װ�
	public void getTopCategory() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from topcategory";
		try {
			pstmt = con.prepareStatement(sql);
			rs= pstmt.executeQuery();
			
			// �迭�� �ݵ�� �� ũ�⸦ ����ؾ� �Ѵ�..
			// ���� �迭���ٴ� �÷��� ��ü �� List�迭�� ��õ�Ѵ�.
			while(rs.next()) {
				topCategory.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs !=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public List getSubCategory(String name) { // top, down, accessay, shoes
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList subList = new ArrayList();
		String sql = "select * from subcategory where topcategory_id=(select topcategory_id from topcategory where name=?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name); // ���ε� ���� ����
			
			rs= pstmt.executeQuery();			
			while(rs.next()) {
				subList.add(rs.getString("name")); // �� �׸��� ��´�.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs !=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return subList;	// ȣ���ϴ� �ڰ� ����ī�װ� ��� ������ �� �ֵ���..
	}
	public static void main(String[] args) {
		new ProductTree();
	}
}
