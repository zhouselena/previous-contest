package SR2015;

import java.util.Scanner;

public class SR52015 {
	
	public int N = 0;
	public int[] nPies = null;
	public int M = 0;
	public int[] mPies = null;
		
	public SR52015(int[] n, int[] m) {
		this.N = n.length;
		this.M = m.length;
		this.nPies = n;
		this.mPies = m;
	}
	
	public int maxPies(int[] pies, int startPoint) {
		if (startPoint >= pies.length) {
			return 0;
		}
		else if (startPoint == pies.length-1) {
			return pies[pies.length-1];
		}
		int first = maxPies(pies, startPoint+1);
		int second = pies[startPoint] + maxPies(pies, startPoint+2);
		return first>second?first:second;
	}
	
	public void run() {
		
		int max = maxPies(nPies, 0);
		System.out.println(max);
		
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
