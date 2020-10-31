package day1030.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReadApp2 {
	FileInputStream fis;
	
	public FileReadApp2() {
		File file = new File("D:/workspace/java_workspace/SeProject/res/data/memo.txt");
		try {
			fis = new FileInputStream(file);
			System.out.println("Stream ���� ����");
			
			// ���Ͼȿ� � ������ �ִ��� �о�ž�.
			int data;
			while(true) {
				data = fis.read();
				// ������ �� �������� ������Ѵ�.
				if(data==-1)break;
				System.out.print((char)data);				
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("������ ã�� �� �����ϴ�.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("������ ���� �� �����ϴ�.");
			e.printStackTrace();
		}finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
		}
	}
	public static void main(String[] args) {
		new FileReadApp2();
	}
}
