package day1029.graphic.color;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorPickerApp extends JFrame{
	JPanel p_north;
	JPanel p_center;
	
	// 7개 이미지 넣기
	ThumbPanel[] thumb = new ThumbPanel[7];
	Color[] colorArray = {
			Color.RED,Color.ORANGE,Color.YELLOW,
			Color.GREEN,Color.CYAN,Color.BLUE,Color.PINK
	};
	
	public ColorPickerApp() {
		// 새성
		p_north = new JPanel();
		p_center = new JPanel();
		
		// 조립
		p_center.setBackground(Color.WHITE);
		add(p_north,BorderLayout.NORTH);
		add(p_center);
		
		// 반복문으로 7개 이미지
		for (int i = 0; i < thumb.length; i++) {
			thumb[i] = new ThumbPanel(p_center,colorArray[i]);
			p_north.add(thumb[i]);
		}
		
		setSize(770,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new ColorPickerApp();
	}
}
