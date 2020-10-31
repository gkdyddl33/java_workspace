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
		list.add(new JButton("ù��ư"));
		list.add(new JButton("��������ư"));
		System.out.println("����Ʈ�� ������ ���� "+list.size());
		
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
		set2.add("�ٳ���");
		set2.add("����");
		set2.add("���纣��");
		
		// ���� X -> �����ο� Iterator()
		Iterator<String> it = set2.iterator();
		while(it.hasNext()) {
			String e = it.next();
			System.out.println(e);
		}

	}
	private void showmMap() {
		HashMap<String,String> map = new HashMap<>();
		map.put("k1", "ƫ��");
		map.put("k2", "�帶");
		map.put("k3", "�Ȱ���");
		map.put("k4", "�ҹ̲�");
		
		// Ű���� ������ ���� ������ �� �ִ�.
		Set<String> keySet = map.keySet(); // Ű ����
		Iterator<String> keyIter = keySet.iterator(); // Ű ������ �����ο�
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