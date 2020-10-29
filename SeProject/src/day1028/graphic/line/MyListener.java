package day1028.graphic.line;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListener implements ActionListener{
	XCanvas can;
	
	// 기존 켄버스 new말고 넘겨받자
	public MyListener(XCanvas can) {
		this.can = can;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// (5) LineMaker의 켄버스에 선을 그리고, LineMaker 클래스의 JTextField 의 값을 이용하여..
		// paint() 메서드는 개발자가 적접 호출할 수도 없고, 호출해서도 안된다.
		// paint()메서드는 그림이 그려질 준비가 되었을 때 시스템. 즉, JVM에 의해 호출된다.
		// 따라서 개발자가 원하는 타임에, 그림을 갱신하게 하려면, paint() 메서드를 직접 호출해서는 아니되고
		// 갱신할 것을 시스템에 요청!
		// repaint() 다시 그려주세요! ->  update() 화면 지우기
		// -> paint()
		// 켄버스가 보유한.repaint();
		// xcanva의 paint() 불가능.		
		can.repaint();
	}
}
