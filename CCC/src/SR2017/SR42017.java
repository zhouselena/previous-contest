package SR2017;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SR42017 {

	public static class Pipe implements Comparable<Pipe>{
		public int to;
		public int from;
		public int cost;
		public boolean active;
		
		public Pipe(int build1, int build2, int cost, boolean active){
			this.from = build1;
			this.to = build2;
			this.cost = cost;
			this.active = active;
		}

		@Override
		public int compareTo(Pipe arg0) {
			if (cost == arg0.cost) {
				if (this.active) {
					return -1;
				}
				else if (arg0.active){
					return 1;
				}
			}
			return cost - arg0.cost;
		}
	}
	
	public List<Pipe>[] allPipes = null;
	public int N = 0;
	public int M = 0;
	public int D = 0;
	public int makeInactive = 0;
	public int makeActive = 0;
	
	@SuppressWarnings("unchecked")
	public SR42017(int n, int m, int d) {
		this.N = n;
		this.M = m;
		this.D = d;
		allPipes = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			allPipes[i] = new ArrayList<Pipe>();
		}
	}
	
	public void addPipe(int build1, int build2, int cost, boolean active) {
		allPipes[build1].add(new Pipe(build1, build2, cost, active));
		allPipes[build2].add(new Pipe(build2, build1, cost, active));
	}
	
	public void addPipeToQueue(int building, PriorityQueue<Pipe> queue, HashMap<Integer, Integer> tree) {
		for (Pipe pip: allPipes[building]) {
			if (!tree.containsKey(pip.to)) {
				queue.add(pip);
			}
		}
	}
	
	public int findMinSpinningTree() {
		PriorityQueue<Pipe> edges = new PriorityQueue<Pipe>();
		HashMap<Integer, Integer> connected = new HashMap<Integer, Integer>();
		
		connected.put(0, 0);
		addPipeToQueue(0, edges, connected);
		
		while (!edges.isEmpty()) {
			Pipe temp = edges.poll();
			if (connected.containsKey(temp.to)) {
				if (temp.active) {
					makeInactive++;
				}
				continue;
			}
			else {
				if (!temp.active) {
					makeActive++;
				}
				connected.put(temp.to, temp.from);
				addPipeToQueue(temp.to, edges, connected);
			}
		}
		
		if (makeInactive > makeActive) {
			return makeInactive;
		}
		else {
			return makeActive;
		}
	}
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		int numbCities = sc.nextInt();
		int numbPipes = sc.nextInt();
		int enhanceStrength = sc.nextInt();
		
		SR42017 game = new SR42017(numbCities, numbPipes, enhanceStrength);
		
		for (int i = 0; i < numbPipes; i++) {
			if (i < numbCities-1) {
				game.addPipe(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt(), true);
			}
			else {
				game.addPipe(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt(), false);
			}
		}
		
		System.out.println(game.findMinSpinningTree());
		
		sc.close();
	}

}
