package event;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MyWindowListener2 implements WindowListener{

	@Override
	public void windowActivated(WindowEvent e) {
		System.out.println("windowActivated 호출");
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("windowClosed 호출");
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("windowClosing 호출");
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		System.out.println("windowDeactivated 호출");
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		System.out.println("windowDeiconified 호출");
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("windowIconified 호출");
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		System.out.println("windowOpened 호출");
		
	}

}
