package day1103.game;

import java.awt.Color;
import java.awt.Graphics2D;

/*
 	게임에 등장하는 모든~요소는 이 객체의 자식이다.
 	따라서 게임오브젝트 class는 보편적인 특징만 보유해야 한다.
 */
public  abstract class GameObject {
	int x;
	int y;
	int width;
	int height;
	int velX;
	int velY;

	public GameObject(int x, int y, int width, int height, int velX, int velY) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velX = velX;
		this.velY = velY;

	}

	// 하위 객체가 어떤 내용으로 물리량을 변화시킬지 부모인 현재 시점에서는
	// 알수도 없거니와,알아서도 안된다.
	// "미래에 자식이 알아서 할 것이다."
	// 자신의 상황에 맞게 재 정의할 날이 올 것이다.
	public abstract void tick();

	// 그래픽 처리(화면 그려질 처리)
	public abstract void render(Graphics2D g2);
}
