package day1103.thread;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

/*진행상황을 직관적으로 알 수 있는 프로그래스바를 활용해보자*/
public class ProgressApp extends JFrame{
	JLabel la;
	JProgressBar bar1,bar2,bar3;
	
	// (2) bar 를 증가시킬 쓰레드
	BarThread t1,t2,t3;
	
	public ProgressApp() {
		la = new JLabel("0",SwingConstants.CENTER);// 가운데정렬
		bar1 = new JProgressBar();
		bar2 = new JProgressBar();
		bar3 = new JProgressBar();
		
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
		
		// (*) 쓰레드 3개 생성하여 runnable로 진입시키자!!
		t1 = new BarThread(bar1, 500);
		t2 = new BarThread(bar2, 250);
		t3 = new BarThread(bar3, 10);
		
		t1.start();
		t2.start();
		t3.start();
		
		setSize(600,500);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		new ProgressApp();
	}
}
