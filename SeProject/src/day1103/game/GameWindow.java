package day1103.game;

import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class GameWindow extends JFrame{

	GamePanel gamePanel;	// ���� ������ �׷��� �г�
	
	public GameWindow() {
		gamePanel = new GamePanel();
		
		setLayout(new FlowLayout());
		add(gamePanel);
		
		// ������ ũ��
		// ������ �ȿ� �ִ� ���빰���� �پ��
		pack();
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		//(la) ������� ������ ����
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
//				System.out.println("������?");
				gamePanel.moveKey(e.getKeyCode());
			}
			@Override
			public void keyReleased(KeyEvent e) {
				gamePanel.stopKey(e.getKeyCode());
			}
		});
		
	}
	public static void main(String[] args) {
		new GameWindow();
	}
}
