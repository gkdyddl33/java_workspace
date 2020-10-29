package day1028.graphic;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class PaintTest2 extends JFrame{
	Canvas can;
	
	public PaintTest2() {
		can = new Canvas();
		can.setBackground(Color.YELLOW);
		add(can);
		
		setSize(300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	// 그래픽처리를 오버라이딩.. jframe이 가지고 있는 paint메소드 오버라이딩..
	// 그래서 우리가 직접 건들면 안됨..
	// 우리가 그래픽을 직접 안건드리고 할 수 있는 컴포넌트가 Canvas,JPanel
//	@Override
//	public void paint(Graphics g) {
//		System.out.println("저 지금 직접 스스로를 그려요");
//	}
	public static void main(String[] args) {
		new PaintTest2();
	}
}
