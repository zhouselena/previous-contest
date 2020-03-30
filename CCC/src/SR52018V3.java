import java.util.PriorityQueue;
import java.util.Scanner;

public class SR52018V3 {
	
	public static class Forest{
		public int[] nodes;
		public int treeCount;
		
		public Forest(int numb) {
			this.nodes = new int[numb];
			for (int i = 0; i < numb; i++) {
				nodes[i] = -1;
			}
			treeCount = numb;
		}
		
		public int findRoot(int node) {
			int root = node;
			while (nodes[root] != -1) {
				root = nodes[root];
			}
			return root;
		}
		
		public void setRoot(int larger, int root) {
			int nextRoot = larger;
			while (nextRoot != -1) {
				int temp = nodes[nextRoot];
				nodes[nextRoot] = root;
				nextRoot = temp;
			}
		}
		
		public boolean linkNode(int node1, int node2) {
			int parent1 = findRoot(node1);
			int parent2 = findRoot(node2);
			
			if (parent1 == parent2) {
				return false;
			}
			else if (parent1 > parent2) {
				setRoot(node1, parent2);
			}
			else {
				setRoot(node2, parent1);
			}
			return true;
		}
		
	}
	
	enum Travel{
		FLIGHT,
		PORTAL
	}
	
	public static class Routine implements Comparable<Routine>{
		public int from;
		public int to;
		public long cost;
		public Travel type;
		
		public Routine(int c1, int c2, int cost, Travel type) {
			this.from = c1;
			this.to = c2;
			this.cost = cost;
			this.type = type;
		}
		
		@Override
		public int compareTo(Routine arg0) {
			return (int)(cost - arg0.cost);
		}
	}
		
	public int N;
	public int M;
	public int P;
	public int Q;
	
	public PriorityQueue<Routine> transport = null;
	public long totalCost;
	
	public Forest cities;
	public Forest planets;
	
	public SR52018V3(int n, int m, int p, int q) {
		this.N = n;
		this.M = m;
		this.P = p;
		this.Q = q;
		this.planets = new Forest(n);
		this.cities = new Forest(m);
		this.transport = new PriorityQueue<Routine>(p+q);
	}
	
	public void addRoutineToQueue(Routine r) {
		transport.add(r);
		switch (r.type) {
		case FLIGHT:
			totalCost += r.cost*N;
			break;
		case PORTAL:
			totalCost += r.cost*M;
		}
	}
	
	public long addFlight(Routine r) {
		long usedCost = 0;
		if (cities.linkNode(r.from, r.to)) {
			cities.treeCount--;
			usedCost += r.cost * planets.treeCount;
		}
		return usedCost;
	}
	
	public long addPortal(Routine r) {
		long usedCost = 0;
		if (planets.linkNode(r.from, r.to)) {
			planets.treeCount--;
			usedCost += r.cost * cities.treeCount;
		}
		return usedCost;
	}
	
	public long findMaxSavings() {
		long totalSaved = 0;
		Routine current = null;
		while (planets.treeCount > 1 || cities.treeCount > 1) {
			current = transport.poll();
			switch (current.type) {
			case FLIGHT:
				totalSaved += addFlight(current);
				break;
			case PORTAL:
				totalSaved += addPortal(current);
				break;
			}
		}
		return totalCost - totalSaved;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int p = sc.nextInt();
		int q = sc.nextInt();
		
		SR52018V3 game = new SR52018V3(n, m, p, q);
		
		for (int i = 0; i < p; i++) {
			game.addRoutineToQueue(new Routine(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt(), Travel.FLIGHT));
		}
		
		for (int i = 0; i < q; i++) {
			game.addRoutineToQueue(new Routine(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt(), Travel.PORTAL));
		}
		
		System.out.println(game.findMaxSavings());
		
		sc.close();
	}

}
