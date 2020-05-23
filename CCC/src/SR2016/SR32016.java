package SR2016;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class SR32016 {
	
	public class Tree {
		
		public int numbNodes = 0;
		public ArrayList<Integer>[] edges = null;
		
		public int vertexOfLongestPath1 = 0;
		public int vertexOfLongestPath2 = 0;
		
		private int[] visited;
        private Queue<Integer> points = new LinkedList<>();
		private boolean[] visitRestaurant;

		public Tree(int numb, int[] phoRest) {
			this.numbNodes = numb;
			edges = new ArrayList[numbNodes];
			this.visited = new int[numbNodes];
			for (int i = 0; i < edges.length; i++) {
				edges[i] = new ArrayList<Integer>();
			}
			for(int i = 0; i < numbNodes; i++) {
				visited[i] = -1;
			}
			visitRestaurant = new boolean[numbNodes];
			for (int rest: phoRest) {
				visitRestaurant[rest] = true;
			}
		}
		
		public void addEdge(int to, int from) {
			edges[from].add(to);
			edges[to].add(from);
		}
		        
		private void BFS(int pointInd) {
	        points.add(pointInd);
	        visited[pointInd] = 0;
	        while (!points.isEmpty()) {
	        	int temp = points.poll();
	        	for (int i = 0; i < edges[temp].size(); i++) {
	        		int pointTo = edges[temp].get(i);
        			if (visited[pointTo]==-1) {
        				points.add(pointTo);
	        			visited[pointTo] = visited[temp]+1;
        			}
		        }
	        }
		}
		
		public int findLongestPath() {
			
			for (int i = 0; i < visited.length; i++) {
		    	   visited[i] = -1;
		    }
			
			int startPoint = 0;
			for (int i = 0; i < visitRestaurant.length; i++) {
				if (visitRestaurant[i]) {
					startPoint = i;
					break;
				}
			}
			
			BFS(startPoint);
			
			for (int i = 0; i < visited.length; i++) {
	        	if (visited[i] > visited[vertexOfLongestPath1]) {
	        		this.vertexOfLongestPath1 = i;
	        	}
	        }
	        
	       for (int i = 0; i < visited.length; i++) {
	    	   visited[i] = -1;
	       }
	       
	       BFS(vertexOfLongestPath1);
	       
	       for (int i = 0; i < visited.length; i++) {
	        	if (visited[i] > visited[vertexOfLongestPath2]) {
	        		this.vertexOfLongestPath2 = i;
	        	}
	        }
	       
	       return visited[vertexOfLongestPath2];
		}
	
		private void trimLeaf(int toTrim) {
			for (int i = 0; i < edges[toTrim].size(); i++) {
				edges[edges[toTrim].get(i)].remove((Integer)(toTrim));
			}
			edges[toTrim].clear();
			numbNodes--;
		}
		
		public boolean needTrim(int parent, int current) {
			
			boolean trim = visitRestaurant[current]? false: true;
			
			int[] visitChild = new int[edges[current].size()];
			for (int i = 0; i < visitChild.length; i++) {
				visitChild[i] = edges[current].get(i);
			}
			
			for (int nextChild: visitChild) {
				if (nextChild==parent) {
					continue;
				}
				if (visitRestaurant[nextChild]) {
					needTrim(current, nextChild);
					trim = false;
				}
				else {
					if (needTrim(current, nextChild)) {
						trimLeaf(nextChild);
					}
					else {
						trim = false;
					}
				}
			}
			
			return trim;
		}
		
	}
	
	public int N;
	public int M;
	public Tree restaurants;
	public int[] phoRest;
	
	public SR32016(int n, int m, int[] pho) {
		this.N = n;
		this.M = m;
		restaurants = new Tree(N, pho);
		this.phoRest = pho;
	}
	
	public int run() {
		restaurants.needTrim(-1, phoRest[0]);
		int longestPath = restaurants.findLongestPath();
		return longestPath + ((restaurants.numbNodes-(longestPath+1))*2);
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int[] pho = new int[m];
		for (int i = 0; i < m; i++) {
			pho[i] = sc.nextInt();
		}
				
		SR32016 game = new SR32016(n,m,pho);
		for (int i = 0; i < n-1; i++) {
			game.restaurants.addEdge(sc.nextInt(), sc.nextInt());
		}
		
		System.out.println(game.run());
	}

}
