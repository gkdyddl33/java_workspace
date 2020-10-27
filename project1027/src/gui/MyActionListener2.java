package gui;

import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener2 implements ActionListener{
	Button bt1,bt2;
	TextField t;
	
	public MyActionListener2(Button bt1,Button bt2,TextField t) {
		this.bt1=bt1;
		this.bt2=bt2;
		this.t=t;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// 이벤트를 일으킨 컴포넌트 반환!!
		Object obj = e.getSource();
		if(obj==bt1) {
			System.out.println("bt1을 눌렀네요");
		}else if(obj==bt2) {
			System.out.println("bt2을 눌렀네요");
		}else if(obj==t) {
			System.out.println("t을 눌렀네요");
		}
		
	}

}
