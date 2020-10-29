package day1029.graphic.image;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ThumbCanvas extends Canvas implements MouseListener{
	Toolkit kit;
	Image img;
	DetailPanel p_center; // null�̱� ������ �������� �Ѱܔf��
	
	public ThumbCanvas(DetailPanel p_center,String path) {
		kit = Toolkit.getDefaultToolkit();	// static�޼��� ȣ��
		img = kit.getImage(path);	// �̹��� ��� �Ѱ� �ޱ�
		
		// ��Ǫ�� ����
		this.setPreferredSize(new Dimension(100,100));
		//(3������) �׸� ũ�� ����	-> detailPanel
		
		this.p_center=p_center;
		
		// (3������) ���콺 �����ʿ� ����
		this.addMouseListener(this);
	}
	
	// ������(��ȭ��) �׸� �׸��� = paint()
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {		
		// p_center���� ���� img�� �����ؾ� �Ѵ�. = detail/setImg
		p_center.setImg(img);
		// (3) p_center �гο�, �̹����� �׸���, ���� ���� �̹����� ������..
		p_center.repaint();	// �̹��� ����
		
		
	}
}
