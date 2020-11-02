package day1102.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
 *	실행중 인 프로그램으로 데이터를 읽거나 쓸 때, 한 바이트, 한 문자씩 입출력을
 *	수행하면, 데이터량이 많을 때 너무 많은 입출력을 수행하게 된다. 
 *	속도 저하..
 *	마치 cmd 창의 버퍼 처리 처럼, 메모리상의 버퍼에 데이터를 모아놓고, 한 줄이 
 *	끝나는 시점에만 입력 및 출력을 처리하면 프로그램 실행의 효율성이 극대화 됨.
 *
 * 	스트림 중 버퍼를 지원하는 스트림은 접두어로 Buffered~가 붙는다.
 * 채팅할 때 BufferedReader, BufferedWriter를 많이 사용한다.
 * 
 * -----------> 실습 : 키보드로 입력된 데이터를 한 줄씩 가져와 보자.
 */
public class BufferedIOApp {

	public static void main(String[] args) {
		// keyboard에 연결된 스트림은 개발자가 원하는 타임에 new 할 수 없다.
		// 왜? os가 이미 얻어다 놓았으므로.. 따라서 자바에서는 시스템으로부터 얻어올 수 있다.
		
		// 입력 스트림의 최상위 추상클래스
		// 키보드,스캐너 등등은 이 스트림으로 받을 수 있다.)
		// 바이트 기반 -> 문자기반으로 upgrade 필요한 부분은 덮음(합체)
		InputStream is = System.in; 
		InputStreamReader reader = new InputStreamReader(is);
		
		// (*) 더 큰 빨대가 꽂힘..한줄로 나온다..
		// 엔터가 한 줄의 끝을 뜻하는데 그걸 줄바꿈 개행문자를 가지고 있는 것이다.
		// 그래서 한 줄로 데이터를 출력해주는 것!!(\n)
		BufferedReader buffr = new BufferedReader(reader);
		
		int data;
		String str = null;
		try {
			while(true) {
				//data = reader.read();
				//if(data==-1)break; // 키보드는 끝이 없어서 생략..파일의 끝이 없음
//				System.out.print((char)data);
				
				str = buffr.readLine();
				System.out.println(str);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
