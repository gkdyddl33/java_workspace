package day1103.thread;

public class ThreadTask implements Runnable{
	private String animal;
	private String sound;

	
	public ThreadTask(String animal, String sound) {
		super();
		this.animal = animal;
		this.sound = sound;
	}

	public ThreadTask(String string) {
		;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(animal);
			System.out.println(sound);
			
			try{
				Thread.sleep(1000);}
			catch(InterruptedException e){
				;
			}
		}
	}
	
}
