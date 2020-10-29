package day1028.graphic.line;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LineMaker extends JFrame{
	JLabel la_x1,la_y1,la_x2,la_y2;	
	JTextField t_x1,t_y1,t_x2,t_y2;	
	// JButton bt;
	MyButton bt; // 버튼도 상속이 가능하다라는 것을 보여주기 위해 클래스 생성
	JPanel p_north;
	//Canvas can;	// 재정의를 해야 하므로 클래스를 하나 만들어야 한다.
	XCanvas can;
	
	public LineMaker() {
		super("선 그리기 어플리케이션");	// 메뉴 제목!! -> 프레임이 부모니깐
		
		la_x1 = new JLabel("x1");
		la_y1 = new JLabel("y1");
		la_x2 = new JLabel("x2");
		la_y2 = new JLabel("y2");
		
		t_x1 = new JTextField("0",10); // (4) "0으로 초기화"
		t_y1 = new JTextField("0",10);
		t_x2 = new JTextField("100",10); // 100,200으로 숫자를 넣으면 선을 그린다.
		t_y2 = new JTextField("200",10);
		
		bt = new MyButton("커스텀 버튼");		
		p_north = new JPanel();		
		can = new XCanvas();	
		
		// (4) 이벤트 미리 선언
		// (4-1) XCanvas(선그리기) -> 메서드를 이용해서 호출 해야됨 매개변수를 사용하자
		can.setLineMaker(this); // 내가 LineMaker
		
		// (3) style 적용
		can.setBackground(Color.YELLOW);
		
		// (1) north 조립
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
		
		// (2) center 조립
		add(can);	// center 영역에 켄버스 추가		
						
		
		// (4-2) MyListener -> 리스너와의 연결
		bt.addActionListener(new MyListener(can));
		
		
		// 윈도우와 관련된 속성 지정 = 실행하기 전 미리 선언!
		setSize(700,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new LineMaker();
	}
}
