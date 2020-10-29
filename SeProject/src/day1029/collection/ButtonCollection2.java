package day1029.collection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonCollection2 extends JFrame implements ActionListener{
	JPanel p_north,p_center;
	JButton bt_create, bt_bg;
	
	ArrayList<JButton> btn = new ArrayList<>();
	
	public ButtonCollection2() {
		p_north = new JPanel();
		p_center = new JPanel();
		bt_create = new JButton("��ư����");
		bt_bg = new JButton("����");
		
		add(p_north,BorderLayout.NORTH);
		p_north.add(bt_create);
		p_north.add(bt_bg);
		add(p_center);
		p_north.setBackground(Color.PINK);
		p_center.setBackground(Color.WHITE);
		
		bt_create.addActionListener(this);
		bt_bg.addActionListener(this);
		
		setSize(500,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==bt_create) {
			create();
		}else if(obj==bt_bg) {
			setBg();
		}		
	}
	public void create() {
		// ��ư �����Ͽ�, p_center�� ���� = �̺�Ʈ�� ����Ű�� �߻��ϴ� ��!
		//JButton bt = new JButton("��ư1");
		JButton bt = new CustomButton2();
		p_center.add(bt);		
		p_center.updateUI();
		
		// ������ ��ư���� ������ �Ѳ����� �ٳ����� ���ս��Ѿ� �� = �迭
		// �迭 : ������ ũ�⸦ ���.. ũ�⸦ ���� �� ���⿡ arrayList�� �̿�!
		btn.add(bt);
		System.out.println("������� ������ ����Ʈ�� ���� "+btn.size());
		bt.setText("��ư"+Integer.toString(btn.size()));
	}
	public void setBg() {
		for (int i = 0; i < btn.size(); i++) {
			JButton bt = btn.get(i); // �����Ǿ��ִ� ��ư�� ������..
			bt.setBackground(Color.YELLOW);
		}

	}
	public static void main(String[] args) {
		new ButtonCollection2();
	}
}
