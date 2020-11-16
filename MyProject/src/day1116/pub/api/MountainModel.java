package day1116.pub.api;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class MountainModel extends AbstractTableModel{
/*
	���� �������迭�� �ƴ϶�, Vector �� �����ؼ� �����Ϳ� �÷����� ó���غ��ڽ��ϴ�.
	Vector�� �÷��� �����ӿ��̴� �迭ó�� ������ ũ�⸦ ������� �ʾƵ� �˴ϴ�..
*/
	Vector<Mountain> data = new Vector<>();
	Vector<String> columnName = new Vector<>();	// �÷� ������ ������ ��� ����
	
	public MountainModel() {
		// Į�� ������ ������ �ٷ� �ʱ�ȭ ���� �����ڿ� ������.
		columnName.add("ID");			//0
		columnName.add("���̸�");	//1
		columnName.add("������");	//2
		columnName.add("����");		//3
		
		// ������ �������� �����غ���(�׽�Ʈ�� ����)
		Mountain mt = new Mountain();
		mt.setMntnid(1);
		mt.setMntnnm("���ǻ�");
		mt.setMntninfopoflc("������");
		mt.setMntninfohght(1000);
		data.add(mt);
		
	}
	public int getColumnCount() {
		return columnName.size();
	}
	
	public int getRowCount() {
		return data.size();
	}

	// �迭�� �ƴϹǷ� get(�ε���)�� �����;� �մϴ�.
	public String getColumnName(int col) {
		return columnName.get(col);
	}

	// 2���� �迭�̳� ���ʹ� ��� �迭�����̹Ƿ� ���ݱ����� [row][col]���·� �����͸� �����Ͽ���.
	// ������ �����Ͱ� VO�̹Ƿ� [row]�� ���� ������ ���������� [col]�� ���� ������ �Ұ��ϴ�	
	// �ذ�å? ���ǹ��� ��� �Ѵ�. VO�� ������ ������ ȣ���� ���⿡ �츮�� ������ �޾ƾ� �Ѵ�.
	public Object getValueAt(int row, int col) {
		System.out.println("row �� = "+row+"col �� = "+col);
		Mountain mt = data.get(row);		// row ��°���� ���� ������ ����..
		
		String obj = null;	// �� ���ǿ� ���� ��ȯ�� ������
		if(col==0) {
			obj = Integer.toString(mt.getMntnid());
		}else if(col==1) {
			obj = mt.getMntnnm();
		}else if(col==2) {
			obj = mt.getMntninfopoflc();
		}else if(col==3) {
			obj = Integer.toString(mt.getMntninfohght());
		}
		// �� �޼����� ��ȯ���� ������Ʈ ���̹Ƿ�, �츮�� ��ü�� ���� ��ȯ�ؾ� �ȴ�.
		return obj;
	}

}
