package day1104.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import common.image.ImageUtil;

// ��ǻ� ���~������ �׷���ó���� �г��� ����ϰ� ��
public class GamePanel extends JPanel{
	Thread loopThread;	// ���� ������ ���ѷ����� ������ ������!!
	public static final int WIDTH=1600;
	public static final int HEIGHT=900;
	
	// ����� ����
	Hero hero;
	//�Ѿ� ���� -> �ϳ��� ���۷����� ���� ���� �߻��� �� ���Ĺ�����.
	// �׷��� ������ �ִ� �迭�� ���� ������ �־�� �ݺ��� ��밡��
	//Bullet bullet;
	ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	Image bgimg;
	
	ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	
	ArrayList<Block> blockList = new ArrayList<>();
	
	GameBg[] gameBg = new GameBg[2];
	boolean flag  = false;
	
	// ����
	int score=0;
	// ����ǥ��
	ArrayList<Hp> hpList = new ArrayList<>();
	boolean over = false; // game over �޽��� ����� ���� �Ǵ�..
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		// ���,���ΰ�,���� ����
		createBg();		
		createHero();
		createEnemy();
		createBlock();
		createHP();
		
		loopThread = new Thread() {
			@Override
			public void run() {
				while(true) {
					if(flag)gameLoop();
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
		
		// �̹��� ��������
		// 1) �÷������ӵ� ��� :Toolkit
		// 2) Ŭ�����н� : Ŭ���� �δ�.getResources(
		//System.out.println(this.getClass().getClassLoader().getResource("res/game/plane.png"));
		
		Image img = ImageUtil.getIcon(this.getClass(), "res/game/plane.png", 100, 45).getImage();
		hero = new Hero(this,img,200, 200, 100, 65, 0, 0);	
			
	}
	// gameWindow ȣ��� �޼��� = �����Ϸ���
	// ����Ű ���޹ޱ� 37-38-39-40
	public void moveKey(int key) {
		switch(key) {
			case KeyEvent.VK_LEFT : hero.velX=-5;break;
			case KeyEvent.VK_RIGHT : hero.velX=5;break;
			case KeyEvent.VK_UP : hero.velY=-5;break;
			case KeyEvent.VK_DOWN : hero.velY=5;break;
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
		Image img = ImageUtil.getIcon(this.getClass(), "res/game/ball.png", 20, 20).getImage();
		// this�� �� �ڽ��� gamePanel�� �Ѱܾ� �۵��Ѵ�.
		Bullet bullet = new Bullet(this,img,hero.x+hero.width, hero.y+(hero.height/2), 20, 20, 10, 0);
		// ������ �Ѿ��� list�� ����
		bulletList.add(bullet);
	}
	// ����̹��� ����
	public void createBg() {
		Image img = ImageUtil.getIcon(this.getClass(), "res/game/game_bg.jpg", WIDTH, HEIGHT).getImage();
		// ������ �̹����� ��水ü 2���� ��������!
		gameBg[0] = new GameBg(img, 0, 0, WIDTH, HEIGHT, -1, 0);
		gameBg[1] = new GameBg(img, WIDTH, 0, WIDTH, HEIGHT, -1, 0);
	}
	// ������ ��Ȳ, ���� ���
	public void printData(Graphics2D g2) {
		g2.setFont(new Font("Arial Black", Font.BOLD, 25));
		
		StringBuffer sb = new StringBuffer();
		sb.append("Bullet :"+bulletList.size());
		sb.append("Enemy :"+enemyList.size());
		sb.append("Score :"+score);
		
		g2.drawString(sb.toString(), 50, 50);
		
		// ���� ���� �޽��� ����
		if(over)showGameOver(g2);
		
	}
	// ���� ���� �޽��� ����
	public void showGameOver(Graphics2D g2) {
		g2.setFont(new Font("Arial Black", Font.BOLD, 200));
		
		StringBuffer sb = new StringBuffer();
		sb.append("Game Over");		
		g2.drawString(sb.toString(), 50, 400);
		
	}
	
	// ��������
	public void createEnemy() {
		String[] path = {"e1.png","e2.png","e3.png","e4.png","e5.png"};
		
		
		for (int i = 0; i < 20; i++) {
			// ���� ���� ����
			double r = Math.random();
			int n = (int)(r*path.length);
			//System.out.println(n);
			
			Image img = ImageUtil.getIcon(this.getClass(), "res/game/"+path[n], 80, 60).getImage();
			Enemy enemy = new Enemy(img, WIDTH-50, 50+(80*i), 80, 60, -2, 0);
			// ���� ��Ͽ� �߰�!
			enemyList.add(enemy);
		}
	}
	
	// �������
	public void createBlock() {
		
		for (int i = 0; i < 20; i++) {
			Image img = ImageUtil.getIcon(this.getClass(), "res/game/block.png", 60, 60).getImage();
			Block block = new Block(img, 300+(i*60), 600, 60, 60, 0, 0);
			// ���� ��Ͽ� �߰�!
			blockList.add(block);
		}
	}
	
	// Hp����
	public void createHP() {
		Image img = ImageUtil.getIcon(this.getClass(), "res/game/heart.png", 30, 30).getImage();
		for(int i=0;i<4;i++) {
			Hp hp = new Hp(img, 50+(30*i), 60, 40, 40, 0, 0);
			hpList.add(hp);
		}
	}
	// ������ ����
	public void tick() {
		hero.tick();
		
		// �����̽��� ������ �¾�� �ϴµ� ���� ���ӷ��������ϸ鼭 ���ư��Ƿ�
		// ������ �߻��Ѵ�.
		
		// �ټ��� �Ѿ˿� ���� tick()
		for(int i =0;i<bulletList.size();i++) {
			Bullet bullet = bulletList.get(i);
			bullet.tick();			
		}
		
		// ������ tick()
		for (int i = 0; i < enemyList.size(); i++) {
			Enemy enemy = enemyList.get(i);
			enemy.tick();
		}
		// ����� tick()
		for (int i = 0; i < blockList.size(); i++) {
			Block block = blockList.get(i);
			block.tick();
		}
		// ����� tick()
		for (int i = 0; i < gameBg.length; i++) {
			gameBg[i].tick();
		}
		// Hp�� tick()
		for (int i = 0; i < hpList.size(); i++) {
			Hp hp = hpList.get(i);
			hp.tick();
		}
	}
	// ������ ��ȭ�� �ݿ�
	public void render(Graphics2D g2) {
		g2.drawImage(bgimg, 0, 0, this);
		for (int i = 0; i < gameBg.length; i++) {
			gameBg[i].render(g2);
		}
		
		hero.render(g2); // ���ΰ��� �ޱ� ������
		
		
		// �ټ��� �Ѿ˿� ���� tick()
		for(int i =0;i<bulletList.size();i++) {
			Bullet bullet = bulletList.get(i);
			bullet.render(g2);			
		}
		// ������ tick()
		for (int i = 0; i < enemyList.size(); i++) {
			Enemy enemy = enemyList.get(i);
			enemy.render(g2);
		}
		// ����� tick()
		for (int i = 0; i < blockList.size(); i++) {
			Block block = blockList.get(i);
			block.render(g2);
		}
		// Hp�� tick()
		for (int i = 0; i < hpList.size(); i++) {
			Hp hp = hpList.get(i);
			hp.render(g2);
		}
		printData(g2);
	}
	
	// ��� ������ tick(), render()�� ȣ��, �� ���ӿ���
	public void gameLoop() {
		tick();
		this.repaint(); // render -> ���ΰ��� ��� ������
		
		//System.out.println("gameLoop() called..");
	}
}
