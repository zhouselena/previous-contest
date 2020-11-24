package SR2012;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class SR32012 {

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
		
		HashMap<Integer, Integer> freq = new HashMap<>();
		ArrayList<Integer> keys = new ArrayList<>();
		int count = Integer.parseInt(sc.nextLine());
		
		for (int i = 0; i < count; i++) {
			
			int current = Integer.parseInt(sc.nextLine());
			if (freq.containsKey(current)) {
				freq.replace(current, freq.get(current)+1);
			}
			else {
				keys.add(current);
				freq.put(current, 1);
			}
			
		}
		
		int highFreq = 0;
		int secHighFreq = 0;
		ArrayList<Integer> max = new ArrayList<>();
		ArrayList<Integer> sec = new ArrayList<>();
		
		for (int i = 0; i < keys.size(); i++) {
			int currentKey = keys.get(i);
			int currentFreq = freq.get(currentKey);
			
			if (currentFreq > highFreq) {
				sec = max;
				secHighFreq = highFreq;
				highFreq = currentFreq;
				max.clear();
				max.add(currentKey);
			}
			else if (currentFreq == highFreq) {
				max.add(currentKey);
			}
			else if (currentFreq > secHighFreq) {
				secHighFreq = currentFreq;
				sec.clear();
				sec.add(currentKey);
			}
			else if (currentFreq == secHighFreq) {
				sec.add(currentFreq);
			}
		}
		
		int value = maximum(max) - minimum(sec);
		System.out.println(value);
		
	}

}
