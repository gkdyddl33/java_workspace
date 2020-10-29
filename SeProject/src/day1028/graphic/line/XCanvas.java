package day1028.graphic.line;

import java.awt.Canvas;
import java.awt.Graphics;

public class XCanvas extends Canvas{
	LineMaker lineMaker;	// null -> 생성자, 매서드로 전달 받을 수 있음
	
	// 다른대서 보일 수 있게 지역에서 뺏음
	// default 접근 제한자는 같은 패키지에 클래스 만이 접근 가능함.
	int x1;
	int y1;
	int x2;
	int y2;
	
	// 이 메서드를 호출하는 자는 , LineMaker의 주소값을 넘기면 된다.
	public void setLineMaker(LineMaker lineMaker) {
		this.lineMaker = lineMaker;
	}
	
	// (4) 컨버스는 개발자가 직접 그림을 그릴 수 있는 빈 도화지이다.
	// 따라서 paint() 메서드를 재정의 하면 된다
	@Override
	public void paint(Graphics g) {
		// drawLine 은 정수만 선언가능 int
		// linemarker은 문자로 나오기 때문에 숫자로 돌려줘야 한다. 
		// 근데 아래외 같이만 해두면은 초기화가 되어 있지 않기 때문에 에러가 발생
		// 숫자가 안들어 가 있기 때문에 -> "0"으로 초기화를 해놓는건 어떨까?
		// TextField는 (문자,int column)을 할 수 있음
		int x1 = Integer.parseInt(lineMaker.t_x1.getText());
		int y1 = Integer.parseInt(lineMaker.t_y1.getText());
		int x2 = Integer.parseInt(lineMaker.t_x2.getText());
		int y2 = Integer.parseInt(lineMaker.t_y2.getText());
		
		g.drawLine(x1, y1, x2, y2);  // LineMarker주소값.x1,y1,x2,y2의 값..
												 // 두 점을 연결한 선 그리기
	}
}
