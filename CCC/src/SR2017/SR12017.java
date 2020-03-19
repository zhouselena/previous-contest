package SR2017;

import java.util.Scanner;

public class SR12017 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int runs = sc.nextInt();
		int[] swift = new int[runs];
		int[] sema = new int[runs];
		for (int i = 0; i < runs; i++) {
			swift[i] = sc.nextInt();
		}
		for (int i = 0; i < runs; i++) {
			sema[i] = sc.nextInt();
		}
		
		int swiftTotal = 0;
		int semaTotal = 0;
		int afterDays = 0;
		
		for (int i = 0; i < runs; i++) {
			swiftTotal += swift[i];
			semaTotal += sema[i];
			if (swiftTotal==semaTotal)
				afterDays = i+1;
		}
		
		System.out.println(afterDays);
		
		sc.close();
	}

}
