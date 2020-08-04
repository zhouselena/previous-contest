package SR2014;

import java.util.Arrays;
import java.util.Scanner;

public class SR22014 {
	
	public static class Partner {
		public int name;
		public int partner;
		
		public Partner() {
			
		}
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int numbClass = sc.nextInt(); sc.nextLine();
		String[] first = sc.nextLine().split(" ");
		String[] second = sc.nextLine().split(" ");
		Partner[] partners = new Partner[numbClass];
		for (int i = 0; i < numbClass; i++) {
			partners[i] = new Partner();
			partners[i].name = i;
			partners[i].partner = Arrays.asList(first).indexOf(second[i]);
		}
		
		boolean good = true;
		
		for (int i = 0; i < numbClass; i++) {
			Partner p = partners[i];
			if (p.partner==p.name) {
				good = false;
				break;
			}
			Partner part = partners[p.partner];
			if (part.partner!=p.name) {
				good = false;
				break;
			}
		}
		
		if (good) {
			System.out.println("good");
		}
		else {
			System.out.println("bad");
		}
		
		sc.close();
	}

}
