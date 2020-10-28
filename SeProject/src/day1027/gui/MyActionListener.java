package day1027.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*Action�� ������ �д�..(��ư���� Ŭ��, �ؽ�Ʈ�ڽ�-���� ��)*/
/*��ư�� Ŭ���̺�Ʈ�� �����غ���!*/
public class MyActionListener implements ActionListener {
	JTextField t_input;
	JTextArea area;

	public MyActionListener(JTextField t_input, JTextArea area) {// ������ �Լ����� �Ű������� ����.
		this.t_input = t_input;
		this.area = area; // ���������°��� �����ϱ����� this
							// this�� �Ⱥ��̸� ���������� �ȴ�.
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println("�� ������?");		

		String msg = t_input.getText(); // �ؽ�Ʈ�ʵ尪���ϱ�
		area.append(msg + "\n");
		t_input.setText("");

	}
}
