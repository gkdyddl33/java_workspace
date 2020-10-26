package event;

import java.awt.Button;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;

public class MyWin2 extends Frame{
	Button bt;
	TextField t;
	Choice ch;	// select 박스 옵션!
	
	public MyWin2() {
		bt = new Button("나 눌러봐");
		t = new TextField(15);
		ch = new Choice();
		
		this.setLayout(new FlowLayout());
		this.add(bt);
		this.add(t);
		
		// (3) choice
		ch.add("Java Programming");
		ch.add("Jsp Programming");
		ch.add("Android Programming");
		ch.add("Spring Programming");
		ch.add("Mybatis Programming");
		ch.addItemListener(new MyItem2());
		
		// (1) 버튼을 누르면?
		bt.addActionListener(new MyListener2());
		
		// (2) 텍스트박스와 리스너 연결
		t.addKeyListener(new MyKey2());
		
		// (4) 마우스
		this.addMouseListener(new MyMouse2());	// 몸체에 
		
		// (5) windowListener
		addWindowListener(new MyWindowListener2());
		
		this.setSize(300,400);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new MyWin2();
	}
}
