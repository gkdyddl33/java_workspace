package day1102.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BufferedIOApp {
	public static void main(String[] args) {
		// �Է� ��Ʈ�� -> ����Ʈ..���ڱ�� �����ֱ�
		InputStream is = System.in;
		InputStreamReader reader = new InputStreamReader(is);
		
		// ���ڱ��..�� �ٷ� ������ִ°� ��������..
		BufferedReader buffr = new BufferedReader(reader);
		
		String str = null;
		try {
			while(true) {
				str = buffr.readLine();
				System.out.println(str);				
			}
			// ������ �������.
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
	}
}
