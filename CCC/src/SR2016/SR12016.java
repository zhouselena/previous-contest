package SR2016;

import java.util.ArrayList;
import java.util.Scanner;

public class SR12016 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String original = sc.nextLine();
		String anagram = sc.nextLine();
		
		sc.close();
		
		if (original.length() != anagram.length()) {
			System.out.println("N");
		}
		else {
			ArrayList<Character> og = new ArrayList<Character>();
			
			for (int i = 0; i < original.length(); i++) {
				og.add(original.charAt(i));
			}
			
			boolean hasStar = false;
			boolean correctLetters = true;
			int numbStar = 0;
			
			for (int i = 0; i < anagram.length(); i++) {
				char temp = anagram.charAt(i);
				if (temp=='*') {
					if (!hasStar)
						hasStar = true;
					numbStar++;
				}
				else {
					if (og.contains(temp)) {
						og.remove((Character)(temp));
					}
					else {
						correctLetters = false;
						break;
					}
						
				}
			}
			
			if (!hasStar || !correctLetters) {
				System.out.println("N");
			}
			else if (numbStar != og.size()) {
				System.out.println("N");
			}
			else {
				System.out.println("A");
			}
		}

	}

}
