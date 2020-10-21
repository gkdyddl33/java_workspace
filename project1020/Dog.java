class Dog{
	String name="도그";
	int age=5;
	static String color = "white";	// 이 변수는, 클래스로부터 생성된 인스턴스에
												// 딸려 올라가지 말고, 클래스 원보에 딱 달라붙어 있어라..
												// 즉, 인스턴스 소속이 아니라, 클래스 원본 소속으로 선언!

	public void bark(){
		System.out.println("웍웍");
	}

	public static void main(String[] args){
		// Dog.color = "black";
		color = "yellow";	// static은 고정이면서 전체 공유이므로 접근가능!
									// static은 레퍼런스 변수로 사용가능!
		Dog d1 = new Dog();
		Dog d2 = new Dog();
		int a= 8;

		boolean k = true;
	}
}
