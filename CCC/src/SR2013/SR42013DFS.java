package SR2013;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class SR42013DFS {
	
	int numbKids = 0;
	ArrayList<Integer>[] heights = null;
	
	public SR42013DFS(int n) {
		this.numbKids = n;
        this.heights = new ArrayList[n];
        for (int i = 0; i < n; i++) {
        	heights[i] = new ArrayList<Integer>();
        }
	}
	
	public void addHeight(int taller, int shorter) {
		heights[taller].add(shorter);
	}
	
    private Stack<Integer> points = new Stack<>();
    private Set<Integer> visited = new HashSet<Integer>();
    private int DFS(int start, int find) {
    	
    	points.clear();
    	visited.clear();
    	points.add(start);
    	
    	while (!points.isEmpty()) {
    		int currentInd = points.pop();
    		ArrayList<Integer> current = heights[currentInd];
    		if (!visited.contains(currentInd) && currentInd==find) {
    			return 1;
    		}
    		visited.add(currentInd);
    		for (int child: current) {
    			points.add(child);
    		}
    	}
    	
    	return 0;
    }
    
	public int isTaller(int a, int b) {
		
		int tallerA = DFS(a, b);
		
		if (tallerA==0) {
			int tallerB = DFS(b, a);
			if (tallerB==0) {
				return -1;
			}
			else {
				return 0;
			}
		}
		else {
			return 1;
		}
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.nextLine();
		
		SR42013DFS game = new SR42013DFS(n);
		
		for (int i = 0; i < m; i++) {
			game.addHeight(sc.nextInt()-1, sc.nextInt()-1);
			sc.nextLine();
		}
		
		int run = game.isTaller(sc.nextInt()-1, sc.nextInt()-1);
		if (run == 1) {
			System.out.println("yes");
		}
		else if (run == 0){
			System.out.println("no");
		}
		else {
			System.out.println("unknown");
		}
		
		sc.close();
		
	}

}
