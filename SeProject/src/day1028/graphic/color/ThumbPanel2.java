package day1028.graphic.color;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

// °ÅÇªÁý
public class ThumbPanel2 extends JPanel implements MouseListener{
	JPanel p_center;
	Color color;
	
	public ThumbPanel2(JPanel p_center,Color color) {
		this.p_center=p_center;
		this.color=color;
		
		this.setPreferredSize(new Dimension(100,100));
		this.setBackground(color);
		this.addMouseListener(this);
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
		p_center.setBackground(color);
		
	}
	
}
