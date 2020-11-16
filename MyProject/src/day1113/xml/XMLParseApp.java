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
 	java�� xml�� �Ľ��ϴ� ����� ũ�� 2������ �ִ�.
 	1) DOM��� - html�� ���� ����..
 						��, ��� �±׸��� 1:1 �����ϴ� DOM��ü�� �޸𸮿� �����س���
 						���α׷��� ���� �ʿ��� ��ü��  �����ϴ� ���
 						ex. javascript --> xe
 						
 						���ſ� DOM��ü�� �޸𸮿� ���ϸ� ����ų �� �ִ�. Ư�� �޸�ũ�Ⱑ .pc�� ����
 						��������� ���� ����̽��� ��� DOM ����� �������� ���ϴ�!
 						
 	2) SAX ��� - xml������  �̷�� ������Ʈ ,�ؽ�Ʈ ���� ��� ��忡 ���� �̺�Ʈ�� �߻������ִ� ���
                		���� �����ڴ� ������ �ڹ��� ��ü�� �޸𸮿� �÷�, xml�� ����Ͽ� 
                		�����͸� ����ϸ� �ȴ�!!
*/

public class XMLParseApp {

	// �������� �ڹ����μ����� ���Ͽ� �����ϱ� ���ؼ��� ���� �Է½�Ʈ�� �迭�� �ʿ��ϴ�!! 
	FileInputStream fis;
	InputStreamReader reader;
	BufferedReader buffr;
	URL url;	// ������ �ִ� ���
	File file;
	URI uri;
	
	public XMLParseApp() {
// ---------------------- ������ �޸𸮿� �ø�.. parse ������!
		url = this.getClass().getClassLoader().getResource("res/pets.xml");
		try {
			// uri �� ��ȯ, File Ŭ������ �����ڿ����� URL�� �ƴ� URI�� ���ϹǷ�..
			uri = url.toURI();	
			file = new File(uri);

			parseData();	// parsing ����
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}			
	}
	
	public void parseData() {
		// SAX����� �ļ��� SAXParserFactory ��ü�κ��� ��´�.
		// Factory�� ���� �ļ��� �ν��Ͻ��� ���� �� �ִ�.
		SAXParserFactory factory;
		factory = SAXParserFactory.newInstance(); // static �޼��带 �̿��Ͽ�  factory�ν��Ͻ� ����
		
		try {
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(file, new MyHandler()); // ����Ʈ�ڵ鷯 -> Ŭ������ ���� ���� ���
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readTest() {
// ---------------------- �� �о������ �׽�Ʈ��
		try {
			fis = new FileInputStream(file=new File(uri));
			
			reader = new InputStreamReader(fis);
			buffr = new BufferedReader(reader);
			
			// �Ľ��ϱ� �� --> �츮�� ������ xml �� ����� ��Ʈ������ �о���ϼ� �ִ��� üũ�غ���.
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
