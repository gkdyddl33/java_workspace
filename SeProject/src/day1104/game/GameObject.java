package day1104.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

/*
 	���ӿ� �����ϴ� ���~��Ҵ� �� ��ü�� �ڽ��̴�.
 	���� ���ӿ�����Ʈ class�� �������� Ư¡�� �����ؾ� �Ѵ�.
 */
public  abstract class GameObject {
	// �̹��� �߰�
	Image img;
	
	int x;
	int y;
	int width;
	int height;
	int velX;
	int velY;

	// "�浹�˻�"�� ���ؼ��� ���~��ü�� �簢���� ������ �־�� �Ѵ�.
	Rectangle rect; 
	
	public GameObject(Image img,int x, int y, int width, int height, int velX, int velY) {
		this.img=img;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.velX = velX;
		this.velY = velY;
		rect = new Rectangle(x, y, width, height); // ������ ������ �ִ� ������ �̿��Ͽ� �簢���� ��������
	}

	// ���� ��ü�� � �������� �������� ��ȭ��ų�� �θ��� ���� ����������
	// �˼��� ���ŴϿ�,�˾Ƽ��� �ȵȴ�.
	// "�̷��� �ڽ��� �˾Ƽ� �� ���̴�."
	// �ڽ��� ��Ȳ�� �°� �� ������ ���� �� ���̴�.
	public abstract void tick();

	// �׷��� ó��(ȭ�� �׷��� ó��)
	public abstract void render(Graphics2D g2);
}
