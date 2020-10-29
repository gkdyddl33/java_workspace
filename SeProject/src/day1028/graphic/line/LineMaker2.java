package day1028.graphic.line;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LineMaker2 extends JFrame{
	JLabel la_x1,la_y1,la_x2,la_y2;
	JTextField t_x1,t_y1,t_x2,t_y2;
	MyButton2 bt;
	JPanel p_north;
	XCanvas2 can;
	
	public LineMaker2() {
		super("선 그리기 어플리케이션");
		
		la_x1 = new JLabel("x1");
		la_y1 = new JLabel("y1");
		la_x2 = new JLabel("x2");
		la_y2 = new JLabel("y2");
		
		t_x1 = new JTextField("0",10);
		t_y1 = new JTextField("0",10);
		t_x2 = new JTextField("100",10);
		t_y2 = new JTextField("200",10);
		
		bt = new MyButton2("커스텀 버튼");
		p_north = new JPanel();
		can = new XCanvas2();
		
		// 이벤트 부여
		can.setLineMaker2(this);
		
		p_north.add(la_x1);
		p_north.add(t_x1);
		p_north.add(la_y1);
		p_north.add(t_y1);
		p_north.add(la_x2);
		p_north.add(t_x2);
		p_north.add(la_y2);
		p_north.add(t_y2);
		p_north.add(bt);
		add(p_north,BorderLayout.NORTH);
		
		add(can); // 상속처리를 미리 해줘야 오류 발생 x
		can.setBackground(Color.YELLOW);
		
		bt.addActionListener(new MyListener2(can));
		
		setSize(700,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new LineMaker2();
	}
}
