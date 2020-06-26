package SR2015;

import java.util.Scanner;

public class SR32015 {

	public int numbGates;
	public int numbPlanes;
	public int[] gates;
	
	public SR32015(int g, int p) {
		this.numbGates = g;
		this.numbPlanes = p;
		this.gates = new int[numbGates];
	}
	
	public int nextInd(int maxGate) {
		int gate = -1;
		for (int i = maxGate; i >= 0; i--) {
			if (gates[i]==0) {
				gate = i;
				break;
			}
		}
		return gate;
	}
	
	public int run(int[] planes) {
		int numbDocked = 0;
		
		for (int i = 0; i < numbPlanes; i++) {
			int ind = nextInd(planes[i]);
			if (ind==-1) {
				break;
			}
			else {
				gates[ind] = 1;
				numbDocked++;
			}
		}
		
		return numbDocked;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		SR32015 game = new SR32015(Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine()));
		int[] planes = new int[game.numbPlanes];
		for (int i = 0; i < planes.length; i++) {
			planes[i] = Integer.parseInt(sc.nextLine())-1;
		}
		
		System.out.println(game.run(planes));
		
		sc.close();
	}

}
