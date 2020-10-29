package day1029.graphic.image;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

// ���̹����� �׷��� �г�, �� �г��� Ŭ������ ������ �����ϴ� ������?
// paint �޼��带 ������ �ϱ� ����
public class DetailPanel extends JPanel{
	private Image img;
	
	// �������� �� �޼��带 ȣ���� �ڴ� � �̹����� ���������� �Ű������� �ѱ�� �ȴ�
	// ����ȭ = ĸ��ȭ private
	public void setImg(Image img) {
		this.img = img;
		//(3������) �׸� ũ�� ����	-> ������ �̹����� �ٽ� �� ����
		this.img=this.img.getScaledInstance(770, 500, Image.SCALE_SMOOTH);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}

}
