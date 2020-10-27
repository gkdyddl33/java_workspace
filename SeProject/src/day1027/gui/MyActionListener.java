package day1027.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

/*Action�� ������ �д�..(��ư���� Ŭ��, �ؽ�Ʈ�ڽ�-���� ��)*/
/*��ư�� Ŭ���̺�Ʈ�� �����غ���!*/
public class MyActionListener implements ActionListener {
	JButton bt;
	JTextArea area;

	public MyActionListener(JButton bt) {// ������ �Լ����� �Ű������� ����.
		this.bt = bt;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println("�� ������?");
		Object obj = e.getSource(); // �̺�Ʈ�� ����Ų ������Ʈ ��ȯ
		if (obj == bt) {
			String msg = bt.getText();
			area.append(msg + "\n");
			bt.setText("");
		}
	}
}
