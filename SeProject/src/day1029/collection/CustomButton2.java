package day1029.collection;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CustomButton2 extends JButton implements ActionListener{
	public CustomButton2() {
		this.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.setBackground(Color.GREEN);
		
	}
}
