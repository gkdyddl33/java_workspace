/*�޼���*/
class ReturnType{
	int price;

	/*�޼��� �ۼ���*/
	public void setPrice1(){// void = ��ȯ���� ����.��, return�� ����
		price = 500;
	}
	/*��ȯ����, �ش� ��ȯ���� �ڷ����� �״�� �������ָ� �ȴ�.*/
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
