package day1103.thread;

import javax.swing.JProgressBar;

public class BarThread extends Thread{
	int n;
	int interval;
	JProgressBar bar;
	
	public BarThread(JProgressBar bar,int interval) {// 기존꺼 bar를 사용
															// 쓰레드 증가하는 대만 거푸집 사용!
		this.bar =bar;
		this.interval = interval;
	}
	@Override
	public void run() {
		while(true) {// 계속 증가를 해야 하므로 반복문사용
			n++;					
			bar.setValue(n);	
			try {
				// 3개의 bar에 속도는 각각 다르므로 변수선언 = interval
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

