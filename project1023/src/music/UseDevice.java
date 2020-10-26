package music;

public class UseDevice {
	public static void main(String[] args) {
		// 추상클래스는 불완전한 클래스이므로 인스턴스 생성 불가
		// 추상클래스의 메소드는 꼭 재정의를 해야 하므로 익명클래스가 바디에 구현이 된다.
		// AudioDevice ad = new Speaker();
		// 부모에 추상클래스 있기 때문에 익명클래스가 구현되지만
		// 자식을 인스턴스화 시키면 가능하다.
		riding.Wing wing = new Speaker();
		wing.fly();		// 자식꺼 호출 업그레이드를 구현 -> 오바라이딩
		// extends 건 implements 건 둘다 is a 다
		// 따라서 서로간 같은 종류의 자료형으로 간주되며, 형변환도 지원
		// 자식이 오버라이딩을 하면 자식의 메서드를 최우선 호출!
	}
}
