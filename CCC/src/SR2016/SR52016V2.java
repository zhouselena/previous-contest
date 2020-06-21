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
			if (cells[i]==2) {
				cells[i] = 1;
			}
			else if (cells[i]==3) {
				cells[i] = 0;
			}
		}
		return cells;
	}
	
	public int[] calculateTwoGen(int[] zero, long gens) {
		
		for (int i = 0; i < numbCells; i++) {
			
			long indBefore = (i-gens)%numbCells;
			if (indBefore<0) {
				indBefore += numbCells;
			}
			long indAfter = (i+gens)%numbCells;
			
			/* 2 = 0 before, 1 now
			 * 3 = 1 before, 0 now */
			
			int valueBefore = zero[(int)indBefore]%2;
			int valueAfter = zero[(int)indAfter]%2;
			
			int now = valueBefore^valueAfter;
			
			if (zero[i]==0 && now==1) {
				zero[i] = 2;
			}
			else if (zero[i]==1 && now==0) {
				zero[i] = 3;
			}
		}
		
		return reset(zero);
	}
	
	public int[] run() {
		
		int[] usingCells = this.values;
		
		for (int i = 62; i >=0; i--) {
			if ((numbGen & ((long)1<<(long)i))!=0) {
				usingCells = calculateTwoGen(usingCells, 1l<<i);
			}
		}
		
		return usingCells;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int cells = sc.nextInt();
		long gens = sc.nextLong();
		sc.nextLine();
		String line = sc.nextLine();
		
		SR52016V2 game = new SR52016V2(cells, gens, line);
		int[] print = game.run();
		for (int i: print) {
			System.out.print(i);
		}
		sc.close();
	}

}
