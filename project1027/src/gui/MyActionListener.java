package gui;

import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener implements ActionListener{
	Button bt1;
	Button bt2;
	TextField t;
	
	public MyActionListener(Button bt1,Button bt2,TextField t) {
		this.bt1=bt1;	// ��������� ���������� ����ϱ� ���� this
		this.bt2=bt2;
		this.t=t;
	}
	@Override
	public void actionPerformed(ActionEvent e) {//�������̵�
		// �̺�Ʈ�� ����Ų ������Ʈ ��ȯ!!
		Object obj = e.getSource();	// ������ü�� ������ü�� ����ų������
		// �׷��� ��ư�� ���� �̺�Ʈ��� ��ư�� �ּҰ��� Object��(���� ��ü��)����
		// �ް� �ǰ�, ��ư�� �ƴ϶� TextField�� ���� �̺�Ʈ���..���� ����..
		if(obj==bt1) {
			System.out.println("bt1�� �����׿�");
		}else if(obj==bt2) {
			System.out.println("bt2�� �����׿�");
		}else if(obj==t) {
			System.out.println("t�� �����׿�");
		}
		System.out.println(e);		
	}

}
