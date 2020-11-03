package day1103.thread;

import javax.swing.JProgressBar;

public class BarThread extends Thread{
	int n;
	int interval;
	JProgressBar bar;
	
	public BarThread(JProgressBar bar,int interval) {// ������ bar�� ���
															// ������ �����ϴ� �븸 ��Ǫ�� ���!
		this.bar =bar;
		this.interval = interval;
	}
	@Override
	public void run() {
		while(true) {// ��� ������ �ؾ� �ϹǷ� �ݺ������
			n++;					
			bar.setValue(n);	
			try {
				// 3���� bar�� �ӵ��� ���� �ٸ��Ƿ� �������� = interval
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

