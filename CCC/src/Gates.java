import java.util.Arrays;
import java.util.Scanner;

public class Gates {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
		
		int numbGates = sc.nextInt();
		int numbPlanes = sc.nextInt();
		
		int[] planes = new int[numbPlanes];
		for(int i=0; i<numbPlanes; i++) {
			planes[i] = sc.nextInt();
		}
		
		int[] gates = new int[numbGates];
		Arrays.fill(gates, 0);
		
		int count = 0;
		
		for(int plane: planes) {
			boolean docked = false;
			for(int i=(plane-1); i>=0; i--) {
				if(gates[i]==0) {
					gates[i] = 1;
					count += 1;
					docked = true;
					break;
				}
			if(docked==false) {
				break;
			}
			}
		}
		
		System.out.println(count);
		
		sc.close();
	}
}
