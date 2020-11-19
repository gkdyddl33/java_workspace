package com.swingmall.admin.product;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ProductModel extends AbstractTableModel{

	// new �� ���� ������ row ���� nullpointexception�� �߻�
	// ���ڵ带 ��Ե� ����Ʈ
	ArrayList<ProductVO> record = new ArrayList<>();
	
	// �÷������� ���� ArrayList ����
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
		// ��� : if���� �÷��� �ش��ϴ� ���� �ֱ� ���� ó��..
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
