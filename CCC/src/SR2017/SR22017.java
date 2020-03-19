package SR2017;

import java.util.Scanner;
import java.util.Stack;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SR22017 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int numbTides = sc.nextInt();
		int[] oginfo = new int[numbTides];
		for (int i = 0; i < numbTides; i++) {
			oginfo[i] = sc.nextInt();
		}
		
		Arrays.sort(oginfo);
		
        Stack<Integer> smaller = new Stack<Integer>(); 
        Queue<Integer> larger = new LinkedList<Integer>(); 
		for (int i = 0; i < (numbTides+1)/2; i++) {
			smaller.push(oginfo[i]);
		}
		for (int i = (numbTides+1)/2; i < numbTides; i++) {
			larger.add(oginfo[i]);
		}
		
		int[] organized = new int[numbTides];
		for (int i = 0; i < numbTides; i+=2) {
			organized[i] = smaller.pop();
			if (!larger.isEmpty())
				organized[i+1] = larger.poll();
		}
		
		for (int i = 0; i < numbTides; i++) {
			System.out.print(organized[i]);
			if (i!=(numbTides-1))
				System.out.print(" ");
		}
		
		sc.close();

	}

}
