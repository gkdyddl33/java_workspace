package day1111.board;

import javax.swing.table.AbstractTableModel;

/*
 	JTable 에게 정보를 제공하는 객체인 TableModel을 재정의하자 	
*/

public class BoardModel extends AbstractTableModel{

	/*
	 	JTable을 위한 메서드 재정의
	 	즉, 아래의 메서드들은 JTable이 호출해 간다. 
	 */
	
	String[] column = {"board_id","title","writer","regdate","hit"}; // content는 보이면 안됨
	String[][] data = {};
		
	@Override
	public int getRowCount() {
		return data.length;
	}
	
	@Override
	public int getColumnCount() {
		return column.length;
	}
	
	// 컬럼제목을 출력하기 위해서는 메서드를 오버라이드 하자.
	@Override
	public String getColumnName(int col) {
		return column[col];
	}
	
	// 해당 좌표의 데이터 -> BoardList의 연결은? 생성자를 넘겨라.
	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
}
