package project1020;

public class Car {	
	// 아래의 변수는 클래스로부터 인스턴스가 생성될 떄, 해당 인스턴스에 포함되어지는
	// 인스턴스 변수로서, 생명력의 해당 인스턴스가 소멸될 때까지 함께 한다.
	
	// 자동차의 부품이므로, 자동차와 생명을 같이 한다.
	// 이러한 멤버변수는 초기화 하지 않으면 컴파일러가 최소한의 관여로 값을 자동초기화 한다.
	String color;
	int price;
	boolean self;

	public void run(){
		System.out.println("자동차가 달린다.");
	}
	public void stop(){
		System.out.println("자동차가 멈춘다.");
	}
	public static void main(String[] args) {
		/*
		 * Car클래스를 선언하면서, 해당 클래스가 보유한 변수인 멤버변수에 아무런
		 * 값도 초기화 시키지 않았다면, 어떤 결과가 나올까?
		 * 일반적인 프로그래밍 언어에서는 변수에 값이 없는 상태로 다른 데이터와의
		 * 연산을 수행하게 되면 에러가 발생한다.
		 */
		// 지역변수는 초기화를 해야 사용가능하다!
		
		// 자동차의 인스턴스를 한개 생성한 후, 그 인스턴스 안의 멤버변수들이 갖는 값을 출력
		Car c = new Car();
		System.out.println("color: "+c.color);
		System.out.println("price: "+c.price);
		System.out.println("self: "+c.self);
		
	}
}
