/*
자동차를 정의한다 Car
색상,가격,자동주행모듈 장착 여부
달린다
멈춘다
*/
class Car{
	String color ;
	int price ;
	boolean self;

	public void run(){
		System.out.println("자동차가 달린다.");
	}
	public void stop(){
		System.out.println("자동차가 멈춘다.");
	}
}
