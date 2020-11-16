package day1113.xml.down;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
	SAX 방식의 파싱을 지원하는 핸들러	
*/
public class MovieHandler extends DefaultHandler{
		
	ArrayList<Movie> movieList;
	Movie movie;
	// 지나가고 있다고 보고 한다.
	boolean isTitle;
	boolean isUrl;
	
	public void startDocument() throws SAXException {
		
	}
	
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		if(tag.equals("movies")) {
			movieList = new ArrayList<Movie>();
		}else if(tag.equals("movie")) {
			movie = new Movie();	// 비어있는 인스턴스 생성
		}else if(tag.equals("title")) {
			isTitle = true;
		}else if(tag.equals("url")) {
			isUrl = true;
		}
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		String data = new String(ch,start,length);	// char배열을 -> String으로
		if(isTitle) {
			movie.setTitle(data);
		}else if(isUrl) {
			movie.setUrl(data);
		}
	}
	
	public void endElement(String uri, String localName, String tag) throws SAXException {
		// 다시 돌려놓기
		if(tag.equals("title")) {
			isTitle = false;
		}else if(tag.equals("url")) {
			isUrl = false;
		}else if(tag.equals("movie")) {
			movieList.add(movie); // 한건 완료? 그럼 리스트에 넣자!
		}
	}
	
	public void endDocument() throws SAXException {
		for(Movie obj : movieList) {
			System.out.println(obj.getTitle());
			System.out.println(obj.getUrl());
			System.out.println("------------------------------------------------");
		}
	}
}
