package day1106.db;

import javax.swing.table.AbstractTableModel;

/*
	8) JTable �� �����ο� �Ұ��ϹǷ�, �� Ŭ�����κ��� �����Ϳ� ���� ������ ��������.
	���� TableModel �������̽��� ������ ��ü�� AbstractTableModel�� ��ӹ���
	
*/
public class ProductController extends AbstractTableModel{
	String[][] data= {}; // ä������ ����? ��Ϲ�ư�� ������ ä������.
	String[] column = {"product_id","subcategory_id","product_name","brand","price","filename"};
	
	@Override
	public int getRowCount() {
		return data.length;
	}
	
	@Override
	public int getColumnCount() {
		return column.length;
	}

	// �÷��� ���� ��������
	// JTable�� getColumnCount()�� ������ŭ ȣ���ϸ鼭 ������� 0,1,2,3,4,5�� 
	// �ѱ�鼭 �÷� ������ ��������.
	@Override
	public String getColumnName(int col) {		
		return column[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

}
