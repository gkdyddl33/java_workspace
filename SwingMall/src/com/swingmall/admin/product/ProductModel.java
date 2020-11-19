package com.swingmall.admin.product;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ProductModel extends AbstractTableModel{

	// new 를 하지 않으면 row 에서 nullpointexception이 발생
	// 레코드를 담게될 리스트
	ArrayList<ProductVO> record = new ArrayList<>();
	
	// 컬럼정보를 위한 ArrayList 선언
	ArrayList<String> column = new ArrayList<>();
	
	public int getRowCount() {
		return record.size();
	}
	
	public int getColumnCount() {	
		return column.size();
	}
	
	public String getColumnName(int col) {
		return column.get(col);
	}

	public Object getValueAt(int row, int col) {
		ProductVO vo = record.get(row);
		String obj = null;
		// 결론 : if문은 컬럼에 해당하는 값을 넣기 위한 처리..
		if(col==0) {
			obj = Integer.toString(vo.getProduct_id());
		}else if(col==1) {
			obj = Integer.toString(vo.getSubcategory_id());
		} else if(col==2) {
			obj = vo.getProduct_name();
		}else if(col==3) {
			obj = vo.getBrand();
		} else if(col==4) {
			obj = Integer.toString(vo.getPrice());
		} else if(col==5) {
			obj = vo.getFilename();
		} else if(col==6) {			
			obj = vo.getDetail();
		}
		return obj;
	}

}
