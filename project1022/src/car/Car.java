package car;

public class Car {
	// 최상위 클래스이므로 가장 보편적인 기능과 속성들만을 보유하면 된다.
	// 보편적인 메서드는 뭐가 있을까요?
	String brand;

	public Car() {
		// 매개변수가 없는 디폴트 생성자
		System.out.println("자식의 super() 호출에 의해 난 호출됩니다.");
	}
	public Car(String brand) {// new Car(""); -> 이렇게 호출		
		this.brand=brand;
	}
	public void move(){
		System.out.println("자동차가 갑니다.");
	}
}
