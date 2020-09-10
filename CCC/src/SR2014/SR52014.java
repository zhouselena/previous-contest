package SR2014;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SR52014 {
	
	public class Distance implements Comparable<Distance>{
		
		public double dist;
		public int a;
		public int b;
		
		public Distance(int a1, int a2, int b1, int b2, int aind, int bind) {
			
			this.a = aind;
			this.b = bind;
			this.dist = Math.pow(a1-b1, 2);
			this.dist += Math.pow(a2-b2, 2);
			
		}
		
		public int compareTo(Distance arg0) {
			return (int) (arg0.dist - dist);
		}
		
	}
	
	public int N;
	public int[][] locations = null;
	ArrayList<Distance> distances = null;
	public double[] d = null;
	public int[] prev = null;
	public int[] treats = null;
	
	public SR52014(int[][] loc, int n) {
		
		this.N = n+1;
		this.locations = loc;
		
		this.distances = new ArrayList<Distance>();
		this.d = new double[N];
		this.prev = new int[N];
		this.treats = new int[N];
		
		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				distances.add(new Distance(locations[i][0], locations[i][1], locations[j][0], locations[j][1], i, j));
			}
		}
		
		Collections.sort(distances);
	}
	
	public int run() {
		
		boolean startfound = false;
		for (int i = 0; i < distances.size(); i++) {
			
			Distance current = distances.get(i);
			
			if (current.a==0) {
				startfound = true;
			}
			
			if (startfound) {
				if (d[current.a] != current.dist) {
					d[current.a] = current.dist;
					prev[current.a] = treats[current.a];
				}
				if (d[current.b] != current.dist) {
					d[current.b] = current.dist;
					prev[current.b] = treats[current.b];
				}
				
				if (current.a==0) {
					treats[current.a] = Math.max(prev[current.a], prev[current.b]);
				}
				else {
					treats[current.a] = Math.max(prev[current.a], prev[current.b]+1);
				}
				treats[current.b] = Math.max(prev[current.b], prev[current.a]+1);
			}
			
		}
		
		int max = 0;
		
		for (int i = 0; i < treats.length; i++) {
			if (treats[i]>max) {
				max = treats[i];
			}
		}
		
		return max;
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		int[][] loc = new int[n+1][2];
		for (int i = 1; i < n+1; i++) {
			loc[i][0] = sc.nextInt();
			loc[i][1] = sc.nextInt();
			sc.nextLine();
		}
		
		SR52014 game = new SR52014(loc, n);
		System.out.println(game.run());
		
		sc.close();

	}

}
