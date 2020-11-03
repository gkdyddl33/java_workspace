package day1103.game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends GameObject{

	public Bullet(int x, int y, int width, int height, int velX, int velY) {
		super(x, y, width, height, velX, velY);

	}

	@Override
	public void tick() {
		this.x += this.velX;
		
	}

	@Override
	public void render(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fillRect(x, y, width, height);
		
	}

}
