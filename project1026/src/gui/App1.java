package gui;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame; // 위치 등록!
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
		/* 난생처음 보는 클래스를 만났을 때 대처법
		 * 대처자세1) 자바의 모든 객체는 결국 3가지 유형밖에 없다.(
		 * 대처자세2) 클래스는 쓰라고 만든것이다. 따라서 메모리에 올리는 법을 알면 된다.
		 * 
		 * 1) 일반클래스 : new 하면된다. new 뒤의 생성자조사(api문서를 통해)
		 * 2) 추상클래스 : new 불가, 자식을 정의해서 new 하거나 이미 구현한 인스턴스를 이용(api문서를 통해)
		 * 3) 인터페이스 : new 불가, 자식을 정의해서 new 하거나 이미 구현한 인스턴스를 이용(api문서를 통해)
		 */
		
		/*윈도우 생성 - 일반이기 때문에 new 다음 생성자를 조사해서 사용*/
		Frame frame =  new Frame();
		
		// 프레임은 디폴트가 눈에 보이지 않는 상태임. 따라서 메소드를 호출해야 한다.
		
		frame.setVisible(true); // window 객체로부터 상속받은 메서드
								// 매개변수로는 논리값을 사용할 수 있다.
		frame.setSize(300, 400); // 윈도우의 너비와 넓이를 지정
		
		/*윈도우 안에 배치할 각종 컴포넌트를 올려놓아보자.*/
		// 버튼 Button(일반,추상,인터페이스 조사 후 일반 new 생성자 조사)
		Button bt = new Button("나 버튼"); // 버튼을 윈도우 컨테이너에 부착하자.
		// 버튼을 부착하기 전에 레이아웃 스타일을 지정해야 한다.
		FlowLayout flow = new FlowLayout(); // 원래 행으로 표현 줄이고 늘리면 안에 내용물이 흘러다님
		frame.setLayout(flow);
		
		frame.add(bt); // add메서드의 매개변수는 Component자료형이므로,
					   // Button도 Component의 자식이기 때문에 같은 자료형에 해당하여 인수로 올 수있다.
		
		 // html 에서의 input type = "text"는 자바에서 textField라 한다.
		TextField tf = new TextField("회원정보",10);
		frame.add(tf);
		// Checkbox
		Checkbox cb1 = new Checkbox("독서");
		Checkbox cb2 = new Checkbox("수영");
		Checkbox cb3 = new Checkbox("컴퓨터");
		frame.add(cb1);
		frame.add(cb2);
		frame.add(cb3);
		
		// TextArea
		TextArea ta = new TextArea(5,20);
		frame.add(ta);
		
		// 그냥텍스트 
		Label la = new Label("회원가입 양식입니다.");
		frame.add(la);
		
		// 이미지 넣기
		// Image 는 추상클래스 이며, 플랫폼(os)이 지정한 방식으로 얻을 수 있다.
		// 플랫폼에 맞게 가져오려면, DefaultToolkit 클래스로 부터 자원을 얻어야 한다.
		Toolkit kit = Toolkit.getDefaultToolkit(); // static 메서드 따라서 클래스명으로 접근할 수 있다.
		// 툴킷은 이미지를 로컬상의 경로로부터 얻어올 수 있다.	
		// 경로 사용시 주의할 점 :  역슬래시는 윈도우 os에서만 사용하는 표기이므로
		
		//Image img = kit.getImage(D:/workspace/js_workspace/images/bandi/bandi);
		//System.out.println("이미지 주소값은 "+img);
		
		// 화면에 출력하는 수업은 오늘 불가..왜?
		// html문서에 덧붙여서 구현했으나 자바와 같은 
		// 언어에서는 랭더링(직접 그리는 작업)을 해야 한다.
	}
}
