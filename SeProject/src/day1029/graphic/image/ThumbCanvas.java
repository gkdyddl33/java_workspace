package day1029.graphic.image;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ThumbCanvas extends Canvas implements MouseListener{
	Toolkit kit;
	Image img;
	DetailPanel p_center; // null이기 때문에 기존꺼를 넘겨봣자
	
	public ThumbCanvas(DetailPanel p_center,String path) {
		kit = Toolkit.getDefaultToolkit();	// static메서드 호출
		img = kit.getImage(path);	// 이미지 경로 넘겨 받기
		
		// 거푸집 생성
		this.setPreferredSize(new Dimension(100,100));
		//(3마지막) 그림 크기 조절	-> detailPanel
		
		this.p_center=p_center;
		
		// (3마지막) 마우스 리스너와 연결
		this.addMouseListener(this);
	}
	
	// 컨버스(도화지) 그림 그리자 = paint()
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {		
		// p_center에게 나의 img를 전달해야 한다. = detail/setImg
		p_center.setImg(img);
		// (3) p_center 패널에, 이미지를 그리되, 현재 나의 이미지를 가지고..
		p_center.repaint();	// 이미지 갱신
		
		
	}
}
