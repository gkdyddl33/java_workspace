package event;

import java.awt.Frame;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Choice;
import java.awt.FlowLayout;

public class MyWin extends Frame{
	Button bt;
	TextField t;	// event추가
	Choice ch;		// html에서의 select 박스와 동일 , onchange
	
	public MyWin() {
		bt= new Button("나 눌러봐");
		t =  new TextField(15);
		ch = new Choice();
		
		this.setLayout(new FlowLayout());
		// 버튼을 윈도우에 부착 - 프레임은 디폴트가 BorderLayout
		this.add(bt);
		this.add(t);  
		this.add(ch);
		
		// (4) ch의 아이템 채우기
		ch.add("Java Programming");
		ch.add("Jsp Programming");
		ch.add("Android Programming");
		ch.add("Spring Programming");
		ch.add("Mybatis Programming");
		
		ch.addItemListener(new MyItem()); // 초이스와 리스너와 연결
		
		// (5) windowListener
		addWindowListener(new MyWindowListner()); // 프레임과 리스너의 연결
		
		// (1)버튼과 리스너와 연결 ex. bt.addEventListener()
		bt.addActionListener(new MyListner()); // event추가
		
		// (2) 텍스트박스와 리스너 연결
		t.addKeyListener(new MyKey());
		
		// (3) 마우스
		// frame에 마우스 리스너 연결(몸체)
		this.addMouseListener(new MyMouse());
		
		this.setSize(300,400);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new MyWin();
	}
}
