package day1103.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

// 사실상 모든~게임의 그래픽처리는 패널이 담당하게 됨
public class GamePanel extends JPanel{
	Thread loopThread;	// 게임 루프를 무한루프로 실행할 쓰레드!!
	public static final int WIDTH=1600;
	public static final int HEIGHT=900;
	
	// 히어로 등장
	Hero hero;
	//총알 등장
	Bullet bullet;
	public GamePanel() {
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		// 주인공 생성
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
		hero = new Hero(200, 200, 50, 50, 0, 0);
	}
	// gameWindow 호출용 메서드 = 움직일려고
	// 방향키 전달받기 37-38-39-40
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
	// 총알 발사
	public void fire() {
		bullet = new Bullet(hero.x, hero.y, 50, 50, 10, 10);
	}
	// 물리량 변경
	public void tick() {
		hero.tick();
		
		// 스페이스를 눌러야 태어나야 하는데 지금 게임루프시작하면서 돌아가므로
		// 에러가 발생한다.
		if(bullet!=null)bullet.tick();
	}
	// 물리량 변화를 반영
	public void render(Graphics2D g2) {
		hero.render(g2); // 주인공을 받기 때문에
		if(bullet!=null)bullet.render(g2);
	}
	
	// 모든 게임의 tick(), render()를 호출, 즉 게임엔진
	public void gameLoop() {
		tick();
		this.repaint(); // render -> 주인공이 계속 움직임
		
		//System.out.println("gameLoop() called..");
	}
}
