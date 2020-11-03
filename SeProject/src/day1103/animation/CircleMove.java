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
			// �����͸�Ŭ������ Ŭ�����̹Ƿ�, .class�� �����ϵ� ��Ī�� �����Ƿ� $(����) ������ ���ϸ��� ���´�.
			// �����͸�Ŭ���� ���� ����? .java�� ���� �ȸ��� �ǰ� ���߽ð�����..
			// �ܺ� Ŭ������ ����� ��ġ �ڱⲨó�� ����� �� �ִ�.
			
			@Override
			public void paint(Graphics g) {				
				// ä���� �簢�� => setBackground ���..
				g.setColor(Color.YELLOW);
				g.fillRect(0, 0, 740, 640);
				
				g.setColor(Color.RED);
				// g.drawOval(100, 100, 50, 50); // �����ε� ��
				g.fillOval(x, y, 50, 50); // ä���� ��
			}
		};
		
		bt = new JButton("�����̱�");
		
		can.setPreferredSize(new Dimension(700,600));
		//can.setBackground(Color.YELLOW); -> paint()�� �������ؼ� �ȸ���
				
		setLayout(new FlowLayout());
		add(bt);
		add(can);
		
		//(2) ������ ����(����)
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// ����������
				//move();
				//can.repaint();
				
				// thread�� runnable ���Խ�Ű��->����
				thread.start();
			}
		});
				
		//(3) ������
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
	
	// (2) ���� ��ǥ�� �׷�����.
	public void move() {
		x +=2;
		y +=2;
		
	}
	
	public static void main(String[] args) {
		new CircleMove();
	}
}
