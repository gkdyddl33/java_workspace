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
	
	public DBMSClientApp() {
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
//				tableColumn.add("table_name");
//				tableColumn.add("tablespace_name");
//				
//				t_tables = new JTable(tableList, tableColumn);	// 4) 초기 벡터값을 넣어주세요. 추후 메서드 호출시 크기가 변경될 것이고
//																					//		JTable을 새로고침 하면 되겟져?
//				
//				seqColumn.add("sequence_name");
//				seqColumn.add("last_number");
//				t_seq = new JTable(seqList,seqColumn);		// 5) 시퀀스 생성자에 벡터 적용하기(제목)
//				
//				columType.add("컬럼명");
//				columType.add("데이터타입");		
//				t_column = new JTable(columnList,columType); // 7)
				
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
	}
	public static void main(String[] args) {
		new DBMSClientApp();
	}
}
