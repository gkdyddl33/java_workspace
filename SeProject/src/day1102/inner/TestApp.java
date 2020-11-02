package day1102.inner;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class TestApp extends JFrame{
	Canvas can;
	JButton bt;
	JTextField t;
	
	public TestApp() {
		// Canvas를 현재 클래스에서만 사용하고, 추후 재사용성이 없을 경우
		// 굳이 거푸집 (.java)을 정의할 이유가 있을까?
		// 즉, 일회성의 객체인 경우 내부익명클래스로 사용할 수 있다.
		// 형식은 앞의 클래스를 구현한 클래스로 이해하면 된다.
		// 주로 이벤트를 구현한 클래스는 해당 어플리케이션에서만 사용되므로,
		// 재사용 가능성이 없다.. 따라서 내부익명클래스로 많이 사용된다.
		//(1)
		can = new Canvas() {// 익명클래스 Anonymous, 내부클래스 inner -> 자식영역
			
			@Override
			public void paint(Graphics g) {
				g.drawLine(0, 0, 100, 100);
			}
			
		};
		setLayout(new FlowLayout());
		
		// (2)부착과 생성을 동시에~
		add(bt=new JButton() {
			@Override
			public void paint(Graphics g) {
				g.setColor(Color.RED);
				g.fillRect(0, 0, 100, 40);
			}
		});
		add(can);
		can.setPreferredSize(new Dimension(300,300));
		
		//(3)
		// 내부익명클래스는 자신을 포함하고 있는 바깥쪽 클래스의 멤버변수를 접근할 수 있다.
		add(t=new JTextField(20));
		t.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("키 눌렀어?");
			}
		});
		final int x =0;
		// 내부 익명 클래스는 지역변수를 final로 된것만 접근이 가능..!!!!
		//(4)
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(x+"나 눌렀어?");				
			}
		});
		
		//(5)윈도우이벤트
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		setSize(400,400);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new TestApp();
	}
}
