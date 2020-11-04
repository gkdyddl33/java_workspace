package day1104.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Hero extends GameObject{
	GamePanel gamePanel; // enemyList�� �ִ� Ŭ���� -> �浹Ȯ�ζ�����
	
	public Hero(GamePanel gamePanel,Image img,int x,int y,int width,int height,int velX,int velY) {
		super(img,x,y,width,height,velX,velY);
		this.gamePanel = gamePanel;
		
	}
	// ������ ���� �浹���θ� �Ǵ��ϰ�, ���� �浹�ϸ� ���� Hp�װ� ���װ�..	
	public void collisionCheck() {
		for(int i=0;i<gamePanel.enemyList.size();i++) {
			Enemy enemy = gamePanel.enemyList.get(i);
			if(this.rect.intersects(enemy.rect)) {// �浹�ߴٸ�
				gamePanel.hpList.remove(gamePanel.hpList.size()-1);//�ڿ������� ���� ���̱�
				gamePanel.enemyList.remove(enemy); // ���װ�
				break;
			}			
		}
	}
	// ������ ��ȭ(�������� ��ȭ)
	public void tick() {
		this.x += this.velX;
		this.y += this.velY;
		
		// �簢���� ���ΰ��� �׻� ��ü�ؾ� �Ѵ�.
		rect.x=x;
		rect.y=y;
		if(gamePanel.hpList.size()>1) {
			// �������� 1���̻��� ��츸 �����ؾ� �ȴ�..
			// 0����� �������Ḧ �ؾ� �� ������ �ٴ�
			collisionCheck();
		}else {
			System.out.println("��������, ������ ��� ����"); 
			gamePanel.over=true;
			gamePanel.flag=false; // ���� �����.
		}
	}
	
	// �׷��� ó��(ȭ�� �׷��� ó��)
	public void render(Graphics2D g2) {
		// �гο� �׷��� �Ѵ�. -> GamePanel�� paint() �� g2 ������ �����;� �Ѵ�..
		// ��� ���� ĳ���ʹ� �гο� �׷��� �ϹǷ�, g2�� �г��� paint() �޼����� ���������� �޾ƿ���
		
		//g2.setColor(Color.RED);
		//g2.fillRect(x, y, 100, 45);		
		g2.drawRect(rect.x, rect.y, rect.width, rect.height);
		// �츮�� �̹� �����ϰ� �ִ� �簢���� �ð�ȭ ���Ѻ���.
		g2.drawImage(img, rect.x, rect.y, null);
			
	}
}
