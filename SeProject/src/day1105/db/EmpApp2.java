package day1105.db;
/*My Sql ���*/
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
	
	// JDBC ����� DBMS ������ ������� �߸����� �ڵ带 �ۼ��� �� �ֵ��� �����Ѵ�.
	// �ش� DB �������� �˸´� Driver�� ����ؾ� �Ѵ�..
	String url ="jdbc:mariadb://localhost/db1105"; 
	// ?autoReconnect=true&serverTimezone=UTC

	String user = "root";
	String password = "1234";
	
	// (���� ��), �� ������ ���� ��ü. ���� ������ �������� �� ��ü�� �ʿ��ϴ�
	Connection con; 
	
	// (������) ���� ��ü =  load, �������̽� �̹Ƿ� new�� �����ϴ� ���� �ƴ϶�
	// ���� ��ü�� Connection ��ü�κ��� �ν��Ͻ��� ���� �� �ִ�.
	// why? ������ �����Ǿ�� �������� ������ �� �����Ƿ�, ���Ӱ�ü�� �������� ���� �翬�ϴ�.
	PreparedStatement pstmt;
	
	// ������ �������� ���� ǥ�� ��ȯ�Ǵµ�, �̶� �� ǥ�� ��� ��ü 
	ResultSet rs;
	public EmpApp2() {
		// ����
		bt_connect = new JButton("Connect");
		bt_load = new JButton("Load");
		
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		// ����
		area.setPreferredSize(new Dimension(780,500));
		setLayout(new FlowLayout());
		add(bt_connect);
		add(bt_load);
		add(scroll);
		
		// ����
		bt_connect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				connect(); // ����Ŭ ����
			}
		});
		bt_load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				load(); // select�� emp ��������				
			}
		});
		
		// ������
		setSize(800,600);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		// db�� ���� �ʰ� ���μ����� �����ع����ϱ� ����ϸ��� �ȵǴ� �ڵ��̴�.
		// ������ �����ʷ� ���Ḧ �ؾ� �Ѵ�.
		this.addWindowListener(new WindowAdapter() {	
			@Override
			public void windowClosing(WindowEvent e) {
				// �����ִ� �����ͺ��̽� ���� ��ü���� ��� ����.
				if(rs !=null) {
					try {
						rs.close(); // close�� �׳� �����Ѵ��� �ǹ�. ���´�!
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
				// �ܼ� ���μ��� ����
				System.exit(0);
			}
		});
	}
	// ����, select ����
	public void connect() {
		// 1) ����Ŭ ���� �õ��ϱ�
		// ����̹� �ε� - ���� 
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			area.append("����̹� �ε� ����\n");
			
			// 2) ���ӽõ�
			con = DriverManager.getConnection(url, user, password);
			if(con==null) {
				area.append("���ӽ���\n");
			}else {
				area.append("���Ӽ���\n");
			}
		} catch (ClassNotFoundException e) {
			area.append("����̹� �ε� ����");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void load() {
		String sql = "select * from emp";
		try {
			pstmt = con.prepareStatement(sql);
			// �Ʒ� �ڵ�� DML �� ��쿡�� ���..
			//pstmt.executeUpdate();
			// select ���� ��쿣 executeQuery() �̿��ؾ� �Ѵ�.
			// ǥ�� ����ִ� ��ȯ�� rs ���
			rs=pstmt.executeQuery();
			
			// rs ���� ǥ�� ����ִ�. ���� ���ϴ� ���ڵ�� Ŀ���� �ű���.
			// next() ���� ���ڵ�� �̵�.. getString() ���� ���� ��������
			// �����Ͱ� �����ϸ�, �� ĭ ���� �� true���� ��ȯ
			area.append("EMPNO\t ENAME\t JOB\t MRG\t HIREDATE\t\t SAL\t COMM\t DEPTNO\t\n");
			while(rs.next()) {
				//rs.next()�� true�� ����..
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
