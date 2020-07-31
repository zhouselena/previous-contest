package SR2015;
import java.util.Arrays;

import java.util.Scanner;

public class SR52015 {
	
	public int N = 0;
	public int M = 0;
	public int[] nPies = null;
	public int[] mPies = null;
	
	public int[][][][] master = null;
		
	public SR52015(int[] n, int[] m) {
		this.N = n.length;
		this.M = m.length;
		this.nPies = n;
		Arrays.sort(m);
		this.mPies = m;
		this.master = new int[N+1][2][M+2][M+2];
		for (int[][][] a: master) {
			for (int[][] b: a) {
				for (int[] fill:b) {
					Arrays.fill(fill, -1);
				}
			}
		}
	}
	
	public int maxPies(int position, int take, int left, int right) {
		
		int ret = master[position][take][left][right];
		
		if (ret!=-1) {
			return ret;
		}
		
		//System.out.println(position);
		if (position==N) {
			if (left<=right) {
				if (take==1) {
					ret = mPies[right-1] + maxPies(position, 0, left, right-1);
					master[position][take][left][right] = ret;
					return ret;
				}
				ret = maxPies(position, 1,left+1, right);
				master[position][take][left][right] = ret;
				return ret;
			}
			else {
				master[position][take][left][right] = 0;
				return 0;
			}
		}
		if (take==1) {
			ret = Math.max(maxPies(position, 0, left, right), (nPies[position]+maxPies(position+1, 0, left, right)));
			if (left <= right) {
				ret = Math.max(ret, mPies[right-1]+maxPies(position, 0, left, right-1));
			}
		}
		else {
			ret = maxPies(position+1, 1, left, right);
			if (left <= right) {
				ret = Math.max(ret, maxPies(position, 1, left+1, right));
			}
		}
		master[position][take][left][right] = ret;
		return ret;
		
	}
	
	public void run() {
		System.out.println(maxPies(0, 1, 1, M));
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
