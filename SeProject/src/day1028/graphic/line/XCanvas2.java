package day1028.graphic.line;

import java.awt.Canvas;
import java.awt.Graphics;

public class XCanvas2 extends Canvas{// �������� ��ȭ�� -> �׷��� ���ִ� �޼ҵ� �ʿ�
	LineMaker2 lineMaker;
	
	int x1;
	int y1;
	int x2;
	int y2;
	
	public void setLineMaker2(LineMaker2 lineMaker) {
		this.lineMaker = lineMaker;
	}
	
	// ** �׷���ó�� �޼ҵ�
	@Override
	public void paint(Graphics g) {
		// �׳� ������ �ȵǰ� drawline�� ������ �ޱ� ������ ���� ���� �ؾߵ�
		x1 = Integer.parseInt(lineMaker.t_x1.getText());
		y1 = Integer.parseInt(lineMaker.t_y1.getText());
		x2 = Integer.parseInt(lineMaker.t_x2.getText());
		y2 = Integer.parseInt(lineMaker.t_y2.getText());
		g.drawLine(x1, y1, x2, y2);
	}

}
