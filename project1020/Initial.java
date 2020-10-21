/*초기화블럭*/
class InitialBlock{
	int sum = 0;
	// 인스턴스가 생성될 때, 반복문을 돌려서 총합을 넣어주고 싶다?
	for(){}
	if(){}
	// -> 불가능/ 무조건 함수에 넣어줘야 한다.
	// 왜? 클래스에는 변수,메서드만 정의할 수 있기 때문에!

	// 멤버영역안에 {지역화} 시켰을 때는?
	{
		// 아래코드를 사용하기 위해서는? 객체를 생성할 때 초기화 해주는 것을 사용한다
		// 그것은? "생성자" 이다.
		for(int i=1; i<=10;i++){
			sum += i;
		}
		// 이 클래스의 "인스턴스가 생성될 때" 마다 이 영역을 <호출>하게 됨.
		// '인스턴스 초기화 블럭' 이라 한다.
		System.out.println("인스턴스 초기화 블럭 호출");
	}
	// static 초기화 블럭
	// 프로그램 <main() 메서드에 의해 실행 직전>, 실행되는 초기화 블럭
	static{
		System.out.println("실행하기전 초기화 블럭 실행");	
		System.out.println("B");
	}
	public static void main(String[] args){
		//int a = 3;
		// 그냥 블럭화 시켜놓은 것임. 혹여나 이 안에 변수를 선언하면은
		// 해당 블럭 내에서만 생명력을 갖는다.
		//{
		//	int b=5;
		//}
		//System.out.println(b);
		System.out.println("A");
		new InitialBlock();
		new InitialBlock();
		new InitialBlock();
	}
}
