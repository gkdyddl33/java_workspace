package event;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MyWindowListener2 implements WindowListener{

	@Override
	public void windowActivated(WindowEvent e) {
		System.out.println("windowActivated ȣ��");
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("windowClosed ȣ��");
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("windowClosing ȣ��");
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		System.out.println("windowDeactivated ȣ��");
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		System.out.println("windowDeiconified ȣ��");
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("windowIconified ȣ��");
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		System.out.println("windowOpened ȣ��");
		
	}

}
