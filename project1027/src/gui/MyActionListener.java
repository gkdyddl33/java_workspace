package gui;

import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener implements ActionListener{
	Button bt1;
	Button bt2;
	TextField t;
	
	public MyActionListener(Button bt1,Button bt2,TextField t) {
		this.bt1=bt1;	// 멤버변수를 지역변수로 사용하기 위해 this
		this.bt2=bt2;
		this.t=t;
	}
	@Override
	public void actionPerformed(ActionEvent e) {//오버라이딩
		// 이벤트를 일으킨 컴포넌트 반환!!
		Object obj = e.getSource();	// 상위객체로 하위객체를 가르킬수있음
		// 그래서 버튼에 의한 이벤트라면 버튼의 주소값을 Object형(상위 객체형)으로
		// 받게 되고, 버튼이 아니라 TextField에 의한 이벤트라면..위와 동일..
		if(obj==bt1) {
			System.out.println("bt1을 눌렀네요");
		}else if(obj==bt2) {
			System.out.println("bt2을 눌렀네요");
		}else if(obj==t) {
			System.out.println("t을 눌렀네요");
		}
		System.out.println(e);		
	}

}
