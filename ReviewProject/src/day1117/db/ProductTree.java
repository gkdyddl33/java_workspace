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
	// 접속
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "user1104";
	String password = "user1104";	
	Connection con;
	
	JTree tree;
	JScrollPane scroll;
	
	String[] category= {"상의","하의","엑세서리","신발"};
	String[][] product={
			{"티셔츠","점퍼","니트","가디건"},
			{"청바지","반바지","면바지","핫바지"},
			{"귀걸이","목걸이","반지","팔찌"},
			{"구두","운동화","스니커즈","샌들"}
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
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("상품정보");
		
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
				JOptionPane.showMessageDialog(this, user+"로 접속에 실패하였습니다.");
				System.exit(0);
			}else {
				this.setTitle(user+" 로그인 중");
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
				topCategory.add(rs.getString("name")); // name을 담을 배열에 넣자.
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
	public List getSubCategory(String name) {// 바인드변수 = 매개변수로 받자.
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
