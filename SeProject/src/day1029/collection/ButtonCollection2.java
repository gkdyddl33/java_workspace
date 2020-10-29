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
		bt_create = new JButton("버튼생성");
		bt_bg = new JButton("배경색");
		
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
		// 버튼 생성하여, p_center에 부착 = 이벤트를 일으키면 발생하는 것!
		//JButton bt = new JButton("버튼1");
		JButton bt = new CustomButton2();
		p_center.add(bt);		
		p_center.updateUI();
		
		// 생성된 버튼들이 배경색이 한꺼번에 바낄려면 집합시켜야 함 = 배열
		// 배열 : 고정적 크기를 명시.. 크기를 정할 수 없기에 arrayList를 이용!
		btn.add(bt);
		System.out.println("현재까지 누적된 리스트의 수는 "+btn.size());
		bt.setText("버튼"+Integer.toString(btn.size()));
	}
	public void setBg() {
		for (int i = 0; i < btn.size(); i++) {
			JButton bt = btn.get(i); // 누적되어있는 버튼을 가져와..
			bt.setBackground(Color.YELLOW);
		}

	}
	public static void main(String[] args) {
		new ButtonCollection2();
	}
}
