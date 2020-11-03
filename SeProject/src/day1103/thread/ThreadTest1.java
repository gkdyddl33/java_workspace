package day1103.thread;

/*
 *	쓰레드란?
 *	- 하나의 프로세스 내에서 비동기적으로 동작할 수 있는 또 하나의 세부 실행 단위를 말한다.
 *	
 *	쓰레드의 생명주기(LifeCycle - 언제죽지?언제까지 살아있지?)
 * 
 */

public class ThreadTest1 {
	// 실행부
	public static void main(String[] args) {
		//(2)시간쓰레드 생성하고, 동작시켜보자.
		TimeThread tt = new TimeThread();
		tt.start();	// runnable 상태로 진입
		
		// 0.5초마다 별을 출력하는 쓰레드를 구현하되, 현재클래스내에서 구현하자
		// (내부익명클래스)
		Thread startThread = new Thread() {
			@Override
			public void run() {
				while(true) {
					System.out.println("★");
					try {
						// 0.5초
						Thread.sleep(500);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
					
				}
			}
		};
		startThread.start();
		// 개발자가 정의한 쓰레드를 이용해서 무한루프 실행!!
		MyThread t1 = new MyThread();	// 분신생성
		
		// 쓰레드가 보유한 run() 메서드는 jvm에 의해 호출된다.
		// run()으로 재정의를 해야 사용가능!
		// 쓰레드의 수행은 시스템에 맡겨야 한다.. = 수행준비를 하게 해주는 것
		// 메인 실행부는 쓰레드가 실행이 되든 말든 자기 갈길 간다 = 비동기방식
		//t1.start();
		
//		while(true) {
//			System.out.println("hi");
//			
//		}
	}
}
