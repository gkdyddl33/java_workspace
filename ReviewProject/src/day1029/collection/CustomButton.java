package day1029.collection;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CustomButton extends JButton implements ActionListener{

	public CustomButton() {
		this.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// 버튼을 누르면 색깔변경
		this.setBackground(Color.GREEN);
		
	}
}
