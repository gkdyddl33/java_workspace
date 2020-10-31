package day1029.graphic.image;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImagePickerApp extends JFrame{
	JPanel p_north;
	DetailPanel p_center;
	
	// 이미지 넣기
	String dir = "D:/workspace/java_workspace/SeProject/res/travel2/";
	String[] path = {
			"aa.jpg","ab.jpg","ax.jpg","bm.jpg","et.jpg","kg.jpg","mx.jpg"
	};
	
	ThumbCanvas[] thumb = new ThumbCanvas[path.length];
	public ImagePickerApp() {
		super("이미지 썸네일");
		
		// 생성
		p_north = new JPanel();
		p_center = new DetailPanel();
		
		// 조립
		add(p_north,BorderLayout.NORTH);
		add(p_center);
		// 이벤트
		for (int i = 0; i < path.length; i++) {
			thumb[i] = new ThumbCanvas(p_center,dir+path[i]);
			p_north.add(thumb[i]);
		}
		
		setSize(770,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new ImagePickerApp();
	}
}
