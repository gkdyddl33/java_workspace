package day1028.graphic.line;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListener implements ActionListener{
	XCanvas can;
	
	public MyListener(XCanvas can) {
		this.can=can;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// 수정 갱신
		can.repaint();
		
	}
}
