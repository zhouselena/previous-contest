package SR2016;

import java.util.Scanner;

public class SR52016V2 {

	public int numbCells;
	public long numbGen;
	public int[] values;
	
	public SR52016V2(int cells, long gen, String string) {
		this.numbCells = cells;
		this.numbGen = gen;
		this.values = new int[numbCells];
		for (int i = 0; i < string.length(); i++) {
			values[i] = string.charAt(i)-'0';
		}
	}
	
	private int[] reset(int[] cells) {
		for (int i = 0; i < numbCells; i++) {
			cells[i] = cells[i]%2;
		}
		return cells;
	}
	
	public int[] calculateTwoGen(int[] zero, int gens) {
		
		for (int i = 0; i < numbCells; i++) {
			
			int indBefore = (i-gens)%numbCells;
			if (indBefore<0) {
				indBefore += numbCells;
			}
			int indAfter = (i+gens)%numbCells;
			
			/* 2 = 1 before, 0 now
			 * 3 = 0 before, 1 now */
			
			int valueBefore = zero[indBefore]%2;
			int valueAfter = zero[indAfter]%2;
			
			int now = valueBefore^valueAfter;
			
			if (zero[i]==0 && now==1) {
				zero[i] = 3;
			}
			else if (zero[i]==1 && now==0) {
				zero[i] = 2;
			}
		}
		
		return reset(zero);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int cells = sc.nextInt();
		int gens = sc.nextInt();
		sc.nextLine();
		String line = sc.nextLine();
		
		SR52016V2 game = new SR52016V2(cells, gens, line);
		sc.close();
	}

}
