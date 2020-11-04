package day1104.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

public class Bullet extends GameObject{
	GamePanel gamePanel;
	
	public Bullet(GamePanel gamePanel,Image img,int x, int y, int width, int height, int velX, int velY) {
		super(img,x, y, width, height, velX, velY);
		this.gamePanel = gamePanel;
	}

	@Override
	public void tick() {
		this.x += this.velX;
		this.y += this.velY;
		
		rect.x=x;
		rect.y=y;
		
		// ȭ������� ������, ȭ���� BulletArray���� �����ؾ�, �׷��� ����� ���� ����
		// ���� BulletArray�� ũ�⸦ �ٿ����ƾ�, ���� �浹�˻�� �ݺ����� Ƚ���� ���� �� �ִ�.
		if(this.x>GamePanel.WIDTH) {// this.x>��ũ���ʺ�
			// ���� ����Ʈ���� �����ּ���~
			gamePanel.bulletList.remove(this);
		}
		
		// �Ѿ˰� ��Ÿ ������Ʈ���� �浹�˻�!!!
		collisionCheck();
	}
	public void collisionCheck() {
		// �Ѿ��� ���� �ټ��� ������ �������θ� �Ǵ��Ͽ� 
		// �����ߴٸ� ���װ�, ������
		// ������ 10�� �ø���.
		for (int i = 0; i < gamePanel.enemyList.size(); i++) {
			Enemy enemy = gamePanel.enemyList.get(i);
			// .intersects�� �����ִ� �޼ҵ�!
			if(this.rect.intersects(enemy.rect)) {// ���� �簢��(.rect) 
				// true? ���װ� ������
				// ���װ�(List���� �����ϸ�, ���̻� tick(), render() ȣ���� �Ͼ�� �����Ƿ� ȭ�鿡�� �����
				gamePanel.bulletList.remove(this);
				// ������
				gamePanel.enemyList.remove(enemy);
				
				//��������
				break;
			}
		}
		
		// �Ѿ��� ���� �ټ��� ���� ��������
		for(int i=0;i<gamePanel.blockList.size();i++) {
			Block block = gamePanel.blockList.get(i);
			if(this.rect.intersects(block.rect)) {
				gamePanel.bulletList.remove(this);
				
				gamePanel.blockList.remove(block);		
				break;
			}
		}
	}

	@Override
	public void render(Graphics2D g2) {
		
		//g2.setColor(Color.BLACK);
		//g2.fillRect(x, y, width, height);			
	
		// �Ѿ˵� �簢�� �غ�Ϸ�!!
		g2.drawRect(rect.x, rect.y, rect.width, rect.height);
		g2.drawImage(img, x, y, null);
		
		
	}

}
