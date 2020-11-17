package day1117.db;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel{
// 테이블에 보여질 레코드를 처리하는 벡터 선언
	Vector<Vector> record = new Vector();
// 테이블에 보여질 컬럼 정보를 갖는 벡터 선언
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
// 층을 먼저 끄집어 내자.		
		return record.get(row).get(col);
	}

	
}
