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
	JPanel can;	// 패널은 도화지 대신으로
	JButton bt;
	int x,y;
	Thread thread;
	
	public CircleMove() {
		// 생성
		can = new JPanel(){
			@Override
			public void paint(Graphics g) {
				// panel 배경
				g.setColor(Color.YELLOW);
				g.fillRect(0, 0, 740, 640);
				
				g.setColor(Color.RED);
				g.fillOval(x, y, 50, 50); // 원은 움직여야 한다. 변수 선언
			}
		};
		bt = new JButton("움직이기");
		can.setPreferredSize(new Dimension(700,600));
		
		// 조립
		setLayout(new FlowLayout());
		add(can);
		add(bt);
		
		// 연결
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//move();
				//can.repaint();
				thread.start();
				
			}
		});
		// 쓰레드
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
		
		// 윈도우
		setSize(740,640);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void move() {
		x +=2;
		y += 2;
	}
	
	public static void main(String[] args) {
		new CircleMove();
	}
}
