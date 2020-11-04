package day1104.game;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class GameWindow extends JFrame{

	GamePanel gamePanel;	// 실제 게임이 그려질 패널
	// 게임을 잠시 멈추기 위해 
	JMenuBar bar;
	JMenu control;
	JMenuItem item_start,item_pause,item_exit;
	
	
	public GameWindow() {
		
		gamePanel = new GamePanel();
		bar = new JMenuBar();
		control = new JMenu("게임 설정");
		item_start = new JMenuItem("게임 시작");
		item_pause = new JMenuItem("pause");
		item_exit = new JMenuItem("게임 종료");
		control.add(item_start);
		control.add(item_pause);
		control.add(item_exit);
		bar.add(control);
		setJMenuBar(bar);
		
		setLayout(new FlowLayout());
		add(gamePanel);
		
		// 윈도우 크기
		// 윈도우 안에 있는 내용물가지 줄어듬
		pack();
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		//(la) 윈도우와 리스너 연결
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
//				System.out.println("눌렀어?");
				gamePanel.moveKey(e.getKeyCode());
			}
			@Override
			public void keyReleased(KeyEvent e) {
				gamePanel.stopKey(e.getKeyCode());
			}
		});
		
		// 메뉴와 리스너 연결
		item_start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gamePanel.flag=true;
			}
		});
		item_pause.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gamePanel.flag=false;
			}
		});
		item_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(JOptionPane.showConfirmDialog(GameWindow.this, "정말 이대로 끝내시려구요?")==JOptionPane.OK_OPTION);
				gamePanel.flag=false;
				System.exit(0);
			}
		});
	}
	public static void main(String[] args) {
		new GameWindow();
	}
}
