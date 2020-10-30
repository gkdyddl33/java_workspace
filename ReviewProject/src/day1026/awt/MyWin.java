package day1026.awt;

import java.awt.Button;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;

public class MyWin extends Frame{
	Button bt;
	TextField t;
	Choice ch;
	
	public MyWin() {
		bt = new Button("��������");
		t = new TextField(15);
		ch = new Choice();
		
		setLayout(new FlowLayout());
		add(bt);
		add(t);
		add(ch);
		
		ch.add("Java programming");
		ch.add("Jsp programming");
		ch.add("Android programming");
		ch.add("Spring programming");
		ch.add("Mybatis programming");
		
		// (1) ��ư�� ������ ����
		bt.addActionListener(new MyListener());
		// (2) �ؽ�Ʈ�ڽ��� ������ ����
		t.addKeyListener(new MyKey());
		// (3) ���콺
		addMouseListener(new MyMouse());
		// (4)����
		ch.addItemListener(new MyItem());
		// (5) ������
		addWindowListener(new MyWindowListener());
		
		setSize(300,400);
		setVisible(true);
	}
	public static void main(String[] args) {
		new MyWin();
	}
}
