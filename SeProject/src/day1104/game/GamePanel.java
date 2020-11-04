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

// 사실상 모든~게임의 그래픽처리는 패널이 담당하게 됨
public class GamePanel extends JPanel{
	Thread loopThread;	// 게임 루프를 무한루프로 실행할 쓰레드!!
	public static final int WIDTH=1600;
	public static final int HEIGHT=900;
	
	// 히어로 등장
	Hero hero;
	//총알 등장 -> 하나의 레퍼런스로 가면 연속 발사할 때 뭉쳐버린다.
	// 그래서 순서가 있는 배열로 가자 순서가 있어야 반복문 사용가능
	//Bullet bullet;
	ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	Image bgimg;
	
	ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	
	ArrayList<Block> blockList = new ArrayList<>();
	
	GameBg[] gameBg = new GameBg[2];
	boolean flag  = false;
	
	// 점수
	int score=0;
	// 생명표시
	ArrayList<Hp> hpList = new ArrayList<>();
	boolean over = false; // game over 메시지 띄울지 여부 판단..
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		// 배경,주인공,적군 생성
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
		// 2D로 업그레이드
		// 2D에 더 적합한 그래픽스 객체로 형변환!
		Graphics2D g2 = (Graphics2D)g;
		
		// 패널의 크기만큼 사각형이 채워진다. -> 이동이 아닌 다시그리기를 하므로
		// 빨강을 흰색으로 그리고 지우고를 반복하는 것임
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, WIDTH, HEIGHT);
		
		// Hero -> 그려서 처리해줘
		render(g2);
	}
	public void createHero() {
		
		// 이미지 가져오기
		// 1) 플랫폼종속된 경로 :Toolkit
		// 2) 클래스패스 : 클래스 로더.getResources(
		//System.out.println(this.getClass().getClassLoader().getResource("res/game/plane.png"));
		
		Image img = ImageUtil.getIcon(this.getClass(), "res/game/plane.png", 100, 45).getImage();
		hero = new Hero(this,img,200, 200, 100, 65, 0, 0);	
			
	}
	// gameWindow 호출용 메서드 = 움직일려고
	// 방향키 전달받기 37-38-39-40
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
	// 총알 발사
	public void fire() {
		Image img = ImageUtil.getIcon(this.getClass(), "res/game/ball.png", 20, 20).getImage();
		// this로 나 자신을 gamePanel를 넘겨야 작동한다.
		Bullet bullet = new Bullet(this,img,hero.x+hero.width, hero.y+(hero.height/2), 20, 20, 10, 0);
		// 생성된 총알을 list에 담자
		bulletList.add(bullet);
	}
	// 배경이미지 생성
	public void createBg() {
		Image img = ImageUtil.getIcon(this.getClass(), "res/game/game_bg.jpg", WIDTH, HEIGHT).getImage();
		// 생성된 이미지로 배경객체 2개를 생성하자!
		gameBg[0] = new GameBg(img, 0, 0, WIDTH, HEIGHT, -1, 0);
		gameBg[1] = new GameBg(img, WIDTH, 0, WIDTH, HEIGHT, -1, 0);
	}
	// 게임의 상황, 정보 출력
	public void printData(Graphics2D g2) {
		g2.setFont(new Font("Arial Black", Font.BOLD, 25));
		
		StringBuffer sb = new StringBuffer();
		sb.append("Bullet :"+bulletList.size());
		sb.append("Enemy :"+enemyList.size());
		sb.append("Score :"+score);
		
		g2.drawString(sb.toString(), 50, 50);
		
		// 게임 종료 메시지 띄우기
		if(over)showGameOver(g2);
		
	}
	// 게임 종료 메시지 띄우기
	public void showGameOver(Graphics2D g2) {
		g2.setFont(new Font("Arial Black", Font.BOLD, 200));
		
		StringBuffer sb = new StringBuffer();
		sb.append("Game Over");		
		g2.drawString(sb.toString(), 50, 400);
		
	}
	
	// 적군생성
	public void createEnemy() {
		String[] path = {"e1.png","e2.png","e3.png","e4.png","e5.png"};
		
		
		for (int i = 0; i < 20; i++) {
			// 적군 랜덤 생성
			double r = Math.random();
			int n = (int)(r*path.length);
			//System.out.println(n);
			
			Image img = ImageUtil.getIcon(this.getClass(), "res/game/"+path[n], 80, 60).getImage();
			Enemy enemy = new Enemy(img, WIDTH-50, 50+(80*i), 80, 60, -2, 0);
			// 적군 목록에 추가!
			enemyList.add(enemy);
		}
	}
	
	// 블락생성
	public void createBlock() {
		
		for (int i = 0; i < 20; i++) {
			Image img = ImageUtil.getIcon(this.getClass(), "res/game/block.png", 60, 60).getImage();
			Block block = new Block(img, 300+(i*60), 600, 60, 60, 0, 0);
			// 적군 목록에 추가!
			blockList.add(block);
		}
	}
	
	// Hp생성
	public void createHP() {
		Image img = ImageUtil.getIcon(this.getClass(), "res/game/heart.png", 30, 30).getImage();
		for(int i=0;i<4;i++) {
			Hp hp = new Hp(img, 50+(30*i), 60, 40, 40, 0, 0);
			hpList.add(hp);
		}
	}
	// 물리량 변경
	public void tick() {
		hero.tick();
		
		// 스페이스를 눌러야 태어나야 하는데 지금 게임루프시작하면서 돌아가므로
		// 에러가 발생한다.
		
		// 다수의 총알에 대한 tick()
		for(int i =0;i<bulletList.size();i++) {
			Bullet bullet = bulletList.get(i);
			bullet.tick();			
		}
		
		// 적군의 tick()
		for (int i = 0; i < enemyList.size(); i++) {
			Enemy enemy = enemyList.get(i);
			enemy.tick();
		}
		// 블락의 tick()
		for (int i = 0; i < blockList.size(); i++) {
			Block block = blockList.get(i);
			block.tick();
		}
		// 배경의 tick()
		for (int i = 0; i < gameBg.length; i++) {
			gameBg[i].tick();
		}
		// Hp의 tick()
		for (int i = 0; i < hpList.size(); i++) {
			Hp hp = hpList.get(i);
			hp.tick();
		}
	}
	// 물리량 변화를 반영
	public void render(Graphics2D g2) {
		g2.drawImage(bgimg, 0, 0, this);
		for (int i = 0; i < gameBg.length; i++) {
			gameBg[i].render(g2);
		}
		
		hero.render(g2); // 주인공을 받기 때문에
		
		
		// 다수의 총알에 대한 tick()
		for(int i =0;i<bulletList.size();i++) {
			Bullet bullet = bulletList.get(i);
			bullet.render(g2);			
		}
		// 적군의 tick()
		for (int i = 0; i < enemyList.size(); i++) {
			Enemy enemy = enemyList.get(i);
			enemy.render(g2);
		}
		// 블락의 tick()
		for (int i = 0; i < blockList.size(); i++) {
			Block block = blockList.get(i);
			block.render(g2);
		}
		// Hp의 tick()
		for (int i = 0; i < hpList.size(); i++) {
			Hp hp = hpList.get(i);
			hp.render(g2);
		}
		printData(g2);
	}
	
	// 모든 게임의 tick(), render()를 호출, 즉 게임엔진
	public void gameLoop() {
		tick();
		this.repaint(); // render -> 주인공이 계속 움직임
		
		//System.out.println("gameLoop() called..");
	}
}
