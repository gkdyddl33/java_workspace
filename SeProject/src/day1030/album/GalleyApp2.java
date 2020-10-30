package day1030.album;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GalleyApp2 extends JFrame{
	JPanel p_west;
	JPanel p_center;
	JScrollPane scroll;
	
	JLabel la_name;
	XCanvas can;
	
	JPanel p_south;
	JButton bt_prev;
	JButton bt_next;
	
	public GalleyApp2() {
		p_west = new JPanel();
		p_center = new JPanel();
		scroll = new JScrollPane(p_west);
		
		
	}
	public static void main(String[] args) {
		new GalleyApp2();
	}
}
