package SR2014;

import java.util.Scanner;
import java.util.Stack;

public class SR32014 {

	public int N = 0;
	public Stack<Integer> mountain = null;
	public Stack<Integer> branch = null;
	
	public SR32014(int n, int[] original) {
		this.N = n;
		this.mountain = new Stack<>();
		this.branch = new Stack<>();
		for (int add: original) {
			mountain.push(add);
		}
	}
	
	public boolean run() {
		
		int lowest = 1;
		int current = 0;
		
		while (lowest <= N) {
			
			if (!branch.isEmpty()) {
				current = branch.peek();
				if (current==lowest) {
					branch.pop();
					lowest++;
					continue;
				}
			}
			
			if (!mountain.isEmpty()) {
				current = mountain.pop();
				if (current==lowest) {
					lowest++;
				}
				else {
					branch.push(current);
				}
			}
			else {
				if (!branch.empty()) {
					return false;
				}
			}
			
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int numbRuns = Integer.parseInt(sc.nextLine());
		
		for (int run = 0; run < numbRuns; run++) {
			int total = Integer.parseInt(sc.nextLine());
			int[] cars = new int[total];
			
			for (int i = 0; i < total; i++) {
				cars[i] = Integer.parseInt(sc.nextLine());
			}
			
			SR32014 game = new SR32014(total, cars);
			boolean possible = game.run();
			
			if (possible) {
				System.out.println("Y");
			}
			else {
				System.out.println("N");
			}
		}
		
		sc.close();

	}

}
