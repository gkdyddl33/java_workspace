package review.stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileTest {
	public static void main(String[] args) throws IOException {
// FileWriter�� �ۼ��� ���ܿ����߻��� �������ش�.
// �׷��� Input,Output exception ����ó���� ����� �Ѵ�. ������ ������ �б⿡�� ��������
//		try {
//			BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// append�� �����̸�,�߰����� -> ������ִ� ���� �ƴ϶� �̾����� �ۼ��ȴ�.
		BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"));
		bw.write("������");

		// bw.write("\n�ູ\n");
		// java�� �ü���� ������ �°� �ڵ����� �ٹٲ� ���ڸ� �������ش�.
		// ��������� �ؽ�Ʈ ���Ϸκ��� �ؽ�Ʈ�� �ҷο� �� ����Ű(�ٹٲ�)�� "\r\n"���� �ν��Ѵ�.
		// ���������� \n�� ����� �� �ν����� ���ϴ� ��찡 �߻��� ���� �ִ�.
		// �̷� ������ �������� "\r\n"���� �ָ� �ذ�ȴ�.
		bw.close();

		// (1)��ü ���(��������)

		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader("test32132.txt"));
			String line = "";
//			System.out.println(br.readLine());
//			System.out.println(br.readLine()); // �������� ������ �ϴ� readLine() -> ��� ���� ���� ������ �ݺ����� ���
			while(true) {
				line = br.readLine();
				if(line==null) {break;}
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("�ش� ��ο� ������ �������� �ʽ��ϴ�.");
		}

		// (2)���� : �������鼭 if�� ��� ���ο� �� �־��� if��(���� ������ ���̴�?) -> ���ο� �� ����
		BufferedReader br = null;
		String result ="";
		
		try {
			br = new BufferedReader(new FileReader("test.txt"));
			String line = "";
//			System.out.println(br.readLine());
//			System.out.println(br.readLine()); // �������� ������ �ϴ� readLine() -> ��� ���� ���� ������ �ݺ����� ���
			while (true) {
				line = br.readLine();
				if (line == null) {
					break;
				}
				if(line.equals("������")) {
					result += "���\n"; // ������ -> ���
					continue;
				}
				result += line + "\n";
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("�ش� ��ο� ������ �������� �ʽ��ϴ�.");
		}
		
		//BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"));
		bw.write(result);
		bw.close();
		
		// (3)�ູ : �����ϱ�
		//BufferedReader br = null;
		//String result = "";	
		try {
			br = new BufferedReader(new FileReader("test.txt"));
			String line = "";

			while (true) {
				line = br.readLine();
				if (line == null) {
					break;
				}
				if(line.equals("�ູ")) {
					continue;	
				}
				result += line + "\n";
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("�ش� ��ο� ������ �������� �ʽ��ϴ�.");
		}
		
		//BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"));
		bw.write(result);
		bw.close();
		
	}
}
