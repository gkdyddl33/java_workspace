package music;

import riding.Wing;

// 서로간 형변환이 가능하다★★★★★
public class Speaker extends AudioDevice implements Wing{
	/*
	 *	상속이란?
	 *	부모의 모든 재산을 자식이 물려받는 제도
	 *	부모클래스가 불완전한 추상클래스 인 경우, 이건 재산이 아니라 빚더미
	 *	즉 부모의 추상적인 메서드의 완성을 자식에게 떠넘긴 것이다.
	 *	그래서 불완전한 메서드를 완전히 재정의 해야 한다 = 오버라이딩 
	 */
	boolean ooper;	// 우퍼지원 여부
	String color = "red";
	
	public void sound() {
	}
	// pm의 부탁을 실수로 까먹음..따라서 기능 누락된 상태이다.
	public void setVolume() {
		// 브레이스가 존재하기만 하면 된다.
		System.out.println("볼륨을 조절해요");
	}
	public void playMP3() {
		System.out.println("MP3 파일 실행");		
	}
	public void fly() {
		System.out.println("하늘을 날아요");
	}
}
