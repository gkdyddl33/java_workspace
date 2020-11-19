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
		
		tableColumn.add("table_name");
		tableColumn.add("tablespace_name");
		t_tables = new JTable(tableList,tableColumn);
		
		seqColumn.add("sequence_name");
		seqColumn.add("last_number");
		t_seq = new JTable(seqList,seqColumn);
		
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
				
				// select �Ű������� �Ѱ� �f��.
				String tableName = (String) t_tables.getValueAt(row, col);
				tableName = tableName.toLowerCase();
				select(tableName);
				t_record.updateUI(); // jtable����
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
	
	// ���� ��� �������� select
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
	// �α���
	public void login() {
		// system���� �α��� ����..
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
			while(rs.next()) {// ���̺��� ����.
				Vector vec = new Vector();
				vec.add(rs.getString("table_name"));
				vec.add(rs.getString("tablespace_name"));
				tableList.add(vec);				
			}
			// �Ѱ� �Ϸ�? �׷� ����!
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
		//������ ���ڵ� ��������
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
			
			// -------- �÷� ���� ����� ���� �ڵ�
			// �� ���ʹ� ���ο� TableModel ��ü�� �ν��Ͻ��� ���� �÷����Ϳ� ���Ե� ����
			Vector column = new Vector();
			// wrapper �� ������ �ִ°Ÿ� ������ ���ٰ� ��������.
			ResultSetMetaData meta = rs.getMetaData();
			int columnCount = meta.getColumnCount();
			
			// �÷� ���� ���
			for(int i=1;i<=columnCount;i++) {
				column.add(meta.getColumnName(i)); // �ȿ� ���ΰ� �ִ� rs �� ���� meta����..
			}
			
			// rs = ���ڵ� �� ���� ������ �ִ� mytablemodel�� ������ �ȴ�.
			Vector record = new Vector();
			while(rs.next()) {
				Vector vec = new Vector();
				for(int i=1;i<=meta.getColumnCount();i++) {
					vec.add(rs.getString(i));					
				}
				record.add(vec);
			}
			// record �� ���� ������ ������ ���͸� ���ο� �� ��ü�� �����ϸ鼭 ��������.
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
	// ������ ���̺��� �������� ��������
	public void getColumnType(ResultSetMetaData meta) {
		// �� �ȿ� ������ �ִ� ������ ����ϱ�		
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
