package day1116.dbclient;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*
 	DBeaver 수준은 아니어도, 딕셔너리를 학습하기 위해 데이터베이스 접속 클라이언트를 자바로 만들어본다.
 	실무에서는, SQLPlus를 잘 사용하지 않음.. 
 	이유는? 업무 효율성이 떨어지기 때문에 
 	언제쓰나? 실무 현장에서는 개발자의 pc에는 각종 개발 툴들이 있지만, 실제적인 운영 서버에는
 				보안상 아무것도 설치해서는 안된다. 
 				따라서 서버에는 툴들이 없기 때문에 만일 오라클을 유지 보수 하러 파견을 나간 경우,
 				콘솔창 기반으로 쿼리를 다뤄야 할 경우가 종종 있다.
 				이때 SQLPlus를 써야함..
 	개발자들이 개발할 때 데이터 베이스 다루는 툴을 "데이터베이스 접속 클라이언트" 라고 부른다.
 	이러한 툴 들 중 유명한 제품은 Toad,.. 등이 있다(유료)
 	Toad는 DBeaver에 비해 기능이 막강하지만 유료이기에 우리는 DBeaver를 사용하고 있음
*/
public class DBMSClientApp extends JFrame{

	JPanel p_west;
	Choice ch_users;	
	JPasswordField t_pass;	
	JButton bt_login;	
		
	JPanel p_center;	
	JPanel p_upper;	// 테이블과 시퀀스를 포함할 패널
	JTable t_tables;	// 유저의 테이블 정보를 출력한 JTable
	JTable t_seq;	// 유저의 시퀀스 정보를 출력한 JTable
	JScrollPane s1,s2;
	
	// 접속
	Connection con;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="system";
	String password ="1234";
	
	// 4) 테이블 출력할 벡터
	Vector tableList = new Vector();	// 또 다른 벡터가 들어갈..이차원 배열과 동일..
	Vector<String> tableColumn = new Vector<>();	// 컬럼명은 String..
	
	// 5) 시퀀스에 필요한 벡터
	Vector seqList = new Vector();
	Vector<String> seqColumn = new Vector<>();
	
	// 6) 선택한 테이블에 대한 레코드 출력에 필요한 벡터들
	Vector recordList = new Vector();
	// -------------------------- 벡터를 계속 만들어서 끌구 가기에는 너무 속상하다..
	Vector productColumn = new Vector();
	Vector empColumn = new Vector();
	
	public DBMSClientApp() {
		// 생성
		p_west = new JPanel();
		ch_users = new Choice();
		t_pass = new JPasswordField();
		bt_login = new JButton("접속");
		
		p_center = new JPanel();		
		p_upper = new JPanel();
		p_center.setLayout(new GridLayout(3,1));
		p_upper.setLayout(new GridLayout(1,2));
		
		// 4-2) 컬럼(제목)정보 초기화하기 -> 테이블 구조를 보이게 하기 위해 위치를 올리자.!
		tableColumn.add("table_name");
		tableColumn.add("tablespace_name");
		
		t_tables = new JTable(tableList, tableColumn);	// 4) 초기 벡터값을 넣어주세요. 추후 메서드 호출시 크기가 변경될 것이고
																			//		JTable을 새로고침 하면 되겟져?
		
		seqColumn.add("sequence_name");
		seqColumn.add("last_number");
		t_seq = new JTable(seqList,seqColumn);		// 5) 시퀀스 생성자에 벡터 적용하기(제목)
		
		s1 = new JScrollPane(t_tables);
		s2 = new JScrollPane(t_seq);
		
		// 조립 및 스타일
		p_west.setPreferredSize(new Dimension(150,350));
		ch_users.setPreferredSize(new Dimension(145,30));
		t_pass.setPreferredSize(new Dimension(145,30));
		bt_login.setPreferredSize(new Dimension(145,30));
		
		p_west.add(ch_users);
		p_west.add(t_pass);
		p_west.add(bt_login);
		p_upper.add(s1);
		p_upper.add(s2);
		p_center.add(p_upper); // 추가로 인해 3층으로 변함
				
		add(p_west,BorderLayout.WEST);
		add(p_center);
				
		
		setVisible(true);
		setSize(700,750);	
		
		// 윈도우 어댑터 구현하여 닫을게 있다면 닫는 처리로 진행!
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				disConnect();
				System.exit(0);
			}
		});
		
		bt_login.addActionListener((e)->{
			login();
		});
		
		// 테이블과 리스너 연결
		t_tables.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// 선택한 좌표의 테이블명 얻기
				int row = t_tables.getSelectedRow();
				int col = t_tables.getSelectedColumn();
				//t_tables.getValueAt(row, col);	// 선택한 row, 선택한 column
				System.out.println(t_tables.getValueAt(row, col));
			}
		});
		
		setLocationRelativeTo(null);
		
		// 호출
		connect();
		getUserList();
		
	}
	
	// 1) 오라클 접속
	public void connect() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			
			if(con==null) {
				JOptionPane.showMessageDialog(this, user+"접속에 실패하였습니다.");
			}else {
				this.setTitle(user+" 계정으로 접속 중..");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 오라클 접속해제
	public void disConnect() {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 2) 유저 목록 가져오기 select
	public void getUserList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select username from dba_users order by username asc";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ch_users.add(rs.getString("username"));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// 3) 유저목록 선택 후 로그인
	public void login() {		
		disConnect();
		user = ch_users.getSelectedItem();	// 현재 초이스 컴포넌트에 선택된 아이템 값!
		password = new String(t_pass.getPassword());	
		connect();
		getTableList();	// 로그인 하자마자. 이 사람의 테이블 정보를 보여주자.
		getSequList();
		System.out.println("보유한 테이블 "+tableList.size());  // ★ 갱신에 문제가 잇음
	}
	
	// 4) 현재 접속 유저의 테이블 목록 가져오기
	public void getTableList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select table_name,tablespace_name from user_tables";
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Vector vec = new Vector();	// 벡터에 담겨질 벡터
				vec.add(rs.getString("table_name"));
				vec.add(rs.getString("tablespace_name"));
				tableList.add(vec);	// 멤버 변수 벡터에 벡터를 담았으니 이차원 벡터가 됨..
			}
			// JTable에 완성한 것을 인수로 넣어야 함.
			// 컬럼 정보는 어떻게 가져올까요? 2개밖에 없으니고정하면 되겠죠?
			// 위에 생성자에서 new 하고 여기서는 갱신을 하자!
			t_tables.updateUI();
			// * 테이블의 레코드와 컬럼갯수 확인 - 갱신문제 해결
			System.out.println("컬럼수는:"+t_tables.getColumnCount()); // 컬럼수가 0이다..올리자
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// 5) 시퀀스 가져오기 -> 레이아웃 변경후 
	public void getSequList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select sequence_name,last_number from user_sequences";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Vector vec = new Vector();
				vec.add(rs.getString("sequence_name"));
				vec.add(rs.getString("last_number"));
				seqList.add(vec);				
			}
			t_seq.updateUI();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public static void main(String[] args) {
		new DBMSClientApp();
	}
}
