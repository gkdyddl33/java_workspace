package gui;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

public class Test {
	public static void main(String[] args) {
		// ������ ����
		Frame frame = new Frame("�α��� ���");
		frame.setVisible(true);
		frame.setSize(300,400);
		
		// id,pass �Է¶� ����
		GridLayout grid = new GridLayout(2,1);
		frame.setLayout(grid);
		
		
		Label label1 = new Label("ID");
		Label label2 = new Label("Password");
		frame.add(label1);
		frame.add(label2);
		
		TextField tf1 = new TextField("",10);
		TextField tf2 = new TextField("",10);
		frame.add(tf1);
		frame.add(tf2);
		
		// �Ʒ� ��ư ����		
		Panel p = new Panel();			
		Button bt1 = new Button("�α���");
		Button bt2 = new Button("ȸ������");
		
		p.add(bt1);
		p.add(bt2);
	}
	
}
