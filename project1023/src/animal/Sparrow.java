package animal;

public class Sparrow {
	String name = "�� ����";
	
	public void jjack() {
		System.out.println("±±");
	}
	// �ڽ�Ŭ�������� �θ�� 100% ������ �޼ҵ带 �����ϴ� �����
	// �������̵�(Overriding) �̶�� �Ѵ�.
	// ����ϴ� ������?
	// �θ��� �޼��带 �ڽĿ��� ����� ����,�߰� �ϴ� ���� ���׷��̵� �ϰ� ���� ��
	public void fly() {
		System.out.println("������ ���ƿ�");		
	}
	public static void main(String[] args) {
		Sparrow sp = new Sparrow();
		sp.fly();   // ������ �޼��� ȣ��
		
		Bird bird = new Bird();
		bird.fly();  // ���� �޼��� ȣ��
		
//		Bird bird2 = new Sparrow();
//		bird2.fly();
	}
	/*
	 *	�����ε��� ���� Ŭ���� ������..
	 *	�������̵��� ��Ӱ��迡��..
	 *	�ϴ� ����̴�. 
	 */
}
