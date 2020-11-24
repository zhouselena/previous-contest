package SR2012;

import java.util.ArrayList;
import java.util.Scanner;

public class SR32012V2 {
	
	public static boolean find (ArrayList<Integer> list, int find) {
				
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == find) {
				return true;
			}
		}
		
		return false;
		
	}
		
	public static int maximum (ArrayList<Integer> list) {
			
		int max = 0;
		
		for (int i = 0; i < list.size(); i++) {
			int current = list.get(i);
			if (max < current) {
				max = current;
			}
		}
		
		return max;
			
	}
	
	public static int minimum (ArrayList<Integer> list) {
		
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
		
		ArrayList<Integer> keys = new ArrayList<>();
		ArrayList<Integer> frequencies = new ArrayList<>();
		
		int n = Integer.parseInt(sc.nextLine());
		
		for (int i = 0; i < n; i++) {
						
			int curr = Integer.parseInt(sc.nextLine());
			if (find(keys, curr)) {
				int ind = keys.indexOf(curr);
				frequencies.set(ind, frequencies.get(ind)+1);
			}
			else {
				keys.add(curr);
				frequencies.add(1);
			}
			
		}
		
		ArrayList<Integer> first = new ArrayList<>();
		ArrayList<Integer> second = new ArrayList<>();
		int firstFreq = 0;
		int secFreq = 0;
		
		for (int i = 0; i < keys.size(); i++) {
			
			if (frequencies.get(i) > firstFreq) {
				first.clear();
				firstFreq = frequencies.get(i);
				first.add(keys.get(i));
			}
			else if (frequencies.get(i) == firstFreq) {
				first.add(keys.get(i));
			}
			else if (frequencies.get(i) > secFreq) {
				second.clear();
				secFreq = frequencies.get(i);
				second.add(keys.get(i));
			}
			else if (frequencies.get(i) == secFreq) {
				second.add(keys.get(i));
			}
			
		}
		
		if (first.size() == 1) {
			int val1 = Math.abs(first.get(0) - minimum(second));
			int val2 = Math.abs(first.get(0) - maximum(second));
			System.out.println(Math.max(val1, val2));
		}
		else {
			int value = maximum(first) - minimum(first);
			System.out.println(Math.abs(value));
		}
		
	}

}
