package day1113.xml.down;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MovieHandler2 extends DefaultHandler{
		
	ArrayList<Movie> movieList;
	Movie movie;
	
	boolean isTitle;
	boolean isUrl;
	
	public void startDocument() throws SAXException {}
	
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		// 여는 태그
		if(tag.equals("movies")) {
			movieList = new ArrayList<>();
		}else if(tag.equals("movie")) {
			movie = new Movie();
		}else if(tag.equals("title")) {
			isTitle = true;
		}else if(tag.equals("url")) {
			isUrl = true;
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		String data = new String(ch, start, length);
		if(isTitle) {
			movie.setTitle(data);
		}else if(isUrl) {
			movie.setUrl(data);
		}
	}
		
	public void endElement(String uri, String localName, String tag) throws SAXException {
		// 닫는태크
		if(tag.equals("title")) {
			isTitle = false;
		}else if(tag.equals("url")) {
			isUrl = false;
		}else if(tag.equals("movie")) {
			movieList.add(movie);
		}
	}
		
	public void endDocument() throws SAXException {
		// 마무리 실행
		for(Movie obj : movieList) {
			System.out.println(obj.getTitle());
			System.out.println(obj.getUrl());
			System.out.println("------------------------------------");
		}
	}
}
