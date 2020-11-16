package day1116.pub.api;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MTMain2 extends JFrame{

	JPanel p_west;
	JTextField t_name;
	JTextField t_op1;
	JTextField t_op2;
	JTextField t_op3;
	JButton bt;
		
/*
	JTable 에서 데이터 연동시 지금까지는 이차원 배열만 써왔는데, 사실 이차원 배열은
	생성시 크기를 정해야 하기 때문에 불편한 점이 많습니다.
	따라서 컬렉션 프레임웍 중 Vector를 지원하는 생성자를 이용해봅시다.
	불편했던 예시) rs.last() 후 rs.getRow()로 데이터 총 수 구하고, 다시 커서를 원상복귀..
*/
	JTable table;
	JScrollPane scroll;
/*
	JTable에서 지원하는 Vector 방식은 2차원 배열보다는 유연하지만
	여전히 2차원 배열의 구조를 유지하므로 TableModel을 이용한 벡터 및 VO를 종합해서 개발해보자.
*/	
	MountainModel mountainModel;
	
	public MTMain2() {
		p_west = new JPanel();
		t_name = new JTextField(15);
		t_op1 = new JTextField(15);
		t_op2 = new JTextField(15);
		t_op3 = new JTextField(15);
		bt = new JButton("검색하기");
		
//		table = new JTable(mountainModel = new MountainModel());
		// TableModel 방식으로 가보자.
		table = new JTable(mountainModel = new MountainModel()); 
		scroll = new JScrollPane(table);
		
		add(p_west,BorderLayout.WEST);		
		p_west.add(t_name);
		p_west.add(t_op1);
		p_west.add(t_op2);
		p_west.add(t_op3);
		p_west.add(bt);		
		add(scroll);
		
		p_west.setPreferredSize(new Dimension(200,600));
		p_west.setBackground(Color.WHITE);
		t_name.setPreferredSize(new Dimension(190,30));
		t_op1.setPreferredSize(new Dimension(190,30));
		t_op2.setPreferredSize(new Dimension(190,30));
		t_op3.setPreferredSize(new Dimension(190,30));
		
		setSize(900,600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new MTMain2();
	}
}
