package day1030.album;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

// 썸네일 하나를 정의한다 - 거푸집!
public class Thumb extends JPanel implements MouseListener{
	// (3) 이미지
	Toolkit kit;
	Image img;
	
	// image 크기는 바뀌지 않으니 고정시키자.
	public static final int WIDTH = 75;
	public static final int HEIGHT = 55;
	
	// (7)
	GalleyApp galleryApp;
	
	public Thumb(String src,GalleyApp galleryApp) {// img의 파일명을 매개변수로 가져오자
		this.galleryApp=galleryApp; // injection 주입받는다!
		kit = Toolkit.getDefaultToolkit();
		img = kit.getImage(src);
		img = img.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setBackground(Color.BLACK);
		
		// 리스너와 연결
		this.addMouseListener(this);
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("썸네일 눌렀어?");
		
		// n을 지금 나의.index 값으로..바꾸자.. = indexOf
		// 현재 패널이 ArrayList 내에서의 몇번재 인덱스에 들어있는지 역추적하자
		galleryApp.n = galleryApp.list.indexOf(this);
		galleryApp.updateData();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
