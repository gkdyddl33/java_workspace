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
		columnName.add("ID");
		columnName.add("���̸�");
		columnName.add("������");
		columnName.add("����");
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

	public Object getValueAt(int rowIndex, int columnIndex) {

		return null;
	}

}
