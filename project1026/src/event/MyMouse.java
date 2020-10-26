package event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouse implements MouseListener{
	// 마우스 클릭시, "마우스 클릭햇어" 메시지 띄우기
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("마우스 클릭햇니?");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//System.out.println("마우스를 쳣니?");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//System.out.println("마우스로 종료했니?");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//System.out.println("마우스로 눌렀니?");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//System.out.println("마우스로 쳤니?");
	}
}
