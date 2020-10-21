class DataType1
{
	/*
		1. 변수/자료형(메모리에 데이터 올리는 방법:일거리제공법)
		2. 연산자(x)
		3. 제어문(조건,반복)(x)
		4. 함수(x)
		5. 배열(x)
		6. 객체
	*/

	/*
		프로그래밍 언어에서 사용되는 데이터의 종류 3가지
		1) 숫자
			- 정수 :			byte < short < int < long
			- 단위(byte)	   1		  2			4		 8
			- 실수 : 소수점을 포함한 수 float < double
			- 단위(byte) :						4			8
		2) 문자 : char(한 단어), 
		3) 논리값 : true/false
	*/

	public static void main(String[] args){
		byte x = 127;	// 1byte 차지 -> 데이터의 자리수는? 2의 8승까지..265가지
		// 정수형은 그냥 큰 이유가 없다면 int형으로 선언한다.
		// 왜? 현재까지의 시스템이 int형에 최적화되어 있으므로..
		System.out.println("x값은 "+x);
		
		// 실수
		// 우리가 다루고 있는 자바의 기본 자료형들엔, 디폴트 자료형이 적용된다.
		// 정수의 디폴트 자료형은 int이고, 실수의 디폴트 자료형은 double이다.
		float b = 3.689f;
		System.out.println("b값은 "+b);

		// 문자
		char j = 'A';		// 1byte
								// 아스키코드
								// 전 세계 모든 문자를 다 표현할 수 있다. = 유니코드

		// 논리값
		boolean e = true;	// 1byte 
									// c,javascript 등에서는 true 값을 1로 대체할 수 있으나,
									// 자바에서는 1이 true를 대신할 수 없음.
	}

}
