package day1102.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KeyBoardInputApp {
	public static void main(String[] args) {
		// 일반 입력 스트림 -> 바이트기반.. -> 문자기반
		InputStream is = System.in;
		InputStreamReader reader = new InputStreamReader(is);
		
		int data;
		try {
			while(true) {
				data = reader.read();
				System.out.println((char)data);				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
