package day1029.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;

/*
 *	Collection Framework
 *	: 전제 조건은 객체만을 대상으로 한다.
 *
 *	- 자바 언어에서는 객체를 모아서 처리할 때 api를 지원하는데, 이 api를 가리켜 
 *	컬렉션 프레임웍 이라고 한다.
 *	그리고 java.util 에서 지원한다.
 *
 *	- 컬렉션 프레임웍에서 지원하는 객체의 수는 상당히 많기는 하지만, 크게는 모여진 모습에 따라서
 *	(1) 순서잇는 유형 List형 : 배열과 거의 같다. [] []
 *										자바의 배열과 차이가 있다면? 
 *										배열은 1) 반드시 생성할때 크기를 명시해야 하므로 동적으로 늘어날 수 없다(고정적)
 *												 2) 자료형을 섞어 사용할 수 없다.
 *													ex. int[] arr = new int[5]; // 오직 int형만 
 *										List는 크기가 자유롭다. 객체 자료형을 섞어서 넣을 수 있다.
 *	(2) 순서없는 유형 Set형 :
 *	(3) key-value 유형 Map형 :  
 */
public class CollectionApp {
	
	private void showList() {
		// List형 - 최상위 객체인 List는 인터페이스 이며, List가 기본적으로 가져야 할 
		// 추상메서드가 명시되어 있다.
		// ex. ArrayList, Stack, Vector
		// 리스트 구조들은 배열과 거의 비슷하다. = js의 배열과 동일) 동작방식
		// <>은 Generic Type으로 선언하면, 컬렉션 프레임웍에 넣을 수 있는 자료형을 제한할 수 있다.		
		// <> 특정요소를 제한할 수 있다 자료형을..
		ArrayList<JButton> list = new ArrayList<JButton>();
		list.add(new JButton("첫버튼"));
//		list.add("사과");
//		list.add("복숭아");
//		list.add("멜론");
		list.add(new JButton("마지막버튼"));
		System.out.println("리스트의 데이터 수는 "+list.size()); // 컬렉션프레임웍은 모두 size()를 사용
		
		// 섞어서 들어가므로, get(반환해준다)은 예측할 수 없기 떄문에 Object형이 반환해준다.
		for (int i = 0; i < list.size(); i++) {
			JButton bt1 = list.get(i);
			System.out.println(bt1.getText());
		}
		
		// List는 중복된 데이터를 허용할까?
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("apple");
		list2.add("apple");
		list2.add("apple");
		System.out.println("list2의 데이터 수는 "+list2.size()); // 3
		// -> 결론 : 중복여부는 따지지 않고 다 넣는다.
	}
	private void showset() {
		HashSet<String> set = new HashSet<String>();
		set.add("banana");
		set.add("banana");
		set.add("banana");
		set.add("banana");
		System.out.println("Hashset의 크기는 "+set.size());
		// -> 결론 : List와 다르게 똑똑하다. 중복된 데이터를 받아들이지 않는다.

		HashSet<String> set2 = new HashSet<String>();
		set2.add("바나나");
		set2.add("포도");
		set2.add("블루베리");
		
		// set은 순서가 x 때문에 순서를 부여해줘야 된다.(iterator반복자)
		Iterator<String> it = set2.iterator();
		while(it.hasNext()) {// 순서를 부여했니? 그럼 안에 있는지 판단해 줄게!
			// next() 로는 내용을 뽑아내는 것 = 요소 반환
			String e = it.next();
			System.out.println(e);
		}
		
	}
	private void showmap() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("k1", "장미");
		map.put("k2", "튤립");
		map.put("k3", "안개꽃");
		map.put("k4", "안개꽃");
		System.out.println("map의 길이는 "+map.size());
		// -> 결론 : key값은 중복을 허용하지 않는다. 하지만, 값은 중복이 가능
		
		// 반복문을 이용해서 모든 꽃을 출력하기
		Set<String> keySet = map.keySet();	// key만 따로 모으자 그래야  value를 구한다.
																// 그리고 Set은 Iterator를 지원하므로 key를 일렬로 늘어뜨리자.
		Iterator<String> keyIter = keySet.iterator();
		
		while(keyIter.hasNext()) {
			String key = keyIter.next();	// <>을 사용하지 않으면 (String)으로 형변환 해줘야함
			String value = map.get(key);	// 데이터 반환!!
			System.out.println(value);
		}
	}
	public static void main(String[] args) {
		CollectionApp app = new CollectionApp();
		app.showList();
		app.showset();
		app.showmap();
	}
}
