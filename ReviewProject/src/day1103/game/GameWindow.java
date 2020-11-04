package day1103.game;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameWindow extends JFrame{
	GamePanel gamePanel;
	JMenuBar bar;
	JMenu control;
	JMenuItem item_start,item_pause,item_exit;
	
	public GameWindow() {
		gamePanel = new GamePanel();
		bar = new JMenuBar();
		control = new JMenu("���� ����");
		item_start = new JMenuItem("���ӽ���");
		item_pause = new JMenuItem("pause");
		item_exit = new JMenuItem("��������");
		control.add(item_start);
		control.add(item_pause);
		control.add(item_exit);
		setJMenuBar(bar);
		
		setLayout(new FlowLayout());
		add(gamePanel);
		
		pack();
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		new GameWindow();
	}
}
