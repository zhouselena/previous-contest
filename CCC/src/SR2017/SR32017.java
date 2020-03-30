package SR2017;

import java.util.Arrays;
import java.util.Scanner;

public class SR32017 {
	
	public int[] woodPieces;
	public int maxLength;
	public int numbTimes;
	
	public SR32017(int[] woodPieces) {
		this.woodPieces = woodPieces;
		maxLength = 0;
		numbTimes = 0;
	}
	
	private void addFrontBack(int indexStart, int indexEnd) {
		int height = woodPieces[indexStart] + woodPieces[indexEnd];
		int currentHeight = 0;
		int currentLength = 1;

		for (int i = indexStart+1; i < indexStart+((indexEnd-indexStart+1)/2); i++) {
			if (i != (indexStart+indexEnd-i)) {
				currentHeight = woodPieces[i] + woodPieces[indexStart+indexEnd-i];
				if (currentHeight == height) {
					currentLength++;
				}
			}
		}
		
		if (currentLength == maxLength) {
			numbTimes++;
		}
		else if (currentLength > maxLength) {
			maxLength = currentLength;
			numbTimes = 1;
		}
	}
	
	public void howManyBoards() {
		for (int i = 0; i < woodPieces.length; i++) {
			for (int j = woodPieces.length - 1; j > i; j--) {
				addFrontBack(i, j);
			}
		}
	}
	
	public static void main(String[] args) {
		/*  Sample Input 1
			4
			1 2 3 4
			Sample Output 1
			2 1
			Explanation for Sample Output 1
			Tudor first combines the pieces of wood with lengths 1 and 4 to form a board of length 5.
			Then he combines the pieces of wood with lengths 2 and 3 to form another board of length 5.
			Finally, he combines the boards to make a fence with length 2 and height 5.
			
			Sample Input 2
			5
			1 10 100 1000 2000
			Sample Output 2
			1 10
			Explanation for Sample Output 2
			Tudor can't make a fence longer than length 1, 
			and there are 10 ways to make a fence with length 1 by choosing any two pieces of wood to nail together.
			Specifically, he may have a fence of height 11, 101, 1001, 2001, 110, 1010, 2010, 1100, 2100 and 3000.
		 */
		
		//basically start pairing from front and back
		
		Scanner sc = new Scanner(System.in);
		
		int numbBoards = sc.nextInt();
		int[] tempBoards = new int[numbBoards];
		
		for (int i = 0; i < numbBoards; i++) {
			tempBoards[i] = sc.nextInt();
		}
		
		Arrays.sort(tempBoards);
		
		SR32017 game = new SR32017(tempBoards);
		game.howManyBoards();
		
		System.out.print(game.maxLength + " ");
		System.out.print(game.numbTimes);
		
		sc.close();

	}

}
