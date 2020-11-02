package review.animal;

import java.util.Random;
import java.util.Scanner;

public class Animal {
	public static String[] quizs = {
			"Q.다음 중 착한일은?\n"
					+ "1. 엄마가 설거지하는 것을 뒤에서 지켜봤어요.\n"
					+ "2. 아빠가 담배를 좋아하셔서 담배를 사다드렸어요\n"
					+ "3. 할머니가 짐이 많으셔서 도와드렸어요\n"
					+ "4. 친구 장난감을 주머니에 슬쩍했어요",
					
					"Q.영희가 빵을 10개 먹고, 철수는 빵을 1개 먹었다 뱉었다.\n"
					+ "영희와 철수가 먹은 빵의 개수는?\n"
					+ "1. 10개"
					+ "2. 11개"
					+ "3. 12개"
					+ "4. 13개",
					
					"Q.다음 중 자음을 고르세요."
					+ "1. ㅐ"
					+ "2. ㅚ"
					+ "3. ㅥ"
					+ "4. ㅛ"
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
	
	// 먹기
	public boolean eat() {
		if(feedCnt < 1) {
			return false;
		}
		feedCnt--;
		hp++;
		return true;
	}
	
	// 산책
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
				System.out.println("체력이 다 떨어졌어요. 털썩.");
				sleep();
			}
		}
		else
		{
			System.out.println("잠을 자고 오세요.");
		}
	}
	
	public void sleep() {
		System.out.println("자는 중");
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
