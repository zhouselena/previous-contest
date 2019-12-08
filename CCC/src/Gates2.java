import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class Gates2 {
	private Map<Integer, Integer> gates = null;
	
	public Gates2() {
		gates = new HashMap<Integer, Integer>();
	}
	public boolean parkedPlane(int maxGate){
		int avail = maxGate;
		ArrayList<Integer> updateGates = new ArrayList<Integer>();
		while(gates.containsKey(avail)&&avail>0) {
			updateGates.add(avail);
			avail = gates.get(avail);
		}
		gates.put(avail, avail-1);
		for(int update: updateGates) {
			gates.put(update, avail-1);
		}
		if(avail>0) {
			return true;
		}
		else {
			return false;
		}
	}
	public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
		
		int numbGates = sc.nextInt();
		int numbPlanes = sc.nextInt();
		
		Gates2 ccc = new Gates2();
		
		int[] planes = new int[numbPlanes];
		for(int i=0; i<numbPlanes; i++) {
			planes[i] = sc.nextInt();
		}
		
		int count = 0;
		
		for(int plane: planes) {
			if (ccc.parkedPlane(plane)) {
				count++;
			}
			else {
				break;
			}
		}
		
		System.out.println(count);
		
		sc.close();
	}
}
