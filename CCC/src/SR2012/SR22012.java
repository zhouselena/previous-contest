package SR2012;

import java.util.HashMap;
import java.util.Scanner;

public class SR22012 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String input = sc.nextLine();
		HashMap<Character, Integer> romanNums = new HashMap<>();
		romanNums.put('I', 1);
		romanNums.put('V', 5);
		romanNums.put('X', 10);
		romanNums.put('L', 50);
		romanNums.put('C', 100);
		romanNums.put('D', 500);
		romanNums.put('M', 1000);
		
		int total = 0;
		int prevRBase = 0;

		for (int i = input.length()-1; i > 0; i-=2) {
			int a = input.charAt(i-1) - '0';
			int r = romanNums.get(input.charAt(i));
			if (prevRBase > r) {
				total -= a*r;
			}
			else {
				total += a*r;
			}
			prevRBase = r;
		}
		
		System.out.println(total);
		
		sc.close();

	}

}
