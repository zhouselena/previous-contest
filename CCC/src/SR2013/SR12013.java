package SR2013;

import java.util.HashMap;
import java.util.Scanner;

public class SR12013 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int input = Integer.parseInt(sc.nextLine());
		boolean found = false;
		
		String currentYear = "";
		while(!false) {
			input++;
			currentYear = Integer.toString(input);
			char[] currentDigits = currentYear.toCharArray();
			int[] current = new int[currentDigits.length];
			for (int i = 0; i < currentDigits.length; i++) {
				current[i] = currentDigits[i] - '0';
			}
			HashMap<Integer, Integer> exists = new HashMap<Integer, Integer>();
			found = true;
			for (int i = 0; i < current.length; i++) {
				if (exists.containsKey(current[i])) {
					found = false;
					break;
				}
				else {
					exists.put(current[i], 0);
				}
			}
			if (found) {
				System.out.println(currentYear);
				break;
			}
		}
		
	}

}
