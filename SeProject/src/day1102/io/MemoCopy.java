package day1102.io;

import java.io.FileInputStream;
/*
 * 지난주 수업에서는 문서파일(영문)과 바이너리 파일(사진)등을 복사해보았다.
 * 하지만, 문서파일의 경우 한글이 섞여 있을 때 어떤 결과가 나오는지 테스트해본다.
 * 
 *	오늘의 수업 주제 : [스트림의 유형]
 *	스트림의 기본은 1byte씩 처리하는 바이트 기반의 스트림이다.
 *	하지만, 스트림상으로 흐르는 데이터를 문자로 해석할 수 있는 스트림을 문자기반 스트림이라 한다.
 *	문자 기반 스트림은 접미어가 ~Reader, ~Writer로 끝난다.
 *
 *	스트림은 방향에 따라 입력 InputStream
 *								출력 OutputStream
 *	문자처리 여부에 따라 바이트
 *	Reader		  문자 기반 입력 : 인간의 눈으로 읽을 수 있다.
 *	Writer		  문자 기반 출력 : 인간의 눈으로 씌여진 데이터 확인 가능
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MemoCopy {
	FileInputStream fis;
	FileOutputStream fos;
	
	// 파일을 대상으로 한 문자기반의 입력,출력 스트림
	FileReader reader;
	FileWriter writer;
	
	String path = "D:/workspace/java_workspace/SeProject/res/data/test.txt";
	String path2 = "D:/workspace/java_workspace/SeProject/res/data/test2.txt";
	public MemoCopy() {
		 try {
			//fis = new FileInputStream(path);
			//fos = new FileOutputStream(path2); // 비어있는 파일 생성 => 복사
			reader = new FileReader(path);
			writer = new FileWriter(path2);
			 // (*) 한 바이트씩 읽어들이면서 영문과 한글이 어떻게 되는지 관찰해보자.
			// 한글은 한 바이트가 아닌데 한 바이트씩으로 출력할려고 하니 에러가 난다.
			// 결론 : FileInputStream 은 바이트 기반의 스트림이므로 1byte씩만 해석할 수 있다.
			// 따라서 한글의 경우 2byte로 구성되어 있으므로, 복사는 성공하겠으나, 스트림상에서
			// 흐르는 데이터를 한글로 보고자 할 떄는 깨져서 보일 수 밖에 없다.
			int data;
			while(true) {
				data = reader.read();
				if(data==-1)break;
				System.out.print((char)data);	
				writer.write(data);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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
		new MemoCopy();
	}
}
