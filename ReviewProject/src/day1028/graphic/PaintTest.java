package day1028.graphic;

import java.awt.Color;

import javax.swing.JFrame;

public class PaintTest extends JFrame{
	MyCanvas can;
	
	public PaintTest() {
		can = new MyCanvas();
		
		can.setBackground(Color.YELLOW);
		add(can);
		
		setSize(300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new PaintTest();
	}
}
