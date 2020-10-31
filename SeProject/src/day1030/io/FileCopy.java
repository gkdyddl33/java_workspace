package day1030.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
	// 파일을 대상으로 한 입력 스트림	
	FileInputStream fis;
	// 파일을 대상으로 한 출력 스트림
	FileOutputStream fos;
	
	// 원본의 위치, 복사될 경로의 위치 => 변수선언
	//String ori = "D:/workspace/java_workspace/SeProject/res/data/memo.txt";
	String ori = "D:/workspace/java_workspace/SeProject/res/travel2/aa.jpg";
	//String dest = "D:/workspace/java_workspace/SeProject/res/data/memo_copy.txt";
	String dest = "D:/workspace/java_workspace/SeProject/res/data/photo.jpg";
		
	public FileCopy() {
		// Stream 생성 -> 아래의 코드는 문법상 문제는 없다.
		// 단, 실행시 파일이 없을 경우 에러가 나면서 프로그램이 비정상 종료 될 수 있는 우려가 있다.
		// 따라서 sun에서는 이러한 우려에 대한 처리를 예외처리로 강제하고 있다.
		try {
			fis = new FileInputStream(ori);
			System.out.println("Stream 생성 성공!");
			// 파일 출력스트림은 지정한 경로로 비어있는 empty 파일을 생성해준다.
			fos = new FileOutputStream(dest);
						
			// 이 예제의 계획)
			// Stream 생성이 성공되었으므로, 데이터를 한 바이트씩 읽어서,
			// 다른 비어있는 파일에 출력해보자.
			//int data;
			//data = fis.read();
			
			int data;
			while(true) {  // 결론)파일이 만들어져서 복원이 된다.
				data = fis.read(); // 읽기
				if(data==-1)break;
				fos.write(data);   // 쓰기				
			}
			System.out.println("복사처리를 완료하였습니다.");
						
		} catch (FileNotFoundException e) {
			// -> 개발자가 원인 분석을 하기 위한 출력 내용
			System.out.println("파일을 찾을 수 없습니다.");	// 어플리케이션 사용자를 위한 메시지
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		new FileCopy();
	}
}
