package string;

public class StringTest {
	/*
	 *	���!!
	 *	String�� Ŭ�����̴�.
	 *	������ �츮 �ΰ��� ��Ʈ���� �ʹ� �е������� ���� ����ϱ� ������
	 *	new �����ڿ� ���� ��Ʈ���� �����ϴ� ���� �ʹ� �����ϱ� ������
	 *	String�� ���ؼ��� ��ġ �Ϲ� ������Ÿ��ó�� new ���� �ʴ� ǥ������
	 *	�������ش�. �̷��� String �� ��������� �Ͻ���,������(explicit,implicit) ������
	 *	������ �������� ���� String�� ���Ǯ�� ���� �����Ǿ� ���µ�,
	 *	�Ʒ� �ڵ尡 ���Ǯ�� ����� �� �ַ� �̰����� ���� ����Ѵ�. 
	 */
	public static void main(String[] args) {
		/*�Ʒ� �ڵ��� ������ �����غ���*/
		String s1 = "apple";	// s1,s2�� ���۷��� ���� - �ּҺ�
		String s2 = "apple";
		System.out.println(s1==s2);
		
		// String �� ���۹����� 'S'�� �빮�ڷ� ���� �и� ��ü���̴�!
		// �츮�� ����ȯ�濡�� String.class�� ��򰡿� �־�� �ϴµ� ������ �ʴ´�.
		// java se�� ���ִ� ��� api�� ���ִ� ����� ����..rt.jar
		
		String s3 = new String("korea");	// �������� �޸� �ö� - ������ �ּҰ��� �ο�
		String s4 = new String("korea");
		System.out.println(s3==s4);
		// ���� ���� new�����ڿ� ���� ��Ʈ���������� �����(explicit)�������̸�,
		// ����� �������� ���Ǯ�� �������� �����Ƿ�, new �� ������ �ߺ����θ�
		// ������ �ʰ� ������ �����ȴ�.
		
		// �ּҺ񱳰� �ƴ�, ��ü ���ϼ� ���ϴ� ��
		String k1 = "orange";
		String k2 = "orange";
		System.out.println(k1==k2); // �ּ�
		System.out.println(k1.equals(k2)); // ����
	}
}
