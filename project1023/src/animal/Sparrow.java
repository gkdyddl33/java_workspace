package animal;

public class Sparrow {
	String name = "난 참새";
	
	public void jjack() {
		System.out.println("짹짹");
	}
	// 자식클래스에서 부모와 100% 동일한 메소드를 정의하는 기법을
	// 오버라이딩(Overriding) 이라고 한다.
	// 사용하는 이유는?
	// 부모의 메서드를 자식에서 기능을 변경,추가 하는 등의 업그레이드 하고 싶을 떄
	public void fly() {
		System.out.println("참새가 날아요");		
	}
	public static void main(String[] args) {
		Sparrow sp = new Sparrow();
		sp.fly();   // 참새의 메서드 호출
		
		Bird bird = new Bird();
		bird.fly();  // 새의 메서드 호출
		
//		Bird bird2 = new Sparrow();
//		bird2.fly();
	}
	/*
	 *	오버로딩은 같은 클래스 내에서..
	 *	오버라이딩은 상속관계에서..
	 *	하는 기법이다. 
	 */
}
