package day1028.graphic.color;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorPickerApp extends JFrame{
	JPanel c_north;
	JPanel c1,c2,c3,c4,c5,c6,c7;
	JPanel p_center;
	
	public ColorPickerApp() {
		c_north = new JPanel();
		c1 = new JPanel();
		c2 = new JPanel();
		c3 = new JPanel();
		c4 = new JPanel();
		c5 = new JPanel();
		c6 = new JPanel();
		c7 = new JPanel();
		p_center = new JPanel();
		
		// 조립
		add(c_north,BorderLayout.NORTH);
		c_north.add(c1);
		c_north.add(c2);
		c_north.add(c3);
		c_north.add(c4);
		c_north.add(c5);
		c_north.add(c6);
		c_north.add(c7);		
		
		// 스타일 적용
		c1.setBackground(Color.RED);
		c2.setBackground(Color.ORANGE);
		c3.setBackground(Color.YELLOW);
		c4.setBackground(Color.GREEN);
		c5.setBackground(Color.CYAN);
		c6.setBackground(Color.BLUE);
		c7.setBackground(Color.DARK_GRAY);
		
		c1.setPreferredSize(new Dimension(100,100));
		c2.setPreferredSize(new Dimension(100,100));
		c3.setPreferredSize(new Dimension(100,100));
		c4.setPreferredSize(new Dimension(100,100));
		c5.setPreferredSize(new Dimension(100,100));
		c6.setPreferredSize(new Dimension(100,100));
		c7.setPreferredSize(new Dimension(100,100));
		
		// 이벤트처리
				
		// 윈도우
		setSize(700,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new ColorPickerApp();
	}
}
