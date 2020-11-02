package day1102.inner;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class TestApp extends JFrame{
	Canvas can;
	JButton bt;
	JTextField t;
	
	public TestApp() {
		can = new Canvas() {
			@Override
			public void paint(Graphics g) {
				g.drawLine(0, 0, 100, 100);
			}
		};
		setLayout(new FlowLayout());
		
		add(can);
		can.setPreferredSize(new Dimension(300,300));
		
		add(bt=new JButton() {
			@Override
			public void paint(Graphics g) {
				g.setColor(Color.RED);
				g.fillRect(0, 0, 100, 40);
			}
		});
		
		final int x = 0;
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(x+"³ª ´­·¶¾î?");
				
			}
		});
		
		add(t=new JTextField(20));
		t.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("Å° ´­·¶¾î?");
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		setSize(400,400);
		setVisible(true);
	}
	public static void main(String[] args) {
		new TestApp();
	}
}
