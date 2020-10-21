/*메서드*/
class ReturnType{
	int price;

	/*메서드 작성법*/
	public void setPrice1(){// void = 반환값이 없음.즉, return이 없음
		price = 500;
	}
	/*반환값은, 해당 반환값의 자료형을 그대로 지정해주면 된다.*/
	public int getPrice2(){
		return price;
	}
	public boolean getBool(){
		return false;
	}
	public char getChar(){
		return 'A';
	}
	public double getNum(){
		return 89.756;
	}
	public static void main(String[] args){
		RetrunType rt = new RetrunType();
		
		System.out.println(rt.getNum());
	}
}
