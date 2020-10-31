package day1030.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 *	Stream�̶�?
 *	���ǿ����� �帣�� ���ٱ⸦ �ǹ�
 *	���꿡���� �帧�� ����� ���� �ƴ� �������̴�!!
 *	�׷���, ���꿡���� �帧�� ���⿡ ���� ������ ���� �з��Ѵ�.
 *	(������ ���� ���� ���α׷�)
 *
 *		1) �Է�(Input) : �������� ���α׷����� �����Ͱ� �귯���� ��
 *		2) ���(Ouput) : �������� ���α׷����� �����Ͱ� �귯������ ��
 * 
 *  �ڹٿ����� ����°� ���õ� ��Ű������ java.io�̴�.
 *  ���⿡�� ����� ó���� ���� �� ���� api ����..
 *  
 *  ���ܶ�?
 *  ���α׷��� ���� ����Ǿ��� �� ���� �������� ��Ȳ�� �ǹ�(����)
 *  ������ �߻��ϸ� �� ������ ���⳪?
 *  ���α׷��� ������ ���ᰡ �ǹ�����..
 */

public class FileReadAPP {
	// ������ ������� �����͸� �о���̴� FileInputStream�� �н��غ���.
	FileInputStream fis;
	
	public FileReadAPP() {
		File file = new File("D:/workspace/java_workspace/SeProject/res/data/memo1.txt");
		
		try {// �� ������  ������ �߻��� ���ɼ��� �ִ� �ڵ����� ���..
			fis = new FileInputStream(file);		
			System.out.println("Stream ���� �����Դϴ�.");
			
			int data;
			while(true) {
				data = fis.read();	// 1byte �о���̱�
				if(data==-1) break;		//������ ���� �����ϸ�, �ݺ��� ��������!
				// data ���� keyCode��
				System.out.print((char)data);				
			}
			
			//data = fis.read();
			//System.out.print((char)data);
						
		} catch (FileNotFoundException e) {// Ȥ���� ����ߴ� ������ �߻��Ѵٸ�, ������ ������������
										  					// ���๮�� �� �������� �ͼ� �����϶�!!
			// FileNotFoundException -> catch���� �μ��� �����Ͽ�, �����ڷ� �Ͽ��� ������
			//										���� ������ �м��� �� �ִ� ��ȸ�� �ִ� ���̴�..
			
			System.out.println("������ ������ ã�� �� �����ϴ�.");
			// ������ ����? �׷� " stack ������, ������ ������ ����϶� " ��� ����ó���޼ҵ�!
			e.printStackTrace(); 
			
			// ����ó�� ������? ������ ������ ����..
		} catch(IOException e) {
			// read�� ���� ����ó��
			System.out.println("������ ���� �� �����ϴ�.");
			e.printStackTrace();
		} finally {
			// �� ������ ����ΰ� try���� �����ϴ� catch���� �����ϴ� ������ ���ļ� �����ϴ� ��..
			// �� ������ �ڿ��� �ݴ� �ڵ带 �ۼ��ؾ� �Ѵ�.
			// �ڡڡ��ַ� �����ͺ��̽����� �������, ��Ʈ�� ���� ����
			if(fis != null) {
				// null�� �ƴҶ���..����
				// db�� stream ���� ���� �ݵ�� null���θ� �������� ������ ����!				
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
		}
	}
	public static void main(String[] args) {
		new FileReadAPP();
	}
}
