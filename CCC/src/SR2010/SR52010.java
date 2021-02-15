package SR2010;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class SR52010 {

	public static class Node {

		public Node left = null;
		public Node right = null;
		public int value;
		public int[] leafPoints = null;
		public int[] leafWithEdge = null;

		public Node(int v, int p) {
			this.value = v;
			this.leafPoints = new int[p + 1];
			leafWithEdge = new int[p+1];
		}

		public Node() {
			this.leafPoints = new int[2501];
			leafWithEdge = new int[2501];
		}

	}

	public SR52010() {
	}

	// figure out maximum value for one node (choose nutrient or width)
	private int singleLeaf(Node node, int allocatedPoints) {

		if (node.leafWithEdge[allocatedPoints]==0) {
			int max = 0;

			for (int i = 0; i <= allocatedPoints; i++) {
				int conveyable = (1 + i) * (1 + i);
				int nutrients = profit(node, allocatedPoints - i);
				int current = Math.min(conveyable, nutrients);
				if (current > max)
					max = current;
			}
			if (node.left==null && node.right==null) {
				node.leafPoints[allocatedPoints] = max;
			}
			
			node.leafWithEdge[allocatedPoints] = max;
		}

		return node.leafWithEdge[allocatedPoints];

	}

	public int combineTwoLeaves(Node l1, Node l2, int allocatedPoints) {

		int max = 0;
		for (int i = 0; i <= allocatedPoints; i++) {
			int maxL = singleLeaf(l1, i);
			int maxR = singleLeaf(l2, allocatedPoints - i);
			int currentTotal = maxL + maxR;
			if (currentTotal > max)
				max = currentTotal;
		}
		return max;

	}

	public int profit(Node root, int allocatedPoints) {

		if (root.left == null && root.right == null) {
			return root.value + allocatedPoints;
		}
		if (root.leafPoints[allocatedPoints] == 0) {
			root.leafPoints[allocatedPoints] = combineTwoLeaves(root.left, root.right, allocatedPoints);

		}

		return root.leafPoints[allocatedPoints];

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String info = sc.nextLine();
		int maxPoints = Integer.parseInt(sc.nextLine());
		Node root = BuildATree(info, maxPoints);
		SR52010 game = new SR52010();

		System.out.println(game.profit(root, maxPoints));

	}

	public static Node BuildATree(String info, int p) {

		// stack, parent node -> return root
		// three cases
		// whitespace -> position ++
		// left bracket -> push parent into stack
		// right bracket -> pop out parent node from stack
		// numbers -> find the numbers
		// take out the substring to get number
		// made new node
		// if left is empty put into left, if left isn't empty then put into right

		Stack<Node> stk = new Stack<>();
		Node current = null;
		int pos = 0;
		Set<Character> dict = new HashSet<>(Arrays.asList(' ', '(', ')'));

		while (pos < info.length()) {
			if (info.charAt(pos) == ' ') {
				pos++;
				continue;
			} else if (info.charAt(pos) == '(') {
				if (current != null)
					stk.push(current);
				current = new Node();
				pos++;
			} else if (info.charAt(pos) == ')') {
				if (!stk.isEmpty()) {
					if (stk.peek().left == null) {
						stk.peek().left = current;
					} else if (stk.peek().right == null) {
						stk.peek().right = current;
					}
					current = stk.pop();
				}
				pos++;
			} else {
				int indTo = pos;
				while (indTo < info.length() && !dict.contains(info.charAt(indTo))) {
					indTo++;
				}
				int numb = Integer.parseInt(info.substring(pos, indTo));
				if (current == null)
					return new Node(numb, p);
				else if (current.left == null) {
					current.left = new Node(numb, p);
				} else {
					current.right = new Node(numb, p);
				}
				pos = indTo;
			}
		}
		return current;
	}

}
