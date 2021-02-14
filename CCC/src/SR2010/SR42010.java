package SR2010;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class SR42010 {
	
	public static class GateInfo {
		
		public int pen;
		public int cost;
		
		public GateInfo(int p, int c) {
			this.pen = p;
			this.cost = c;
		}
		
	}
	
	public static class Pen implements Comparable<Pen> {
		
		public int pen1;
		public int pen2;
		public int cost;
		
		public Pen(int p1, int p2, int c) {
			this.pen1 = p1;
			this.pen2 = p2;
			this.cost = c;
		}
		
		@Override
		public int compareTo(Pen o) {
			return this.cost-o.cost;
		}
		
	}
	
	public static void addEdges(int n, int[][] pens, Queue<Pen> pensQueue, int index) {
		for (int i = 0; i < n+1; i++) {
			
			if (pens[index][i]>0) {
				pensQueue.add(new Pen(index, i, pens[index][i]));
			}
			
		}
	}
	
	public static int MST(int n, int[][] pens) {
		
		Queue<Pen> pensQueue = new PriorityQueue<>();
		Set<Integer> addedPen = new HashSet<>();
		addedPen.add(0);
		
		addEdges(n, pens, pensQueue, 0);
		
		int totalCost = 0;
		
		while(!pensQueue.isEmpty()) {
			
			Pen smallestCost = pensQueue.poll();
			if (addedPen.contains(smallestCost.pen1) && addedPen.contains(smallestCost.pen2)) {
				continue;
			}
			totalCost += smallestCost.cost;
			if (!addedPen.contains(smallestCost.pen1)) {
				addedPen.add(smallestCost.pen1);
				addEdges(n, pens, pensQueue, smallestCost.pen1);
			}
			else if (!addedPen.contains(smallestCost.pen2)) {
				addedPen.add(smallestCost.pen2);
				addEdges(n, pens, pensQueue, smallestCost.pen2);
			}
			
		}
		
		if (addedPen.size()<n) {
			
			totalCost = Integer.MAX_VALUE;
			
		}
		
		return totalCost;
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		GateInfo[][] gates = new GateInfo[1000][1000];
		int[][] pens = new int[n+1][n+1];
		
		for (int i = 0; i < n; i++) {
			int numbVertices = sc.nextInt();
			int[] vertices = new int[numbVertices];
			int[] costs = new int[numbVertices];
			for (int j = 0; j < numbVertices; j++) {
				vertices[j] = sc.nextInt();
			}
			for (int j = 0; j < numbVertices; j++) {
				costs[j] = sc.nextInt();
			}
			for (int j = 0; j < numbVertices; j++) {
				if (gates[vertices[j]][vertices[(j+1)%numbVertices]] == null) {
					gates[vertices[j]][vertices[(j+1)%numbVertices]] = new GateInfo(i, costs[j]);
					gates[vertices[(j+1)%numbVertices]][vertices[j]] = gates[vertices[j]][vertices[(j+1)%numbVertices]];
				}
				else {
					GateInfo currentGate = gates[vertices[j]][vertices[(j+1)%numbVertices]];
					pens[i][currentGate.pen] = costs[j];
					pens[currentGate.pen][i] = pens[i][currentGate.pen];
					currentGate.pen = -1;
				}
			}
		}
		
		int totalCost = MST(n, pens);
		
		for (int i = 0; i < 1000; i++) {
			for (int j = i+1; j < 1000; j++) {
				GateInfo currentGate = gates[i][j];
				if (currentGate!=null && currentGate.pen!=-1) {
					pens[n][currentGate.pen] = currentGate.cost;
					pens[currentGate.pen][n] = pens[n][currentGate.pen];
				}
			}
		}
		
		int outsideCost = MST(n, pens);
		
		System.out.println(Math.min(totalCost, outsideCost));
		
	}

}
