class DataType3{
	public static void main(String[] args){
		short s1=3; //(가)
		short s2=4; //(나)
		
		int a1 = 3; //(다)
		int a2 = 4; //(라)
		
		// 정수의 연산수행에서 만일, 연산의 대상이 되는 데이터가 int형보다
		// 작은 자료형일 경우(byte, short) 연산 속도를 높이기 위해, 연산에
		// 최적화된 자료형인 int 형으로 자동 형변환을 일으킨다.
		// 형변환을 일으킨다.

		short sum = s1+s2; //(마)
			// 에러발생
			// 해결책1? int sum s1+s2
			// 해결책2? short sum = (short)(s1+s2)
			// (short) 에서의 소괄화를 cast 연산자라 한다.
		int result = a1+a2; //(바)	

		long k1 =5;
		int k2 = 9;
		// 하지만, long일 경우 8byte이기 때문에 4byte로 줄이면 데이터손실이
		// 발생하므로 long으로 형변환을 한다.

	}
}
