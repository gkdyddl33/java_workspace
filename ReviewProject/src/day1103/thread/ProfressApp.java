package day1103.thread;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class ProfressApp extends JFrame{
	JLabel la;
	JProgressBar bar1,bar2,bar3;
	BarThread t1,t2,t3;
	LaThread lat;
	
	public ProfressApp() {
		// 생성
		la = new JLabel("0",SwingConstants.CENTER);
		bar1 = new JProgressBar();
		bar2 = new JProgressBar();
		bar3 = new JProgressBar();
		
		// 조립
		setLayout(new FlowLayout());
		add(la);
		add(bar1);
		add(bar2);
		add(bar3);
		
		la.setPreferredSize(new Dimension(500,150));
		la.setFont(new Font("Verdana", Font.BOLD|Font.ITALIC, 45));
		bar1.setPreferredSize(new Dimension(500,70));
		bar2.setPreferredSize(new Dimension(500,70));
		bar3.setPreferredSize(new Dimension(500,70));
		
		// 쓰레드
		t1 = new BarThread(bar1,500);
		t2 = new BarThread(bar2,250);
		t3 = new BarThread(bar3,10);
		lat = new LaThread(bar1,500);
		
		t1.start();
		t2.start();
		t3.start();
		lat.start();
		
		// 윈도우
		setSize(600,500);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	public static void main(String[] args) {
		new ProfressApp();
	}
}
