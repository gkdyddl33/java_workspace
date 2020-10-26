package event;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;

public class MultiButtonApp2 extends Frame{
	Button bt1,bt2;
	
	public MultiButtonApp2() {
		bt1 = new Button("버튼1");
		bt2 = new Button("버튼2");
		this.setLayout(new FlowLayout());
		
		this.add(bt1);
		this.add(bt2);
		
		bt1.addActionListener(new MultiActionListener2());
		bt2.addActionListener(new MultiActionListener2());
		
		this.setSize(300,400);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new MultiButtonApp2();
	}
}
