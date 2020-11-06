package day1105.db;
/*Oracle ���*/
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
	
	// ����
	String url ="jdbc:oracle:thin:@localhost:1521:XE";
	String user = "user1104";
	String password = "user1104";
	// ���� ��, �� ������ ���� ��ü. ���� ������ �������� �� ��ü�� �ʿ��ϴ�
	Connection con; 
	// ������ ���� ��ü =  load, �������̽� �̹Ƿ� new�� �����ϴ� ���� �ƴ϶�
	// ���� ��ü�� Connection ��ü�κ��� �ν��Ͻ��� ���� �� �ִ�.
	// why? ������ �����Ǿ�� �������� ������ �� �����Ƿ�, ���Ӱ�ü�� �������� ���� �翬�ϴ�.
	PreparedStatement pstmt;
	
	// ������ �������� ���� ǥ�� ��ȯ�Ǵµ�, �̶� �� ǥ�� ��� ��ü 
	ResultSet rs;
	public EmpApp() {
		// ����
		bt_connect = new JButton("Connect");
		bt_load = new JButton("Load");
		
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		// ����
		area.setPreferredSize(new Dimension(680,500));
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
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	// ����, select ����
	public void connect() {
		// 1) ����Ŭ ���� �õ��ϱ�
		// ����̹� �ε� - ���� 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new EmpApp();
	}
}
