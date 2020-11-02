package day1102.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MemoCopy {
	FileInputStream fis;
	FileOutputStream fos;
	
	FileReader reader;
	FileWriter writer;
	
	String path = "D:/workspace/java_workspace/SeProject/res/data/test.txt";
	String path2 = "D:/workspace/java_workspace/SeProject/res/data/test3.txt";
	public MemoCopy() {
		try {
//			fis = new FileInputStream(path);
//			fos= new FileOutputStream(path2);
			reader = new FileReader(path);
			writer = new FileWriter(path2);
			
			int data;
			while(true) {
				data = reader.read();
				if(data==-1)break;
				writer.write(data);				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new MemoCopy();
	}
}
