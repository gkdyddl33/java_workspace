package day1030.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy2 {
	FileInputStream fis;
	FileOutputStream fos;
	
	String ori = "D:/workspace/java_workspace/SeProject/res/data/memo.txt";
	String dest = "D:/workspace/java_workspace/SeProject/res/data/memo_copy2.txt";
	public FileCopy2() {
		try {
			fis = new FileInputStream(ori);
			System.out.println("Stream ���� ����!");
			fos = new FileOutputStream(dest);
			
			int data;
			while(true) {
				data = fis.read();
				// ������ ���� -> ���뵵 �����ؼ� ����.
				if(data == -1)break;
				fos.write(data);					
			}
			System.out.println("����ó�� �Ϸ��Ͽ����ϴ�.");
			
		} catch (FileNotFoundException e) {
			System.out.println("������ ã�� �� �����ϴ�.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("������ ���� �� �����ϴ�.");
			e.printStackTrace();
		}finally {
			if(fis != null) {// ������ �־�� ��Ʈ���� �ݱ� ������
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
			if(fos != null) {// ������ �־�� ��Ʈ���� �ݱ� ������
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
		}
	}
	public static void main(String[] args) {
		new FileCopy2();
	}
}
