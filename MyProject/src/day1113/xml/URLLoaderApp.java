package day1113.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/*
	���ݱ����� URL ���� �ڿ��� ������ �� �� ����� �̹����� �Ͽ�����.
	ex. ImageIO.read() �� �ǽ�..
	
	���� �� �ǽ������� �̹��� �Ӹ� �ƴ϶� �������� ��� ������ �ڿ��� ������� �����Ͽ�
	��Ʈ������ �����͸� �о�� �ǽ��� �����غ���.
*/
public class URLLoaderApp {

	// ������ ��� �ڿ��� ������� ���� �� �����͸� �о�� �� �ִ� ��ü
	URLConnection con; // �߻�Ŭ���� �̹Ƿ�, URL ��ü�κ��� �ν��Ͻ��� ��´�.
	HttpURLConnection http;
	URL url;
	FileOutputStream fos;
	
	public URLLoaderApp() {
		try {
			url = new URL("https://www.sciencetimes.co.kr/wp-content/uploads/2020/04/%EC%9A%B0%EC%A3%BC-2-480x270.jpg");
			
			// ������ �������� �ڿ��� ������ �õ�!
			con = url.openConnection();
			http = (HttpURLConnection)con;
			http.setRequestMethod("GET");
			
			// ���� ��ü�κ��� ��Ʈ���� ���ͼ� �����͸� �о��.
			InputStream is = http.getInputStream();
			
			// ���Ϸ� �����غ���.(�׳� �Ϲ� ���丮 res ����)
			File file = new File("D:/workspace/java_workspace/MyProject/src/res/copy.jpg");
			fos = new FileOutputStream(file);
			
			// �� ����Ʈ �� �о�ͼ� ��½�Ʈ���� �̿��Ͽ� ���Ͽ� ����
			int data = -1;	// ó������ �о���� �����Ͱ� ���ٰ� ����
			
			while(true) {
				data = is.read();
				if(data==-1)break;
				fos.write(data);
			}
			System.out.println("���ͳݻ��� ������ ���÷� ����Ϸ��߾��.");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
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
		new URLLoaderApp();
	}
}
