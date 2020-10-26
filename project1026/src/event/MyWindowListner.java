package event;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/*윈도우창을 대상으로 발생할 수 있는 이벤트를 감지하는 리스너 구현하기*/
public class MyWindowListner implements WindowListener{

	@Override
	public void windowActivated(WindowEvent e) {
		System.out.println("windowActivated 호출");	
		// 활성화 될 때
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("windowClosed 호출");
		// 종료
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("windowClosing 호출");	
		// 종료가 된거는 아니지만 눌렀을 때 진행중
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		System.out.println("windowDeactivated 호출");
		// 아이콘 화의반대
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		System.out.println("windowDeiconified 호출");
		// 최소화도 다시 켜지는 것
	}

	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("windowIconified 호출");
		// 최소화로 내려 오는 것
	}

	@Override
	public void windowOpened(WindowEvent e) {
		System.out.println("windowOpened 호출");
		// 창이 뜰때
	}
	
}
