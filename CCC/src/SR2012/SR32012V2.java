package SR2012;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SR32012V2 {
	
	public static int maximum (List<Integer> list) {
		
		int max = 0;
		
		for (int i = 0; i < list.size(); i++) {
			int current = list.get(i);
			if (max < current) {
				max = current;
			}
		}
		
		return max;
		
	}
	
	public static int minimum (List<Integer> list) {
		
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < list.size(); i++) {
			int current = list.get(i);
			if (min > current) {
				min = current;
			}
		}
		
		return min;
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int[] frequencies = new int[1000];
		
		int n = Integer.parseInt(sc.nextLine());
		
		for (int i = 0; i < n; i++) {
			int curr = Integer.parseInt(sc.nextLine());
			frequencies[curr-1]++;
		}
		
		List<Integer> first = new ArrayList<>();
		List<Integer> second = new ArrayList<>();
		int firstFreq = 0;
		int secFreq = 0;
		
		for (int i = 0; i < 1000; i++) {
			
			int curr = frequencies[i];
			
			if (curr > firstFreq) {
				second = first;
				secFreq = firstFreq;
				first = new ArrayList<Integer>();
				firstFreq = curr;
				first.add(i);
			}
			else if (curr == firstFreq) {
				first.add(i);
			}
			else if (curr > secFreq) {
				second.clear();
				secFreq = curr;
				second.add(i);
			}
			else if (curr == secFreq) {
				second.add(i);
			}
			
		}
		
		if (first.size() == 1) {
			int val1 = Math.abs(first.get(0) - maximum(second));
			int val2 = Math.abs(first.get(0) - minimum(second));
			System.out.println(Math.max(val1, val2));
		}
		else {
			int val = Math.abs(maximum(first) - minimum(first));
			System.out.println(val);
		}
	}

}
