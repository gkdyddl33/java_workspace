package day1029.graphic.image;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ThumbCanvas extends Canvas implements MouseListener {
	// °ÅÇªÁý
	Toolkit kit;
	Image img;
	DetailPanel p_center;
	
	public ThumbCanvas(DetailPanel p_center,String path) {
		kit = Toolkit.getDefaultToolkit();
		img = kit.getImage(path);
		
		this.p_center = p_center;
		this.setPreferredSize(new Dimension(100,100));
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
		p_center.repaint();
		
	}
}
