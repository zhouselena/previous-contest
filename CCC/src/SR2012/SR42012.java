package SR2012;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SR42012 {

	public final int N;
	public ByteBuffer original = null;
	
	public SR42012 (int n, int[] og) {
		this.N = n;
		this.original = ByteBuffer.allocate(8);
		for (int i = 0; i < og.length; i++) {
			byte b = 0;
			b = (byte) (b | (1 << og[i]-1));
			original.put(i, b);
		}
	}
	
	private ByteBuffer LongToBB (long l) {
		
		ByteBuffer ret = ByteBuffer.allocate(8);
		ret.putLong(l);
		return ret;
		
	}
	
	private int smallestPos(byte c) {
		
		int ret = 8;
		
		for (int i = 0; i < 8; i++) {
			if (((c >> i) & 1) == 1) {
				ret = i;
				break;
			}
		}
		
		return ret;
		
	}
	
	public int game() {
		
		ByteBuffer ending = ByteBuffer.allocate(8);
		for (int i = 0; i < this.N; i++) {
			ending.put(i, (byte)(1<<i));
		}
		long end = ending.getLong(0);
		
		Set<Long> visited = new TreeSet<>();
		Queue<Long> upNext = new LinkedList<>();
		Map<Long, Integer> moves = new HashMap<>();

		upNext.add(original.getLong(0));
		moves.put(original.getLong(0), 0);
		
		while (!upNext.isEmpty()) {
			
			for (int i = 0; i < upNext.size(); i++) {
				
				long curr = upNext.poll();
				
				if (!visited.contains(curr)) {
					visited.add(curr);
					if (curr == end) {
						if (!moves.containsKey(curr)) return 0;
						else return moves.get(curr);
					}
					
					for (int j = 1; j < this.N; j++) {
						ByteBuffer add = LongToBB(curr);
						int smallestJ = smallestPos(add.get(j));
						int smallestJMinus = smallestPos(add.get(j-1));
						if (smallestJ < smallestJMinus) {
							byte jPos = (byte) (add.get(j) - (1 << smallestJ));
							byte jminusPos = (byte) (add.get(j-1) + (1 << smallestJ));
							add.put(j, jPos);
							add.put(j-1, jminusPos);
							upNext.add(add.getLong(0));
							moves.put(add.getLong(0), moves.get(curr)+1);
						}
						else if (smallestJ > smallestJMinus) {
							byte jPos = (byte) (add.get(j) + (1 << smallestJMinus));
							byte jminusPos = (byte) (add.get(j-1) - (1 << smallestJMinus));
							add.put(j, jPos);
							add.put(j-1, jminusPos);
							upNext.add(add.getLong(0));
							moves.put(add.getLong(0), moves.get(curr)+1);
						}
					}
				}
							
			}		
		}
		
		return -1;
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int numb = Integer.parseInt(sc.nextLine());
		
		while (numb != 0) {
			
			int[] ogPosition = new int[numb];
			for (int i = 0; i < numb; i++) {
				ogPosition[i] = sc.nextInt();
			}
			sc.nextLine();
			
			SR42012 game = new SR42012(numb, ogPosition);
			int moves = game.game();
			if (moves == -1) System.out.println("IMPOSSIBLE");
			else System.out.println(moves);
			
			numb = Integer.parseInt(sc.nextLine());
			
		}

	}

}
