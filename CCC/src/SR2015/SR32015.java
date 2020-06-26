package SR2015;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SR32015 {

	public int numbGates;
	public int numbPlanes;
	public Map<Integer, Integer> nextGate = null;
	
	public SR32015(int g, int p) {
		this.numbGates = g;
		this.numbPlanes = p;
		this.nextGate = new HashMap<Integer, Integer>();
	}
	
	public boolean dockPlane(int maxGate) {
		int next = maxGate;
		ArrayList<Integer> update = new ArrayList<Integer>();
		while (nextGate.containsKey(next) && next > 0) {
			update.add(next);
			next = nextGate.get(next);
		}
		nextGate.put(next, next-1);
		for (int i: update) {
			nextGate.put(i, next-1);
		}
		if (next > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int run(int[] planes) {
		int numbDocked = 0;
		
		for (int i: planes) {
			if (dockPlane(i)) {
				numbDocked++;
			}
			else {
				break;
			}
		}
		
		return numbDocked;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		SR32015 game = new SR32015(Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine()));
		int[] planes = new int[game.numbPlanes];
		for (int i = 0; i < planes.length; i++) {
			planes[i] = Integer.parseInt(sc.nextLine());
		}
		
		System.out.println(game.run(planes));
		
		sc.close();
	}

}
