package day1106.db;

import javax.swing.table.AbstractTableModel;

public class MyModel extends AbstractTableModel{
	// ������ �غ�
	String[][] flower = {
			{"���","50000","RED","Korea"},
			{"ƫ��","70000","Purple","France"},
			{"�Ȱ���","35000","White","Korea"}
	};
	
	String[][] car = {
			{"BMW","7000","white"},
			{"Benz","9500","silver"},
			{"Audi","8000","black"}
	};
	
	@Override
	public int getRowCount() {
		// ���� ������ ��ȯ�ϴ� �޼���
		return flower.length;
	}
	
	@Override
	public int getColumnCount() {
		// ���� ������ ��ȯ�ϴ� �޼���
		return flower[0].length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		// ������ ��ǥ�� ���� ��ȯ
		System.out.println("row:"+row+","+"col:"+col);
		return flower[row][col];
	}

}
