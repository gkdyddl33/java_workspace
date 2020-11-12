package day1111.board;

import javax.swing.table.AbstractTableModel;

/*
 	JTable ���� ������ �����ϴ� ��ü�� TableModel�� ���������� 	
*/

public class BoardModel extends AbstractTableModel{

	/*
	 	JTable�� ���� �޼��� ������
	 	��, �Ʒ��� �޼������ JTable�� ȣ���� ����. 
	 */
	
	String[] column = {"board_id","title","writer","regdate","hit"}; // content�� ���̸� �ȵ�
	String[][] data = {};
		
	@Override
	public int getRowCount() {
		return data.length;
	}
	
	@Override
	public int getColumnCount() {
		return column.length;
	}
	
	// �÷������� ����ϱ� ���ؼ��� �޼��带 �������̵� ����.
	@Override
	public String getColumnName(int col) {
		return column[col];
	}
	
	// �ش� ��ǥ�� ������ -> BoardList�� ������? �����ڸ� �Ѱܶ�.
	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
}
