package day1116.pub.api;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class MountainModel extends AbstractTableModel{
/*
	이제 이차원배열이 아니라, Vector 를 선언해서 데이터와 컬럼명을 처리해보겠습니다.
	Vector는 컬렉션 프레임웍이니 배열처럼 생성시 크기를 명시하지 않아도 됩니다..
*/
	Vector<Mountain> data = new Vector<>();
	Vector<String> columnName = new Vector<>();	// 컬럼 제목의 정보를 담는 백터
	
	public MountainModel() {
		// 칼럼 제목을 생성시 바로 초기화 말고 생성자에 만들자.
		columnName.add("ID");
		columnName.add("산이름");
		columnName.add("소재지");
		columnName.add("높이");
	}
	public int getColumnCount() {
		return columnName.size();
	}
	
	public int getRowCount() {
		return data.size();
	}

	// 배열이 아니므로 get(인덱스)로 가져와야 합니다.
	public String getColumnName(int col) {
		return columnName.get(col);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {

		return null;
	}

}
