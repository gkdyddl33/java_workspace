package day1028.graphic.album;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PhotoAlbum2 extends JFrame implements ActionListener{
	AlbumPanel2 p;	// 사진을 담을 공간 변수 선언
	
	JPanel p_south;
	JButton bt_prev,bt_next;
	
	public PhotoAlbum2() {
		p = new AlbumPanel2();
		p_south = new JPanel();
		bt_prev = new JButton("이전사진");
		bt_next = new JButton("다음사진");
		
		add(p);
		p_south.add(bt_prev);
		p_south.add(bt_next);
		add(p_south,BorderLayout.SOUTH);
		
		// ------------------------------------------- 리스너 연결 미리 선언
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		setSize(500,450);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// 이미지가 넘어간다. 이미지를 먼저 만들자
		Object obj = e.getSource();
		if(obj==bt_prev) {
			p.n--;
		}else if(obj==bt_next) {
			p.n++;
		}
		p.repaint(); // 사진이 넘어갈 때 지우고 다시 이미지를 넣는 작업!!
	}
	public static void main(String[] args) {
		new PhotoAlbum2();
	}
	
}
