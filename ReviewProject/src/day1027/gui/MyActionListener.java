package day1027.gui;

import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener implements ActionListener{
	Button bt1;
	Button bt2;
	TextField t;
	
	public MyActionListener(Button bt1,Button bt2,TextField t) {
		this.bt1=bt1;
		this.bt2=bt2;
		this.t=t;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==bt1) {
			System.out.println("bt1À» ´­·¶³×¿ä.");
		}else if(obj==bt2) {
			System.out.println("bt2À» ´­·¶³×¿ä.");
		}else if(obj==t) {
			System.out.println("tÀ» ´­·¶³×¿ä.");
		}
		
	}

}
