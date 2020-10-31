package day1029.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;

public class CollectionApp {
	public void showList() {
		ArrayList<JButton> list = new ArrayList<>();
		list.add(new JButton("첫버튼"));
		list.add(new JButton("마지막 버튼"));
		
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			JButton bt1 = list.get(i);
			System.out.println(bt1.getText());
		}
		
		ArrayList<String> list2 = new ArrayList<>();
		list2.add("apple");
		list2.add("apple");
		list2.add("apple");
		System.out.println(list2.size());
	}
	public void showSet() {
		HashSet<String> set = new HashSet<>();
		set.add("banana");
		set.add("banana");
		set.add("banana");
		set.add("banana");
		System.out.println(set.size());
		
		HashSet<String> set2 = new HashSet<>();
		set2.add("바나나");
		set2.add("포도");
		set2.add("블루베리");
		
		// 순서가 없기 때문에 get으로 가져 올 수가 없다
		// 그래서 순서를 먼저 부여 해줘야 한다.
		Iterator<String> it = set2.iterator();
		while(it.hasNext()) {
			String e = it.next();
			System.out.println(e);
		}
		
	}
	public void showMap() {
		HashMap<String, String> map = new HashMap<>();
		map.put("k1", "장미");
		map.put("k2", "튤립");
		map.put("k3", "할미꽃");
		map.put("k3", "안개꽃");
		System.out.println(map.size());
		
		// 순서가 없다. 그리고 key값으로 value 를 구한다.
		// set은 iterator을 지원 하므로 key를 일렬로 늘어트리자.
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		
		while(it.hasNext()) {
			String key = it.next();	// 키 늘어트린거를 
			String value = map.get(key);	//값을 가지고 와줘
			System.out.println(value);
		}

	}
	public static void main(String[] args) {
		CollectionApp app = new CollectionApp();
		//app.showList();
		//app.showSet();
		app.showMap();
	}
}
