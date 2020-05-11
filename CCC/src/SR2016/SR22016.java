package SR2016;

import java.util.Arrays;
import java.util.Scanner;

public class SR22016 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int prob = sc.nextInt();
		int numbCit = sc.nextInt();
		int[] Acity = new int[numbCit];
		int[] Bcity = new int[numbCit];
		for (int i = 0; i < numbCit; i++) {
			Acity[i] = sc.nextInt();
		}
		sc.nextLine();
		for (int i = 0; i < numbCit; i++) {
			Bcity[i] = sc.nextInt();
		}
		
		sc.close();
		
		Arrays.sort(Acity);
		Arrays.sort(Bcity);
		
		if (prob==1) {
			
			int speed = 0;
			
			for (int i = 0; i < numbCit; i++) {
				if (Acity[i] >= Bcity[i]) {
					speed += Acity[i];
				}
				else {
					speed += Bcity[i];
				}
			}
			
			System.out.println(speed);
		}
		
		else {
			
			int speed = 0;
			
			int endInd1 = numbCit-1;
			int endInd2 = numbCit-1;
			int count = 0;
			
			while (count < numbCit) {
				if (Acity[endInd1] >= Bcity[endInd2]) {
					speed += Acity[endInd1];
					endInd1--;
				}
				else {
					speed += Bcity[endInd2];
					endInd2--;
				}
				count++;
			}
			
			System.out.println(speed);
		}
	}

}
