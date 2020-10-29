package day1029.graphic.image;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import day1028.graphic.color.ColorPickerApp;

public class ImagePickerApp extends JFrame{
	String dir = "D:/workspace/java_workspace/SeProject/res/travel2/";
	String[] path = {
			"aa.jpg",
			"ab.jpg",
			"ax.jpg",
			"bm.jpg",
			"et.jpg",
			"kg.jpg",
			"mx.jpg"
	};
	
	JPanel p_north;		// 작은 이미지
	DetailPanel p_center;		// 큰 이미지
	
	ThumbCanvas[] thumb = new ThumbCanvas[path.length]; // (2)이미지 칸
	
	public ImagePickerApp() {
		p_north = new JPanel();
		p_center = new DetailPanel();
		
		// (2)
		for (int i = 0; i < thumb.length; i++) {
			thumb[i] = new ThumbCanvas(p_center,dir+path[i]); // 하나의 거푸집
			p_north.add(thumb[i]);
		}
		
		// 조립
		add(p_north,BorderLayout.NORTH);
		add(p_center);
		
		setSize(770,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new ImagePickerApp();
	}
}
