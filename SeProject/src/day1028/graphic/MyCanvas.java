package day1028.graphic;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class MyCanvas extends Canvas{
	// (5) �̹��� - �÷���(os or mac..)�� ���� �˸°� �ڿ��� ������ �� �ֵ��� ���ִ� ��ü
	Toolkit kit;
	Image img;	// �� ���̵� �߻�������, Toolkit���� ���� �� �ִ�.
	
	public MyCanvas() {
		// (5) toolkit�� �߻��̷̹� �ν��Ͻ��� ��ȯ���ִ� �޼��带 �̿�����.
		kit = Toolkit.getDefaultToolkit();	// ��ü �޼��带 ���� �ν��Ͻ� ��� ��� 
		img = kit.getImage("D://workspace//java_workspace//SeProject//res//travel2//aa.jpg");	// �ϵ��Ʈ ��θ� ���� �� ����=������os
	}
	
	// (4) can�� ���� �����Ǹ� �ϴ� ���� - ��, �θ��� ĵ������ ������ paint�޼��带
	// �����ع�����, �츮 ��Ȳ�� �°� ������ ����. = �������� �ö�´� �ؼ� over ride
	@Override
	public void paint(Graphics g) {// �׷����� �߻�Ŭ����
		// �Ű������� Graphics ��ü�� ���ǿ����� �ȷ�Ʈ�� ������ �� ������
		// ����, ���� �����ϴ� ���� �ƴ϶� ����,�ؽ�Ʈ,�̹���,��,�� �� 
		// �پ��� �׷��� ó���� ���� ����� �����ϴ� ������ �׷��� ó�� ������̴�.
		g.drawLine(50, 50, 300, 350);  // ĵ������ �� �׸���
		g.drawOval(0, 50, 200, 200);   //  �� ��� �׸���
		g.drawRect(50, 100, 100, 200);	// �簢�� �׸���
		
		// ���� ����Ʈ ���� ����
		g.setColor(Color.RED);
		g.fillRect(200, 100, 50, 50);
		
		g.setColor(Color.BLUE);		
		g.drawString("�̰� �׸��۾���.", 50, 200); // �۾��� �ƴ� �׸��� ����.
		
		// �̹��� �ֱ�
		// Image : �̹����� ǥ���� ��ü
		//				�Ϲ�,�߻�,�������̽�? �߻�Ŭ������.
		//				�Ǹ���������. �߻�Ŭ������ ���, �����ڰ� �ڽ�Ŭ������ ���� �� 
		//				�ڽ��� new �ϴ°� ��Ģ������..
		//				sun�簡 �������ִ� ��κ��� �߻�Ŭ������ ���, �̹� �ν��Ͻ��� ������ ����
		//				���������� ���� ��� �޼��带 �������ش�.
		// �ý��ۿ� �ϵ��ũ ��θ� �̿��Ͽ� �̹����� ������ ��쿣, �÷����� ����
		// �̹��� ��ü�� ������ �� �ִ� Toolkit��ü�� �̿��ؾ� �ϹǷ�,
		g.drawImage(img, 20, 20, this); // �������� �̹��� �����ڸ� ��
	}
}
