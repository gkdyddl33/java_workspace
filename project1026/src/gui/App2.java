package gui;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;

public class App2 {
	public static void main(String[] args) {
		Frame frame = new Frame();
		frame.setVisible(true);
		frame.setSize(300,400);
		
		// ��ư����
		Button bt = new Button("�� ��ư");
		FlowLayout flow = new FlowLayout();
		frame.setLayout(flow);
		frame.add(bt);
		
		// input type = "text"
		TextField tt = new TextField("ȸ������",10);
		frame.add(tt);
		
		// checkbox
		Checkbox cc1 = new Checkbox("����");
		Checkbox cc2 = new Checkbox("����");
		Checkbox cc3 = new Checkbox("��ǻ��");
		frame.add(cc1);
		frame.add(cc2);
		frame.add(cc3);
		
		// textarea
		TextArea area = new TextArea(5,20);
		frame.add(area);
		
		// text
		Label la = new Label("ȸ������ ����Դϴ�.");
		frame.add(la);
		
		// image
		Toolkit kit = Toolkit.getDefaultToolkit();
		//Image img = kit.getImage(null);
	}
}
