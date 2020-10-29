package day1028.graphic.album;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PhotoAlbum extends JFrame implements ActionListener{
	// paint() 메서드를 재정의 하려면 클래스로 정의해야 한다. 커스터마이징
	AlbumPanel p;
	
	JPanel p_south;
	JButton bt_prev,bt_next;
	
	public PhotoAlbum() {
		// 생성
		p = new AlbumPanel();
		p.setBackground(Color.YELLOW);
		p_south = new JPanel();
		bt_prev = new JButton("이전 사진");
		bt_next = new JButton("다음 사진");
		
		// 조립
		add(p);	// center에 앨범패널 넣기
		p_south.add(bt_prev);
		p_south.add(bt_next);
		// 남쪽에 버튼패널넣기
		add(p_south,BorderLayout.SOUTH);
		
		// --------------------------------------------------------
		
		// (2) 리스너 연결
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		// 윈도우
		setSize(500,450);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// (2) 액션이벤트 정의!
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();	// 이벤트를 일으킨 컴포넌트 구하기
		
		if(obj==bt_prev) {
			// 이전 버튼이면 AlbumPanel(p).n을 감소
			p.n--;
		}else if(obj==bt_next) {
			// 다음 버튼이면 AlbumPanel.n을 증가
			p.n++;
		}
		// 화면 갱신 -> 직접 AlbumPanel의 paint 호출 불가해서 repaint()
		p.repaint(); // 다시그려주세요~ update() -> paint()
		
	}
	public static void main(String[] args) {
		new PhotoAlbum();
	}
	
}
