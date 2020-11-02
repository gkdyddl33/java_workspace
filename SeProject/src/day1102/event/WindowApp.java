package day1102.event;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class WindowApp extends JFrame {

	String str;
	public WindowApp() {
		// 인터페이스는 원래 new 를 할 수 없다..
		this.addWindowListener(new Windowdapter() {// 내부,익명클래스 - 재정의
			
			@Override
			public void windowClosing(WindowEvent e) {// 창에 의해 프로그램 종료될 때
			System.out.println("windowClosing");
			System.exit(0);//프로세스 종료
			}
		});
		setSize(300,400);
		setVisible(true);
		setLocationRelativeTo(null);
	}	

	@Override
	public void windowActivated(WindowEvent e) {;}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {;}

	@Override
	public void windowDeiconified(WindowEvent e) {;}

	@Override
	public void windowIconified(WindowEvent e) {;}

	@Override
	public void windowOpened(WindowEvent e) {;}
	
	public static void main(String[] args) {
		new WindowApp();
	}
}
