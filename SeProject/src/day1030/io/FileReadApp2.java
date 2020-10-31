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
			System.out.println("Stream 생성 성공");
			
			// 파일안에 어떤 내용이 있는지 읽어볼거야.
			int data;
			while(true) {
				data = fis.read();
				// 파일을 다 읽으면은 멈춰야한다.
				if(data==-1)break;
				System.out.print((char)data);				
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("파일을 읽을 수 없습니다.");
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
