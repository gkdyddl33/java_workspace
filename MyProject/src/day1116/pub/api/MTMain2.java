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
	JTable ���� ������ ������ ���ݱ����� ������ �迭�� ��Դµ�, ��� ������ �迭��
	������ ũ�⸦ ���ؾ� �ϱ� ������ ������ ���� �����ϴ�.
	���� �÷��� �����ӿ� �� Vector�� �����ϴ� �����ڸ� �̿��غ��ô�.
	�����ߴ� ����) rs.last() �� rs.getRow()�� ������ �� �� ���ϰ�, �ٽ� Ŀ���� ���󺹱�..
*/
	JTable table;
	JScrollPane scroll;
/*
	JTable���� �����ϴ� Vector ����� 2���� �迭���ٴ� ����������
	������ 2���� �迭�� ������ �����ϹǷ� TableModel�� �̿��� ���� �� VO�� �����ؼ� �����غ���.
*/	
	MountainModel mountainModel;
	
	public MTMain2() {
		p_west = new JPanel();
		t_name = new JTextField(15);
		t_op1 = new JTextField(15);
		t_op2 = new JTextField(15);
		t_op3 = new JTextField(15);
		bt = new JButton("�˻��ϱ�");
		
//		table = new JTable(mountainModel = new MountainModel());
		// TableModel ������� ������.
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
