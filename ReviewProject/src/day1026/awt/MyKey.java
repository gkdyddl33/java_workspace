package day1026.awt;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKey implements KeyListener{
	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println("Ű�� ���� ��, keyPressed called");
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("Ű�� ������ ����, keyPressed called");
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
