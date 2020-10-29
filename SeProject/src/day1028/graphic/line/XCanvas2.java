package day1028.graphic.line;

import java.awt.Canvas;
import java.awt.Graphics;

public class XCanvas2 extends Canvas{// 컨버스는 빈도화지 -> 그래픽 해주는 메소드 필요
	LineMaker2 lineMaker;
	
	int x1;
	int y1;
	int x2;
	int y2;
	
	public void setLineMaker2(LineMaker2 lineMaker) {
		this.lineMaker = lineMaker;
	}
	
	// ** 그래픽처리 메소드
	@Override
	public void paint(Graphics g) {
		// 그냥 넣으면 안되고 drawline은 정수만 받기 때문에 수정 먼저 해야됨
		x1 = Integer.parseInt(lineMaker.t_x1.getText());
		y1 = Integer.parseInt(lineMaker.t_y1.getText());
		x2 = Integer.parseInt(lineMaker.t_x2.getText());
		y2 = Integer.parseInt(lineMaker.t_y2.getText());
		g.drawLine(x1, y1, x2, y2);
	}

}
