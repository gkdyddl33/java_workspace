package day1029.poly;

/*
 *	�������� ���� �ٽ� �ѹ� �����غ���! 
 */

public class Bird {
	String name = "����";
	String local = "�ѱ�";
	
	private void fly() {
		System.out.println("���� ���ƿ�");
	}
	public static void main(String[] args) {
		// ������ ������� ������ �����ϱ�
		Bird b1 = new Bird();
		
		// Bird �� Ŭ��������,�޼��� ���� o
		// Duck�� �ڽĸ޼��嵵 ���� o
		Bird b2 = new Duck();
		Bird b3 = new Sparrow();
		
		// b3.fly(); // ���� �ൿ�� �پ��ϳ�~
		
		// �� �����
		// �ڷ����� �θ� �׻� �������� ����������
		// �� ���������Բ� �ڽ��� ���׷��̵� �Ǹ�
		// �ڽ��� �޼��带 ����ų���� �ִٴ� ��!!
		// ������ �ڱ� �ڽŸ� ������ �� �ִ�!!!
		// ������ = �ڽĿ� �θ� ������ ���� ��
	}
}
