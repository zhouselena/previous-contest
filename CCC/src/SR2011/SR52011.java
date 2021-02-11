package SR2011;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class SR52011 {
	
	public static class Pair {
		
		public int index = 0;
		public int count = 0;
		
		public Pair (int i, int c) {
			this.index = i;
			this.count = c;
		}
		
	}

	// for one single cluster
	public static int numbLightsSwitched(int[] cluster) {
		
		if (cluster.length >= 8) return 0;
		else {
			int total = cluster.length;
			if (total < 4) total = 4;
			int on = 0;
			for (int i = 0; i < cluster.length; i++) {
				if (cluster[i]==1) on++;
			}
			return total-on;
		}
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		int[] init = new int[N];
		for (int i = 0; i < N; i++) {
			init[i] = Integer.parseInt(sc.nextLine());
		}
		
		ArrayList<Pair> lightsOn = new ArrayList<>();
		int count = 0;
		for (int i = 0; i < N; i++) {
			if (init[i]==0 && count > 0) {
				lightsOn.add(new Pair(i-count, count));
				count = 0;
			}
			else if (init[i]==1) {
				count++;
			}
		}
		if (count > 0) {
			lightsOn.add(new Pair(N-count, count));
		}
		
		int[] numbSwitch = new int[lightsOn.size()+1];
		for (int i = 0; i < numbSwitch.length; i++) {
			numbSwitch[i] = N;
		}
		numbSwitch[lightsOn.size()] = 0;
		
		for (int i = lightsOn.size()-1; i >= 0; i--) {
			int numbLightsOn = 0;
			for (int j = i; j < lightsOn.size(); j++) {
				int lightsFromItoJ = lightsOn.get(j).index-lightsOn.get(i).index+lightsOn.get(j).count;
				if (lightsFromItoJ > 7) {
					break;
				}
				else {
					numbLightsOn += lightsOn.get(j).count;
				}
				
				if (lightsFromItoJ == 6) {
					if (init[i+2]==1 && init[i+3]==1) {
						continue;
					}
				}
				else if (lightsFromItoJ == 7) {
					if (init[i+3]==1) {
						continue;
					}
				}
				
				int turnOff = (lightsFromItoJ<4?4:lightsFromItoJ) - numbLightsOn + numbSwitch[j+1];
				if (turnOff < numbSwitch[i])
					numbSwitch[i] = turnOff;
			}
			
		}
		
		System.out.println(numbSwitch[0]);

	}

}
