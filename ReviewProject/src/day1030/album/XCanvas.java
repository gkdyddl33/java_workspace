package day1030.album;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class XCanvas extends Canvas{
	private Toolkit kit = Toolkit.getDefaultToolkit();
	private Image img;
	private String src;
	
	// image 크기는 바뀌지 않으니 고정시키자.
	public static final int WIDTH = 660;
	public static final int HEIGHT = 450;
	
	public XCanvas(String src) {
		img = kit.getImage(src);
		img = img.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setBackground(Color.GRAY);
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		img = kit.getImage(src);
		img = img.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		this.src = src;
	}
	
}
