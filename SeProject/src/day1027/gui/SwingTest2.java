package day1027.gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class SwingTest2 extends JFrame{
	JCheckBox ch;
	JButton bt;
	
	public SwingTest2() {
		ch = new JCheckBox("��ȭ");
		bt = new JButton("����ư");
		add(ch);
		add(bt);
		
		setLayout(new FlowLayout());
		setSize(300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new SwingTest2();
	}
}
