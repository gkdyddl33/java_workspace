package day1103.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CircleMove extends JFrame{
	JPanel can;
	JButton bt;
	
	int x,y;
	Thread thread;
	
	public CircleMove() {
		can = new JPanel() {
			// 내부익명클래스도 클래스이므로, .class로 존재하되 명칭이 없으므로 $(순번) 형태의 파일명을 갖는다.
			// 내부익명클래스 사용시 장점? .java를 굳이 안만들어도 되고 개발시간단축..
			// 외부 클래스의 멤버를 마치 자기꺼처럼 사용할 수 있다.
			
			@Override
			public void paint(Graphics g) {				
				// 채워진 사각형 => setBackground 대신..
				g.setColor(Color.YELLOW);
				g.fillRect(0, 0, 740, 640);
				
				g.setColor(Color.RED);
				// g.drawOval(100, 100, 50, 50); // 선으로된 원
				g.fillOval(x, y, 50, 50); // 채워진 원
			}
		};
		
		bt = new JButton("움직이기");
		
		can.setPreferredSize(new Dimension(700,600));
		//can.setBackground(Color.YELLOW); -> paint()를 재정의해서 안먹힘
				
		setLayout(new FlowLayout());
		add(bt);
		add(can);
		
		//(2) 리스너 연결(수동)
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// 수동움직임
				//move();
				//can.repaint();
				
				// thread를 runnable 진입시키자->날라감
				thread.start();
			}
		});
				
		//(3) 쓰레드
		thread = new Thread() {
			@Override
			public void run() {
				while(true) {
					move();
					can.repaint();
					try {
						thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		setSize(740,640);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	// (2) 원의 좌표를 그려보자.
	public void move() {
		x +=2;
		y +=2;
		
	}
	
	public static void main(String[] args) {
		new CircleMove();
	}
}
