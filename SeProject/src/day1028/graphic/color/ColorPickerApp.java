package day1028.graphic.color;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorPickerApp extends JFrame{
	// (1) 썸네일이 모일 패널설정
	JPanel p_north;	
	JPanel p_center;

	
	// (1-1) 썸네일 7가지 배열
	ThumbPanel[] thumb = new ThumbPanel[7];
	// 컬러 선언
	Color[] colorArray= {
			Color.RED,Color.ORANGE,Color.YELLOW,
			Color.GREEN,Color.CYAN,Color.BLUE,Color.PINK
	};
	
	public ColorPickerApp() {
		p_north = new JPanel();
		p_center = new JPanel();
		
		// (1-1) 배열을 반복문으로 색상넣기
		for (int i = 0; i < thumb.length; i++) {
			thumb[i] = new ThumbPanel(p_center,colorArray[i]); // (2)
			p_north.add(thumb[i]);			
		}
		
		p_center.setBackground(Color.WHITE);
		// (1) p_north를 프레임의 북쪽에 부착
		add(p_north,BorderLayout.NORTH);
		add(p_center);	// 프레임의 센터에 부착
	
		
		// 윈도우
		setSize(770,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new ColorPickerApp();
	}
}
