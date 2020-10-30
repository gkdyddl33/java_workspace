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
		bt = new Button("나눌러봐");
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
		
		// (1) 버튼과 리스너 연결
		bt.addActionListener(new MyListener());
		// (2) 텍스트박스와 리스너 연결
		t.addKeyListener(new MyKey());
		// (3) 마우스
		addMouseListener(new MyMouse());
		// (4)선택
		ch.addItemListener(new MyItem());
		// (5) 윈도우
		addWindowListener(new MyWindowListener());
		
		setSize(300,400);
		setVisible(true);
	}
	public static void main(String[] args) {
		new MyWin();
	}
}
