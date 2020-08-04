package SR2014;

import java.util.Stack;

public class SR32014 {

	public int N = 0;
	public Stack<Integer> mountain = null;
	public Stack<Integer> branch = null;
	
	public SR32014(int n, int[] original) {
		this.N = n;
		this.mountain = new Stack<>();
		this.branch = new Stack<>();
		for (int add: original) {
			mountain.push(add);
		}
	}
	
	public boolean run() {
		
		int current = mountain.pop();
		
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
