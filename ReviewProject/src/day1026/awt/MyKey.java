package day1026.awt;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKey implements KeyListener{
	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println("키를 누를 때, keyPressed called");
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("키를 눌렀다 뗄때, keyPressed called");
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
