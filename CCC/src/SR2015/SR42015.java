package SR2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SR42015 {
	
	public class Island {
		
		public int[] costs;
		public int self;
		public int parent;
		
		public Island(int weight, int self) {
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
		calculate[startIsland].parent = startIsland;
		this.endIsland = end;
	}
	
	public void addRoute(int to, int from, int time, int cost) {
		this.routes[from].add(new Route(to, from, time, cost));
		this.routes[to].add(new Route(from, to, time, cost));
	}
	
	public void run() {
		
		Island parent = calculate[startIsland];
		Island current = null;
		
		while(current.self != endIsland) {
			
			int lowestDist = Integer.MAX_VALUE;
			
			for (int i = 0; i < routes[parent.self].size(); i++) {
				Route currentRoute = routes[startIsland].get(i);
				current = calculate[currentRoute.to];
				if (currentRoute.cost > this.hullInitial) {
					continue;
				}
				else if (current.costs[currentRoute.cost] > currentRoute.distance) {
					current.costs[currentRoute.cost] = currentRoute.distance;
				}
			}
			
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
