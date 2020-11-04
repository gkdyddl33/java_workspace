package day1103.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class GameObject {
	/*���ӿ� �����ϴ� ���~��Ҵ� �� ��ü�� �ڽ��̴�.*/
	Image img;
	
	int x;
	int y;
	int width;
	int height;
	int velX;
	int velY;
	
	// �浹�˻�
	Rectangle rect;
	public GameObject(Image img,int x, int y, int width, int height, int velX, int velY) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velX = velX;
		this.velY = velY;
		// ��������Ȱ��
		rect = new Rectangle(x, y, width, height);
	}
	// ��������ȭ
	public abstract void tick();
	// �׷���ó�� ��, �����̰�
	public abstract void render(Graphics2D g2);
}
