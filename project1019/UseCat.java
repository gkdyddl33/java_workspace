class UseCat{
	public static void main(String[] args){
		Cat c = new Cat();
		System.out.println("고양이 탄생 성공");
		System.out.println("고양이 이름: ",c.name);
		System.out.println("고양이 나이: ",c.age);
		System.out.println("고양이 결혼: ",c.ismarry);
		walk();
		cry();
	}
}
