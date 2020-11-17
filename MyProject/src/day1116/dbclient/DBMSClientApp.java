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
 	DBeaver ������ �ƴϾ, ��ųʸ��� �н��ϱ� ���� �����ͺ��̽� ���� Ŭ���̾�Ʈ�� �ڹٷ� ������.
 	�ǹ�������, SQLPlus�� �� ������� ����.. 
 	������? ���� ȿ������ �������� ������ 
 	��������? �ǹ� ���忡���� �������� pc���� ���� ���� ������ ������, �������� � ��������
 				���Ȼ� �ƹ��͵� ��ġ�ؼ��� �ȵȴ�. 
 				���� �������� ������ ���� ������ ���� ����Ŭ�� ���� ���� �Ϸ� �İ��� ���� ���,
 				�ܼ�â ������� ������ �ٷ�� �� ��찡 ���� �ִ�.
 				�̶� SQLPlus�� �����..
 	�����ڵ��� ������ �� ������ ���̽� �ٷ�� ���� "�����ͺ��̽� ���� Ŭ���̾�Ʈ" ��� �θ���.
 	�̷��� �� �� �� ������ ��ǰ�� Toad,.. ���� �ִ�(����)
 	Toad�� DBeaver�� ���� ����� ���������� �����̱⿡ �츮�� DBeaver�� ����ϰ� ����
*/
public class DBMSClientApp extends JFrame{

	JPanel p_west;
	Choice ch_users;	
	JPasswordField t_pass;	
	JButton bt_login;	
		
	JPanel p_center;	
	JPanel p_upper;	// ���̺�� �������� ������ �г�
	JTable t_tables;	// ������ ���̺� ������ ����� JTable
	JTable t_seq;	// ������ ������ ������ ����� JTable
	JScrollPane s1,s2;
	
	// ����
	Connection con;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="system";
	String password ="1234";
	
	// 4) ���̺� ����� ����
	Vector tableList = new Vector();	// �� �ٸ� ���Ͱ� ��..������ �迭�� ����..
	Vector<String> tableColumn = new Vector<>();	// �÷����� String..
	
	// 5) �������� �ʿ��� ����
	Vector seqList = new Vector();
	Vector<String> seqColumn = new Vector<>();
	
	// 6) ������ ���̺� ���� ���ڵ� ��¿� �ʿ��� ���͵�
	Vector recordList = new Vector();
	// -------------------------- ���͸� ��� ���� ���� ���⿡�� �ʹ� �ӻ��ϴ�..
	Vector productColumn = new Vector();
	Vector empColumn = new Vector();
	
	public DBMSClientApp() {
		// ����
		p_west = new JPanel();
		ch_users = new Choice();
		t_pass = new JPasswordField();
		bt_login = new JButton("����");
		
		p_center = new JPanel();		
		p_upper = new JPanel();
		p_center.setLayout(new GridLayout(3,1));
		p_upper.setLayout(new GridLayout(1,2));
		
		// 4-2) �÷�(����)���� �ʱ�ȭ�ϱ� -> ���̺� ������ ���̰� �ϱ� ���� ��ġ�� �ø���.!
		tableColumn.add("table_name");
		tableColumn.add("tablespace_name");
		
		t_tables = new JTable(tableList, tableColumn);	// 4) �ʱ� ���Ͱ��� �־��ּ���. ���� �޼��� ȣ��� ũ�Ⱑ ����� ���̰�
																			//		JTable�� ���ΰ�ħ �ϸ� �ǰ���?
		
		seqColumn.add("sequence_name");
		seqColumn.add("last_number");
		t_seq = new JTable(seqList,seqColumn);		// 5) ������ �����ڿ� ���� �����ϱ�(����)
		
		s1 = new JScrollPane(t_tables);
		s2 = new JScrollPane(t_seq);
		
		// ���� �� ��Ÿ��
		p_west.setPreferredSize(new Dimension(150,350));
		ch_users.setPreferredSize(new Dimension(145,30));
		t_pass.setPreferredSize(new Dimension(145,30));
		bt_login.setPreferredSize(new Dimension(145,30));
		
		p_west.add(ch_users);
		p_west.add(t_pass);
		p_west.add(bt_login);
		p_upper.add(s1);
		p_upper.add(s2);
		p_center.add(p_upper); // �߰��� ���� 3������ ����
				
		add(p_west,BorderLayout.WEST);
		add(p_center);
				
		
		setVisible(true);
		setSize(700,750);	
		
		// ������ ����� �����Ͽ� ������ �ִٸ� �ݴ� ó���� ����!
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
		
		// ���̺�� ������ ����
		t_tables.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// ������ ��ǥ�� ���̺�� ���
				int row = t_tables.getSelectedRow();
				int col = t_tables.getSelectedColumn();
				//t_tables.getValueAt(row, col);	// ������ row, ������ column
				System.out.println(t_tables.getValueAt(row, col));
			}
		});
		
		setLocationRelativeTo(null);
		
		// ȣ��
		connect();
		getUserList();
		
	}
	
	// 1) ����Ŭ ����
	public void connect() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			
			if(con==null) {
				JOptionPane.showMessageDialog(this, user+"���ӿ� �����Ͽ����ϴ�.");
			}else {
				this.setTitle(user+" �������� ���� ��..");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// ����Ŭ ��������
	public void disConnect() {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 2) ���� ��� �������� select
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
	
	// 3) ������� ���� �� �α���
	public void login() {		
		disConnect();
		user = ch_users.getSelectedItem();	// ���� ���̽� ������Ʈ�� ���õ� ������ ��!
		password = new String(t_pass.getPassword());	
		connect();
		getTableList();	// �α��� ���ڸ���. �� ����� ���̺� ������ ��������.
		getSequList();
		System.out.println("������ ���̺� "+tableList.size());  // �� ���ſ� ������ ����
	}
	
	// 4) ���� ���� ������ ���̺� ��� ��������
	public void getTableList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select table_name,tablespace_name from user_tables";
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Vector vec = new Vector();	// ���Ϳ� ����� ����
				vec.add(rs.getString("table_name"));
				vec.add(rs.getString("tablespace_name"));
				tableList.add(vec);	// ��� ���� ���Ϳ� ���͸� ������� ������ ���Ͱ� ��..
			}
			// JTable�� �ϼ��� ���� �μ��� �־�� ��.
			// �÷� ������ ��� �����ñ��? 2���ۿ� �����ϰ����ϸ� �ǰ���?
			// ���� �����ڿ��� new �ϰ� ���⼭�� ������ ����!
			t_tables.updateUI();
			// * ���̺��� ���ڵ�� �÷����� Ȯ�� - ���Ź��� �ذ�
			System.out.println("�÷�����:"+t_tables.getColumnCount()); // �÷����� 0�̴�..�ø���
			
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
	
	// 5) ������ �������� -> ���̾ƿ� ������ 
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
