package event;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKey2 implements KeyListener{

	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println("Ű�� ���� ��,keyPressed called");
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		System.out.println("Ű�� ������ ����,keyPressed called");
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// System.out.println("Ű�� ĥ��,keyPressed called");
		
	}
	
}
