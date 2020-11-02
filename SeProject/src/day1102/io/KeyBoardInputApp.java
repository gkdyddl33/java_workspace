package day1102.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
 *	�Է� ��Ʈ�� ó���� ���Ͽ� ���ѵ� ����� �ƴϴ�.
 *	��, �������� ���α׷����� �����Ͱ� �귯���´ٸ� �� ��� ���� �� �Է½�Ʈ���̴�.
 *	���� �� ���������� ������ ������� �����͸� �д� ���� �ƴ϶�
 *	Ű���带 ������� �����͸� �о�� ����.
 *	����) ���ϰ��� �޸�, Ű������ ��Ʈ���� �ڹٿ��� ������ �� �ִ� ���� �ƴ϶�,
 *	�̹� OS �������� ��Ʈ���� �����ϹǷ�, �ڹٴ� ���� �̹� �����ϴ� ��Ʈ���� 
 *	���� �� �� ���� ���̴�.. 
 */
public class KeyBoardInputApp {

	public static void main(String[] args) {
		// Ű���� ��Ʈ���� �̹� �ý��������� �����ϹǷ�, �ڹ��� System Ŭ�����κ��� ����
		// ǥ�� �Է�,��½�Ʈ��
		// ����Ʈ ��� -> �ѱ��� �Է��ϸ� ������. -> ����Ʈ�� ���ڸ� �Է¹ް� ������ ����
		InputStream is = System.in;
		InputStreamReader reader = new InputStreamReader(is);
		int data;
		try {
			while(true) {
				data = reader.read(); // ���� �ѱ۵� ó���� �� �ִ� �����Ͱ� ��.
				// read() �� Ư¡ : �Է��� �Ϸ� ���� ������ ���Ѵ����·� �ӹ��� ����
				// ��, �Է��� �Ǿ�� read() �޼��� ������ ������ ����� �� �ִ�.
				System.out.print((char)data);				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println();
	}
}
