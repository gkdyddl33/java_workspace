package day1103.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

// 키보드로부터 입력받은 데이터를 파일로 저장해본다.
public class KeyBoardFileApp {
	String dir;
	FileWriter writer;
	
	public KeyBoardFileApp() {
		//(2)파일 경로
		URL url = this.getClass().getClassLoader().getResource("res/");
		
		
		
		// URI로 구해보기..패키지경로
		try {
			// 디렉토리 + 파일명 조합
			URL url2 = new URL(url,"empty.txt");
			
			URI uri = url2.toURI();
			System.out.println(uri);
			
			writer = new FileWriter(new File(uri));
			
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	public void saveFile() {
		// (1)키보드 스트림은 System으로부터 얻어와야 한다.
		// 바이트(영어)->문자(한글)기반으로 업그레이드
		InputStream is = System.in;
		InputStreamReader reader = new InputStreamReader(is);
		BufferedReader buffr = new BufferedReader(reader);
		
		// 파일 출력스트림 계열은 빈(empty) 파일을 생성해준다.
		//FileWriter writer=new FileWriter(file);
		
		String msg = null;
		try {
			// 한줄 받고 저장하고 끝내자 반복문 사용하지 말구!!
			msg = buffr.readLine();
			System.out.println(msg);
			writer.write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}			
	}
	public static void main(String[] args) {
		new KeyBoardFileApp().saveFile();
	}
}
