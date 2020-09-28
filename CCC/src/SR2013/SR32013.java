package SR2013;

import java.util.Scanner;

public class SR32013 {

	public class Outcome {
		
		public int winner = 0;
		
		
	}
	
	public int favourite;
	public int numbPlayed;
	public int[][] gamesPlayed;
	
	public SR32013(int f, int n, int[][] g) {
		this.favourite = f;
		this.numbPlayed = n;
		this.gamesPlayed = g;
	}
	
	public void run() {
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int fav = Integer.parseInt(sc.nextLine())-1;
		int numbPlayed = Integer.parseInt(sc.nextLine());
		int[][] gamesPlayed = new int[numbPlayed][4];
		for (int i = 0; i < numbPlayed; i++) {
			gamesPlayed[i][0] = sc.nextInt();
			gamesPlayed[i][1] = sc.nextInt();
			gamesPlayed[i][2] = sc.nextInt();
			gamesPlayed[i][3] = sc.nextInt();
			sc.nextLine();
		}
		
		SR32013 game = new SR32013(fav, numbPlayed, gamesPlayed);
		game.run();
		
		sc.close();
		
	}

}
