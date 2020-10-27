package gui;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionListener;

public class MultiButton2 extends Frame{
	Button bt1,bt2;
	TextField t;
	private ActionListener listener;
	public MultiButton2() {
		bt1 = new Button("버튼1");
		bt2 = new Button("버튼2");
		t = new TextField(20);
		
		setLayout(new FlowLayout());
		add(bt1);
		add(bt2);
		add(t);
		
		// 버튼과 텍스트박스에 리스너 연결(미리 선언)
		MyActionListener2 listener = new MyActionListener2(bt1, bt2, t);
		bt1.addActionListener(listener);
		bt2.addActionListener(listener);
		t.addActionListener(listener);
		
		setVisible(true);
		setSize(300,400);
	}
}
