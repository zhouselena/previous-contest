package SR2013;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SR22013 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		final int maxWeight = Integer.parseInt(sc.nextLine());
		int numberTrains = Integer.parseInt(sc.nextLine());
		int[] trains = new int[numberTrains];
		for (int i = 0; i < numberTrains; i++) {
			trains[i] = Integer.parseInt(sc.nextLine());
		}
		
		Queue<Integer> bridge = new LinkedList<Integer>();
		int weight = 0;
		int travelled = 0;
		
		for(int i = 0; i < numberTrains; i++) {
			bridge.add(trains[i]);
			weight += trains[i];
			if (weight > maxWeight) {
				break;
			}
			travelled++;
			if (bridge.size() == 4) {
				weight -= bridge.poll();
			}
		}
		
		System.out.println(travelled);
		
		sc.close();
	}

}
