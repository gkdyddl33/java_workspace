package review.animal;

import java.util.Random;
import java.util.Scanner;

public class Animal {
	public static String[] quizs = {
			"Q.���� �� ��������?\n"
					+ "1. ������ �������ϴ� ���� �ڿ��� ���Ѻþ��.\n"
					+ "2. �ƺ��� ��踦 �����ϼż� ��踦 ��ٵ�Ⱦ��\n"
					+ "3. �ҸӴϰ� ���� �����ż� ���͵�Ⱦ��\n"
					+ "4. ģ�� �峭���� �ָӴϿ� ��½�߾��",
					
					"Q.���� ���� 10�� �԰�, ö���� ���� 1�� �Ծ��� �����.\n"
					+ "����� ö���� ���� ���� ������?\n"
					+ "1. 10��"
					+ "2. 11��"
					+ "3. 12��"
					+ "4. 13��",
					
					"Q.���� �� ������ ������."
					+ "1. ��"
					+ "2. ��"
					+ "3. ��"
					+ "4. ��"
	};

	private static int[] answers;
	
	String name;
	int hp;
	String feed;
	int feedCnt;
	
	public Animal() {}

	public Animal(String name, int hp, String feed, int feedCnt) {
		super();
		this.name = name;
		this.hp = hp;
		this.feed = feed;
		this.feedCnt = feedCnt;
	}
	
	// �Ա�
	public boolean eat() {
		if(feedCnt < 1) {
			return false;
		}
		feedCnt--;
		hp++;
		return true;
	}
	
	// ��å
	public void walk() {
		if(hp>1)
		{
			hp--;
			Random r = new Random();
			int idx = r.nextInt(Animal.quizs.length);
			int choice = 0;
			int answer = Animal.answers[idx];
			
			System.out.println(Animal.quizs[idx]);
			choice = new Scanner(System.in).nextInt();
			
			if(answer == choice)
			{
				feedCnt++;
			}
			else
			{
				hp--;
				System.out.println("ü���� �� ���������. �н�.");
				sleep();
			}
		}
		else
		{
			System.out.println("���� �ڰ� ������.");
		}
	}
	
	public void sleep() {
		System.out.println("�ڴ� ��");
		for (int i = 0; i < 3; i++) {
			System.out.println(".");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			System.out.println();
			hp++;
		}
	}
}
