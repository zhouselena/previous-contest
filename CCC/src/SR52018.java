import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Scanner;

public class SR52018 {
	
	public class City{
		int planet = 0;
		int city = 0;
		public City(int p, int c) {
			planet = p;
			city = c;
		}
	}
	
	public class Flight{
		City from;
		City to;
		int cost=0;
		
		public Flight(City city1, City city2, int cost) {
			from = city1;
			to = city2;
			this.cost = cost;
		}
	}
	
	public class FlightComparator implements Comparator<Flight>  {
		public int compare(Flight f1, Flight f2) {
			if (f1.cost < f2.cost)
				return -1;
			else if (f1.cost==f2.cost)
				return 0 ;
			else return 1;
		}
	}
	
	public City[][] galaxy = null;
	public List<Flight>[][] transport = null;
	
	public int M = 0;
	public int N = 0;
	public int P = 0;
	public int Q = 0;
	
	public long totalCost = 0;
	
	@SuppressWarnings("unchecked")
	public SR52018(int n, int m, int p, int q) {
		M = m;
		N = n;
		P = p;
		Q = q;
		galaxy = new City[N][M];
		transport = new ArrayList[N][M];
		for (int i=0;i<N;i++)
			for (int j=0;j<M;j++) {
				galaxy[i][j] = new City(i,j);
				transport[i][j] = new ArrayList<Flight>();
			}
	}
	
	public void addFlight(int city1, int city2, int cost) {
		for (int i = 0; i < N; i++) {
			transport[i][city1].add(new Flight(galaxy[i][city1], galaxy[i][city2], cost));
			transport[i][city2].add(new Flight(galaxy[i][city2], galaxy[i][city1], cost));
		}
		totalCost += (N*(long)cost);
	}
	
	public void addPortal(int planet1, int planet2, int cost) {
		for (int i = 0; i < M; i++) {
			transport[planet1][i].add(new Flight(galaxy[planet1][i], galaxy[planet2][i], cost));
			transport[planet2][i].add(new Flight(galaxy[planet2][i], galaxy[planet1][i], cost));
		}
		totalCost += (M*(long)cost);
	}
	
	public long findMinimalSpinningTree() {
		long weight = 0;
		PriorityQueue<Flight> edges = new PriorityQueue<Flight>(N*P+M*Q, new FlightComparator());
		Set<City> included = new HashSet<City>();
		
		included.add(galaxy[0][0]) ;
		addFlightIntoQueue(0, 0, edges, included) ;
		while (!edges.isEmpty()) {
			Flight temp = edges.poll();
			if (included.contains(temp.to)) {
				continue;
			}
			else {
				included.add(temp.to);
				weight += temp.cost;
				if (included.size()==N*M) {
					break;
				}
				addFlightIntoQueue(temp.to.planet, temp.to.city, edges, included);
			}
		}
		return weight;
	}
	
	public void addFlightIntoQueue(int planet, int city, PriorityQueue<Flight> queue, Set<City> tree) {
		for (Flight fly: transport[planet][city]) {
			if (!tree.contains(fly.to))
				queue.add(fly);
		}
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int p = sc.nextInt();
		int q = sc.nextInt();
		
		SR52018 game = new SR52018(n,m,p,q);
		
		for (int i = 0; i < p; i++) {
			game.addFlight(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt());
		}
		
		for (int i = 0; i < q; i++) {
			game.addPortal(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt());
		}
		
		long weight = game.findMinimalSpinningTree();
		long total = game.totalCost;
		
		long save = total - weight ;
		System.out.println(save);
		
		sc.close();
	}

}
