package SR2012;

import java.util.Scanner;

public class SR12012 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int jersey = sc.nextInt();
		
		if (jersey <= 7) {
			if (jersey == 7)
				System.out.println(20);
			else if (jersey == 6)
				System.out.println(10);
			else if (jersey == 5)
				System.out.println(4);
			else if (jersey == 4)
				System.out.println(1);
			else
				System.out.println(0);
		}
		else {
			int count = (jersey-1)*(jersey-2)*(jersey-3);
			count /= 6;
			System.out.println(count);
		}
		
		sc.close();
	}

}
