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

/*
 	day1116일차에 구현했던 데이터 베이스 클라이언트 프로그램에서 JTable 생성자의 Vector방식을 
 	이용하면 동적인 테이블 선택시 유지 보수성이 거의 불가능한 수준이므로,
 	이를 개선해 본다. 
*/
public class DBMSClientApp2 extends JFrame{

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
	
	// 4) 테이블 출력할 벡터
	Vector tableList = new Vector();	// 또 다른 벡터가 들어갈..이차원 배열과 동일..
	Vector<String> tableColumn = new Vector<>();	// 컬럼명은 String..
	
	// 5) 시퀀스에 필요한 벡터
	Vector seqList = new Vector();
	Vector<String> seqColumn = new Vector<>();
	
	// 7) 테이블 컬럼 정보에 필요한 벡터들
	Vector columnList = new Vector();
	Vector<String> columType = new Vector<>();
	
	// 6) TableModel 보유
	MyTableModel model;
		
	public DBMSClientApp2() {
		// 생성
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
		
		// 4-2) 컬럼(제목)정보 초기화하기 -> 테이블 구조를 보이게 하기 위해 위치를 올리자.!
		tableColumn.add("table_name");
		tableColumn.add("tablespace_name");
		
		t_tables = new JTable(tableList, tableColumn);	// 4) 초기 벡터값을 넣어주세요. 추후 메서드 호출시 크기가 변경될 것이고
																			//		JTable을 새로고침 하면 되겟져?
		
		seqColumn.add("sequence_name");
		seqColumn.add("last_number");
		t_seq = new JTable(seqList,seqColumn);		// 5) 시퀀스 생성자에 벡터 적용하기(제목)
		
		columType.add("컬럼명");
		columType.add("데이터타입");		
		t_column = new JTable(columnList,columType); // 7)
		
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
				//System.out.println(t_tables.getValueAt(row, col));
				
				// 6) 구해진 테이블 명을 select() 메서드의 인수로 넘기자
				String tableName = (String)t_tables.getValueAt(row, col);
				tableName = tableName.toLowerCase();	// 대문자->소문자
				System.out.println(tableName);
				
				select(tableName);
				t_record.updateUI();// jtable 갱신
				//System.out.println("모델의 컬럼 카운트는 "+t_record.getColumnCount()); // new를 한적이 없어서 null
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
	
	// 6) 선택한 테이블의 레코드 가져오기
	// 매개변수가 넘어오면, 테이블 명만 사용하고, 안넘어오면 전체 SQL문 대체하자.
	public void select(String tableName) {// 매개변수 = 선택한 테이블 명
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = null;
		if(tableName != null) {// 테이블명을 매개변수로 넘기면 아래의 쿼리문을 사용
			sql = "select * from "+tableName;
			
		}else {
			sql = area.getText();
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
					
/*
 	컬럼 vs 레코드 중 고정된 데이터로 간주되는 것은? 칼럼이다.
 	DB에서 Table 생성시 컬럼은 create문으로 생성된 이후 부터는 고정된 상태이고,
 	이 컬럼에 record 생성
*/
			/* --------------------------------------
				컬럼 정보 만들기 위한 코드
			 --------------------------------------*/
			Vector column = new Vector();	// 이 벡터는 새로운 TableModel 객체의 인스턴스가 가진 컬럼벡터에 대입될 예정
			ResultSetMetaData meta = rs.getMetaData();
			int columnCount = meta.getColumnCount();	// 총 컬럼수
			System.out.println("당신이 선택한 "+tableName+" 테이블의 컬럼수는 "+columnCount);
			
			// 컬럼정보 출력
			// 출력만 확인하지 말고 mytablemodel의 벡터에 정보를 채워넣자.
			for(int i =1;i<=columnCount;i++) {
				column.add(meta.getColumnName(i));
			}
			
			// rs를 어디에 담아야 할까? TableModel 인 MyTableModel이 보유한 벡터에 담으면 된다.
			Vector record = new Vector();
			while(rs.next()) {				
				Vector vec = new Vector();
				//rs.getString(1);	// 1부터 시작! 단, 1부터 몇까지 컬럼이 존재하는지 알수가 없다.
										// 알 수 있는 방법은?
										// 이럴때는 테이블에 대한 메타 정보를 가져오면 된다!
										// 메타 데이터란? 어떤 것의 desc가 담긴 데이터.
				for(int i =1;i<=meta.getColumnCount();i++) {
					vec.add(rs.getString(i)); // 데이터 채우기
				}
				record.add(vec);	// 벡터에 추가
			}
			// 데이터를 담은 이차원 벡터와 컬럼을 담은 일차원 벡터를 새로운 모델 객체를 생성하면서 전달하자!
			model = new MyTableModel(record,column);	// 생성자 매개변수(이차원컬럼 벡터, 일차원컬럼 벡터)
			// 테이블에 모델을 생성자가 아닌, 메서드로 적용하자!
			t_record.setModel(model);
			
			// rs에 죽기전에 메타를 호출하자. -> finally
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
	
	// 7) 유저가 선택한 테이블의 구조 정보 가져오기
	public void getColumnType(ResultSetMetaData meta) {
		try {
			int total = meta.getColumnCount();	// 총 컬럼 수
			columnList.removeAll(columnList);
			for(int i=1;i<=total;i++) {
				System.out.println("컬럼명"+meta.getColumnName(i)+"("+meta.getColumnTypeName(i)+")");
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
		new DBMSClientApp2();
	}
}
