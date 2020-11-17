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
 	day1116������ �����ߴ� ������ ���̽� Ŭ���̾�Ʈ ���α׷����� JTable �������� Vector����� 
 	�̿��ϸ� ������ ���̺� ���ý� ���� �������� ���� �Ұ����� �����̹Ƿ�,
 	�̸� ������ ����. 
*/
public class DBMSClientApp2 extends JFrame{

	JPanel p_west;
	Choice ch_users;	
	JPasswordField t_pass;	
	JButton bt_login;	
		
	JPanel p_center;	
	JPanel p_upper;	// ���̺�� �������� ������ �г�
	JPanel p_middle; 	// ** SQL �����Ⱑ ��ġ�� �̵� �г�(FLOW layout , ��ư�� �����Ƿ�)
	JPanel p_footer; 	// �ϴ��� �׸���(1,2)�� ����� �г�
	JTextArea area;
	JButton bt_execute;
	JTable t_tables;	// ������ ���̺� ������ ����� JTable
	JTable t_seq;	// ������ ������ ������ ����� JTable
	JTable t_record;	// ** ������ ������ ���̺��� ���ڵ尡 ����� JTable
	JTable t_column; 	// ������ ������ ���̺��� ������ ����� JTable
	
	JScrollPane s1,s2,s3,s4,s5;
	
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
	
	// 7) ���̺� �÷� ������ �ʿ��� ���͵�
	Vector columnList = new Vector();
	Vector<String> columType = new Vector<>();
	
	// 6) TableModel ����
	MyTableModel model;
		
	public DBMSClientApp2() {
		// ����
		p_west = new JPanel();
		ch_users = new Choice();
		t_pass = new JPasswordField();
		bt_login = new JButton("����");
		
		p_center = new JPanel();		
		p_upper = new JPanel();
		p_middle = new JPanel();
		p_footer = new JPanel();
		
		area = new JTextArea();
		bt_execute = new JButton("SQL�� ����");
		
		p_center.setLayout(new GridLayout(3,1));
		p_upper.setLayout(new GridLayout(1,2));
		p_middle.setLayout(new BorderLayout());
		p_footer.setLayout(new GridLayout(1,2));
		
		// 4-2) �÷�(����)���� �ʱ�ȭ�ϱ� -> ���̺� ������ ���̰� �ϱ� ���� ��ġ�� �ø���.!
		tableColumn.add("table_name");
		tableColumn.add("tablespace_name");
		
		t_tables = new JTable(tableList, tableColumn);	// 4) �ʱ� ���Ͱ��� �־��ּ���. ���� �޼��� ȣ��� ũ�Ⱑ ����� ���̰�
																			//		JTable�� ���ΰ�ħ �ϸ� �ǰ���?
		
		seqColumn.add("sequence_name");
		seqColumn.add("last_number");
		t_seq = new JTable(seqList,seqColumn);		// 5) ������ �����ڿ� ���� �����ϱ�(����)
		
		columType.add("�÷���");
		columType.add("������Ÿ��");		
		t_column = new JTable(columnList,columType); // 7)
		
		s1 = new JScrollPane(t_tables);
		s2 = new JScrollPane(t_seq);
		s3 = new JScrollPane(area); // ��� ����
		s5 = new JScrollPane(t_column);
		
		// ** ������ ���̺��� ���ڵ� ������ JTable ����
		t_record = new JTable(null);	// MyTableModel�� ������ ����
		s4 = new JScrollPane(t_record);
		
		// ���� �� ��Ÿ��
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
		
		p_center.add(p_upper);//�׸����� 1��
		p_center.add(p_middle);//�׸����� 2�� 
		p_center.add(p_footer);//�׸����� 3�� 
		
		add(p_west, BorderLayout.WEST);
		add(p_center);
				
		
		setVisible(true);
		setSize(900,750);	
		
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
				//System.out.println(t_tables.getValueAt(row, col));
				
				// 6) ������ ���̺� ���� select() �޼����� �μ��� �ѱ���
				String tableName = (String)t_tables.getValueAt(row, col);
				tableName = tableName.toLowerCase();	// �빮��->�ҹ���
				System.out.println(tableName);
				
				select(tableName);
				t_record.updateUI();// jtable ����
				//System.out.println("���� �÷� ī��Ʈ�� "+t_record.getColumnCount()); // new�� ������ ��� null
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
	
	// 6) ������ ���̺��� ���ڵ� ��������
	// �Ű������� �Ѿ����, ���̺� �� ����ϰ�, �ȳѾ���� ��ü SQL�� ��ü����.
	public void select(String tableName) {// �Ű����� = ������ ���̺� ��
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = null;
		if(tableName != null) {// ���̺���� �Ű������� �ѱ�� �Ʒ��� �������� ���
			sql = "select * from "+tableName;
			
		}else {
			sql = area.getText();
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
					
/*
 	�÷� vs ���ڵ� �� ������ �����ͷ� ���ֵǴ� ����? Į���̴�.
 	DB���� Table ������ �÷��� create������ ������ ���� ���ʹ� ������ �����̰�,
 	�� �÷��� record ����
*/
			/* --------------------------------------
				�÷� ���� ����� ���� �ڵ�
			 --------------------------------------*/
			Vector column = new Vector();	// �� ���ʹ� ���ο� TableModel ��ü�� �ν��Ͻ��� ���� �÷����Ϳ� ���Ե� ����
			ResultSetMetaData meta = rs.getMetaData();
			int columnCount = meta.getColumnCount();	// �� �÷���
			System.out.println("����� ������ "+tableName+" ���̺��� �÷����� "+columnCount);
			
			// �÷����� ���
			// ��¸� Ȯ������ ���� mytablemodel�� ���Ϳ� ������ ä������.
			for(int i =1;i<=columnCount;i++) {
				column.add(meta.getColumnName(i));
			}
			
			// rs�� ��� ��ƾ� �ұ�? TableModel �� MyTableModel�� ������ ���Ϳ� ������ �ȴ�.
			Vector record = new Vector();
			while(rs.next()) {				
				Vector vec = new Vector();
				//rs.getString(1);	// 1���� ����! ��, 1���� ����� �÷��� �����ϴ��� �˼��� ����.
										// �� �� �ִ� �����?
										// �̷����� ���̺� ���� ��Ÿ ������ �������� �ȴ�!
										// ��Ÿ �����Ͷ�? � ���� desc�� ��� ������.
				for(int i =1;i<=meta.getColumnCount();i++) {
					vec.add(rs.getString(i)); // ������ ä���
				}
				record.add(vec);	// ���Ϳ� �߰�
			}
			// �����͸� ���� ������ ���Ϳ� �÷��� ���� ������ ���͸� ���ο� �� ��ü�� �����ϸ鼭 ��������!
			model = new MyTableModel(record,column);	// ������ �Ű�����(�������÷� ����, �������÷� ����)
			// ���̺� ���� �����ڰ� �ƴ�, �޼���� ��������!
			t_record.setModel(model);
			
			// rs�� �ױ����� ��Ÿ�� ȣ������. -> finally
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
	
	// 7) ������ ������ ���̺��� ���� ���� ��������
	public void getColumnType(ResultSetMetaData meta) {
		try {
			int total = meta.getColumnCount();	// �� �÷� ��
			columnList.removeAll(columnList);
			for(int i=1;i<=total;i++) {
				System.out.println("�÷���"+meta.getColumnName(i)+"("+meta.getColumnTypeName(i)+")");
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
