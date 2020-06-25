package SR2015;

import java.util.Scanner;
import java.util.Stack;

public class SR12015 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Stack<Integer> numbs = new Stack<Integer>();
		int n = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < n; i++) {
			int numb = Integer.parseInt(sc.nextLine());
			if (numb==0) {
				numbs.pop();
			}
			else {
				numbs.push(numb);
			}
		}
		
		int sum = 0;
		for (int i: numbs) {
			sum += i;
		}
		System.out.println(sum);
	}

}
