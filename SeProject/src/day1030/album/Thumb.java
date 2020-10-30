package day1030.album;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

// ����� �ϳ��� �����Ѵ� - ��Ǫ��!
public class Thumb extends JPanel implements MouseListener{
	// (3) �̹���
	Toolkit kit;
	Image img;
	
	// image ũ��� �ٲ��� ������ ������Ű��.
	public static final int WIDTH = 75;
	public static final int HEIGHT = 55;
	
	// (7)
	GalleyApp galleryApp;
	
	public Thumb(String src,GalleyApp galleryApp) {// img�� ���ϸ��� �Ű������� ��������
		this.galleryApp=galleryApp; // injection ���Թ޴´�!
		kit = Toolkit.getDefaultToolkit();
		img = kit.getImage(src);
		img = img.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setBackground(Color.BLACK);
		
		// �����ʿ� ����
		this.addMouseListener(this);
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("����� ������?");
		
		// n�� ���� ����.index ������..�ٲ���.. = indexOf
		// ���� �г��� ArrayList �������� ����� �ε����� ����ִ��� ����������
		galleryApp.n = galleryApp.list.indexOf(this);
		galleryApp.updateData();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
