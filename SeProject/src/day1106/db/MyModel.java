package day1106.db;

import javax.swing.table.AbstractTableModel;

public class MyModel extends AbstractTableModel{
	// 데이터 준비
	String[][] flower = {
			{"장미","50000","RED","Korea"},
			{"튤립","70000","Purple","France"},
			{"안개꽃","35000","White","Korea"}
	};
	
	String[][] car = {
			{"BMW","7000","white"},
			{"Benz","9500","silver"},
			{"Audi","8000","black"}
	};
	
	@Override
	public int getRowCount() {
		// 행의 갯수를 반환하는 메서드
		return flower.length;
	}
	
	@Override
	public int getColumnCount() {
		// 열의 갯수를 반환하는 메서드
		return flower[0].length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		// 지정한 좌표의 값을 반환
		System.out.println("row:"+row+","+"col:"+col);
		return flower[row][col];
	}

}
