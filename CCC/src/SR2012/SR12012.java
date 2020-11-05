package SR2012;

import java.util.Scanner;

public class SR12012 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int jersey = sc.nextInt();
		int count = 0;
		int i = 1;
		
		while (i < 33) {
			
			int current = jersey;
			for (int j = 0; j < 3; j++) {
				current -= i;
			}
			
			if (current > 0)
				count++;
			else
				break;
			
			i++;
			
		}
		
		System.out.println(count);

		sc.close();
	}

}
