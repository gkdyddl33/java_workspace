package animal;

public class Duck extends Bird{
	String name = "�� ����";
	
	public void quack() {
		System.out.println("�в�");
	}
	public static void main(String[] args) {
		Duck d1 = new Duck();
		Duck d2 = new Duck();
		
		Bird b = new Bird();
//		b=d1;
//		b=new Duck();
		
		Duck a = new Duck();
		Bird r = new Duck();
		r=a;	
		a=(Duck)r;	// downcasting �� <- ū 
		r=(Bird)a;	// // upcasting 
		
		// ��ü�ڷ����� �ڷ����̴�.
		// �⺻�ڷ����� ��Ģ�� ��� �κ� �״�� ����ǰ� �ִ�.
	}
}
