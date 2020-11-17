package day1117.db;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel{
// ���̺� ������ ���ڵ带 ó���ϴ� ���� ����
	Vector<Vector> record = new Vector();
// ���̺� ������ �÷� ������ ���� ���� ����
	Vector<String> column = new Vector<>();
	
	public MyTableModel(Vector record,Vector column) {
		this.record=record;
		this.column=column;
	}
	public int getColumnCount() {
		return column.size();
	}

	public int getRowCount() {
		return record.size();
	}

	public String getColumnName(int col) {
		return column.get(col);
	}
	
	public Object getValueAt(int row, int col) {
// ���� ���� ������ ����.		
		return record.get(row).get(col);
	}

	
}
