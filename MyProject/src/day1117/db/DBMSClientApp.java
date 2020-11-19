package day1117.db;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class DBMSClientApp extends JFrame{

	JPanel p_west;
	Choice ch_users;	
	JPasswordField t_pass;	
	JButton bt_login;	
		
	JPanel p_center;	
	JPanel p_upper;	// 테이블과 시퀀스를 포함할 패널
	JPanel p_middle; 	// ** SQL 편집기가 위치할 미들 패널(FLOW layout , 버튼도 있으므로)
	JPanel p_footer; 	// 하단의 그리드(1,2)가 적용될 패널
	JTextArea area;
	JButton bt_execute;
	JTable t_tables;	// 유저의 테이블 정보를 출력한 JTable
	JTable t_seq;	// 유저의 시퀀스 정보를 출력한 JTable
	JTable t_record;	// ** 유저가 선택한 테이블의 레코드가 출력할 JTable
	JTable t_column; 	// 유저가 선택한 테이블의 구조를 출력할 JTable
	
	JScrollPane s1,s2,s3,s4,s5;
	
	// 접속
	Connection con;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="system";
	String password ="1234";
	
	Vector tableList = new Vector();
	Vector<String> tableColumn = new Vector<>();
	
	Vector seqList = new Vector();
	Vector<String> seqColumn = new Vector<>();
	
	Vector columnList = new Vector();
	Vector<String> columType = new Vector<>();
	
	MyTableModel model;
	
	public DBMSClientApp() {
		p_west = new JPanel();
		ch_users = new Choice();
		t_pass = new JPasswordField();
		bt_login = new JButton("접속");
		
		p_center = new JPanel();		
		p_upper = new JPanel();
		p_middle = new JPanel();
		p_footer = new JPanel();
		
		area = new JTextArea();
		bt_execute = new JButton("SQL문 실행");
		
		p_center.setLayout(new GridLayout(3,1));
		p_upper.setLayout(new GridLayout(1,2));
		p_middle.setLayout(new BorderLayout());
		p_footer.setLayout(new GridLayout(1,2));
		
		tableColumn.add("table_name");
		tableColumn.add("tablespace_name");
		t_tables = new JTable(tableList,tableColumn);
		
		seqColumn.add("sequence_name");
		seqColumn.add("last_number");
		t_seq = new JTable(seqList,seqColumn);
		
		s1 = new JScrollPane(t_tables);
		s2 = new JScrollPane(t_seq);
		s3 = new JScrollPane(area); // 가운데 공간
		s5 = new JScrollPane(t_column);
		
		// ** 선택한 테이블의 레코드 보여줄 JTable 생성
		t_record = new JTable(null);	// MyTableModel을 대입할 예정
		s4 = new JScrollPane(t_record);
		
		// 조립 및 스타일
		p_west.setPreferredSize(new Dimension(150, 350));
		ch_users.setPreferredSize(new Dimension(145, 30));
		t_pass.setPreferredSize(new Dimension(145, 30));
		bt_login.setPreferredSize(new Dimension(145, 30));
		area.setFont(new Font("Arial Black", Font.BOLD, 20));
		
		p_west.add(ch_users);
		p_west.add(t_pass);
		p_west.add(bt_login);
		
		p_upper.add(s1);
		p_upper.add(s2);
		p_middle.add(s3);
		p_middle.add(bt_execute, BorderLayout.SOUTH);
		p_footer.add(s4);
		p_footer.add(s5);
		
		p_center.add(p_upper);//그리드의 1층
		p_center.add(p_middle);//그리드의 2층 
		p_center.add(p_footer);//그리드의 3층 
		
		add(p_west, BorderLayout.WEST);
		add(p_center);
						
		setVisible(true);
		setSize(900,750);	
		setLocationRelativeTo(null);
		
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
		
		t_tables.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				int row = t_tables.getSelectedRow();
				int col = t_tables.getSelectedColumn();
				
				// select 매개변수로 넘겨 봣자.
				String tableName = (String) t_tables.getValueAt(row, col);
				tableName = tableName.toLowerCase();
				select(tableName);
				t_record.updateUI(); // jtable갱신
			}
		});
		connect();
		getUserList();
	}
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
	
	// 유저 목록 가져오기 select
	public void getUserList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		String sql = "select username from dba_users order by username asc";
		try {
			pstmt = con.prepareStatement(sql);
			rs= pstmt.executeQuery();
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
	// 로그인
	public void login() {
		// system으로 로그인 상태..
		disConnect();
		user = ch_users.getSelectedItem();
		password = new String(t_pass.getPassword());
		connect();
		getTableList();
		getSequList();
	}
	public void getTableList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select table_name,tablespace_name from user_tables";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {// 테이블을 담자.
				Vector vec = new Vector();
				vec.add(rs.getString("table_name"));
				vec.add(rs.getString("tablespace_name"));
				tableList.add(vec);				
			}
			// 한건 완료? 그럼 갱신!
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
		}  finally {
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
	public void select(String tableName ) {
		//선택한 레코드 가져오기
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = null;
		if(tableName != null) {
			sql = "select * from "+tableName;
		}else {
			sql = area.getText();
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			// -------- 컬럼 정보 만들기 위한 코드
			// 이 벡터는 새로운 TableModel 객체의 인스턴스가 가진 컬럼벡터에 대입될 예정
			Vector column = new Vector();
			// wrapper 로 감싸져 있는거를 끄집어 낸다고 생각하자.
			ResultSetMetaData meta = rs.getMetaData();
			int columnCount = meta.getColumnCount();
			
			// 컬럼 정보 출력
			for(int i=1;i<=columnCount;i++) {
				column.add(meta.getColumnName(i)); // 안에 감싸고 있는 rs 를 가진 meta에서..
			}
			
			// rs = 레코드 행 열을 가지고 있는 mytablemodel에 담으면 된다.
			Vector record = new Vector();
			while(rs.next()) {
				Vector vec = new Vector();
				for(int i=1;i<=meta.getColumnCount();i++) {
					vec.add(rs.getString(i));					
				}
				record.add(vec);
			}
			// record 에 담은 이차원 일차원 벡터를 새로운 모델 객체를 생성하면서 전달하자.
			model = new MyTableModel(record, column);
			t_record.setModel(model);
			getColumnType(meta);
			
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
	// 선택한 테이블의 구조정보 가져오기
	public void getColumnType(ResultSetMetaData meta) {
		// 속 안에 감싸져 있는 정보를 출력하기		
		try {
			int total = meta.getColumnCount();
			columnList.removeAll(columnList);
			for(int i=1;i<=total;i++) {
				Vector vec = new Vector();
				vec.add(meta.getColumnName(i));
				vec.add(meta.getColumnTypeName(i));		
				columnList.add(vec);
				t_column.removeAll();
			}
			t_column.updateUI();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new DBMSClientApp();
	}
}
