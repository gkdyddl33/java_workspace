// 객체지향 언이이기 때문에, 모든~언어는 클래스안에 작성을 해야 한다.
class Plane{
	String name="보잉";
	static int price=9000;
	String color="white";

	public void setPrice(){
		price =10;
	}

	public static void main(String[] args){
		Plane p1 = new Plane();
		Plane p2 = new Plane();

		p1.price=3000;
		System.out.println(p2.price);
		price=5000;
		System.out.println(p1.price);
	}
}

