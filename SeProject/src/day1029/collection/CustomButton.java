package day1029.collection;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CustomButton extends JButton implements ActionListener{
	public CustomButton() {
		// 나(버튼)와 리스너(나)와 연결
		this.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.setBackground(Color.GREEN);		
	}
}
