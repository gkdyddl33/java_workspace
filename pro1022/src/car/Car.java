package car;

public class Car {
	// �ֻ��� Ŭ�����̹Ƿ� ���� �������� ��ɰ� �Ӽ��鸸�� �����ϸ� �ȴ�.
	// �������� �޼���� ���� �������?
	String brand;

	public Car() {
		// �Ű������� ���� ����Ʈ ������
		System.out.println("�ڽ��� super() ȣ�⿡ ���� �� ȣ��˴ϴ�.");
	}
	public Car(String brand) {// new Car(""); -> �̷��� ȣ��		
		this.brand=brand;
	}
	public void move(){
		System.out.println("�ڵ����� ���ϴ�.");
	}
}
