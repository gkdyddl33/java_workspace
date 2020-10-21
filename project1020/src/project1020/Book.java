package project1020;

public class Book {
	
	int price; //A
	int page=300; 
	boolean onSale; //B
	
	public static void main(String[] args) {
//		price=500; //C
		Book b1 = new Book(); //D
		Book b2 = new Book(); //E
		
		b1.page=200; //
		System.out.println(b2.page);//F
		// 값을 선언하지 않아도 기본값을 컴파일러가 채워넣는다!!
		System.out.println(b2.price);
		System.out.println(b2.onSale);
	}

}
