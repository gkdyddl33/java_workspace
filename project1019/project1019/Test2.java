package project1019;

public class Test2 {
	int x = 7;
	public static void main(String[] args) {
		// 클래스가 아직 안태어났는데 값을 어떻게 변경하니?
		// 물체가 태어나야 가능하다.
		Test2 t = new Test2();	// (1) 클래스 객체생성
		t.x = 10; // 누구의.x
		System.out.println("변경된 값은 "+t.x);
	}
}
