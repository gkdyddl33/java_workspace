/*
고양이를 정의하시오.
클래스명 : Cat
이름 : 묘
나이 : 3
결혼여부 : 미혼
걷는 동작
우는 동작
*/
class Cat{
	String name = "묘";
	int age = 3;
	boolean ismarry = false;

	public void walk(){
		System.out.println("터벅 터벅..");
	}
	public void cry(){
		System.out.println("낑낑 끼이잉..");
	}
}
