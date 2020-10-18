package SR2013;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class SR42013 {
	
	int numbKids = 0;
	ArrayList<Integer>[] heights = null;
	
	public SR42013(int n) {
		this.numbKids = n;
        this.heights = new ArrayList[n];
        for (int i = 0; i < n; i++) {
        	heights[i] = new ArrayList<Integer>();
        }
	}
	
	public void addHeight(int taller, int shorter) {
		heights[taller].add(shorter);
	}
	
    private Queue<Integer> points = new LinkedList<>();
    private Set<Integer> visited = new HashSet<Integer>();
    private int BFS(int start, int find) {
    	points.add(start);
    	
    	while (!points.isEmpty()) {
    		ArrayList<Integer> current = heights[points.poll()];
    		for (int child: current) {
    			if (visited.contains(child)) {
    				continue;
    			}
    			if (child==find) {
    				return 1;
    			}
    			points.add(child);
    			visited.add(child);
    		}
    	}
    	
    	return 0;
    }
    
	public int isTaller(int a, int b) {
		
		int tallerA = BFS(a, b);
		
		if (tallerA==0) {
			int tallerB = BFS(b, a);
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
		
		SR42013 game = new SR42013(n);
		
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
