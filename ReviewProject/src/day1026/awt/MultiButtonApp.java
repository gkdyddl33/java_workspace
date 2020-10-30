package day1026.awt;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;

public class MultiButtonApp extends Frame{
	Button bt1,bt2;
	
	public MultiButtonApp() {
		bt1 = new Button();
		bt2 = new Button();
		setLayout(new FlowLayout());
		
		add(bt1);
		add(bt2);
		
		// 버튼과 리스너
		bt1.addActionListener(new MultiActionListener());
		bt2.addActionListener(new MultiActionListener());
		
		setSize(300,400);
		setVisible(true);
	}
	public static void main(String[] args) {
		new MultiButtonApp();
	}
}
