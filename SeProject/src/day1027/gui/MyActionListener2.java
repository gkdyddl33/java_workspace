package day1027.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class MyActionListener2 implements ActionListener{
	JButton bt;	
	JTextArea area;
	
	public MyActionListener2(JButton bt) {// ������ �Լ����� �Ű������� ����.
		this.bt = bt;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println("�� ������?");
		Object obj = e.getSource(); // �̺�Ʈ�� ����Ų ������Ʈ ��ȯ
		if(obj==bt) {
			String msg = bt.getText();
			area.append(msg+"\n");
			bt.setText("");
		}
	}
}
