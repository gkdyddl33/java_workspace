package day1028.graphic;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class MyCanvas2 extends Canvas{
	Toolkit kit;
	Image img;
	
	// ������ �ʱ�ȭ�� ����
	public MyCanvas2() {
		kit = Toolkit.getDefaultToolkit(); // �̹��� �ν��Ͻ��� ��ȯ���ִ� ��� ���
		img = kit.getImage("D://workspace//java_workspace//SeProject//res//travel2//aa.jpg");
	}
	@Override
	public void paint(Graphics g) {// �׷��� = �ȷ�Ʈ = �׷��� ó�� �����..
		g.drawLine(50, 50, 300, 350);	// ��
		g.drawOval(0, 50, 200, 200);		// ��
		g.drawRect(50, 100, 100, 200);  // �簢��
		
		// ���� ����
		g.setColor(Color.RED);
		g.fillRect(200, 100, 50, 50); // �׸� �簢���ȿ� ������ �����
		
		g.setColor(Color.BLUE);
		g.drawString("�̰� �׸��۾���", 50, 200);
		
		// �̹��� =  �߻� = Toolkit�� ��������.
		g.drawImage(img, 20, 20, this);
	}
	
}
