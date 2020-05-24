package SR2016;

import java.util.ArrayList;
import java.util.Scanner;

public class SR42016 {

	public ArrayList<Integer> riceBalls;
	
	public SR42016(int[] rice){
		this.riceBalls = new ArrayList<>();
		for (int ball: rice) {
			riceBalls.add(ball);
		}
	}
	
	private boolean combineBall() {
		for (int i = 0; i < riceBalls.size()-1; i++) {
			int current = riceBalls.get(i);
			int second = riceBalls.get(i+1);

			if (i<riceBalls.size()-2 && current==riceBalls.get(i+2)) {
				riceBalls.remove(i);
				riceBalls.remove(i);
				riceBalls.remove(i);
				riceBalls.add(i, current*2+second);
				return true;
			}
			else if (current==second) {
				riceBalls.remove(i);
				riceBalls.remove(i);
				riceBalls.add(i, current+second);
				return true;
			}
			
		}
		return false;
	}
	
	public int run() {
		
		boolean keepGoing = false;
		do {
			keepGoing = combineBall();
		} while (keepGoing);
		
		int max = 0;
		
		for (int i: riceBalls) {
			if (i > max) {
				max = i;
			}
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] riceBalls = new int[n];
		
		for (int i = 0; i < n; i++) {
			riceBalls[i] = sc.nextInt();
		}
		
		sc.close();

		SR42016 game = new SR42016(riceBalls);
		System.out.println(game.run());
		
	}

}
