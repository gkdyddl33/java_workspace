package use;
import car.Bus;
import car.Taxi;
import car.Truck;

public class UseCar {
	public static void main(String[] args) {
		// �θ� new���� �ʰ� �ڽĸ� new�� �����ص� �θ�� �ڵ����� �ν��Ͻ��� �����ȴ�.
		Taxi t = new Taxi();
		t.pass();
		t.move();
	}
}
