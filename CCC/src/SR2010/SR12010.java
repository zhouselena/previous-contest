package SR2010;

import java.util.Scanner;

public class SR12010 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = Integer.parseInt(sc.nextLine());
		String[][] rawInfo = new String[n][4];
		for (int i = 0; i < n; i++) {
			rawInfo[i] = sc.nextLine().split(" ");
		}
		
		String maxName = ""; int max = 0;
		String max2Name = ""; int max2 = 0;
		for (int i = 0; i < n; i++) {
			String curr = rawInfo[i][0];
			int R = Integer.parseInt(rawInfo[i][1]);
			int S = Integer.parseInt(rawInfo[i][2]);
			int D = Integer.parseInt(rawInfo[i][3]);
			int val = 2*R + 3*S + D;
			if (val > max) {
				max2 = max;
				max2Name = maxName;
				max = val;
				maxName = curr;
			}
			else if (val == max) {
				if (maxName.charAt(0) > curr.charAt(0)) {
					max2 = max;
					max2Name = maxName;
					max = val;
					maxName = curr;
				}
				else {
					max2 = val;
					max2Name = curr;
				}
			}
			else if (val > max2) {
				max2 = val;
				max2Name = curr;
			}
		}
		
		System.out.println(maxName);
		System.out.println(max2Name);
		
	}

}
