package day1026.awt;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MyWindowListener implements WindowListener{
@Override
public void windowActivated(WindowEvent arg0) {
	
		System.out.println("windowActivated ȣ��");	
		// Ȱ��ȭ �� ��
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("windowClosed ȣ��");
		// ����
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("windowClosing ȣ��");	
		// ���ᰡ �ȰŴ� �ƴ����� ������ �� ������
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		System.out.println("windowDeactivated ȣ��");
		// ������ ȭ�ǹݴ�
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		System.out.println("windowDeiconified ȣ��");
		// �ּ�ȭ�� �ٽ� ������ ��
	}

	@Override
	public void windowIconified(WindowEvent e) {
		System.out.println("windowIconified ȣ��");
		// �ּ�ȭ�� ���� ���� ��
	}

	@Override
	public void windowOpened(WindowEvent e) {
		System.out.println("windowOpened ȣ��");
		// â�� �㶧
	}
}
