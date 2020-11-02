package day1029.collection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonCollection extends JFrame implements ActionListener{
	JPanel p_north,p_center;
	JButton bt_create,bt_bg;
	ArrayList<JButton> btn = new ArrayList<>();
	Color color;
	
	public ButtonCollection() {
		// 생성
		p_north = new JPanel();
		p_center = new JPanel();
		bt_create = new JButton("버튼생성");
		bt_bg = new JButton("배경색");
		
		// 조립
		add(p_north,BorderLayout.NORTH);
		p_north.add(bt_create);
		p_north.add(bt_bg);
		add(p_center);
		p_north.setBackground(Color.PINK);
		p_center.setBackground(Color.WHITE);
		
		// 이벤트
		bt_create.addActionListener(this);
		bt_bg.addActionListener(this);
				
		// 윈도우
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
//		JButton bt = new JButton("버튼1");
		JButton bt = new CustomButton(); // 내가 만든 버튼에 이벤트 부여
		
		p_center.add(bt); // 버튼을 몇개 만들지를 모르니 배열로 묶자.
		btn.add(bt);
		//System.out.println(btn.size());
		bt.setText("버튼"+Integer.toString(btn.size()));
		p_center.updateUI();
	}
	public void setBg() {
		for (int i = 0; i < btn.size(); i++) {
			JButton bt = btn.get(i);
			bt.setBackground(Color.YELLOW);
		}
	}
	
	public static void main(String[] args) {
		new ButtonCollection();
	}
}
