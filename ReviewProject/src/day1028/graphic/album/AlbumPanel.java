package day1028.graphic.album;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class AlbumPanel extends JPanel{
	Toolkit kit = Toolkit.getDefaultToolkit();
	String dir = "D:/workspace/java_workspace/SeProject/res/travel2/";
	String[] src = {
			"aa.jpg","ab.jpg","ax.jpg","bm.jpg","et.jpg","kg.jpg","mx.jpg",
			"pk.jpg","ub.jpg","ya.jpg"	
	};
	// 이미지 공간 확보
	Image[] img = new Image[src.length];
	
	int n =0;
	public AlbumPanel() {// 이미지가 반복적으로 보이게
		for (int i = 0; i < img.length; i++) {
			img[i] = kit.getImage(dir+src[i]);
			img[i] = img[i].getScaledInstance(500, 400, Image.SCALE_SMOOTH);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img[n], 0, 0, this);
	}
}
