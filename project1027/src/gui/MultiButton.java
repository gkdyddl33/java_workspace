package gui;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;

public class MultiButton extends Frame{
	Button bt1,bt2;
	TextField t;
	
	public MultiButton() {// �������Լ��� "�ʱ�ȭ"
		bt1 = new Button("��ư1");
		bt2 = new Button("��ư2");
		t = new TextField(20);
		
		setLayout(new FlowLayout());		
		add(bt1);
		add(bt2);
		add(t);
		
		// (1) ��ư�� �ؽ�Ʈ�ڽ��� �����ʿ���(�̸�����)
		// new�� ()�ȿ� ������ ���������ʱ�����
		MyActionListener listener = new MyActionListener(bt1,bt2,t);
		bt1.addActionListener(listener);
		bt2.addActionListener(listener);
		t.addActionListener(listener);
		
		setVisible(true);
		setSize(300,400);
		
	}
	public static void main(String[] args) {
		new MultiButton();
	}
}
