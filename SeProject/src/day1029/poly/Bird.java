package day1029.poly;

/*
 *	다형성에 대해 다시 한번 공부해보자! 
 */

public class Bird {
	String name = "난새";
	String local = "한국";
	
	private void fly() {
		System.out.println("새가 날아요");
	}
	public static void main(String[] args) {
		// 새들을 대상으로 다형성 공부하기
		Bird b1 = new Bird();
		
		// Bird 의 클래스변수,메서드 접근 o
		// Duck의 자식메서드도 접근 o
		Bird b2 = new Duck();
		Bird b3 = new Sparrow();
		
		// b3.fly(); // 새의 행동이 다양하네~
		
		// 즉 결론은
		// 자료형인 부모를 항상 따라가지만 예외적으로
		// 언어를 유연해지게끔 자식이 업그레이드 되면
		// 자식의 메서드를 가리킬수도 있다는 것!!
		// 원래는 자기 자신만 가져올 수 있다!!!
		// 다형성 = 자식에 부모를 재정의 했을 때
	}
}
