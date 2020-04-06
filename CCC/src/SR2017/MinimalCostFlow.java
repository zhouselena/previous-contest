package SR2017;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MinimalCostFlow {
	public static class Forest {
		int[]nodes;
		int treeCount = 0 ;
		public Forest(int n) {
			nodes = new int[n];
			for (int i=0;i<n; i++)
				nodes[i] = -1 ;
			treeCount = n;
		}
		public int findRoot(int node) {
			int root = node ;
			while (nodes[root]!=-1) {
				root = nodes[root];
			}
			return root;
		}
		public void setRoot(int node, int parent) {
			while(node!=-1) {
				int temp = nodes[node];
				nodes[node] = parent;
				node = temp;
			}
		}
		public boolean linkNodes(int node1, int node2) {
			if (node1 == node2 ) return false ;
			int p1 = findRoot(node1) ;
			int p2 = findRoot(node2) ;
			if (p1==p2)	return false ;
			if (p1<p2)
				setRoot(node2, p1);
			else
				setRoot(node1, p2);
			treeCount --;
			return true;
		}
		public int getTreeCount() {
			return treeCount;
		}
	}
	
	public static class Pipe implements Comparable<Pipe>{
		int from = 0;
		int to = 0 ;
		int cost = 0;
		boolean activated = false;
		public Pipe(int building1, int building2, int c, boolean status) {
			from = building1;
			to = building2;
			cost  = c;
			activated = status;
		}
		@Override
		public int compareTo(Pipe arg0) {
			return cost - arg0.cost;
		}
	}
	
	private PriorityQueue<Pipe> activatedPipes = null;
	private PriorityQueue<Pipe> backupPipes = null;
	private int N = 0;
	private int M = 0;
	private int D = 0;
	
	public MinimalCostFlow(int n, int m, int d) {
		N = n;
		M = m;
		D = d;
		activatedPipes = new PriorityQueue<Pipe>(N-1);
		backupPipes = new PriorityQueue<Pipe>(M-N+1);
	}
	public void addPipe(Pipe p) {
		if (p.activated) activatedPipes.add(p);
		else
			backupPipes.add(p);
	}
	private Pipe findReplacedPipe(List<Pipe> working, Pipe boost) {
		Pipe who = null;
		Forest buildings = new Forest(N);
		for (Pipe p : working) {
			if (boost!=null && boost.cost<= p.cost) {
				who= boost ;
				boost =  null;
			}
			else
				who = p;
			if (!buildings.linkNodes(who.from, who.to))
				break;
		}
		return who;
	}
	public int findMinimalDays() {
		int days = 0 ;
		long costs = 0 ;

		List<Pipe> workingPipes = new ArrayList<Pipe>();
		List<Pipe> unusedPipes = new ArrayList<Pipe>();
//calcalute the minimal cost without boost		
		Forest buildings = new Forest(N) ;
		while (buildings.getTreeCount()>1) {
			Pipe a = activatedPipes.peek();
			Pipe b = backupPipes.peek();
			Pipe who = null;
			if (a!=null && (b==null || a.compareTo(b) <=0)) {
				who = activatedPipes.poll();
			}
			else {
				who = backupPipes.poll();
			}
			if (buildings.linkNodes(who.from, who.to)) {
				workingPipes.add(who);
				if (!who.activated)	days++;
				costs += who.cost;
			}
			else {
				if (who.activated)
					unusedPipes.add(who);
			}
		}
///////////////////
		
		unusedPipes.addAll(activatedPipes);
		long minCost = costs;
		int minDays = days;
		
		if (D>0) {
			for (Pipe boost : unusedPipes) {
				boost.cost = boost.cost < D ? 0 : boost.cost - D;
				Pipe replaced = findReplacedPipe(workingPipes, boost) ;
				long temp = costs+boost.cost-replaced.cost;
				if ( temp < minCost) {
					minCost = temp;
					if (replaced.activated) minDays = days;
					else
						minDays = days - 1;
				}
			}
		}
		
		return minDays;
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int numbCities = sc.nextInt();
		int numbPipes = sc.nextInt();
		int enhanceStrength = sc.nextInt();
		
		MinimalCostFlow game = new MinimalCostFlow(numbCities, numbPipes, enhanceStrength);
		
		for (int i = 0; i < numbPipes; i++) {
			if (i < numbCities-1) {
				game.addPipe(new Pipe(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt(), true));
			}
			else {
				game.addPipe(new Pipe(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt(), false));
			}
		}
		
		System.out.println(game.findMinimalDays());
		
		sc.close();
	}
}