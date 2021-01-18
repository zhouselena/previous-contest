package SR2011;

import java.util.Scanner;

public class SR32011 {
	
	public final int Levels;
	public final int Dimensions;
	public final int X;
	public final int Y;
	public final int[][] fivebyfive;
	
	public SR32011 (int l, int x, int y) {
		this.Levels = l;
		this.X = x;
		this.Y = y;
		this.fivebyfive = new int [][] {
						{-1, 1, 1, 1, -1}, 
						{-1, 0, 1, 0, -1},
						{-1, -1, 0, -1, -1},
						{-1, -1, -1, -1, -1},
						{-1, -1, -1, -1, -1}};
		int total = 1;
		for (int i = 0; i < l; i++) {
			total = total*5;
		}
		this.Dimensions = total;
	}
	
	public boolean run(int x, int y, int d) {
		
		int fivex = (x) / (d/5);
		int fivey = (y) / (d/5);
		
		if (fivebyfive[fivey][fivex] == 1) {
			return true;
		}
		else if (fivebyfive[fivey][fivex] == -1) {
			return false;
		}
		else {
			if (d==5) return false;
			return false || run(x%(d/5), y%(d/5), d/5);
		}
		
		
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int numbGames = Integer.parseInt(sc.nextLine());
		
		for (int i = 0; i < numbGames; i++) {
			SR32011 game = new SR32011(sc.nextInt(), sc.nextInt(), sc.nextInt());
			if (game.run(game.X, game.Y, game.Dimensions)) {
				System.out.println("crystal");
			}
			else {
				System.out.println("empty");
			}
			sc.nextLine();
		}
		
		sc.close();
	}

}
