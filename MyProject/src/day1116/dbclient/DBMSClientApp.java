package day1116.dbclient;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.GridLayout;
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
	Choice ch_users;	// 유저명이 출력될 초이스 컴포넌트
	JPasswordField t_pass;	// 비밀번호 텍스트 필드
	JButton bt_login;	// 접속 버튼
		
	JPanel p_center;	// 그리드 적용
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
	Vector<String> columnList = new Vector<>();	// 컬럼명은 String..
	
	public DBMSClientApp() {
		// 생성
		p_west = new JPanel();
		ch_users = new Choice();
		t_pass = new JPasswordField();
		bt_login = new JButton("접속");
		
		p_center = new JPanel();
		p_center.setLayout(new GridLayout(2,1));	
		t_tables = new JTable(tableList, columnList);	// 4) 초기 벡터값을 넣어주세요. 추후 메서드 호출시 크기가 변경될 것이고
																			//		JTable을 새로고침 하면 되겟져?
		t_seq = new JTable();
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
		p_center.add(s1);
		p_center.add(s2);
		add(p_west,BorderLayout.WEST);
		add(p_center);
				
		setVisible(true);
		setSize(700,350);	
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
		setLocationRelativeTo(null);
		
		// 호출
		connect();
		getUserList();
		
		// 4-2) 컬럼정보 초기화하기
		columnList.add("table_name");
		columnList.add("tablespace_name");
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
		// 현재 유지되고 있는 접속 객체인 connection을 끊고 다른 유저로 접속을 시도!
		disConnect();
		user = ch_users.getSelectedItem();	// 현재 초이스 컴포넌트에 선택된 아이템 값!
		password = new String(t_pass.getPassword());	// 비밀번호 설정
		connect();	// 현재 멤버변수에 system 으로 되어 있으므로 초이스 값으로 교체
		getTableList();	// 로그인 하자마자. 이 사람의 테이블 정보를 보여주자.
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
