package day1103.thread;

public class ThreadTaskMain {
	public static void main(String[] args) {

		ThreadTask th1 = new ThreadTask("멍멍");
		ThreadTask th2 = new ThreadTask("야옹");
		ThreadTask th3 = new ThreadTask("음매");


		// Start를 사용하기 위해서 -> Thread(이 클래스안에 존재)로 전달
		// Thread2 에 runnable가 있기 때문에 -> 업캐스팅
		Thread t1 = new Thread(th1);
		Thread t2 = new Thread(th2);
		Thread t3 = new Thread(th3);

		t1.start();
		t2.start();
		t3.start();

		// Thread의 순서(join)를 정해주면 해당 문자가 마지막에 출력.
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			;
		}
		System.out.println("다 울음");
	}
}