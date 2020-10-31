package day1028.graphic.line;

import java.awt.Canvas;
import java.awt.Graphics;

public class XCanvas extends Canvas{
	LineMaker lineMaker;
	int x1,y1,x2,y2;
	
	// ���⼭ �׷����� ���� �� �ڽſ��� �ִ� �� = �Ű������� ����
	public void setLineMaker(LineMaker lineMaker) {
		this.lineMaker=lineMaker;
	}
	
	@Override
	public void paint(Graphics g) {
		x1 = Integer.parseInt(lineMaker.t_x1.getText());		
		y1 = Integer.parseInt(lineMaker.t_y1.getText());
		x2 = Integer.parseInt(lineMaker.t_x2.getText());		
		y2 = Integer.parseInt(lineMaker.t_y2.getText());		
		
		g.drawLine(x1, y1, x2, y2);
	}
}
