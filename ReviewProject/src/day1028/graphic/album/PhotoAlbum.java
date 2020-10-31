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
	
	// 이미지 담겨 있는 클래스
	AlbumPanel p;
	
	public PhotoAlbum() {
		// 생성
		p_south = new JPanel();
		bt_next = new JButton("다음사진");
		bt_prev = new JButton("이전사진");
		p = new AlbumPanel();
		
		// 조립		
		add(p);
		add(p_south,BorderLayout.SOUTH);
		p_south.add(bt_prev);
		p_south.add(bt_next);
				
		// 스타일
		p.setBackground(Color.YELLOW);
		
		// 이벤트 연결
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
