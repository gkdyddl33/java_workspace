package day1028.gui;

public abstract class Shape {
	int x;
	int y;

	public abstract void draw();

	public void setAnchor(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public static void main(String[] args) {
		 Shape s = new Circle(); // O
		 s.setAnchor(10,10);
		 s.draw();

	}
}
