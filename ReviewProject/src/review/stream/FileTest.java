package review.stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileTest {
	public static void main(String[] args) throws IOException {
// FileWriter을 작성시 예외오류발생을 예고해준다.
// 그래서 Input,Output exception 예외처리를 해줘야 한다. 들어오고 나가는 읽기에서 오류예빵
//		try {
//			BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// append는 파일이름,추가내용 -> 덮어씌워주는 것이 아니라 이어져서 작성된다.
		BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"));
		bw.write("사랑기쁨");

		// bw.write("\n행복\n");
		// java는 운영체제의 종류에 맞게 자동으로 줄바꿈 문자를 변경해준다.
		// 윈도우즈에서 텍스트 파일로부터 텍스트를 불로올 떄 엔터키(줄바꿈)을 "\r\n"으로 인식한다.
		// 구분점으로 \n을 사용할 때 인식하지 못하는 경우가 발생할 수도 있다.
		// 이럴 때에는 구분점을 "\r\n"으로 주면 해결된다.
		bw.close();

		// (1)전체 목록(가져오기)

		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader("test32132.txt"));
			String line = "";
//			System.out.println(br.readLine());
//			System.out.println(br.readLine()); // 다음줄을 가져와 하는 readLine() -> 계속 쓸수 없기 때문에 반복문을 사용
			while(true) {
				line = br.readLine();
				if(line==null) {break;}
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("해당 경로에 파일이 존재하지 않습니다.");
		}

		// (2)수정 : 가져오면서 if문 사용 새로운 값 넣어라는 if문(내가 수정할 줄이니?) -> 새로운 값 연결
		BufferedReader br = null;
		String result ="";
		
		try {
			br = new BufferedReader(new FileReader("test.txt"));
			String line = "";
//			System.out.println(br.readLine());
//			System.out.println(br.readLine()); // 다음줄을 가져와 하는 readLine() -> 계속 쓸수 없기 때문에 반복문을 사용
			while (true) {
				line = br.readLine();
				if (line == null) {
					break;
				}
				if(line.equals("사랑기쁨")) {
					result += "사랑\n"; // 사랑기쁨 -> 사랑
					continue;
				}
				result += line + "\n";
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("해당 경로에 파일이 존재하지 않습니다.");
		}
		
		//BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"));
		bw.write(result);
		bw.close();
		
		// (3)행복 : 삭제하기
		//BufferedReader br = null;
		//String result = "";	
		try {
			br = new BufferedReader(new FileReader("test.txt"));
			String line = "";

			while (true) {
				line = br.readLine();
				if (line == null) {
					break;
				}
				if(line.equals("행복")) {
					continue;	
				}
				result += line + "\n";
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("해당 경로에 파일이 존재하지 않습니다.");
		}
		
		//BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"));
		bw.write(result);
		bw.close();
		
	}
}
