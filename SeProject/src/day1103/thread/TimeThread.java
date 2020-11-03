package day1103.thread;

import java.util.Calendar;

public class TimeThread extends Thread{

	@Override
	public void run() {
		while(true) {
			// 쓰레드를 계속 사용하기 위해 반복문을 사용
			// 현재 시간을 구해서 1초마다 갱신해서 출력!
			// 추상클래스이므로 자체 메서드로 인스턴스를 얻자.
			Calendar cal= Calendar.getInstance();
			
			// 년,월,일,시,분,초 조사해보기
			System.out.println(cal.get(Calendar.YEAR)+"년"
			+(cal.get(Calendar.MONTH)+1)+"월"+cal.get(Calendar.DATE)
			+cal.get(Calendar.HOUR)+"시"+cal.get(Calendar.MINUTE)+"분"
			+cal.get(Calendar.SECOND));

			
//			System.out.println(cal);
			try {
				// 1000분의 1초 -> 사라졌다가 다시 복귀(runnable상태->복귀)
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
