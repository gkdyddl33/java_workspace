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

public class MTMain extends JFrame{

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
	Vector data = new Vector<>();	// 이차원 배열이 될 예정
	Vector<String> columnName = new Vector<>();
	
	MountainModel mountainModel;
	
	public MTMain() {
		init();		// 데이터 채우기
		
		
		p_west = new JPanel();
		t_name = new JTextField(15);
		t_op1 = new JTextField(15);
		t_op2 = new JTextField(15);
		t_op3 = new JTextField(15);
		bt = new JButton("검색하기");
		
//		table = new JTable(mountainModel = new MountainModel());
		table = new JTable(data,columnName); // 생성자에 vector를 매개변수로 넣기
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
	// 1) 벡터를 초기화 하자. --> 해당 메서드를 생성자에서 호출하자.
	public void init() {
		// 데이터 1건 넣어보기
		Vector v = new Vector();
		v.add("1");
		v.add("설악산");
		v.add("강원도");
		v.add("3000");
		
		data.add(v);	// 벡터안의 벡터!
							// 결국 이차원 배열 모습을 그대로 유지함
							// 우리가 원했던 것은 Mountain VO를 넣어야 하지만,
							// 벡터를 넣는 것을 강제함..옛날방식!
		
		// 칼럼 정보 채우고
		columnName.add("ID");
		columnName.add("산이름");
		columnName.add("소재지");
		columnName.add("높이");
	}
	
	public static void main(String[] args) {
		new MTMain();
	}
}
