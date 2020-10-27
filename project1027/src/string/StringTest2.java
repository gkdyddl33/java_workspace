package string;

public class StringTest2 {
	public static void main(String[] args) {
		/*String은 불변(mutable)이다*/
		String s1 = "korea";
		System.out.println(s1);
		s1 = s1+" fighting..";
		System.out.println(s1);
		
		// 누적 생성에 대한 해결책
		// 수정가능한 문자열 처리 객체
		// StringBuffer, StringBuilder
		StringBuilder sb = new StringBuilder();
		sb.append("");
		for (int i = 0; i < 100; i++) {
			sb.append("줄넘기"+i+"회\n");
		}
		// sb 는 string이 아니므로 문자 출력 toString() 메소드 사용
		System.out.println(sb.toString());
		
	}
}
