package day1102.event;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class WindowApp extends JFrame {

	String str;
	public WindowApp() {
		// �������̽��� ���� new �� �� �� ����..
		this.addWindowListener(new Windowdapter() {// ����,�͸�Ŭ���� - ������
			
			@Override
			public void windowClosing(WindowEvent e) {// â�� ���� ���α׷� ����� ��
			System.out.println("windowClosing");
			System.exit(0);//���μ��� ����
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
