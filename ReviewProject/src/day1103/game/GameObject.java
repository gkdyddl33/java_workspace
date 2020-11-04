package day1103.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class GameObject {
	/*게임에 등장하는 모든~요소는 이 객체의 자식이다.*/
	Image img;
	
	int x;
	int y;
	int width;
	int height;
	int velX;
	int velY;
	
	// 충돌검사
	Rectangle rect;
	public GameObject(Image img,int x, int y, int width, int height, int velX, int velY) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velX = velX;
		this.velY = velY;
		// 기존정보활용
		rect = new Rectangle(x, y, width, height);
	}
	// 물리량변화
	public abstract void tick();
	// 그래픽처리 즉, 움직이게
	public abstract void render(Graphics2D g2);
}
