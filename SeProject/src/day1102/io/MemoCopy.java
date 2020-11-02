package day1102.io;

import java.io.FileInputStream;
/*
 * ������ ���������� ��������(����)�� ���̳ʸ� ����(����)���� �����غ��Ҵ�.
 * ������, ���������� ��� �ѱ��� ���� ���� �� � ����� �������� �׽�Ʈ�غ���.
 * 
 *	������ ���� ���� : [��Ʈ���� ����]
 *	��Ʈ���� �⺻�� 1byte�� ó���ϴ� ����Ʈ ����� ��Ʈ���̴�.
 *	������, ��Ʈ�������� �帣�� �����͸� ���ڷ� �ؼ��� �� �ִ� ��Ʈ���� ���ڱ�� ��Ʈ���̶� �Ѵ�.
 *	���� ��� ��Ʈ���� ���̾ ~Reader, ~Writer�� ������.
 *
 *	��Ʈ���� ���⿡ ���� �Է� InputStream
 *								��� OutputStream
 *	����ó�� ���ο� ���� ����Ʈ
 *	Reader		  ���� ��� �Է� : �ΰ��� ������ ���� �� �ִ�.
 *	Writer		  ���� ��� ��� : �ΰ��� ������ ������ ������ Ȯ�� ����
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MemoCopy {
	FileInputStream fis;
	FileOutputStream fos;
	
	// ������ ������� �� ���ڱ���� �Է�,��� ��Ʈ��
	FileReader reader;
	FileWriter writer;
	
	String path = "D:/workspace/java_workspace/SeProject/res/data/test.txt";
	String path2 = "D:/workspace/java_workspace/SeProject/res/data/test2.txt";
	public MemoCopy() {
		 try {
			//fis = new FileInputStream(path);
			//fos = new FileOutputStream(path2); // ����ִ� ���� ���� => ����
			reader = new FileReader(path);
			writer = new FileWriter(path2);
			 // (*) �� ����Ʈ�� �о���̸鼭 ������ �ѱ��� ��� �Ǵ��� �����غ���.
			// �ѱ��� �� ����Ʈ�� �ƴѵ� �� ����Ʈ������ ����ҷ��� �ϴ� ������ ����.
			// ��� : FileInputStream �� ����Ʈ ����� ��Ʈ���̹Ƿ� 1byte���� �ؼ��� �� �ִ�.
			// ���� �ѱ��� ��� 2byte�� �����Ǿ� �����Ƿ�, ����� �����ϰ�����, ��Ʈ���󿡼�
			// �帣�� �����͸� �ѱ۷� ������ �� ���� ������ ���� �� �ۿ� ����.
			int data;
			while(true) {
				data = reader.read();
				if(data==-1)break;
				System.out.print((char)data);	
				writer.write(data);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new MemoCopy();
	}
}
