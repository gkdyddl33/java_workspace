package day1029.graphic.image;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImagePickerApp2 extends JFrame{
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
	
	JPanel p_north;
	DetailPanel2 p_center;
	
	// 7개 이미지 = 배열
	ThumbCanvas2[] thumb = new ThumbCanvas2[path.length];
	
	public ImagePickerApp2() {
		p_north = new JPanel();
		p_center = new DetailPanel2();
		
		for (int i = 0; i < thumb.length; i++) {
			thumb[i] = new ThumbCanvas2(p_center,path[i]);
			p_north.add(thumb[i]);
		}
		
		add(p_north,BorderLayout.NORTH);
		add(p_center);
		
		setSize(770,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new ImagePickerApp2();
	}
}
