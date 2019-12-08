import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class Gates2 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
		
		@SuppressWarnings("unused")
		int numbGates = sc.nextInt();
		int numbPlanes = sc.nextInt();
		
		int[] planes = new int[numbPlanes];
		for(int i=0; i<numbPlanes; i++) {
			planes[i] = sc.nextInt();
		}
		
		Map<Integer, Integer> gates = new HashMap<Integer, Integer>();
		
		int count = 0;
		
		for(int plane: planes) {
			List<Integer> updateGates = new ArrayList<Integer>();
			int avail = plane;
			while(gates.containsKey(avail)&&avail>0) {
				updateGates.add(avail);
				avail = gates.get(avail);
			}
			if(avail>0) {
				gates.put(avail, avail-1);
				for(int gate: updateGates) {
					gates.put(gate, avail-1);
				}
				count += 1;
			}
			else {
				break;
			}
		}
		
		System.out.println(count);
		
		sc.close();
	}
}
