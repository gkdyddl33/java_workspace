package day1028.graphic.line;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LineMaker extends JFrame{
	JLabel la_x1,la_y1,la_x2,la_y2;
	JTextField t_x1,t_y1,t_x2,t_y2;
	JPanel p_north;
	XCanvas can;
	MyButton bt;
	
	public LineMaker() {
		super("선 그리기 어플리케이션");
		// 생성
		la_x1 = new JLabel("x1");
		la_y1 = new JLabel("y1");
		la_x2 = new JLabel("x2");
		la_y2 = new JLabel("y2");
		
		t_x1 = new JTextField("0",10);
		t_y1 = new JTextField("0",10);
		t_x2 = new JTextField("100",10);
		t_y2 = new JTextField("200",10);
		
		bt = new MyButton("커스텀버튼");
		p_north = new JPanel();
		can = new XCanvas();
		
		// 조립
		add(p_north,BorderLayout.NORTH);
		p_north.add(la_x1);
		p_north.add(t_x1);
		p_north.add(la_y1);
		p_north.add(t_y1);
		p_north.add(la_x2);
		p_north.add(t_x2);
		p_north.add(la_y2);
		p_north.add(t_y2);
		p_north.add(bt);
		
		add(can);
		can.setBackground(Color.YELLOW);
		
		// 이벤트
		can.setLineMaker(this);
		bt.addActionListener(new MyListener(can));
		
		setSize(700,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new LineMaker();
	}
}
