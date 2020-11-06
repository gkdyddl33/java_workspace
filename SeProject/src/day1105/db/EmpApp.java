package day1105.db;
/*Oracle 방식*/
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EmpApp extends JFrame{
	JButton bt_connect, bt_load;
	JTextArea area;
	JScrollPane scroll;
	
	// 접속
	String url ="jdbc:oracle:thin:@localhost:1521:XE";
	String user = "user1104";
	String password = "user1104";
	// 접속 후, 그 정보를 가진 객체. 따라서 접속을 끊으려면 이 객체가 필요하다
	Connection con; 
	// 쿼리문 수행 객체 =  load, 인터페이스 이므로 new로 생성하는 것이 아니라
	// 접속 객체인 Connection 객체로부터 인스턴스를 얻어올 수 있다.
	// why? 접속이 성공되어야 쿼리문을 수행할 수 있으므로, 접속객체에 의존적인 것이 당연하다.
	PreparedStatement pstmt;
	
	// 쿼리문 수행결과에 의해 표가 반환되는데, 이때 이 표를 담는 객체 
	ResultSet rs;
	public EmpApp() {
		// 생성
		bt_connect = new JButton("Connect");
		bt_load = new JButton("Load");
		
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		// 조립
		area.setPreferredSize(new Dimension(680,500));
		setLayout(new FlowLayout());
		add(bt_connect);
		add(bt_load);
		add(scroll);
		
		// 연결
		bt_connect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				connect(); // 오라클 접속
			}
		});
		bt_load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				load(); // select로 emp 가져오기				
			}
		});
		
		// 윈도우
		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	// 접속, select 실행
	public void connect() {
		// 1) 오라클 접속 시도하기
		// 드라이버 로드 - 접속 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			area.append("드라이버 로드 성공\n");
			
			// 2) 접속시도
			con = DriverManager.getConnection(url, user, password);
			if(con==null) {
				area.append("접속실패\n");
			}else {
				area.append("접속성공\n");
			}
		} catch (ClassNotFoundException e) {
			area.append("드라이버 로드 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void load() {
		String sql = "select * from emp";
		try {
			pstmt = con.prepareStatement(sql);
			// 아래 코드는 DML 의 경우에만 사용..
			//pstmt.executeUpdate();
			// select 문의 경우엔 executeQuery() 이용해야 한다.
			// 표를 담아주는 반환형 rs 사용
			rs=pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new EmpApp();
	}
}
