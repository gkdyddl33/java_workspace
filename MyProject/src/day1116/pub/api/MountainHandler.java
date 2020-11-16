package day1116.pub.api;

import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/*
 	SAX Parsing 시 모든 node(요소, 텍스트 등 xml 을 이루는 요소들을 일컬음) 마다
 	이벤트를 발생시켜주는 객체
 	공공데이터포털의 실제 데이터를 가져오기 위해서는 Parsing을 해야 함..
*/
public class MountainHandler extends DefaultHandler{
	// 발견되는 산이 있을 때 VO로 생성후, 그 VO를 담게 될 벡터
	Vector<Mountain> mtList;
	Mountain mt;	// 산의 정보 1건을 임시적으로 담을 변수
	
	// 현재 실행부가 어느 위치를 지났는지를 알기 위한 변수
	boolean isMntnid;
	boolean isMntnnm;
	boolean isMntninfopoflc;
	boolean isMntninfohght;
	
	public void startDocument() throws SAXException {
		// 문서가 시작되면 벡터를 생성한다.		
	}
	
	// 시작태그
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		System.out.println("<"+tag+">");
		
		if(tag.equals("items")) {// 여기서 벡터 생성
			mtList = new Vector<Mountain>();
		}else if(tag.equals("item")) {// 여기서 VO 생성
			mt = new Mountain();
		}else if(tag.equals("mntnid")) {// 실행부가 지나간다.
			isMntnid=true;
		}else if(tag.equals("mntnnm")) {
			isMntnnm=true;
		}else if(tag.equals("mntninfopoflc")) {
			isMntninfopoflc=true;
		}else if(tag.equals("mntninfohght")) {
			isMntninfohght=true;
		}
	}
	
	// 중간태그
	public void characters(char[] ch, int start, int length) throws SAXException {
		String data = new String(ch, start, length);
		System.out.println(data);
		
		//태그위치에 따른 데이터 담기 
		if(isMntnid) {
			mt.setMntnid(Integer.parseInt(data)); //산의 아이디 담기
		}else if(isMntnnm) {
			mt.setMntnnm(data);//산의 이름
		}else if(isMntninfopoflc) {
			mt.setMntninfopoflc(data);//산의 위치
		}else if(isMntninfohght) {
			mt.setMntninfohght(Integer.parseInt(data)); //산의높이
		}
	}
	
	// 닫는태그
	public void endElement(String uri, String localName, String tag) throws SAXException {
		System.out.println("</"+tag+">");	
		if(tag.equals("mntnid")) {// 실행부가 지나간다.
			isMntnid=false;
		}else if(tag.equals("mntnnm")) {
			isMntnnm=false;
		}else if(tag.equals("mntninfopoflc")) {
			isMntninfopoflc=false;
		}else if(tag.equals("mntninfohght")) {
			isMntninfohght=false;
		}else if(tag.equals("item")) {
			mtList.add(mt);			
		}
	}	
	
	public void endDocument() throws SAXException {
		System.out.println("검색된 산의 갯수는 "+mtList.size());
		
		for(int i=0;i<mtList.size();i++) {
			Mountain obj = mtList.get(i);
			System.out.println("산ID"+obj.getMntnid());
			System.out.println("산이름"+obj.getMntnnm());
			System.out.println("산위치"+obj.getMntninfopoflc());
			System.out.println("산높이"+obj.getMntninfohght());
			System.out.println("------------------------------------------------");
		}
	}
}
