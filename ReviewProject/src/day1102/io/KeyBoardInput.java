package day1102.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KeyBoardInput {
	public static void main(String[] args) {
		InputStream is = System.in;
		InputStreamReader reader = new InputStreamReader(is);
		
		int data;
		try {
			data = reader.read();
			System.out.println(data);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
