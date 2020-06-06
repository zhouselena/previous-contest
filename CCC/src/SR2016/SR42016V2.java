package SR2016;

import java.util.ArrayList;
import java.util.Scanner;

public class SR42016V2 {

	public int N;
	public int[][] merged;
	public int[] riceBalls;
	
	public SR42016V2(int[] rice, int n) {
		this.N = n;
		riceBalls = rice;
		merged = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i >= j) {
					merged[i][j] = 1;
				}
			}
		}
	}
	
	private int weight(int a, int b) {
		int weight = 0;
		for (int i = a; i <= b; i++) {
			weight += riceBalls[i];
		}
		return weight;
	}
	
	public void canMerge(int i, int j) {
		
		int a = i;
		int b = j;
		
		while (a<=b) {
			int weightIA = weight(i,a);
			int weightBJ = weight(b,j);
			if (merged[i][a] != 1) {
				a++;
			}
			else if (merged[b][j] != 1) {
				b--;
			}
			else if (weightIA < weightBJ) {
				a++;
			}
			else if (weightIA > weightBJ){
				b--;
			}
			else if (weightIA==weightBJ && b-a==1) {
				merged[i][j] = 1;
				break;
			}
			else {
				if (merged[a][b] == 1) {
					merged[i][j] = 1;
					break;
				}
				else {
					a++;
					b--;
				}
			}
		}
	}
	
	public void merge() {
		
		for (int k = 1; k < N-1; k++) {
			for (int i = 0; i < N; i++) {
				int j = i+k;
				if (j >= N) {
					break;
				}
				canMerge(i, j);
			}
		}
	}
	
	public int biggestWeight() {
		
		merge();
		int max = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				if (merged[i][j]==1) {
					int wei = weight(i, j);
					if (wei > max) {
						max = wei;
					}
				}
			}
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] riceBalls = new int[n];
		
		for (int i = 0; i < n; i++) {
			riceBalls[i] = sc.nextInt();
		}
		
		sc.close();
	
		SR42016V2 game = new SR42016V2(riceBalls, n);
		System.out.println(game.biggestWeight());
		
	}

}
