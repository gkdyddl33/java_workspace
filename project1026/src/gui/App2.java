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
		
		// 버튼생성
		Button bt = new Button("나 버튼");
		FlowLayout flow = new FlowLayout();
		frame.setLayout(flow);
		frame.add(bt);
		
		// input type = "text"
		TextField tt = new TextField("회원정보",10);
		frame.add(tt);
		
		// checkbox
		Checkbox cc1 = new Checkbox("독서");
		Checkbox cc2 = new Checkbox("수영");
		Checkbox cc3 = new Checkbox("컴퓨터");
		frame.add(cc1);
		frame.add(cc2);
		frame.add(cc3);
		
		// textarea
		TextArea area = new TextArea(5,20);
		frame.add(area);
		
		// text
		Label la = new Label("회원가입 양식입니다.");
		frame.add(la);
		
		// image
		Toolkit kit = Toolkit.getDefaultToolkit();
		//Image img = kit.getImage(null);
	}
}
