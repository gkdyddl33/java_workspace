package day1029.graphic.image;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class DetailPanel extends JPanel{
	private Image img;
	
	public void setImg(Image img) {
		this.img = img;
		this.img = this.img.getScaledInstance(770, 500, Image.SCALE_SMOOTH);

	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(img,0, 0, this);
	}
}
