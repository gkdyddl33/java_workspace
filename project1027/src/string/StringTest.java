package string;

public class StringTest {
	/*
	 *	결론!!
	 *	String은 클래스이다.
	 *	하지만 우리 인간은 스트링은 너무 압도적으로 많이 사용하기 때문에
	 *	new 연산자에 의해 스트링을 생성하는 것은 너무 불편하기 때문에
	 *	String에 한해서는 마치 일반 데이터타입처럼 new 쓰지 않는 표현식을
	 *	지원해준다. 이러한 String 의 생성방법을 암시적,묵시적(explicit,implicit) 생성법
	 *	묵시적 생성법에 의한 String은 상수풀에 의해 관리되어 지는데,
	 *	아래 코드가 상수풀을 사용한 것 주로 이것으로 많이 사용한다. 
	 */
	public static void main(String[] args) {
		/*아래 코드의 수행결과 예측해보기*/
		String s1 = "apple";	// s1,s2는 레퍼런스 변수 - 주소비교
		String s2 = "apple";
		System.out.println(s1==s2);
		
		// String 은 시작문자인 'S'가 대문자로 보아 분명 객체형이다!
		// 우리의 개발환경에서 String.class가 어딘가에 있어야 하는데 보이지 않는다.
		// java se가 모여있는 모든 api가 모여있는 압축된 파일..rt.jar
		
		String s3 = new String("korea");	// 힙영역에 메모리 올라감 - 각자의 주소값이 부여
		String s4 = new String("korea");
		System.out.println(s3==s4);
		// 위와 같이 new연산자에 의해 스트링생성법을 명시적(explicit)생성법이며,
		// 명시적 생성법은 상수풀에 생성되지 않으므로, new 할 때마다 중복여부를
		// 따지지 않고 무조건 생성된다.
		
		// 주소비교가 아닌, 객체 동일성 비교하는 법
		String k1 = "orange";
		String k2 = "orange";
		System.out.println(k1==k2); // 주소
		System.out.println(k1.equals(k2)); // 내용
	}
}
