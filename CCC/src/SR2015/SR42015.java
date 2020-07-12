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
			for (int i = 0; i < weight; i++) {
				parents[i] = -1;
			}
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
		for (int i = 0; i < routes; i++) {
			this.routes[i] = new ArrayList<Route>();
		}
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
			if (currentRoute.cost+prevCost >= this.hullInitial) {
				continue;
			}
			else if (currentIsland.costs[currentRoute.cost+prevCost] > currentRoute.distance+prevDistance) {
				currentIsland.costs[currentRoute.cost+prevCost] = currentRoute.distance + prevDistance;
				currentIsland.parents[currentRoute.cost+prevCost] = parentIndex;
			}
		}
		
		calculate[parentIndex].visited[prevCost] = true;
		
	}
	
	public void run() {
				
		for (int k = 0; k < this.hullInitial; k++) {
			
			int minimum = 0;
			
			while (minimum < Integer.MAX_VALUE) {
				
				minimum = Integer.MAX_VALUE;
				Island toVisit = null;

				// find which next point to visit
				for (int i = 0; i < calculate.length; i++) {
					Island current = calculate[i];
					if (current.visited[k]) {
						continue;
					}
					else if (current.costs[k] < minimum) {
						minimum = current.costs[k];
						toVisit = calculate[current.self];
					}
				}
				if (minimum==Integer.MAX_VALUE) {
					continue;
				}
				else {
					visit(toVisit.self, k, toVisit.costs[k]);
				}
			}
			
		}
		
		int time = Integer.MAX_VALUE;
		
		for (int i: calculate[endIsland].costs) {
			if (i < time) {
				time = i;
			}
		}
		
		if (time==Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(time);
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
		
		game.startAndEnd(sc.nextInt()-1, sc.nextInt()-1);
		game.run();
		
		sc.close();
	}

}
