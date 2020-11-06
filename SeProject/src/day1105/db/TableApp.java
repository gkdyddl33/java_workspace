package day1105.db;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*
 	스윙 컴포넌트 중, 이 차원 구조의 데이터(표)를 표현하기에 최적화 된
 	JTable 을 사용해본다.
 */
public class TableApp extends JFrame{
	// <표를 만들어줘서 수정,변경하기 용이>
	JTable table;
	String[] column = {"번호","이름","연락처","주소","성별"};
	String[][] data = {
			{"1","Batman","011","고담시","남"},
			{"2","superman","017","크립톤","남"},
			{"3","wonder","019","아마존","여"}
	};
	
	JScrollPane scroll;
	
	public TableApp() {
		// 생성
		table = new JTable(data,column); // row,col		
		scroll = new JScrollPane(table);
		
		// 조립
		setLayout(new FlowLayout());
		add(scroll);		
		
		// 이벤트 구현!
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				// 이렇게 좌표를 알게 되면은 데이터의 값을 가져올 수 있다.
				int col = table.getSelectedColumn(); // 유저가 선택한 컬럼 index(호수)
				int row = table.getSelectedRow(); // 유저가 선택한 row (층수)
				System.out.println("좌표( "+row+","+col+")");
				
				String value = (String)table.getValueAt(row, col);
				System.out.println(value);
			}
		});
		
		// 윈도우
		setFont(new Font("Verdana", Font.BOLD, 10));
		setSize(400,200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	public static void main(String[] args) {
		new TableApp();
	}
}
