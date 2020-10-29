package day1028.graphic.line;

import java.awt.Canvas;
import java.awt.Graphics;

public class XCanvas extends Canvas{
	LineMaker lineMaker;	// null -> ������, �ż���� ���� ���� �� ����
	
	// �ٸ��뼭 ���� �� �ְ� �������� ����
	// default ���� �����ڴ� ���� ��Ű���� Ŭ���� ���� ���� ������.
	int x1;
	int y1;
	int x2;
	int y2;
	
	// �� �޼��带 ȣ���ϴ� �ڴ� , LineMaker�� �ּҰ��� �ѱ�� �ȴ�.
	public void setLineMaker(LineMaker lineMaker) {
		this.lineMaker = lineMaker;
	}
	
	// (4) �������� �����ڰ� ���� �׸��� �׸� �� �ִ� �� ��ȭ���̴�.
	// ���� paint() �޼��带 ������ �ϸ� �ȴ�
	@Override
	public void paint(Graphics g) {
		// drawLine �� ������ ���𰡴� int
		// linemarker�� ���ڷ� ������ ������ ���ڷ� ������� �Ѵ�. 
		// �ٵ� �Ʒ��� ���̸� �صθ��� �ʱ�ȭ�� �Ǿ� ���� �ʱ� ������ ������ �߻�
		// ���ڰ� �ȵ�� �� �ֱ� ������ -> "0"���� �ʱ�ȭ�� �س��°� ���?
		// TextField�� (����,int column)�� �� �� ����
		int x1 = Integer.parseInt(lineMaker.t_x1.getText());
		int y1 = Integer.parseInt(lineMaker.t_y1.getText());
		int x2 = Integer.parseInt(lineMaker.t_x2.getText());
		int y2 = Integer.parseInt(lineMaker.t_y2.getText());
		
		g.drawLine(x1, y1, x2, y2);  // LineMarker�ּҰ�.x1,y1,x2,y2�� ��..
												 // �� ���� ������ �� �׸���
	}
}
