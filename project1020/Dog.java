class Dog{
	String name="����";
	int age=5;
	static String color = "white";	// �� ������, Ŭ�����κ��� ������ �ν��Ͻ���
												// ���� �ö��� ����, Ŭ���� ������ �� �޶�پ� �־��..
												// ��, �ν��Ͻ� �Ҽ��� �ƴ϶�, Ŭ���� ���� �Ҽ����� ����!

	public void bark(){
		System.out.println("����");
	}

	public static void main(String[] args){
		// Dog.color = "black";
		color = "yellow";	// static�� �����̸鼭 ��ü �����̹Ƿ� ���ٰ���!
									// static�� ���۷��� ������ ��밡��!
		Dog d1 = new Dog();
		Dog d2 = new Dog();
		int a= 8;

		boolean k = true;
	}
}
