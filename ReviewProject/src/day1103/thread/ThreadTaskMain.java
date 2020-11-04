package day1103.thread;

public class ThreadTaskMain {
	public static void main(String[] args) {
		ThreadTask th1 = new ThreadTask("멍멍");
		ThreadTask th2 = new ThreadTask("야옹");
		ThreadTask th3 = new ThreadTask("음매");
		
		Thread t1 = new Thread(th1);
		Thread t2 = new Thread(th2);
		Thread t3 = new Thread(th3);
		
		t1.start();
		t2.start();
		t3.start();
		
		// 순서 정해주기
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("다 울음");
	}
}
