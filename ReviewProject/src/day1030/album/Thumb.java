package day1030.album;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Thumb extends JPanel implements MouseListener{
	Toolkit kit;
	Image img;
	GalleyApp galleryApp;

	// image 크기는 바뀌지 않으니 고정시키자.
	public static final int WIDTH = 75;
	public static final int HEIGHT = 55;

	public Thumb(String src, GalleyApp galleyApp) {
		this.galleryApp = galleyApp;
		
		kit = Toolkit.getDefaultToolkit();
		img = kit.getImage(src);
		img = img.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.BLACK);
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
		galleryApp.n= galleryApp.list.indexOf(this);
		galleryApp.updateData();
		
	}
}
