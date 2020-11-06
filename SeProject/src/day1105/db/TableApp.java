package day1105.db;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*
 	���� ������Ʈ ��, �� ���� ������ ������(ǥ)�� ǥ���ϱ⿡ ����ȭ ��
 	JTable �� ����غ���.
 */
public class TableApp extends JFrame{
	// <ǥ�� ������༭ ����,�����ϱ� ����>
	JTable table;
	String[] column = {"��ȣ","�̸�","����ó","�ּ�","����"};
	String[][] data = {
			{"1","Batman","011","����","��"},
			{"2","superman","017","ũ����","��"},
			{"3","wonder","019","�Ƹ���","��"}
	};
	
	JScrollPane scroll;
	
	public TableApp() {
		// ����
		table = new JTable(data,column); // row,col		
		scroll = new JScrollPane(table);
		
		// ����
		setLayout(new FlowLayout());
		add(scroll);		
		
		// �̺�Ʈ ����!
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				// �̷��� ��ǥ�� �˰� �Ǹ��� �������� ���� ������ �� �ִ�.
				int col = table.getSelectedColumn(); // ������ ������ �÷� index(ȣ��)
				int row = table.getSelectedRow(); // ������ ������ row (����)
				System.out.println("��ǥ( "+row+","+col+")");
				
				String value = (String)table.getValueAt(row, col);
				System.out.println(value);
			}
		});
		
		// ������
		setFont(new Font("Verdana", Font.BOLD, 10));
		setSize(400,200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	public static void main(String[] args) {
		new TableApp();
	}
}
