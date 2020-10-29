package day1028.graphic.color;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorPickerApp2 extends JFrame{
	JPanel p_north;
	JPanel p_center;
	
	ThumbPanel2[] thumb = new ThumbPanel2[7];
	Color[] colorArray= {
			Color.RED,Color.ORANGE,Color.YELLOW,
			Color.GREEN,Color.CYAN,Color.BLUE,Color.PINK
	};
	
	public ColorPickerApp2() {
		p_north = new JPanel();
		p_center = new JPanel();
		
		for (int i = 0; i < thumb.length; i++) {
			thumb[i] = new ThumbPanel2(p_center,colorArray[i]);
			p_north.add(thumb[i]);
		}
		
		p_center.setBackground(Color.WHITE);
		add(p_north,BorderLayout.NORTH);
		add(p_center);
		
		// À©µµ¿ì
		setSize(770,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new ColorPickerApp2();
	}
}
