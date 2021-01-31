package SR2011;

import java.util.Scanner;

public class SR42011 {
	
	public static int maxPatients(int[] bloodUnits, int[] pats) {
		
		int count = 0;
		
		// O blood
		count += Math.min(bloodUnits[0], pats[0]);
		if (bloodUnits[0] > pats[0])
			bloodUnits[0] -= pats[0];
		else bloodUnits[0] = 0;
		
		// A blood
		count += Math.min(bloodUnits[0], pats[1]);
		if (bloodUnits[0] > pats[1])
			bloodUnits[0] -= pats[1];
		else bloodUnits[0] = 0;
		count += Math.min(bloodUnits[1], pats[1]);
		if (bloodUnits[1] > pats[1])
			bloodUnits[1] -= pats[1];
		else bloodUnits[1] = 0;
		
		
		// B blood
		count += Math.min(bloodUnits[0], pats[2]);
		if (bloodUnits[0] > pats[2])
			bloodUnits[0] -= pats[2];
		else bloodUnits[0] = 0;
		count += Math.min(bloodUnits[2], pats[2]);
		if (bloodUnits[2] > pats[2])
			bloodUnits[2] -= pats[2];
		else bloodUnits[2] = 0;
		
		// AB blood
		count += Math.min(bloodUnits[0], pats[3]);
		if (bloodUnits[0] > pats[3])
			bloodUnits[0] -= pats[3];
		else bloodUnits[0] = 0;
		count += Math.min(bloodUnits[1], pats[3]);
		if (bloodUnits[1] > pats[3])
			bloodUnits[1] -= pats[3];
		else bloodUnits[1] = 0;
		count += Math.min(bloodUnits[2], pats[3]);
		if (bloodUnits[2] > pats[3])
			bloodUnits[2] -= pats[3];
		else bloodUnits[2] = 0;
		
		return count;
		
	}
	
	public static void main(String[] args) {
		
		/* Type O -> Type O
		 * Type A -> Type O or Type A
		 * Type B -> Type O or Type B
		 * Type AB -> any type
		 * Negative -> Negative
		 * Positive -> Negative or Positive
		 */
		
		int[] NegativeBloodUnits = new int[4];
		int[] PositiveBloodUnits = new int[4];
		int[] NegativePatients = new int[4];
		int[] PositivePatients = new int[4];
		
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 4; i++) {
			NegativeBloodUnits[i] = sc.nextInt();
			PositiveBloodUnits[i] = sc.nextInt();
		}
		for (int i = 0; i < 4; i++) {
			NegativePatients[i] = sc.nextInt();
			PositivePatients[i] = sc.nextInt();
		}
		
		int count = 0;
		count += maxPatients(NegativeBloodUnits, NegativePatients);
		
		for (int i = 0; i < 4; i++) {
			PositiveBloodUnits[i] += NegativeBloodUnits[i];
		}
		count += maxPatients(PositiveBloodUnits, PositivePatients);
		
		System.out.println(count);
		
	}

}
