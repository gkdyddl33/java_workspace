package day1027.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*Action은 범위가 넓다..(버튼에는 클릭, 텍스트박스-엔터 등)*/
/*버튼에 클릭이벤트를 감지해보자!*/
public class MyActionListener implements ActionListener {
	JTextField t_input;
	JTextArea area;

	public MyActionListener(JTextField t_input, JTextArea area) {// 생성자 함수에서 매개변수를 받자.
		this.t_input = t_input;
		this.area = area; // 멤버변수라는것을 구분하기위해 this
							// this를 안붙이면 지역변수가 된다.
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println("나 눌렀어?");		

		String msg = t_input.getText(); // 텍스트필드값구하기
		area.append(msg + "\n");
		t_input.setText("");

	}
}
