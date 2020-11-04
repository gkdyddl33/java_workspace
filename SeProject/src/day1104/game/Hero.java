package day1104.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Hero extends GameObject{
	GamePanel gamePanel; // enemyList가 있는 클래스 -> 충돌확인때문에
	
	public Hero(GamePanel gamePanel,Image img,int x,int y,int width,int height,int velX,int velY) {
		super(img,x,y,width,height,velX,velY);
		this.gamePanel = gamePanel;
		
	}
	// 적군과 나의 충돌여부를 판단하고, 만일 충돌하면 나의 Hp죽고 너죽고..	
	public void collisionCheck() {
		for(int i=0;i<gamePanel.enemyList.size();i++) {
			Enemy enemy = gamePanel.enemyList.get(i);
			if(this.rect.intersects(enemy.rect)) {// 충돌했다면
				gamePanel.hpList.remove(gamePanel.hpList.size()-1);//뒤에서부터 생명 죽이기
				gamePanel.enemyList.remove(enemy); // 너죽고
				break;
			}			
		}
	}
	// 물리량 변화(데이터의 변화)
	public void tick() {
		this.x += this.velX;
		this.y += this.velY;
		
		// 사각형과 주인공은 항상 일체해야 한다.
		rect.x=x;
		rect.y=y;
		if(gamePanel.hpList.size()>1) {
			// 에너지가 1개이상일 경우만 실행해야 된다..
			// 0개라면 게임종료를 해야 됨 에너지 바닥
			collisionCheck();
		}else {
			System.out.println("게임종료, 에너지 모두 소진"); 
			gamePanel.over=true;
			gamePanel.flag=false; // 모든게 멈춘다.
		}
	}
	
	// 그래픽 처리(화면 그려질 처리)
	public void render(Graphics2D g2) {
		// 패널에 그려야 한다. -> GamePanel의 paint() 에 g2 변수를 가져와야 한다..
		// 모든 게임 캐릭터는 패널에 그려야 하므로, g2를 패널의 paint() 메서드의 지역변수를 받아오자
		
		//g2.setColor(Color.RED);
		//g2.fillRect(x, y, 100, 45);		
		g2.drawRect(rect.x, rect.y, rect.width, rect.height);
		// 우리가 이미 보유하고 있는 사각형을 시각화 시켜보자.
		g2.drawImage(img, rect.x, rect.y, null);
			
	}
}
