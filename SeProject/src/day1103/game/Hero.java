package day1103.game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Hero extends GameObject{
	
	public Hero(int x,int y,int width,int height,int velX,int velY) {
		super(x,y,width,height,velX,velY);
//		this.x=x;
//		this.y=y;
//		this.width=width;
//		this.height=height;
//		this.velX=velX;
//		this.velY=velY;
		
	}
	// ������ ��ȭ(�������� ��ȭ)
	public void tick() {
		this.x += this.velX;
		this.y += this.velY;
	}
	
	// �׷��� ó��(ȭ�� �׷��� ó��)
	public void render(Graphics2D g2) {
		// �гο� �׷��� �Ѵ�. -> GamePanel�� paint() �� g2 ������ �����;� �Ѵ�..
		// ��� ���� ĳ���ʹ� �гο� �׷��� �ϹǷ�, g2�� �г��� paint() �޼����� ���������� �޾ƿ���
		g2.setColor(Color.RED);
		g2.fillRect(x, y, 100, 45);
	
	}
}
