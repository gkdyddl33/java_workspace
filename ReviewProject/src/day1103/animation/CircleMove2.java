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

public class CircleMove2 extends JFrame{
	JPanel can;
	JButton bt;
	
	int x,y;
	Thread thread;
	public CircleMove2() {
		// 생성
		can = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.setColor(Color.YELLOW);
				g.fillRect(0, 0, 740, 640);
				
				g.setColor(Color.RED);
				g.fillOval(x, y, 50, 50); // 공은 움직일거니깐 
			}
		};
		bt=new JButton("움직이기");
		
		// 조립
		can.setPreferredSize(new Dimension(700,600));
		
		setLayout(new FlowLayout());
		add(bt);
		add(can);
		
		// 연결
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				thread.start();
				
			}
		});
		
		thread = new Thread() {
			@Override
			public void run() {
				move();
				can.repaint();
				try {
					thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
		x += 2;
		y+=2;
	}
	public static void main(String[] args) {
		new CircleMove2();
	}
}
