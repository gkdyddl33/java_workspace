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

public class KeyBoardFile{
	FileWriter writer;
	
	public KeyBoardFile() {
		// ���
		
		// ������ ���纻
		URL url = this.getClass().getClassLoader().getResource("res/");
		try {
			URL url2 = new URL(url, "empty2.txt");
			
			URI uri = url2.toURI();
			System.out.println(uri);
			
			// ��� �ִ°� ����ҷ�? ��δ�?
			writer = new FileWriter(new File(uri));
			
		}catch (MalformedURLException e1) {
			e1.printStackTrace();
		}catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	public void saveFile() {
		// �Է�
		InputStream is = System.in;
		InputStreamReader reader = new InputStreamReader(is);
		BufferedReader buffr = new BufferedReader(reader);
		
		String msg = null;
		try {
			msg = buffr.readLine();
			System.out.println(msg);
			
			// ����ϰԲ� ����
			writer.write(msg);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			// ��´ݱ�
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
		new KeyBoardFile().saveFile();;
	}
}

