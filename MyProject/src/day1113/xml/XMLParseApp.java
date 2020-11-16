package day1113.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/*
 	java로 xml을 파싱하는 방법은 크게 2가지가 있다.
 	1) DOM방식 - html과 같은 원리..
 						즉, 모든 태그마다 1:1 대응하는 DOM객체를 메모리에 생성해놓고
 						프로그래밍 언어에서 필요한 객체를  접근하는 방식
 						ex. javascript --> xe
 						
 						무거운 DOM객체가 메모리에 부하를 일으킬 수 있다. 특히 메모리크기가 .pc에 비해
 						상대적으로 작은 디바이스의 경우 DOM 방식은 적절하지 못하다!
 						
 	2) SAX 방식 - xml문서를  이루는 엘리먼트 ,텍스트 등의 모든 노드에 대한 이벤트를 발생시켜주는 방식
                		따라서 개발자는 적절한 자바의 객체를 메모리에 올려, xml을 대신하여 
                		데이터를 사용하면 된다!!
*/

public class XMLParseApp {

	// 실행중인 자바프로세스가 파일에 접근하기 위해서는 파일 입력스트림 계열이 필요하다!! 
	FileInputStream fis;
	InputStreamReader reader;
	BufferedReader buffr;
	URL url;	// 파일이 있는 경로
	File file;
	URI uri;
	
	public XMLParseApp() {
// ---------------------- 파일을 메모리에 올림.. parse 시작해!
		url = this.getClass().getClassLoader().getResource("res/pets.xml");
		try {
			// uri 로 변환, File 클래스의 생성자에서는 URL이 아닌 URI를 원하므로..
			uri = url.toURI();	
			file = new File(uri);

			parseData();	// parsing 시작
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}			
	}
	
	public void parseData() {
		// SAX방식의 파서는 SAXParserFactory 객체로부터 얻는다.
		// Factory로 부터 파서의 인스턴스를 얻을 수 있다.
		SAXParserFactory factory;
		factory = SAXParserFactory.newInstance(); // static 메서드를 이용하여  factory인스턴스 얻음
		
		try {
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(file, new MyHandler()); // 디폴트핸들러 -> 클래스를 새로 만들어서 사용
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readTest() {
// ---------------------- 잘 읽어오는지 테스트함
		try {
			fis = new FileInputStream(file=new File(uri));
			
			reader = new InputStreamReader(fis);
			buffr = new BufferedReader(reader);
			
			// 파싱하기 전 --> 우리가 정의한 xml 을 제대로 스트림으로 읽어들일수 있는지 체크해본다.
			String data = null;
			while(true) {
				data = buffr.readLine();
				if(data==null)break;
				System.out.println(data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new XMLParseApp();
	}
}
