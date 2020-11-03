package day1103.game;

import java.awt.Color;
import java.awt.Graphics2D;

/*
 	���ӿ� �����ϴ� ���~��Ҵ� �� ��ü�� �ڽ��̴�.
 	���� ���ӿ�����Ʈ class�� �������� Ư¡�� �����ؾ� �Ѵ�.
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

	// ���� ��ü�� � �������� �������� ��ȭ��ų�� �θ��� ���� ����������
	// �˼��� ���ŴϿ�,�˾Ƽ��� �ȵȴ�.
	// "�̷��� �ڽ��� �˾Ƽ� �� ���̴�."
	// �ڽ��� ��Ȳ�� �°� �� ������ ���� �� ���̴�.
	public abstract void tick();

	// �׷��� ó��(ȭ�� �׷��� ó��)
	public abstract void render(Graphics2D g2);
}
