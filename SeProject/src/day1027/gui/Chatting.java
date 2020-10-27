package day1027.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.JFrame;

public class Chatting extends JFrame{
	TextField t;
	TextArea area;
	Button bt;
	Panel p_center;
	Panel p_south;
	
	public Chatting() {
		t = new TextField();
		area = new TextArea();
		bt = new Button("전송");
		p_center = new Panel();
		p_south = new Panel();
		
		// 프레임에 부착. 단, center와 south로 주문후 부착
		p_center.setBackground(Color.WHITE);
		this.add(p_center,BorderLayout.CENTER);
		p_south.setBackground(Color.WHITE);
		this.add(p_south,BorderLayout.SOUTH);
			
		p_center.add(t);
		p_south.setLayout(new FlowLayout());
		p_south.add(area);
		p_south.add(bt);
		
		t.setBackground(Color.YELLOW);
		t.setPreferredSize(new Dimension(160,160));
		bt.setBackground(Color.BLACK);
		bt.setForeground(Color.WHITE);
		
		setSize(300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new Chatting();
	}
}
