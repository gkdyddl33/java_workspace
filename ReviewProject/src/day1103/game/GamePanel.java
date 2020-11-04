package day1103.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import common.image.ImageUtil;

public class GamePanel extends JPanel{
	// ���ӷ����� ���ѷ����� ������ ������!
	Thread loopThread;
	
	public static final int WIDTH=1600;
	public static final int HEIGHT=900;
	
	// ����� ����
	Hero hero;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		createHero();
		
		loopThread = new Thread() {
			
			@Override
			public void run() {
				while(true) {
					gameLoop();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}					
				}
			}
		};
		loopThread.start();
	}
	// ��ȭ���� �׸��׸���
	public void paint(Graphics g) {
		
	}
	// (1) ����� ����
	public void createHero() {
		Image img = ImageUtil.getIcon(this.getClass(), "res/game/plane.png", 100, 45).getImage();
		hero = new Hero(this,img,200 , 200, 100, 65, 0, 0);
	}
	
	public void tick() {
		hero.tick();
		
	}
	public void render(Graphics2D g2) {
		hero.render(g2);
	}
	// main() ��� gameLoop() �޼ҵ�� ���� ������ ����
	public void gameLoop() {
		
	}
}
