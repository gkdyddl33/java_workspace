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
	JButton bt_create,bt_bg;	
	JPanel p_north,p_center;	
	
	// (*) 배열 -> 크기를 정해야 하므로, 버튼을 몇개를 누를 지 모르니 제한이 걸린다.
	// JButton btn = new JButton[];
	// 그래서 크기에 제한이 없는 것 사용
	ArrayList<JButton> btn = new ArrayList<>();
	Color color;
	
	public ButtonCollection() {
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
		
		// 버튼과 리스너 연결
		bt_create.addActionListener(this);
		bt_bg.addActionListener(this);
		
		setSize(500,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();	// 이벤트를 일으키면 반환! 버튼누르면 행동개시
		if(obj==bt_create) {
			create();
		}else if(obj.equals(bt_bg)) {
			setBg();
		}
	}
	public void create() {
		// 버튼 생성하여, p_center에 부착 = 이벤트를 일으키면 발생하는 것!
//		JButton bt = new JButton("버튼1");
		JButton bt = new CustomButton(); // 자식->부모
		p_center.add(bt);
		
		// 생성된 버튼들을 한 집합(배열)에 넣어놓고 , 배경색을 눌렀을 때 변할 수 있게 한다
		// (*) <배열에 담기는 것> 리스트에 추가하기 push() 메서드와 같다. 
		btn.add(bt);
		System.out.println("현재까지 누적된 리스트의 수는 "+btn.size());
		bt.setText("버튼"+Integer.toString(btn.size())); // 숫자를 문자로 바꿔준다.
		
		// 이대로 했을 때 프레임을 움직여야 생성된 버튼들이 보인다. 프레임을 안건드리고 
		// 버튼이 보이게 하기 위해서는?
		// 우리는 p_center에 버튼을 paint로 그린게 아니라 버튼을 추가한 것이다..컨버스x
		// 따라서 이때는 p_center를 갱신하면 된다. updateUi()이다.
		p_center.updateUI();
	}
	public void setBg() {
		// btn 리스트에 들어있는 모든 요소를 대상으로 배경색 바꾸기
		// arrayList는 순서있는 집합이므로, for문을 사용할 수 있다.
		for (int i = 0; i < btn.size(); i++) {
			JButton bt = btn.get(i);
			bt.setBackground(Color.YELLOW); 
			
		}
		// (*) 색이 바뀐다 -> upgrade : 내가 누르는 버튼의 색을 "green"
		
	}
	
	public static void main(String[] args) {
		new ButtonCollection();
	}

}
