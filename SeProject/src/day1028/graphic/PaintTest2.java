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
	// �׷���ó���� �������̵�.. jframe�� ������ �ִ� paint�޼ҵ� �������̵�..
	// �׷��� �츮�� ���� �ǵ�� �ȵ�..
	// �츮�� �׷����� ���� �Ȱǵ帮�� �� �� �ִ� ������Ʈ�� Canvas,JPanel
//	@Override
//	public void paint(Graphics g) {
//		System.out.println("�� ���� ���� �����θ� �׷���");
//	}
	public static void main(String[] args) {
		new PaintTest2();
	}
}
