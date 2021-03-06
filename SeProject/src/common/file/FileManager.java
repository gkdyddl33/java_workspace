package common.file;

public class FileManager {

	// 파일명 구하기	: 매개변수로 파일의 경로를 넘겨받아 파일명만 추출한다.
	public static String getFilename(String path) {
		int lastIndex = path.lastIndexOf("/");	// 마지막에 위치한 '/'의 인덱스 구하기
		
		return path.substring(lastIndex+1, path.length());
	}
	
	// 확장자 구하기	: 매개변수로 파일명을 넘겨받아 확장자는 추출한다.
	public static String getExtend(String filename) {
		String[] str = filename.split("\\.");	// . 을 기준으로 문자열 분리..분리후에는 배열이 반환됨
		return str[1];	// 두번째 칸이 확장자임..
	}
/*	
	public static void main(String[] args) {
		String filename = getFilename("https://image.auction.co.kr/itemimage/15/5d/05/155d0590e6.jpg");
		System.out.println(filename);
		
		String ext = getExtend(filename);
		System.out.println(ext);
	}
*/	
	public static void main(String[] args) {
		new FileManager();
	}
}
