package day1102.event;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
 *	�����ʴ� �������̽��̱� ������ �����ʸ� �����ϴ��ڴ� �ݵ��
 *	�߻�޼��带 ������ �ؾ� �Ѵ�..
 *	������!! �����ʰ� ������ �߻�޼����� ���� �ʹ� ���� ���
 *	��, ��������� �ʴ� ����ִ� �޼��带 �츮�� ������ Ŭ�������� �δ� ����
 *	ȿ�������� ���ϴ�..
 * ���� sun������ �������� �޼��尡 3�� �̻��� ���, ������ �����ʸ� ������
 * ����Ͷ�� ��ü�� �������ش�! 
 */

public class MyWinAdapter extends WindowAdapter{
	// ex..
	// WindowListener�� ������ Ŭ������ WindowAdapter
	// MouseListener�� ������ Ŭ������ MouseAdapter
	// KeyListener�� ������ Ŭ������ KeyAdapter 
	@Override
	public void windowClosed(WindowEvent e) {// â�� ���� ���α׷� ����ɶ�
		System.out.println("â ����.");
	}
	@Override
	public void windowClosing(WindowEvent e) {// â�� ���� ���α׷� ����� ��
		System.out.println("windowClosing");
		System.exit(0);//���μ��� ����
	}
}
