package review.animal;

public class Field {

	public static void main(String[] args) {
		
			Animal[] animals = {new Dog(), new Rabbit(), new Cat()};
			
			Dog �ٵ��� = new Dog("�ٵ���",7,"���",1);
			
			Cat ���� = new Cat();
			����.sleep();
			System.out.println("���� ü��:"+����.hp);
			
			Rabbit ����� = new Rabbit("�����", 1, "���Ǯ", 2);
			�����.walk();
			System.out.println("���� ü�� : "+�����.hp);	
			System.out.println("���� " + �����.feed + "���� : " + �����.feedCnt );
		
			if(�ٵ���.eat()) {
				System.out.println("�ȳ� ���־� "+�ٵ���.feed+" ����.");
				System.out.println("���� ü�� : " + �ٵ���.hp);
			}
			else {
				System.out.println(�ٵ���.feed + "�� �����մϴ�.");
			}
		}		
	
}
