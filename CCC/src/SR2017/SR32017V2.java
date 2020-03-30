package SR2017;

import java.util.ArrayList;
import java.util.Scanner;

public class SR32017V2 {

	public int[] boards;
	public int[] heights;
	public int numbLengths;
    ArrayList<Integer> activeBoard;
	
	public SR32017V2(int[] given) {
		boards = new int[2001];
		activeBoard = new ArrayList<Integer>();
		for (int i = 0; i < given.length; i++) {
			boards[given[i]]++;
			if (boards[given[i]]==1) {
				activeBoard.add(given[i]);
			}
		}
		heights = new int[4001];
		numbLengths = 0;
	}
	
	public void CalcHeights() {
		for (int i = 0; i < activeBoard.size(); i++) {
			heights[activeBoard.get(i)*2] += (boards[activeBoard.get(i)]/2);
			for (int j = i+1; j < activeBoard.size(); j++) {
				int lower = boards[activeBoard.get(i)];
				if (boards[activeBoard.get(j)] < lower) {
					lower = boards[activeBoard.get(j)];
				}
				heights[activeBoard.get(i)+activeBoard.get(j)] += lower;
			}
		}
	}
	
	public int findLongestLength() {
		int max = 0;
		for (int i = 0; i < heights.length; i++) {
			if (heights[i] > max) {
				max = heights[i];
				numbLengths = 1;
			}
			else if (heights[i]==max) {
				numbLengths++;
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int numbBoards = sc.nextInt();
		int[] tempBoards = new int[numbBoards];
		
		for (int i = 0; i < numbBoards; i++) {
			tempBoards[i] = sc.nextInt();
		}

		SR32017V2 game = new SR32017V2(tempBoards);
		game.CalcHeights();
		System.out.print(game.findLongestLength()+" ");
		System.out.print(game.numbLengths);
		
		sc.close();
		
	}

}
