package gui;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame; // ��ġ ���!
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.TextArea;
import java.awt.Label;
import java.awt.Image;
import java.awt.Toolkit;

public class App1 {
	public static void main(String[] args) {		
		/* ����ó�� ���� Ŭ������ ������ �� ��ó��
		 * ��ó�ڼ�1) �ڹ��� ��� ��ü�� �ᱹ 3���� �����ۿ� ����.(
		 * ��ó�ڼ�2) Ŭ������ ����� ������̴�. ���� �޸𸮿� �ø��� ���� �˸� �ȴ�.
		 * 
		 * 1) �Ϲ�Ŭ���� : new �ϸ�ȴ�. new ���� ����������(api������ ����)
		 * 2) �߻�Ŭ���� : new �Ұ�, �ڽ��� �����ؼ� new �ϰų� �̹� ������ �ν��Ͻ��� �̿�(api������ ����)
		 * 3) �������̽� : new �Ұ�, �ڽ��� �����ؼ� new �ϰų� �̹� ������ �ν��Ͻ��� �̿�(api������ ����)
		 */
		
		/*������ ���� - �Ϲ��̱� ������ new ���� �����ڸ� �����ؼ� ���*/
		Frame frame =  new Frame();
		
		// �������� ����Ʈ�� ���� ������ �ʴ� ������. ���� �޼ҵ带 ȣ���ؾ� �Ѵ�.
		
		frame.setVisible(true); // window ��ü�κ��� ��ӹ��� �޼���
								// �Ű������δ� ������ ����� �� �ִ�.
		frame.setSize(300, 400); // �������� �ʺ�� ���̸� ����
		
		/*������ �ȿ� ��ġ�� ���� ������Ʈ�� �÷����ƺ���.*/
		// ��ư Button(�Ϲ�,�߻�,�������̽� ���� �� �Ϲ� new ������ ����)
		Button bt = new Button("�� ��ư"); // ��ư�� ������ �����̳ʿ� ��������.
		// ��ư�� �����ϱ� ���� ���̾ƿ� ��Ÿ���� �����ؾ� �Ѵ�.
		FlowLayout flow = new FlowLayout(); // ���� ������ ǥ�� ���̰� �ø��� �ȿ� ���빰�� �귯�ٴ�
		frame.setLayout(flow);
		
		frame.add(bt); // add�޼����� �Ű������� Component�ڷ����̹Ƿ�,
					   // Button�� Component�� �ڽ��̱� ������ ���� �ڷ����� �ش��Ͽ� �μ��� �� ���ִ�.
		
		 // html ������ input type = "text"�� �ڹٿ��� textField�� �Ѵ�.
		TextField tf = new TextField("ȸ������",10);
		frame.add(tf);
		// Checkbox
		Checkbox cb1 = new Checkbox("����");
		Checkbox cb2 = new Checkbox("����");
		Checkbox cb3 = new Checkbox("��ǻ��");
		frame.add(cb1);
		frame.add(cb2);
		frame.add(cb3);
		
		// TextArea
		TextArea ta = new TextArea(5,20);
		frame.add(ta);
		
		// �׳��ؽ�Ʈ 
		Label la = new Label("ȸ������ ����Դϴ�.");
		frame.add(la);
		
		// �̹��� �ֱ�
		// Image �� �߻�Ŭ���� �̸�, �÷���(os)�� ������ ������� ���� �� �ִ�.
		// �÷����� �°� ����������, DefaultToolkit Ŭ������ ���� �ڿ��� ���� �Ѵ�.
		Toolkit kit = Toolkit.getDefaultToolkit(); // static �޼��� ���� Ŭ���������� ������ �� �ִ�.
		// ��Ŷ�� �̹����� ���û��� ��ηκ��� ���� �� �ִ�.	
		// ��� ���� ������ �� :  �������ô� ������ os������ ����ϴ� ǥ���̹Ƿ�
		
		//Image img = kit.getImage(D:/workspace/js_workspace/images/bandi/bandi);
		//System.out.println("�̹��� �ּҰ��� "+img);
		
		// ȭ�鿡 ����ϴ� ������ ���� �Ұ�..��?
		// html������ ���ٿ��� ���������� �ڹٿ� ���� 
		// ������ ������(���� �׸��� �۾�)�� �ؾ� �Ѵ�.
	}
}
