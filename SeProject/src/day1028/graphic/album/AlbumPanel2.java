package day1028.graphic.album;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class AlbumPanel2 extends JPanel{
	Toolkit kit = Toolkit.getDefaultToolkit();
	
	// 공간에 이미지 넣기 -> 호출할때 같이 호출
	String dir = "D:/workspace/java_workspace/SeProject/res/travel2/";
	String[] src = {
			"aa.jpg",
			"ab.jpg",
			"ax.jpg",
			"bm.jpg",
			"et.jpg",
			"kg.jpg",
			"mx.jpg",
			"pk.jpg",
			"ub.jpg",
			"ya.jpg"
	};
	
	Image[] img = new Image[src.length]; // src 에 있는 이미지의 수 만큼 공간 확보
	
	int n =0;
	
	public AlbumPanel2() {
		// 이미지 보이게
		for (int i = 0; i < img.length; i++) {
			img[i] = kit.getImage(dir+src[i]);
			// 이미지 크기 조정
			img[i] = img[i].getScaledInstance(500, 400, Image.SCALE_SMOOTH);
		}
		
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(img[n], 0, 0, this);
	}
}
