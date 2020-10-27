package gui;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;

public class MultiButton extends Frame{
	Button bt1,bt2;
	TextField t;
	
	public MultiButton() {// 생성자함수로 "초기화"
		bt1 = new Button("버튼1");
		bt2 = new Button("버튼2");
		t = new TextField(20);
		
		setLayout(new FlowLayout());		
		add(bt1);
		add(bt2);
		add(t);
		
		// (1) 버튼과 텍스트박스에 리스너연결(미리선언)
		// new를 ()안에 여러번 선언하지않기위해
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
