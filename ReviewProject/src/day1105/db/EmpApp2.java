package day1105.db;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EmpApp2 extends JFrame{
	JButton bt_connect,bt_load;
	JTextArea area;
	JScrollPane scroll;
	
	String url = "jdbc:mariadb://localhost/db1105";
	String user = "root";
	String password = "1234";
	
	Connection con;
	PreparedStatement pstmt; // 쿼리문 수행객체
	ResultSet rs; // 쿼리문 수행결과 = 표로 반환
	public EmpApp2() {
		bt_connect = new JButton("Connect");
		bt_load = new JButton("Load");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		area.setPreferredSize(new Dimension(780,500));
		setLayout(new FlowLayout());
		add(bt_connect);
		add(bt_load);
		add(scroll);
		
		// 연결
		bt_connect.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		bt_load.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});
		
		setSize(800,600);
		setVisible(true);
		// 종료 -> db 활용시 주의!
		// 프로세스만 종료하지 말고 db 도 종료를 해줘야 한다.
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(rs != null) {
					try {
						rs.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				if(pstmt !=null) {
					try {
						pstmt.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				if(con !=null) {
					try {
						con.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				System.exit(0);
			}
		});
	}
	
	public void connect() {
		// 1) 드라이버 로드
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			area.append("드라이버 로드 성공\n");
			
			// 2) 접속
			con = DriverManager.getConnection(url, user, password);
			if(con==null) {
				area.append("접속실패\n");
			}else {
				area.append("접속성공");
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
			rs = pstmt.executeQuery();
			
			// 레코드 커서를 옮기자.
			area.append("EMPNO\t ENAME\t JOB\t MRG\t HIREDATE\t\t SAL\t COMM\t DEPTNO\t\n");
			while(rs.next()) {
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");			
				String job = rs.getString("job");
				int mgr = rs.getInt("mgr");
				String hiredate = rs.getString("hiredate");
				int sal = rs.getInt("sal");
				int comm = rs.getInt("comm");
				int deptno = rs.getInt("deptno");
				
				area.append(empno+"\t"+ename+"\t"+job+"\t"+mgr+"\t"+hiredate+"\t"+sal+"\t"+comm+"\t"+deptno+"\n");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new EmpApp2();
	}
}
