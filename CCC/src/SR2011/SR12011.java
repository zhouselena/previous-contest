package SR2011;

import java.util.Scanner;

public class SR12011 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int numbLines = Integer.parseInt(sc.nextLine());
		int Scount = 0;
		int Tcount = 0;
		
		for (int i = 0; i < numbLines; i++) {
			
			String text = sc.nextLine();
			for (char now: text.toCharArray()) {
				if (now == 't' || now == 'T') {
					Tcount ++;
				}
				else if (now == 's' || now == 'S') {
					Scount ++;
				}
			}
			
		}
		
		if (Scount >= Tcount) {
			System.out.println("French");
		}
		else {
			System.out.println("English");
		}
		

	}
	
}
