package day1029.graphic.image;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ThumbCanvas2 extends Canvas implements MouseListener{
	Toolkit kit;
	Image img;
	DetailPanel2 p_center;
	
	public ThumbCanvas2(DetailPanel2 p_center,String path) {
		kit = Toolkit.getDefaultToolkit();
		img = kit.getImage(path);
		
		this.setPreferredSize(new Dimension(100,100));
		
		this.p_center = p_center;
		this.addMouseListener(this);
			
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);		
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
	@Override
	public void mouseReleased(MouseEvent e) {
		p_center.setImg(img);
		p_center.repaint(); // detailimg 지워졋다가 새로 생기는 갱신
		
	}
	
}
