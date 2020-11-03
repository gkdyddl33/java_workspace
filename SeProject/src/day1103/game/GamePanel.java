package day1103.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

// ��ǻ� ���~������ �׷���ó���� �г��� ����ϰ� ��
public class GamePanel extends JPanel{
	Thread loopThread;	// ���� ������ ���ѷ����� ������ ������!!
	public static final int WIDTH=1600;
	public static final int HEIGHT=900;
	
	// ����� ����
	Hero hero;
	//�Ѿ� ����
	Bullet bullet;
	public GamePanel() {
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		// ���ΰ� ����
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
	
	@Override
	public void paint(Graphics g) {
		// 2D�� ���׷��̵�
		// 2D�� �� ������ �׷��Ƚ� ��ü�� ����ȯ!
		Graphics2D g2 = (Graphics2D)g;
		
		// �г��� ũ�⸸ŭ �簢���� ä������. -> �̵��� �ƴ� �ٽñ׸��⸦ �ϹǷ�
		// ������ ������� �׸��� ����� �ݺ��ϴ� ����
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, WIDTH, HEIGHT);
		
		// Hero -> �׷��� ó������
		render(g2);
	}
	public void createHero() {
		hero = new Hero(200, 200, 50, 50, 0, 0);
	}
	// gameWindow ȣ��� �޼��� = �����Ϸ���
	// ����Ű ���޹ޱ� 37-38-39-40
	public void moveKey(int key) {
		switch(key) {
			case KeyEvent.VK_LEFT : hero.velX=-2;break;
			case KeyEvent.VK_RIGHT : hero.velX=2;break;
			case KeyEvent.VK_UP : hero.velY=-2;break;
			case KeyEvent.VK_DOWN : hero.velY=2;break;
			case KeyEvent.VK_SPACE : fire();break;
		}
		
}
	public void stopKey(int key) {
		switch(key) {
			case KeyEvent.VK_LEFT : hero.velX=0;break;
			case KeyEvent.VK_RIGHT : hero.velX=0;break;
			case KeyEvent.VK_UP : hero.velY=0;break;
			case KeyEvent.VK_DOWN : hero.velY=0;break;
		}
		
	}
	// �Ѿ� �߻�
	public void fire() {
		bullet = new Bullet(hero.x, hero.y, 50, 50, 10, 10);
	}
	// ������ ����
	public void tick() {
		hero.tick();
		
		// �����̽��� ������ �¾�� �ϴµ� ���� ���ӷ��������ϸ鼭 ���ư��Ƿ�
		// ������ �߻��Ѵ�.
		if(bullet!=null)bullet.tick();
	}
	// ������ ��ȭ�� �ݿ�
	public void render(Graphics2D g2) {
		hero.render(g2); // ���ΰ��� �ޱ� ������
		if(bullet!=null)bullet.render(g2);
	}
	
	// ��� ������ tick(), render()�� ȣ��, �� ���ӿ���
	public void gameLoop() {
		tick();
		this.repaint(); // render -> ���ΰ��� ��� ������
		
		//System.out.println("gameLoop() called..");
	}
}
