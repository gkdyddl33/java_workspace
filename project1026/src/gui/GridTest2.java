package gui;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;

public class GridTest2 {
	public static void main(String[] args) {
		Frame frame = new Frame();
		
		GridLayout layout = new GridLayout(1,3);
		frame.setLayout(layout);
		frame.setVisible(true);
		frame.setSize(300,200);
		
		Panel p = new Panel();
		Button bt1 = new Button("��ư1");
		Button bt2 = new Button("��ư2");
		Button bt3 = new Button("��ư3");
		p.add(bt1);
		p.add(bt2);
		p.add(bt3);
		frame.add(bt1);
		frame.add(bt2);
		frame.add(bt3);
	}
}
