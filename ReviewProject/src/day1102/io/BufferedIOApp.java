package day1102.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BufferedIOApp {
	public static void main(String[] args) {
		// 입력 스트림 -> 바이트..문자기반 덮어주기
		InputStream is = System.in;
		InputStreamReader reader = new InputStreamReader(is);
		
		// 문자기반..한 줄로 만들어주는게 가능해짐..
		BufferedReader buffr = new BufferedReader(reader);
		
		String str = null;
		try {
			while(true) {
				str = buffr.readLine();
				System.out.println(str);				
			}
			// 읽은걸 출력해줘.
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
	}
}
