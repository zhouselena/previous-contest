package SR2012;

import java.util.Scanner;

public class SR52012 {

	public final int R;
	public final int C;
	public int[][] lab = null;
	
	public SR52012(int rows, int columns, int[][] cats) {
		
		this.R = rows;
		this.C = columns;
		this.lab = new int[rows+1][columns+1];
		
		//set all values as -1
		for (int i = 0; i < this.R+1; i++) {
			for (int j = 0; j < this.C+1; j++) {
				lab[i][j] = -1;
			}
		}
		
		//set values in first row and last column as 0
		for (int i = 0; i < this.R+1; i++) {
			lab[i][0] = 0;
		}
		for (int j = 0; j < this.C+1; j++) {
			lab[0][j] = 0;
		}
		
		//set cat values to 0
		for (int i = 0; i < cats.length; i++) {
			lab[cats[i][0]][cats[i][1]] = 0;
		}
		
		lab[1][1] = 1;
		
	}
	
	public int run() {
		
		for (int i = 1; i < this.R+1; i++) {
			for (int j = 1; j < this.C+1; j++) {
				if (lab[i][j] == -1) {
					lab[i][j] = lab[i-1][j] + lab[i][j-1];
				}
			}
		}
		
		return lab[this.R][this.C];
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int rows = sc.nextInt();
		int columns = sc.nextInt();
		sc.nextLine();
		int numbCats = Integer.parseInt(sc.nextLine());
		int[][] catCoords = new int[numbCats][2];
		for (int i = 0; i < numbCats; i++) {
			catCoords[i][0] = sc.nextInt();
			catCoords[i][1] = sc.nextInt();
			sc.nextLine();
		}
		
		SR52012 game = new SR52012(rows, columns, catCoords);
		System.out.println(game.run());
		
		sc.close();
		
	}

}
