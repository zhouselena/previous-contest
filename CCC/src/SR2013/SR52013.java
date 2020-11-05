package SR2013;

import java.util.Scanner;

public class SR52013 {

	public int result = 0;
	
	public SR52013(int n) {
		this.result = n;
	}
	
	public int findLargestFactor(int a) {
		
		int largest = (int) Math.sqrt(a);
		int min = 1;
		for (int i = largest; i > 1; i--) {
			if (a%i == 0) {
				min = a/i;
			}
		}
				
		return min;
		
	}
	
	public int run() {
		
		int current = this.result;
		int cost = 0;
		
		while (current > 1) {
			int del = findLargestFactor(current);
			current = current - del;
			cost += current/del;
		}
		
		return cost;
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int numb = sc.nextInt();
		SR52013 game = new SR52013(numb);
		
		System.out.println(game.run());
		
	}

}
