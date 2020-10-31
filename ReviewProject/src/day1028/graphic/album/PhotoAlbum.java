package day1028.graphic.album;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PhotoAlbum extends JFrame implements ActionListener{
	JPanel p_south;
	JButton bt_prev,bt_next;
	
	// �̹��� ��� �ִ� Ŭ����
	AlbumPanel p;
	
	public PhotoAlbum() {
		// ����
		p_south = new JPanel();
		bt_next = new JButton("��������");
		bt_prev = new JButton("��������");
		p = new AlbumPanel();
		
		// ����		
		add(p);
		add(p_south,BorderLayout.SOUTH);
		p_south.add(bt_prev);
		p_south.add(bt_next);
				
		// ��Ÿ��
		p.setBackground(Color.YELLOW);
		
		// �̺�Ʈ ����
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		setSize(500,450);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==bt_prev) {
			p.n--;
		}else if(obj==bt_next) {
			p.n++;
		}
		p.repaint();
	}
	
	public static void main(String[] args) {
		new PhotoAlbum();
	}
}
