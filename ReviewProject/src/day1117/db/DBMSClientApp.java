package day1117.db;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
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
	
	public DBMSClientApp() {
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
//				tableColumn.add("table_name");
//				tableColumn.add("tablespace_name");
//				
//				t_tables = new JTable(tableList, tableColumn);	// 4) �ʱ� ���Ͱ��� �־��ּ���. ���� �޼��� ȣ��� ũ�Ⱑ ����� ���̰�
//																					//		JTable�� ���ΰ�ħ �ϸ� �ǰ���?
//				
//				seqColumn.add("sequence_name");
//				seqColumn.add("last_number");
//				t_seq = new JTable(seqList,seqColumn);		// 5) ������ �����ڿ� ���� �����ϱ�(����)
//				
//				columType.add("�÷���");
//				columType.add("������Ÿ��");		
//				t_column = new JTable(columnList,columType); // 7)
				
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
	}
	public static void main(String[] args) {
		new DBMSClientApp();
	}
}
