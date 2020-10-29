package day1028.graphic.album;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

//Canvas�� ������� �ʰ�, �г��� �̿��ϸ� ������ ȿ���� �� �� �ִ�.
public class AlbumPanel extends JPanel{
	// (2) �̹��� �÷���
	Toolkit kit = Toolkit.getDefaultToolkit();	
	
	// (1) ������ �̹��� 10���� �迭�� �غ��ϸ� ȿ������ ���� ����	
	String dir = "D:/workspace/java_workspace/SeProject/res/travel2/";
	String[] src = {
			//D:/workspace/java_workspace/SeProject/res/travel2/ = �����ϴ� ������ ����			
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
	Image[] img = new Image[src.length];
	
	// (2) ��ư 
	int n = 0;
	
	// �����ڿ��� �̹����� ��������!!
	public AlbumPanel() {
		for (int i = 0; i < img.length; i++) {
			// (2) 
			img[i] = kit.getImage(dir+src[i]);
			// (3) �̹��� ũ������!
			img[i]=img[i].getScaledInstance(500, 400, Image.SCALE_SMOOTH);
		}
	}
	
	// �츮�� �г��� ������ ���� �ִٴ� ���� �̹� paint �޼��� ȣ�⿡ ����, �׸��� �ϼ��Ǿ��� ������
	@Override
	public void paint(Graphics g) {
		g.drawImage(img[n], 0, 0, this);
	}
}
