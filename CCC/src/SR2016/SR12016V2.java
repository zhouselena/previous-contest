package SR2016;

import java.util.Arrays;
import java.util.Scanner;

public class SR12016V2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String original = sc.nextLine();
		String anagram = sc.nextLine();
		
		sc.close();
		
		if (original.length()!=anagram.length()) {
			System.out.println("N");
		}
		else {
			char[] og = original.toCharArray();
			char[] ag = anagram.toCharArray();
			
			Arrays.sort(og);
			Arrays.sort(ag);
			
			boolean isCorrectLetters = true;
			int startInd = 0;
			int endInd1 = og.length-1;
			int endInd2 = og.length-1;
			
			while(startInd<=endInd2) {
				if (og[endInd1]==ag[endInd2]) {
					endInd1--;
					endInd2--;
				}
				else if (og[endInd1]!=ag[endInd2]) {
					if (ag[startInd]=='*') {
						startInd++;
						endInd1--;
					}
					else {
						isCorrectLetters = false;
						break;
					}
				}
			}
			
			if (isCorrectLetters) {
				System.out.println("A");
			}
			else {
				System.out.println("N");
			}
		}

	}

}
