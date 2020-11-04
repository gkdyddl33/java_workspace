package day1103.thread;

import javax.swing.JProgressBar;

public class BarThread extends Thread {
	JProgressBar bar;
	int interval;
	int n;
	
	public BarThread(JProgressBar bar,int interval) {
		// 기존의 선언해 놓은 변수를 사용할 꺼다.. 여기는 거푸집이므로 매개변수로 받아서
		// 사용하자.
		// 각각의bar, 각각의 속도
		this.bar = bar;
		this.interval =interval;
	}
	
	@Override
	public void run() {
		// 계속 증가
		while(true) {
			n++;
			bar.setValue(n);
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
	}
}
