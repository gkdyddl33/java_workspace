package day1027.gui;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;

public class MultiButton extends Frame{
	Button bt1,bt2;
	TextField t;
	
	public MultiButton() {
		bt1 = new Button();
		bt2 = new Button();
		t = new TextField();
		
		setLayout(new FlowLayout());
		add(bt1);
		add(bt2);
		add(t);
		
		MyActionListener listener = new MyActionListener(bt1,bt2,t);
		bt1.addActionListener(listener);
		bt2.addActionListener(listener);
		t.addActionListener(listener);
		
		setSize(300,400);
		setVisible(true);
	}
	public static void main(String[] args) {
		new MultiButton();
	}
}
