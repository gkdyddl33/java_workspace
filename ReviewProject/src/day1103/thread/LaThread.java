package day1103.thread;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class LaThread extends Thread {	
	int interval;	
	int n;
	JProgressBar bar1;
	
	public LaThread(JProgressBar bar1,int interval) {
		// 기존의 선언해 놓은 변수를 사용할 꺼다.. 여기는 거푸집이므로 매개변수로 받아서
		// 사용하자.
		// 각각의bar, 각각의 속도		
		this.bar1 =bar1;
	}
	
	@Override
	public void run() {
		// 계속 증가
		while(true) {
			n++;
			bar1.setValue(n);
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
	}
}
