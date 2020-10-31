package day1030.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
	// ������ ������� �� �Է� ��Ʈ��	
	FileInputStream fis;
	// ������ ������� �� ��� ��Ʈ��
	FileOutputStream fos;
	
	// ������ ��ġ, ����� ����� ��ġ => ��������
	//String ori = "D:/workspace/java_workspace/SeProject/res/data/memo.txt";
	String ori = "D:/workspace/java_workspace/SeProject/res/travel2/aa.jpg";
	//String dest = "D:/workspace/java_workspace/SeProject/res/data/memo_copy.txt";
	String dest = "D:/workspace/java_workspace/SeProject/res/data/photo.jpg";
		
	public FileCopy() {
		// Stream ���� -> �Ʒ��� �ڵ�� ������ ������ ����.
		// ��, ����� ������ ���� ��� ������ ���鼭 ���α׷��� ������ ���� �� �� �ִ� ����� �ִ�.
		// ���� sun������ �̷��� ����� ���� ó���� ����ó���� �����ϰ� �ִ�.
		try {
			fis = new FileInputStream(ori);
			System.out.println("Stream ���� ����!");
			// ���� ��½�Ʈ���� ������ ��η� ����ִ� empty ������ �������ش�.
			fos = new FileOutputStream(dest);
						
			// �� ������ ��ȹ)
			// Stream ������ �����Ǿ����Ƿ�, �����͸� �� ����Ʈ�� �о,
			// �ٸ� ����ִ� ���Ͽ� ����غ���.
			//int data;
			//data = fis.read();
			
			int data;
			while(true) {  // ���)������ ��������� ������ �ȴ�.
				data = fis.read(); // �б�
				if(data==-1)break;
				fos.write(data);   // ����				
			}
			System.out.println("����ó���� �Ϸ��Ͽ����ϴ�.");
						
		} catch (FileNotFoundException e) {
			// -> �����ڰ� ���� �м��� �ϱ� ���� ��� ����
			System.out.println("������ ã�� �� �����ϴ�.");	// ���ø����̼� ����ڸ� ���� �޽���
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
		new FileCopy();
	}
}
