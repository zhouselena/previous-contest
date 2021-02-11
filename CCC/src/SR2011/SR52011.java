package SR2011;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SR52011 {

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
		
		ArrayList<int[]> lightsOn = new ArrayList<>();
		int count = 0;
		for (int i = 0; i < N; i++) {
			if (init[i]==0 && count > 0) {
				int[] openLightCluster = {i-count, count};
				lightsOn.add(openLightCluster);
				count = 0;
			}
		}
		
		int[] numbSwitch = new int[lightsOn.size()];
		for (int i = numbSwitch.length-1; i > 0; i--) {
			int[] currentClusterInfo = lightsOn.get(i);
			int[] currentCluster = new int[currentClusterInfo[1]];
			for (int t = 0; t < currentClusterInfo[1]; t++) {
				currentCluster[t] = 1;
			}
			
		}

	}

}
