package SR2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SR42015 {
	
	public class Island {
		
		public int[] costs;
		public int self;
		public int[] parents;
		public boolean[] visited;
		
		public Island(int weight, int self) {
			this.parents = new int[weight];
			this.visited = new boolean[weight];
			this.costs = new int[weight];
			for (int i = 0; i < weight; i++) {
				costs[i] = Integer.MAX_VALUE;
			}
			this.self = self;
		}
		
		public void addDistance(int weight, int cost) {
			if (costs[weight] > cost) {
				costs[weight] = cost;
			}
		}
	}
	
	public class Route {
		
		public int to;
		public int from;
		public int distance;
		public int cost;
		
		public Route(int to, int from, int dist, int c) {
			this.to = to;
			this.from = from;
			this.distance = dist;
			this.cost = c;
		}
		
	}
	
	private int hullInitial = 0;
	private int numbIsland = 0;
	private int numbRoute = 0;
	
	private Island[] calculate = null;
	private List<Route>[] routes = null;
	private int startIsland;
	private int endIsland;
	
	public SR42015(int hull, int islands, int routes) {
		this.hullInitial = hull;
		this.numbIsland = islands;
		this.numbRoute = routes;
		this.calculate = new Island[islands];
		for (int i = 0; i < islands; i++) {
			calculate[i] = new Island(hull, i);
		}
		this.routes = new ArrayList[routes];
	}
	
	public void startAndEnd(int start, int end) {
		this.startIsland = start;
		calculate[startIsland].costs[0] = 0;
		calculate[startIsland].parents[0] = startIsland;
		this.endIsland = end;
	}
	
	public void addRoute(int to, int from, int time, int cost) {
		this.routes[from].add(new Route(to, from, time, cost));
		this.routes[to].add(new Route(from, to, time, cost));
	}
	
	private void visit(int parentIndex, int prevCost, int prevDistance) {
		
		for (int i = 0; i < routes[parentIndex].size(); i++) {
			Route currentRoute = routes[parentIndex].get(i);
			Island currentIsland = calculate[currentRoute.to];
			if (currentRoute.cost+prevCost > this.hullInitial) {
				continue;
			}
			else if (currentIsland.costs[currentRoute.cost+prevCost] > currentRoute.distance+prevDistance) {
				currentIsland.costs[currentRoute.cost+prevCost] = currentRoute.distance + prevDistance;
				currentIsland.parents[currentRoute.cost+prevCost] = parentIndex;
			}
		}
		
		// when to mark visited??
		
	}
	
	public void run() {
		
		Island parent = calculate[startIsland];
		
		for (int k = 0; k < this.hullInitial; k++) {
			
			int minimum = Integer.MAX_VALUE;
			Island toVisit = null;
			for (int i = 0; i < routes[parent.self].size(); i++) {
				Island current = calculate[routes[parent.self].get(i).to];
				if (!current.visited[k]) {
					continue;
				}
				else if (current.costs[k] < minimum) {
					minimum = current.costs[k];
					toVisit = calculate[current.self];
				}
			}
			visit(toVisit.self, k, toVisit.costs[k]);
		}
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int hull = sc.nextInt();
		int islands = sc.nextInt();
		int routes = sc.nextInt();
		sc.nextLine();
		
		SR42015 game = new SR42015(hull, islands, routes);
		
		for (int i = 0; i < routes; i++) {
			game.addRoute(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt(), sc.nextInt());
			sc.nextLine();
		}
		
		game.startAndEnd(sc.nextInt(), sc.nextInt());
		game.run();
		
		sc.close();
	}

}
