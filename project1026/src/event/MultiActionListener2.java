package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiActionListener2 implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e);
		
		// 이벤트 객체의 메서드 중에, 이벤트를 일으킨 컴포넌트(이벤트)를 추출하는 메서드
		// 이벤트를 일으킨 것을 잡아내는 메서드
		if(e.getSource()) {
			
		}
	}

}
