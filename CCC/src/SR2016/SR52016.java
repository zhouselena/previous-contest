package SR2016;

import java.util.ArrayList;
import java.util.Scanner;

public class SR52016 {

	public int numbCells;
	public int numbGen;
	public int[] values;
	
	public SR52016(int cells, int gen, String string) {
		this.numbCells = cells;
		this.numbGen = gen;
		this.values = new int[numbCells];
		for (int i = 0; i < string.length(); i++) {
			values[i] = string.charAt(i)-'0';
		}
	}
	
	public void generation() {
		for (int i = 0; i < numbCells; i++) {
			
			int indBefore = ((i-1)+numbCells) % numbCells;
			int indAfter = ((i+1)-numbCells) % numbCells;
			
			/* 2 = 1 before, 0 now
			 * 3 = 0 before, 1 now */
			
			if ((values[indBefore]==1 || values[indBefore]==2) && (values[indAfter]==0 || values[indAfter]==3)) {
				if (values[i]==0) {
					values[i] = 3;
				}
			}
			else if ((values[indAfter]==1 || values[indAfter]==2) && (values[indBefore]==0 || values[indBefore]==3)) {
				if (values[i]==0) {
					values[i] = 3;
				}
			}
			else {
				if (values[i]==1) {
					values[i] = 2;
				}
			}
		}
	}
	
	public void reset() {
		for (int i = 0; i < numbCells; i++) {
			values[i] = values[i]%2;
		}
	}
	
	public String run() {
		for (int i = 0; i < numbGen; i++) {
			generation();
			reset();
		}
		return values.toString();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int cells = sc.nextInt();
		int gens = sc.nextInt();
		String line = sc.nextLine();
		
		SR52016 game = new SR52016(cells, gens, line);
		System.out.println(game.run());
		
		sc.close();
	}

}
