package day1028.graphic;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
/*
 * ���ݱ����� sun���� �����ڰ� �������� �״�� ������Ʈ���� ���ƿ�����,
 * �� ���������� �츮�� ������Ʈ�� �׷����� ����� �����Ͽ�, ���ϴ� �׸����� 
 * ������Ʈ�� ���������� ó���غ���!!
 */
public class PaintTest extends JFrame {
	MyCanvas can;	// (2) ��ȭ���� ǥ���� ������Ʈ -> (4) Canvas���� ����
	
	public PaintTest() {
		can = new MyCanvas();
		
		// (3) �Ⱥ��̴� ��׶���� ǥ���غ���.
		can.setBackground(Color.YELLOW);
		// (4) ĵ������ �׸��� �׸�����, ĵ������ ������ �׸��� �޼����� .paint()�� �������ؾ��Ѵ�.
		
		add(can);	// (2) ĵ������ �����ӿ� ����
		
		setSize(300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	// ���� Ŭ������ PaintTest�� JFrame�� paint.�޼ҵ带 �������̵��ϸ�
	// ����� �ڽ��� �������� �޼��带 �켱������ ȣ�����ش�.
	// ��ư�� �����ϸ� �ȵǿ� ��..�Ϸ��� ����� ����..�׳� ����������..
	// �׷��� ������Ʈ �� �����ڰ� �ֵ��ؼ� �׸��� �׸� �� �ִ� ������Ʈ..����̳� ��..
	// Canvas(AWT), JPanel(�� ����ִ� ������Ʈ)
	
//	(1) @Override
//		public void paint(Graphics g) {//			
//			System.out.println("�� ���� ���� �����θ� �׷���");
//		}
	public static void main(String[] args) {
		new PaintTest();
	}
}
