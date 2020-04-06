package SR2017;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SR42017V2 {

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
			return true;
		}
	}
	
	public Forest buildings;
	public int N = 0;
	public int M = 0;
	public int D = 0;
	public PriorityQueue<Pipe> activePipes = null;
	public PriorityQueue<Pipe> activePipesNotAdded = null;
	public PriorityQueue<Pipe> closedPipes = null;
	public List<Pipe> workingPipeSystem = null;
	
	public SR42017V2(int n, int m, int d) {
		this.N = n;
		this.M = m;
		this.D = d;
		buildings = new Forest(N);
		activePipes = new PriorityQueue<Pipe>();
		closedPipes = new PriorityQueue<Pipe>();
		workingPipeSystem = new ArrayList<Pipe>();
		activePipesNotAdded = new PriorityQueue<Pipe>();
	}
	
	public void addPipeToQueue(int build1, int build2, int cost, boolean active) {
		if (active) 
			activePipes.add(new Pipe(build1, build2, cost, active));
		else
			closedPipes.add(new Pipe(build1, build2, cost, active));
	}
	
	public boolean addPipeToSystem(Pipe p) {
		if (buildings.linkBuildings(p.from, p.to)) {
			buildings.treeCount--;
			workingPipeSystem.add(p);
			return true;
		}
		return false;
	}
	
	public int findMinDays() {
		int makeActive = 0;
		
		Pipe current = null;
		while (buildings.treeCount > 1) {
			Pipe temp1 = activePipes.peek();
			Pipe temp2 = closedPipes.peek();
			if (temp1 == null || (temp2 != null && temp2.cost < temp1.cost))
				current = closedPipes.poll();
			else
				current = activePipes.poll();
			
			if (addPipeToSystem(current)) {
				if (!current.active)
					makeActive++;
			}
			else if (current.active) {
				this.activePipesNotAdded.add(current);
			}
		}
		
		return makeActive;
	}
	
	public long sumCost() {
		long val = 0 ;
		for (Pipe pipe : workingPipeSystem) {
			val += pipe.cost;
		}
		return val;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int numbCities = sc.nextInt();
		int numbPipes = sc.nextInt();
		int enhanceStrength = sc.nextInt();
		
		SR42017V2 game = new SR42017V2(numbCities, numbPipes, enhanceStrength);
		
		for (int i = 0; i < numbPipes; i++) {
			if (i < numbCities-1) {
				game.addPipeToQueue(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt(), true);
			}
			else {
				game.addPipeToQueue(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt(), false);
			}
		}
		
		int currentBest = game.findMinDays();
		int temp = 0;
		
		long costSum = game.sumCost();
		if (enhanceStrength > 0) {
			for (Pipe addNotAddedActive: game.activePipesNotAdded) {
				game.activePipes.add(addNotAddedActive);
			}
			for (Pipe maybe: game.activePipes) {
				SR42017V2 enhance = new SR42017V2(numbCities, numbCities, enhanceStrength);
				for (Pipe add: game.workingPipeSystem) {
					enhance.addPipeToQueue(add.from, add.to, add.cost, add.active);
				}
				
				if (maybe.cost < enhanceStrength) {
					enhance.addPipeToQueue(maybe.from, maybe.to, 0, maybe.active);
				}
				else {
					enhance.addPipeToQueue(maybe.from, maybe.to, maybe.cost - enhanceStrength, maybe.active);
				}
				
				temp = enhance.findMinDays();
				long tempSum = enhance.sumCost();
				if (tempSum<costSum) {
					currentBest = temp;
					costSum = tempSum;
				}
			}
		}
		
		System.out.println(currentBest);
		
		sc.close();
		
	}

}
