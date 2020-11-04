package day1102.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


public class KeyBoardFileApp {
	FileWriter writer;
	
	public KeyBoardFileApp() {
		URL url = this.getClass().getClassLoader().getResource("res/");
		try {
			URL url2 = new URL(url,"empty.txt"); // บนป็บป
			URI uri = url2.toURI();
			System.out.println(uri);
			writer = new FileWriter(new File(uri));
			
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
	public void saveFile(){
		InputStream is = System.in;
		InputStreamReader reader = new InputStreamReader(is);
		BufferedReader buffr = new BufferedReader(reader);
		
		String str = null;
		try {
			str = buffr.readLine();
			writer.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}			
	}	
	public static void main(String[] args) {
		new KeyBoardFileApp().saveFile();;
	}
}
