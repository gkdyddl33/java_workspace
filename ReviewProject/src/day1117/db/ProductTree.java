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
	
	String[] category= {"����","����","��������","�Ź�"};
	String[][] product={
			{"Ƽ����","����","��Ʈ","�����"},
			{"û����","�ݹ���","�����","�ֹ���"},
			{"�Ͱ���","�����","����","����"},
			{"����","�ȭ","����Ŀ��","����"}
	};
	
	ArrayList<String> topCategory = new ArrayList<>();
	ArrayList<ArrayList> subCategory = new ArrayList<>();
	
	public ProductTree() {
		connect();
		getTopCategory();
		
		for(int i=0;i<topCategory.size();i++) {
			String name = topCategory.get(i);
			ArrayList subList = (ArrayList) getSubCategory(name);
			subCategory.add(subList);
		}
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("��ǰ����");
		
		tree = new JTree(top);
		scroll = new JScrollPane(tree);
		
		for(int i=0;i<topCategory.size();i++) {
			String name = topCategory.get(i);
			top.add(getCreatedNode(name,subCategory.get(i)));
		}
		
		add(scroll);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				disConnect();
				System.exit(0);
			}
		});
		
		setSize(400,500);
		setVisible(true);		
		setLocationRelativeTo(null);
	}
	
	public void connect() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			if(con==null) {
				JOptionPane.showMessageDialog(this, user+"�� ���ӿ� �����Ͽ����ϴ�.");
				System.exit(0);
			}else {
				this.setTitle(user+" �α��� ��");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void disConnect() {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public void getTopCategory() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from topcategory";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				topCategory.add(rs.getString("name")); // name�� ���� �迭�� ����.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	public List getSubCategory(String name) {// ���ε庯�� = �Ű������� ����.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList subList = new ArrayList();
		
		String sql = "select * from subcategory where topcategory_id=(select topcategory_id from topcategory where name=?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				subList.add(rs.getString("name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return subList;
	}
	public DefaultMutableTreeNode getCreatedNode(String parentName, ArrayList childName) {
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentName);
		if(childName !=null) {
			for(int i=0;i<childName.size();i++) {
				parent.add(new DefaultMutableTreeNode(childName.get(i)));			
			}			
		}
		return parent;
	}


	public static void main(String[] args) {
		new ProductTree();
	}
}
