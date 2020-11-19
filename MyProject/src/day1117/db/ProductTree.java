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
	
	// * 배열만 있다면 트리 생성은 메서드가 알아서 해주는 코드..
	String[] category= {"상의","하의","악세사리","신발"};	// 상위 카테고리 배열
	String[][] product = {
			{"티셔츠","점퍼","니트","가디건"},
			{"청바지","반바지","면바지","핫바지"},
			{"귀걸이","목걸이","반지","팔찌"},
			{"구두","운동화","스니커즈","샌들"}
	};
	// top, down, accessary, shoes
	ArrayList<String> topCategory= new ArrayList<>();	// 상위카테고리
	ArrayList<ArrayList> subCategory= new ArrayList<>();	// 하위카테고리
	
	public ProductTree() {
		// 2) DB 연동하여 배열들의 데이터를 실제 DB  데이터로 가져오자!
		getTopCategory();
		// 노드 생성을 외부 메서드로부터 공수받자!
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("상품정보");
		// 서브카데고리 메서드를 상위카테고리 수만큼 호출하면서 2차원 ArrayList구조를 생성하자
		for(int i=0;i<topCategory.size();i++) {
			String name = topCategory.get(i); // top, down, accessary, shoes
//			ArrayList subList = (ArrayList)getSubCategory(name);
//			subCategory.add(subList);
			top.add(getCreatedNode(name, subCategory.get(i)));
		}
		
//		for(int i=0;i<category.length;i++) {
//			top.add(getCreatedNode(category[i], product[i]));	// 한건이 들어감			
//		}
		
		tree = new JTree(top);	//최상위부착!
		scroll = new JScrollPane(tree);
		
		add(scroll);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				disconnect();
				System.exit(0);
			}
		});
		
		// 호출
		connect();
		
		setSize(400,500);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	// 이 메서드를 호출하는 자가 배열을 넘기면 배열의 길이만큼 노드를 구성하여 반환해줄 것임
	// 한 가족 생성!!
	public DefaultMutableTreeNode getCreatedNode(String parentName,String[] childName) {// 부모가 누구냐 자식이 누구냐
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentName);	// 부모생성
		// 자식의 수만큼 노드 만들어 부모에 부착!
		if(childName != null) {
			for(int i=0;i<childName.length;i++) {
				parent.add(new DefaultMutableTreeNode(childName[i]));
			}			
		}
		
		return parent;
	}
	
	// ---- 위 메서드는 배열에 최적화 되어 있어 처리하는 용이지만, 이 메서드는 이름은 같지만 List용으로 처리..
	public DefaultMutableTreeNode getCreatedNode(String parentName,ArrayList childName) {// 부모가 누구냐 자식이 누구냐
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentName);	// 부모생성
		// 자식의 수만큼 노드 만들어 부모에 부착!
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
				JOptionPane.showMessageDialog(this, user+"로 접속에 실패하였습니다.");
			}else {
				this.setTitle(user+" 로그인 중");
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
	// 상위, 하위 카테고리
	public void getTopCategory() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from topcategory";
		try {
			pstmt = con.prepareStatement(sql);
			rs= pstmt.executeQuery();
			
			// 배열은 반드시 그 크기를 명시해야 한다..
			// 차라리 배열보다는 컬렉션 객체 중 List계열을 추천한다.
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
			pstmt.setString(1, name); // 바인드 변수 지정
			
			rs= pstmt.executeQuery();			
			while(rs.next()) {
				subList.add(rs.getString("name")); // 한 항목을 담는다.
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
		return subList;	// 호출하는 자가 하위카테고리 목록 가져갈 수 있도록..
	}
	public static void main(String[] args) {
		new ProductTree();
	}
}
