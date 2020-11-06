package day1105.db;
/*My Sql 방식*/
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
	JButton bt_connect, bt_load;
	JTextArea area;
	JScrollPane scroll;
	
	// JDBC 기술은 DBMS 종류에 상관없이 중립적인 코드를 작성할 수 있도록 지원한다.
	// 해당 DB 기종마다 알맞는 Driver를 사용해야 한다..
	String url ="jdbc:mariadb://localhost/db1105"; 
	// ?autoReconnect=true&serverTimezone=UTC

	String user = "root";
	String password = "1234";
	
	// (접속 후), 그 정보를 가진 객체. 따라서 접속을 끊으려면 이 객체가 필요하다
	Connection con; 
	
	// (쿼리문) 수행 객체 =  load, 인터페이스 이므로 new로 생성하는 것이 아니라
	// 접속 객체인 Connection 객체로부터 인스턴스를 얻어올 수 있다.
	// why? 접속이 성공되어야 쿼리문을 수행할 수 있으므로, 접속객체에 의존적인 것이 당연하다.
	PreparedStatement pstmt;
	
	// 쿼리문 수행결과에 의해 표가 반환되는데, 이때 이 표를 담는 객체 
	ResultSet rs;
	public EmpApp2() {
		// 생성
		bt_connect = new JButton("Connect");
		bt_load = new JButton("Load");
		
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		// 조립
		area.setPreferredSize(new Dimension(780,500));
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
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		// db를 닫지 않고 프로세스만 종료해버리니깐 사용하면은 안되는 코드이다.
		// 윈도우 리스너로 종료를 해야 한다.
		this.addWindowListener(new WindowAdapter() {	
			@Override
			public void windowClosing(WindowEvent e) {
				// 열려있는 데이터베이스 관련 객체들을 모두 닫자.
				if(rs !=null) {
					try {
						rs.close(); // close은 그냥 단절한다의 의미. 끊는다!
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
				// 콘솔 프로세스 종료
				System.exit(0);
			}
		});
	}
	// 접속, select 실행
	public void connect() {
		// 1) 오라클 접속 시도하기
		// 드라이버 로드 - 접속 
		try {
			Class.forName("org.mariadb.jdbc.Driver");
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
			
			// rs 에는 표가 들어있다. 따라서 원하는 레코드로 커서를 옮기자.
			// next() 다음 레코드로 이동.. getString() 으로 제목 가져오기
			// 데이터가 존재하면, 한 칸 전진 후 true까지 반환
			area.append("EMPNO\t ENAME\t JOB\t MRG\t HIREDATE\t\t SAL\t COMM\t DEPTNO\t\n");
			while(rs.next()) {
				//rs.next()가 true인 동안..
				int empno = rs.getInt("empno");
				String ename = rs.getString("ename");			
				String job = rs.getString("job");
				int mgr = rs.getInt("mgr");
				String hiredate = rs.getString("hiredate");
				int sal = rs.getInt("sal");
				int comm = rs.getInt("comm");
				int deptno = rs.getInt("deptno");						
				
				//System.out.println(ename+"\t"+job);
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
