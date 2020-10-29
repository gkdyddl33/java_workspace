package day1029.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;

public class CollectionApp2 {
	private void showList() {
		ArrayList<JButton> list = new ArrayList<JButton>();
		list.add(new JButton("첫버튼"));
		list.add(new JButton("마지막버튼"));
		System.out.println("리스트의 데이터 수는 "+list.size());
		
		for (int i = 0; i < list.size(); i++) {
			JButton bt1 = list.get(i);
			System.out.println(bt1.getText());
		}
		
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("apple");
		list2.add("apple");
		list2.add("apple");
		System.out.println(list2.size());
	}
	private void showSet() {
		HashSet<String> set = new HashSet<String>();
		set.add("banana");
		set.add("banana");
		set.add("banana");
		set.add("banana");
		System.out.println(set.size());
		
		HashSet<String> set2 = new HashSet<>();
		set2.add("바나나");
		set2.add("포도");
		set2.add("블루베리");
		
		// 순서 X -> 순서부여 Iterator()
		Iterator<String> it = set2.iterator();
		while(it.hasNext()) {
			String e = it.next();
			System.out.println(e);
		}

	}
	private void showmMap() {
		HashMap<String,String> map = new HashMap<>();
		map.put("k1", "튤립");
		map.put("k2", "장마");
		map.put("k3", "안개꽃");
		map.put("k4", "할미꽃");
		
		// 키값을 가지고 값을 가져올 수 있다.
		Set<String> keySet = map.keySet(); // 키 모음
		Iterator<String> keyIter = keySet.iterator(); // 키 모음에 순서부여
		while(keyIter.hasNext()) {
			String key = keyIter.next();
			String value = map.get(key);
			System.out.println(value);
		}

	}
	public static void main(String[] args) {
		CollectionApp2 app = new CollectionApp2();
		app.showList();
		app.showSet();
		app.showmMap();
	}
}
