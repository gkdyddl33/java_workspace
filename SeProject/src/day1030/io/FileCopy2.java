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
			System.out.println("Stream 생성 성공!");
			fos = new FileOutputStream(dest);
			
			int data;
			while(true) {
				data = fis.read();
				// 파일이 생김 -> 내용도 복사해서 넣자.
				if(data == -1)break;
				fos.write(data);					
			}
			System.out.println("복사처리 완료하였습니다.");
			
		} catch (FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("파일을 읽을 수 없습니다.");
			e.printStackTrace();
		}finally {
			if(fis != null) {// 파일이 있어야 스트림을 닫기 때문에
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
			if(fos != null) {// 파일이 있어야 스트림을 닫기 때문에
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
