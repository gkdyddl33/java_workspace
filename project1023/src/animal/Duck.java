package animal;

public class Duck extends Bird{
	String name = "난 오리";
	
	public void quack() {
		System.out.println("꽥꽥");
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
		a=(Duck)r;	// downcasting 작 <- 큰 
		r=(Bird)a;	// // upcasting 
		
		// 객체자료형도 자료형이다.
		// 기본자료형의 원칙이 상당 부분 그대로 적용되고 있다.
	}
}
