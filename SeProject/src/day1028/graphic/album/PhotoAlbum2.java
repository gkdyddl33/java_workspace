package day1028.graphic.album;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PhotoAlbum2 extends JFrame implements ActionListener{
	AlbumPanel2 p;	// ������ ���� ���� ���� ����
	
	JPanel p_south;
	JButton bt_prev,bt_next;
	
	public PhotoAlbum2() {
		p = new AlbumPanel2();
		p_south = new JPanel();
		bt_prev = new JButton("��������");
		bt_next = new JButton("��������");
		
		add(p);
		p_south.add(bt_prev);
		p_south.add(bt_next);
		add(p_south,BorderLayout.SOUTH);
		
		// ------------------------------------------- ������ ���� �̸� ����
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		setSize(500,450);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// �̹����� �Ѿ��. �̹����� ���� ������
		Object obj = e.getSource();
		if(obj==bt_prev) {
			p.n--;
		}else if(obj==bt_next) {
			p.n++;
		}
		p.repaint(); // ������ �Ѿ �� ����� �ٽ� �̹����� �ִ� �۾�!!
	}
	public static void main(String[] args) {
		new PhotoAlbum2();
	}
	
}
