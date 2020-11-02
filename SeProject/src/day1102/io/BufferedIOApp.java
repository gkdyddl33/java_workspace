package day1102.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
 *	������ �� ���α׷����� �����͸� �аų� �� ��, �� ����Ʈ, �� ���ھ� �������
 *	�����ϸ�, �����ͷ��� ���� �� �ʹ� ���� ������� �����ϰ� �ȴ�. 
 *	�ӵ� ����..
 *	��ġ cmd â�� ���� ó�� ó��, �޸𸮻��� ���ۿ� �����͸� ��Ƴ���, �� ���� 
 *	������ �������� �Է� �� ����� ó���ϸ� ���α׷� ������ ȿ������ �ش�ȭ ��.
 *
 * 	��Ʈ�� �� ���۸� �����ϴ� ��Ʈ���� ���ξ�� Buffered~�� �ٴ´�.
 * ä���� �� BufferedReader, BufferedWriter�� ���� ����Ѵ�.
 * 
 * -----------> �ǽ� : Ű����� �Էµ� �����͸� �� �پ� ������ ����.
 */
public class BufferedIOApp {

	public static void main(String[] args) {
		// keyboard�� ����� ��Ʈ���� �����ڰ� ���ϴ� Ÿ�ӿ� new �� �� ����.
		// ��? os�� �̹� ���� �������Ƿ�.. ���� �ڹٿ����� �ý������κ��� ���� �� �ִ�.
		
		// �Է� ��Ʈ���� �ֻ��� �߻�Ŭ����
		// Ű����,��ĳ�� ����� �� ��Ʈ������ ���� �� �ִ�.)
		// ����Ʈ ��� -> ���ڱ������ upgrade �ʿ��� �κ��� ����(��ü)
		InputStream is = System.in; 
		InputStreamReader reader = new InputStreamReader(is);
		
		// (*) �� ū ���밡 ����..���ٷ� ���´�..
		// ���Ͱ� �� ���� ���� ���ϴµ� �װ� �ٹٲ� ���๮�ڸ� ������ �ִ� ���̴�.
		// �׷��� �� �ٷ� �����͸� ������ִ� ��!!(\n)
		BufferedReader buffr = new BufferedReader(reader);
		
		int data;
		String str = null;
		try {
			while(true) {
				//data = reader.read();
				//if(data==-1)break; // Ű����� ���� ��� ����..������ ���� ����
//				System.out.print((char)data);
				
				str = buffr.readLine();
				System.out.println(str);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
