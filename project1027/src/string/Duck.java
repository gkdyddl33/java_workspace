package string;
/*
 *	�ڹ��� ���~��ü�� ���� ���� �� ���� �ֻ��� ��ü�� �ΰ� �ִ�.
 *	Object ��ü�̴�. �����ڰ� ����� ������� �ʴ��� ����Ʈ�� �̹� ��ӵǾ� �ִ�. 
 */
public class Duck {
	String name = "����";
	
	/* �Ʒ��� �޼���� Object Ŭ�����κ��� ��ӹ��� �޼����̸�,
	 * �� �޼���� ��ü�� ��Ʈ������ ��ȯ�ϰ��� �� �� �����Ѵ�. 
	 * ��, �Ʒ��� �޼����, ��ü�� ����ϰ��� �� �� � �ڵ����� �����Ѵ�.
	 * �Ʒ��� �޼���� Object�� �޼�������, �׽�Ʈ�� �����ϴ� ������ �˱�����
	 * ��� �������̵� �غô�. */
	
	public String toString() {// �������̵�
		System.out.println("toString() �޼��� �����մϴ�.");
		return "";
	}
	
	public static void main(String[] args) {
		Duck d = new Duck();
		
		// System.out.println(new Duck()); // �ּҰ� ���
		System.out.println(d);
	}
}
