package SR2017;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SR42017V3 {

	public static class Pipe implements Comparable<Pipe>{
		public int to;
		public int from;
		public int cost;
		public boolean active;
		
		public Pipe(int build1, int build2, int cost, boolean active) {
			this.from = build1;
			this.to = build2;
			this.cost = cost;
			this.active = active;
		}

		@Override
		public int compareTo(Pipe o) {
			if (cost==o.cost) {
				int a = this.active? 0:1;
				int b = o.active? 0:1;
				return a - b;
			}
			return cost - o.cost;
		}
	}
	
	public static class Forest{
		public int[] buildings;
		public int treeCount;
		
		public Forest(int numbBuildings) {
			this.buildings = new int[numbBuildings];
			for (int i = 0; i < numbBuildings; i++) {
				buildings[i] = -1;
			}
			treeCount = numbBuildings;
		}
		
		public int findRoot(int build) {
			int root = build;
			while (buildings[root] != -1) {
				root = buildings[root];
			}
			return root;
		}
		
		public void setRoot(int build, int root) {
			int nextRoot = build;
			while (nextRoot != -1) {
				int temp = buildings[nextRoot];
				buildings[nextRoot] = root;
				nextRoot = temp;
			}
		}
		
		public boolean linkBuildings(int build1, int build2) {
			int root1 = findRoot(build1);
			int root2 = findRoot(build2);
			
			if (root1 == root2) {
				return false;
			}
			else if (root1 > root2) {
				setRoot(build1, root2);
			}
			else {
				setRoot(build2, root1);
			}
			treeCount--;
			return true;
		}
	}
	
	public Forest buildings;
	public int N = 0;
	public int M = 0;
	public int D = 0;
	public ArrayList<Pipe> pipes;
	
	public SR42017V3(int n, int m, int d) {
		this.N = n;
		this.M = m;
		this.D = d;
		buildings = new Forest(N);
		pipes = new ArrayList<Pipe>();
	}
	
	public void addPipe(int build1, int build2, int cost, boolean active) {
		pipes.add(new Pipe(build1, build2, cost, active));
	}
	
	// minimum spanning tree
	public int findMinDays() {
		int days = 0;
		Collections.sort(pipes);
		Pipe lastPipe = null;
		
		for (Pipe pipe: pipes) {
			if (buildings.linkBuildings(pipe.from, pipe.to)) {
				if (!pipe.active) {
					days++;
				}
				if (buildings.treeCount == 1) {
					lastPipe = pipe;
					break;
				}
			}
		}
		
		// add consideration for cost enhancer if last pipe is non-active
		if (!lastPipe.active && lastPipe.cost<=D) {
			Forest f2 = new Forest(N);
			for (Pipe pipe: pipes) {
				if (f2.findRoot(pipe.from) != f2.findRoot(pipe.to)) {
					if (pipe.cost < lastPipe.cost || pipe.cost == lastPipe.cost && pipe.active)
						f2.linkBuildings(pipe.from, pipe.to);
					else if (pipe.cost <= D && pipe.active) {
						days--;
						break;
					}
					else if (pipe.cost > D)
						break;
				}
			}
		}
		
		return days;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int numbCities = sc.nextInt();
		int numbPipes = sc.nextInt();
		int enhanceStrength = sc.nextInt();
		
		SR42017V3 game = new SR42017V3(numbCities, numbPipes, enhanceStrength);
		
		for (int i = 0; i < numbPipes; i++) {
			if (i < numbCities-1) {
				game.addPipe(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt(), true);
			}
			else {
				game.addPipe(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt(), false);
			}
		}
		System.out.println(game.findMinDays());
		
		sc.close();
		
	}

}
