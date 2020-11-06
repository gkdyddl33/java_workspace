package day1106.db;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*
	JTable 더 자세히 알아보기
	JTable 은 MVC 패턴을 제공한다.
	
	MVC패턴이 적용된 JTable에서 사용되는 TableModel은 명칭으로는 마치 모델인 것처럼 보이지만,
	그 역할로 본다면 컨트롤러이다!
	
	JTable(View) ---------컨트롤러(TableModel)--------- 보여질 데이터(Model)
*/
public class JTableApp extends JFrame{
	JTable table;
	JScrollPane scroll;
	MyModel model;
	
	public JTableApp() {
		// 생성
		// JTable 과 컨트롤러인 MyModel 연결..
		table = new JTable(model=new MyModel());
		scroll = new JScrollPane(table);
		add(scroll);
		
		setSize(400,200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new JTableApp();
	}
}
