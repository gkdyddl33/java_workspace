package day1103.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

public class Hero extends GameObject{
	GamePanel gamePanel;
	
	public Hero(GamePanel gamePanel,Image img, int x, int y, int width, int height, int velX, int velY) {
		super(img, x, y, width, height, velX, velY);
		this.gamePanel=gamePanel;
	}

	@Override
	public void tick() {
		this.x += this.velX;
		this.y += this.velY;
		
		// 사각형 충돌여부 확인 과 주인공은 항상 일체해야 한다.
		rect.x=x;
		rect.y=y;
		
		
	}

	@Override
	public void render(Graphics2D g2) {
		//g2.setColor(Color.RED);
		//g2.fillRect(x, y, 100, 45);
		
		g2.drawRect(rect.x, rect.y, rect.width, rect.height);
		g2.drawImage(img, rect.x, rect.y, null);		
	}

	
}
