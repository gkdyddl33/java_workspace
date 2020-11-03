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

// Ű����κ��� �Է¹��� �����͸� ���Ϸ� �����غ���.
public class KeyBoardFileApp {
	String dir;
	FileWriter writer;
	
	public KeyBoardFileApp() {
		//(2)���� ���
		URL url = this.getClass().getClassLoader().getResource("res/");
		
		
		
		// URI�� ���غ���..��Ű�����
		try {
			// ���丮 + ���ϸ� ����
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
		// (1)Ű���� ��Ʈ���� System���κ��� ���;� �Ѵ�.
		// ����Ʈ(����)->����(�ѱ�)������� ���׷��̵�
		InputStream is = System.in;
		InputStreamReader reader = new InputStreamReader(is);
		BufferedReader buffr = new BufferedReader(reader);
		
		// ���� ��½�Ʈ�� �迭�� ��(empty) ������ �������ش�.
		//FileWriter writer=new FileWriter(file);
		
		String msg = null;
		try {
			// ���� �ް� �����ϰ� ������ �ݺ��� ������� ����!!
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
