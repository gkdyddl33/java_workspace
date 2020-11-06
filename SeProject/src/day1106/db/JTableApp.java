package day1106.db;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*
	JTable �� �ڼ��� �˾ƺ���
	JTable �� MVC ������ �����Ѵ�.
	
	MVC������ ����� JTable���� ���Ǵ� TableModel�� ��Ī���δ� ��ġ ���� ��ó�� ��������,
	�� ���ҷ� ���ٸ� ��Ʈ�ѷ��̴�!
	
	JTable(View) ---------��Ʈ�ѷ�(TableModel)--------- ������ ������(Model)
*/
public class JTableApp extends JFrame{
	JTable table;
	JScrollPane scroll;
	MyModel model;
	
	public JTableApp() {
		// ����
		// JTable �� ��Ʈ�ѷ��� MyModel ����..
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
