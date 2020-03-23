import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SR52018V2 {
	
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
	
	public long distinctCities = 0;
	public long totalCost = 0;
	public int[] nmpq;
	public int[] planetCities;
	public PriorityQueue<Routine> transport = null;
	
	public SR52018V2(int n, int m, int p, int q){
		nmpq = new int[4];
		nmpq[0] = n;
		nmpq[1] = m;
		nmpq[2] = p;
		nmpq[3] = q;
		planetCities = new int[m*n];
		for (int i = 0; i < m*n; i++) {
			planetCities[i] = -1;
		}
		transport = new PriorityQueue<Routine>(p+q);
		distinctCities = m*n;
	}
	
	public void addRoutineToQueue(Routine r) {
		transport.add(r);
		switch (r.type) {
		case FLIGHT:
			totalCost += r.cost*nmpq[0];
			break;
		case PORTAL:
			totalCost += r.cost*nmpq[1];
		}
	}
	
	public boolean linkCities(int city1, int city2) {
		int parent1 = findRoot(city1);
		int parent2 = findRoot(city2);
		if (parent1==parent2) {
			return false;
		}
		else if (parent1 > parent2) {
			setRoot(city1, parent2);
		}
		else {
			setRoot(city2, parent1);
		}
		return true;
	}
	
	public void setRoot(int larger, int root) {
		int nextRoot = larger;
		while (nextRoot != -1) {
			int temp = planetCities[nextRoot];
			planetCities[nextRoot] = root;
			nextRoot = temp;
		}
	}
	
	public int findRoot(int city) {
		int root = city;
		while (planetCities[root] != -1) {
			root = planetCities[root];
		}
		return root;
	}

	public long addFlight(Routine r) {
		long usedCost = 0;
		for (int i = 0; i < nmpq[0]; i++) {
			if (linkCities(r.from+i*nmpq[1], r.to+i*nmpq[1])) {
				distinctCities--;
				usedCost += r.cost;
			}
		}
		return usedCost;
	}
	
	public long addPortal(Routine r) {
		long usedCost = 0;
		for (int i = 0; i < nmpq[1]; i++) {
			if(linkCities(r.from*nmpq[1]+i, r.to*nmpq[1]+i)) {
				distinctCities--;
				usedCost += r.cost;
			}
		}
		return usedCost;
	}
	
	public long findMaxSavings() {
		long totalSaving = 0;
		Routine dealW = null;
		while (distinctCities > 1) {
			dealW = transport.poll();
			switch (dealW.type) {
			case FLIGHT:
				totalSaving += addFlight(dealW);
				break;
			case PORTAL:
				totalSaving += addPortal(dealW);
				break;
			}
		}
		return totalCost - totalSaving;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int p = sc.nextInt();
		int q = sc.nextInt();
		
		SR52018V2 game = new SR52018V2(n, m, p, q);
		
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
