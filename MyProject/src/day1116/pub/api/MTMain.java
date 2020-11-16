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
	JTable ���� ������ ������ ���ݱ����� ������ �迭�� ��Դµ�, ��� ������ �迭��
	������ ũ�⸦ ���ؾ� �ϱ� ������ ������ ���� �����ϴ�.
	���� �÷��� �����ӿ� �� Vector�� �����ϴ� �����ڸ� �̿��غ��ô�.
	�����ߴ� ����) rs.last() �� rs.getRow()�� ������ �� �� ���ϰ�, �ٽ� Ŀ���� ���󺹱�..
*/
	JTable table;
	JScrollPane scroll;	
	Vector data = new Vector<>();	// ������ �迭�� �� ����
	Vector<String> columnName = new Vector<>();
	
	MountainModel mountainModel;
	
	public MTMain() {
		init();		// ������ ä���
		
		
		p_west = new JPanel();
		t_name = new JTextField(15);
		t_op1 = new JTextField(15);
		t_op2 = new JTextField(15);
		t_op3 = new JTextField(15);
		bt = new JButton("�˻��ϱ�");
		
//		table = new JTable(mountainModel = new MountainModel());
		table = new JTable(data,columnName); // �����ڿ� vector�� �Ű������� �ֱ�
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
	// 1) ���͸� �ʱ�ȭ ����. --> �ش� �޼��带 �����ڿ��� ȣ������.
	public void init() {
		// ������ 1�� �־��
		Vector v = new Vector();
		v.add("1");
		v.add("���ǻ�");
		v.add("������");
		v.add("3000");
		
		data.add(v);	// ���;��� ����!
							// �ᱹ ������ �迭 ����� �״�� ������
							// �츮�� ���ߴ� ���� Mountain VO�� �־�� ������,
							// ���͸� �ִ� ���� ������..�������!
		
		// Į�� ���� ä���
		columnName.add("ID");
		columnName.add("���̸�");
		columnName.add("������");
		columnName.add("����");
	}
	
	public static void main(String[] args) {
		new MTMain();
	}
}
