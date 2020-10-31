package day1030.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 *	Stream이란?
 *	현실에서는 흐르는 물줄기를 의미
 *	전산에서는 흐름의 대상이 물이 아닌 데이터이다!!
 *	그러나, 전산에서는 흐름의 방향에 따라 다음과 같이 분류한다.
 *	(기준은 실행 중인 프로그램)
 *
 *		1) 입력(Input) : 실행중인 프로그램으로 데이터가 흘러들어가는 것
 *		2) 출력(Ouput) : 실행중인 프로그램에서 데이터가 흘러나오는 것
 * 
 *  자바에서는 입출력과 관련된 패키지명이 java.io이다.
 *  여기에는 입출력 처리를 위한 수 많은 api 지원..
 *  
 *  예외란?
 *  프로그램이 정상 실행되어질 수 없는 예외적인 상황을 의미(에러)
 *  에러가 발생하면 왜 문제가 생기나?
 *  프로그램이 비정상 종료가 되버린다..
 */

public class FileReadAPP {
	// 파일을 대상으로 데이터를 읽어들이는 FileInputStream을 학습해본다.
	FileInputStream fis;
	
	public FileReadAPP() {
		File file = new File("D:/workspace/java_workspace/SeProject/res/data/memo1.txt");
		
		try {// 이 영역은  에러가 발생할 가능성이 있는 코드임을 명시..
			fis = new FileInputStream(file);		
			System.out.println("Stream 생성 성공입니다.");
			
			int data;
			while(true) {
				data = fis.read();	// 1byte 읽어들이기
				if(data==-1) break;		//파일의 끝에 도달하면, 반복문 빠져나옴!
				// data 값은 keyCode값
				System.out.print((char)data);				
			}
			
			//data = fis.read();
			//System.out.print((char)data);
						
		} catch (FileNotFoundException e) {// 혹여나 우려했던 에러가 발생한다면, 비정상 종료하지말고
										  					// 실행문은 이 영역으로 와서 수행하라!!
			// FileNotFoundException -> catch문의 인수로 전달하여, 개발자로 하여금 에러에
			//										대한 정보를 분석할 수 있는 기회를 주는 것이다..
			
			System.out.println("지정한 파일을 찾을 수 없습니다.");
			// 에러가 났다? 그럼 " stack 구조로, 에러의 원일을 출력하라 " 라는 예외처리메소드!
			e.printStackTrace(); 
			
			// 예외처리 목적은? 비정상 종료의 방지..
		} catch(IOException e) {
			// read에 대한 예외처리
			System.out.println("파일을 읽을 수 없습니다.");
			e.printStackTrace();
		} finally {
			// 이 영역은 실행부가 try문을 수행하던 catch문을 수행하던 무조건 거쳐서 가야하는 곳..
			// 이 영역에 자원을 닫는 코드를 작성해야 한다.
			// ★★★주로 데이터베이스와의 연결끊기, 스트림 연결 끊기
			if(fis != null) {
				// null이 아닐때만..닫자
				// db와 stream 닫을 때는 반드시 null여부를 따져보는 습관을 갖자!				
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
		}
	}
	public static void main(String[] args) {
		new FileReadAPP();
	}
}
