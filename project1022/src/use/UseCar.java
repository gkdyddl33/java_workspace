package use;
import car.Bus;
import car.Taxi;
import car.Truck;

public class UseCar {
	public static void main(String[] args) {
		// 부모를 new하지 않고 자식만 new로 생성해도 부모는 자동으로 인스턴스가 생성된다.
		Taxi t = new Taxi();
		t.pass();
		t.move();
	}
}
