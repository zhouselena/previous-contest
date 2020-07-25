package SR2015;

import java.util.Scanner;

public class SR52015 {
	
	public int N = 0;
	public int[] nPies = null;
	public int[] maxNPies = null;
	public int M = 0;
	public int[] mPies = null;
		
	public SR52015(int[] n, int[] m) {
		this.N = n.length;
		this.M = m.length;
		this.nPies = n;
		this.maxNPies = new int[N];
		this.mPies = m;
	}
	
	public int maxPies() {
		
		maxNPies[N-1] = nPies[N-1];
		maxNPies[N-2] = nPies[N-1]>nPies[N-2]?nPies[N-1]:nPies[N-2];
		
		for (int i = N-3; i >=0; i--) {
			int firstandlast = nPies[i] + maxNPies[i+2];
			int second = maxNPies[i+1];
			maxNPies[i] = firstandlast>second?firstandlast:second;
		}
		
		return maxNPies[0];
	}
	
	public void run() {
		System.out.println(maxPies());
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = Integer.parseInt(sc.nextLine());
		int[] nPies = new int[n];
		
		for (int i = 0; i < n; i++) {
			nPies[i] = Integer.parseInt(sc.nextLine());
		}
		
		int m = Integer.parseInt(sc.nextLine());
		int[] mPies = new int[m];
		
		for (int i = 0; i < m; i++) {
			mPies[i] = Integer.parseInt(sc.nextLine());
		}
		
		sc.close();
		
		SR52015 game = new SR52015(nPies, mPies);
		
		game.run();
		
	}

}
