package string;

public class StringTest2 {
	public static void main(String[] args) {
		/*String�� �Һ�(mutable)�̴�*/
		String s1 = "korea";
		System.out.println(s1);
		s1 = s1+" fighting..";
		System.out.println(s1);
		
		// ���� ������ ���� �ذ�å
		// ���������� ���ڿ� ó�� ��ü
		// StringBuffer, StringBuilder
		StringBuilder sb = new StringBuilder();
		sb.append("");
		for (int i = 0; i < 100; i++) {
			sb.append("�ٳѱ�"+i+"ȸ\n");
		}
		// sb �� string�� �ƴϹǷ� ���� ��� toString() �޼ҵ� ���
		System.out.println(sb.toString());
		
	}
}
