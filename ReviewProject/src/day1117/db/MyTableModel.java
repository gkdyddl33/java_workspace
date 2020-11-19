package day1117.db;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel{

	Vector<Vector> record = new Vector<>();
	Vector<String> column = new Vector<>();
	
	public MyTableModel(Vector record,Vector column) {
		this.record=record;
		this.column=column;
	}
	@Override
	public int getColumnCount() {

		return column.size();
	}

	@Override
	public int getRowCount() {

		return record.size();
	}

	
	public String getColumnName(int col) {
		return column.get(col);
	}
	
	@Override
	public Object getValueAt(int row, int col) {

		return record.get(row).get(col);
	}

}
