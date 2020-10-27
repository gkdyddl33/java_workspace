package day1027.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class MyActionListener2 implements ActionListener{
	JButton bt;	
	JTextArea area;
	
	public MyActionListener2(JButton bt) {// 생성자 함수에서 매개변수를 받자.
		this.bt = bt;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println("나 눌렀어?");
		Object obj = e.getSource(); // 이벤트를 일으킨 컴포넌트 반환
		if(obj==bt) {
			String msg = bt.getText();
			area.append(msg+"\n");
			bt.setText("");
		}
	}
}
