package SR2014;

import java.util.Scanner;

public class SR42014 {
	
	int numbGlasses = 0;
	int threshold = 0;
	int[][] glasses = null;
	
	int maxY;
	int maxX;
	int[][] window = null;
	
	public SR42014(int N, int t) {
		this.numbGlasses = N;
		this.threshold = t;
		this.glasses = new int[numbGlasses][5];
		maxY = 0;
		maxX = 0;
	}
	
	public void addGlass(int index, int x1, int y1, int x2, int y2, int tint) {
		glasses[index][0] = x1;
		glasses[index][1] = y1;
		glasses[index][2] = x2;
		glasses[index][3] = y2;
		glasses[index][4] = tint;
		if (x1 > maxX) {
			maxX = x1;
		}
		if (x2 > maxX) {
			maxX = x2;
		}
		if (y1 > maxY) {
			maxY = y1;
		}
		if (y2 > maxY) {
			maxY = y2;
		}
	}
	
	public int run() {
		
		this.window = new int[maxY][maxX];
		
		for (int index = 0; index < numbGlasses; index++) {
			int secX = glasses[index][2]-1;
			int secY = glasses[index][3]-1;
			for (int firstX = glasses[index][0]; firstX <= secX; firstX++) {
				for (int firstY = glasses[index][1]; firstY <= secY; firstY++) {
					window[firstY][firstX] += glasses[index][4];
				}
			}
		}
		
		int area = 0;
		for (int i = 0; i < maxY; i++) {
			for (int j = 0; j < maxX; j++) {
				if (window[i][j] >= this.threshold) {
					area++;
				}
			}
		}
		
		return area;
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		SR42014 game = new SR42014(Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine()));
		
		for (int i = 0; i < game.numbGlasses; i++) {
			game.addGlass(i, sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
			sc.nextLine();
		}

		System.out.println(game.run());
		
	}

}
