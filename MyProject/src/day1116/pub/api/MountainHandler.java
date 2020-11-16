package day1116.pub.api;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/*
 	SAX Parsing 시 모든 node(요소, 텍스트 등 xml 을 이루는 요소들을 일컬음) 마다
 	이벤트를 발생시켜주는 객체
*/
public class MountainHandler extends DefaultHandler{
	
	public void startDocument() throws SAXException {
		
	}
	
	// 시작태그
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
			
	}
	
	// 중간태그
	public void characters(char[] ch, int start, int length) throws SAXException {
			
	}
	
	// 닫는태그
	public void endElement(String uri, String localName, String tag) throws SAXException {
			
	}
	
	
	public void endDocument() throws SAXException {
			
	}
}
