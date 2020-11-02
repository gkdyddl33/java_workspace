package review.animal;

public class Field {

	public static void main(String[] args) {
		
			Animal[] animals = {new Dog(), new Rabbit(), new Cat()};
			
			Dog 바둑이 = new Dog("바둑이",7,"고기",1);
			
			Cat 나비 = new Cat();
			나비.sleep();
			System.out.println("현재 체력:"+나비.hp);
			
			Rabbit 토순이 = new Rabbit("토순이", 1, "토기풀", 2);
			토순이.walk();
			System.out.println("현재 체력 : "+토순이.hp);	
			System.out.println("현재 " + 토순이.feed + "개수 : " + 토순이.feedCnt );
		
			if(바둑이.eat()) {
				System.out.println("냠냠 맛있어 "+바둑이.feed+" 좋아.");
				System.out.println("현재 체력 : " + 바둑이.hp);
			}
			else {
				System.out.println(바둑이.feed + "가 부족합니다.");
			}
		}		
	
}
