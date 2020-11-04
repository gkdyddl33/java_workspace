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

	GamePanel gamePanel;	// ���� ������ �׷��� �г�
	// ������ ��� ���߱� ���� 
	JMenuBar bar;
	JMenu control;
	JMenuItem item_start,item_pause,item_exit;
	
	
	public GameWindow() {
		
		gamePanel = new GamePanel();
		bar = new JMenuBar();
		control = new JMenu("���� ����");
		item_start = new JMenuItem("���� ����");
		item_pause = new JMenuItem("pause");
		item_exit = new JMenuItem("���� ����");
		control.add(item_start);
		control.add(item_pause);
		control.add(item_exit);
		bar.add(control);
		setJMenuBar(bar);
		
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
		
		// �޴��� ������ ����
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
				if(JOptionPane.showConfirmDialog(GameWindow.this, "���� �̴�� �����÷�����?")==JOptionPane.OK_OPTION);
				gamePanel.flag=false;
				System.exit(0);
			}
		});
	}
	public static void main(String[] args) {
		new GameWindow();
	}
}
