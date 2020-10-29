package day1028.graphic.line;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListener2 implements ActionListener{
	XCanvas2 can;
	
	public MyListener2(XCanvas2 can) {
		this.can = can;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// 컨버스로 이미 그려져 있기때문에 지우고 다시 해야됨 = 갱신
		can.repaint();
		
	}
}
