package event;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MyItem2 implements ItemListener{

	@Override
	public void itemStateChanged(ItemEvent e) {
		System.out.println("선택했어?");
		
	}

}
