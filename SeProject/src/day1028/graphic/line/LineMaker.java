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
	MyButton bt; // ��ư�� ����� �����ϴٶ�� ���� �����ֱ� ���� Ŭ���� ����
	JPanel p_north;
	//Canvas can;	// �����Ǹ� �ؾ� �ϹǷ� Ŭ������ �ϳ� ������ �Ѵ�.
	XCanvas can;
	
	public LineMaker() {
		super("�� �׸��� ���ø����̼�");	// �޴� ����!! -> �������� �θ�ϱ�
		
		la_x1 = new JLabel("x1");
		la_y1 = new JLabel("y1");
		la_x2 = new JLabel("x2");
		la_y2 = new JLabel("y2");
		
		t_x1 = new JTextField("0",10); // (4) "0���� �ʱ�ȭ"
		t_y1 = new JTextField("0",10);
		t_x2 = new JTextField("100",10); // 100,200���� ���ڸ� ������ ���� �׸���.
		t_y2 = new JTextField("200",10);
		
		bt = new MyButton("Ŀ���� ��ư");		
		p_north = new JPanel();		
		can = new XCanvas();	
		
		// (4) �̺�Ʈ �̸� ����
		// (4-1) XCanvas(���׸���) -> �޼��带 �̿��ؼ� ȣ�� �ؾߵ� �Ű������� �������
		can.setLineMaker(this); // ���� LineMaker
		
		// (3) style ����
		can.setBackground(Color.YELLOW);
		
		// (1) north ����
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
		
		// (2) center ����
		add(can);	// center ������ �˹��� �߰�		
						
		
		// (4-2) MyListener -> �����ʿ��� ����
		bt.addActionListener(new MyListener(can));
		
		
		// ������� ���õ� �Ӽ� ���� = �����ϱ� �� �̸� ����!
		setSize(700,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new LineMaker();
	}
}
