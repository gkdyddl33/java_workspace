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
		columnName.add("ID");			//0
		columnName.add("산이름");	//1
		columnName.add("소재지");	//2
		columnName.add("높이");		//3
		
		// 데이터 거짓말로 구성해보기(테스트를 위해)
		Mountain mt = new Mountain();
		mt.setMntnid(1);
		mt.setMntnnm("설악산");
		mt.setMntninfopoflc("강원도");
		mt.setMntninfohght(1000);
		data.add(mt);
		
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

	// 2차원 배열이나 벡터는 모두 배열구조이므로 지금까지는 [row][col]형태로 데이터를 추출하였다.
	// 이제는 데이터가 VO이므로 [row]에 대한 접근은 가능하지만 [col]에 대한 접근은 불가하다	
	// 해결책? 조건문을 써야 한다. VO는 층수는 있지만 호수가 없기에 우리가 조건을 달아야 한다.
	public Object getValueAt(int row, int col) {
		System.out.println("row 값 = "+row+"col 값 = "+col);
		Mountain mt = data.get(row);		// row 번째에서 산을 끄집어 낸다..
		
		String obj = null;	// 각 조건에 따라 반환될 데이터
		if(col==0) {
			obj = Integer.toString(mt.getMntnid());
		}else if(col==1) {
			obj = mt.getMntnnm();
		}else if(col==2) {
			obj = mt.getMntninfopoflc();
		}else if(col==3) {
			obj = Integer.toString(mt.getMntninfohght());
		}
		// 이 메서드의 반환형이 오브젝트 형이므로, 우리는 객체형 으로 반환해야 된다.
		return obj;
	}

}
