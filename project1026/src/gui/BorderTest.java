package gui;

import java.awt.Button;
import java.awt.Frame;
import java.awt.BorderLayout;

/*
 *	각종 컴포넌트를 올려놓기 전에, 어떤 방식으로 올려놓을지를 결정하는 것을 배치라 한다.
 *	AWT 에서 지원하는 배치는 다음과 같이 5가지 유형이 있다.
 *	1) FlowLayout : 행방향으로 늘어서는 배치방식, 만일 공간이 부족하면 다음 행으로 내려간다.
 *					컨테이너의 크기에 따라 흐르듯 배치가 되므로 flow라는 뜻이 부여
 *	2) BorderLayout : 북,동,서,남,중앙이라는 방위를 갖는 배치 방법
 *	3) GridLayout : 격자 즉 바둑판 모양의 배치방법
 *	-> 사실상 위에 1,2,3 만으로 충분히 개발 가능함
 *	4) CardLayout : 카드처럼 하나의 카드가 보일 때는 다른 카드가 안보이는 방식의 배치
 *	5) GridBagLayout : GridLayout을 픽셀단위로 상세하게 제어할 수 있는 배치방식
 *	6) Frame
 */
public class BorderTest {
	public static void main(String[] args) {
		Frame frame = new Frame();
		
		// BorderLayout 학습
		Button bt_north = new Button("North");
		Button bt_south = new Button("south");
		Button bt_west = new Button("west");
		Button bt_east = new Button("east");
		Button bt_center = new Button("center");
		
		// 배치 방법을 결정하자.
		frame.setLayout(new BorderLayout());
		
		//버튼을 프레임에 붙이기
		frame.add(bt_north,BorderLayout.NORTH); // 북쪽에 버튼 부착
		frame.add(bt_south,BorderLayout.SOUTH); // 남쪽에 버튼 부착
		frame.add(bt_west,BorderLayout.WEST); // 서쪽에 버튼 부착
		frame.add(bt_east,BorderLayout.EAST); // 동쪽에 버튼 부착
		// CENTER는 명시하지 않아도 디폴트이기 때문에 생략해도 적용된다.
		frame.add(bt_center,BorderLayout.CENTER); // 가운데에 버튼 부착		
		
		// 프레임 세팅
		frame.setSize(250, 200);
		frame.setVisible(true);
	}
}
