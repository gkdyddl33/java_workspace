package car;

public class Taxi extends Car{
	// 자식 클래스에는 기본으로 매개변수가 없는 생성자가 자동 삽입되 있다.
	// Car()를 호출하고 있는 것과 같이 super()라는 부모를 만들어 놓고 존재한다.
	public Taxi() {
		// 부모의 매개변수가 있는 생성자를 직접 호출해주기 위해,
		// 개발자가 생성자를 정의하고 있는 것.
		//super("Benz");	// 매개변수 있는 생성자호출!
		super();
	}
	public void pass() {
		System.out.println("승객을 태우고 갑니다..");		
	}
}
