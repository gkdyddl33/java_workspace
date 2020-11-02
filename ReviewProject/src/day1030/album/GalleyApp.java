package day1030.album;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import day1028.graphic.line.XCanvas;

public class GalleyApp extends JFrame{
	// 틀
	JPanel p_west;
	JPanel p_center;
	JScrollPane scroll;
	
	JLabel la_name;
	XCanvas can;
	JPanel p_south;
	JButton bt_prev;
	JButton bt_next;
	
	// 거푸집 Thumb
	ArrayList<Thumb> list = new ArrayList<>();
	String dir = "D:/workspace/java_workspace/SeProject/res/travel2/";
	String[] src = {
			"aa.jpg","ab.jpg","ax.jpg","bm.jpg","et.jpg","kg.jpg","mx.jpg",
			"pk.jpg","ub.jpg","ya.jpg"
	};
	int n =0;
	public GalleyApp() {
		super("자바 갤러리");
		
		p_west = new JPanel();
		p_center = new JPanel();
		scroll = new JScrollPane(p_west);
		
		la_name = new JLabel();
		can = new XCanvas();
		p_south = new JPanel();
		bt_prev = new JButton("이전");
		bt_next = new JButton("다음");
		
		p_west.setPreferredSize(new Dimension(100,600));
		p_center.setPreferredSize(new Dimension(700,600));
		p_west.setBackground(Color.YELLOW);
		p_center.setBackground(Color.GREEN);
		add(scroll,BorderLayout.WEST);
		add(p_center);
		
		la_name.setPreferredSize(new Dimension(700,50));
		la_name.setBackground(Color.YELLOW);
		la_name.setFont(new Font("Verdana", Font.BOLD, 25));
		p_south.add(bt_prev);
		p_south.add(bt_next);
		p_center.add(la_name);
		p_center.add(can);
		p_center.add(p_south);
		
		// 이미지와 경로 배열
		for (int i = 0; i < src.length; i++) {
			Thumb thumb = null;
			list.add(thumb = new Thumb(dir+src[i],this)); // 생성자함수
			p_west.add(thumb);
		}
		
		setSize(800,600);
		setVisible(true);
		setLocationRelativeTo(null); // 상대의 위치를 놓는다 = 중앙
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	// 중복코드 사용
	public void updateData() {
		// 도화지
		//can.setSrc(dir+src[n]);
		can.repaint();
		la_name.setText(src[n]+"("+(n+1)+"/"+src.length+")");
	}
	
	public static void main(String[] args) {
		new GalleyApp();
	}
}
