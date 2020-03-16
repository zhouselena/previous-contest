import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SR42018 {
	
	public Map<Integer, Integer> weightTree = new HashMap<Integer, Integer>();
	
	public int howManyTree(int weight) {
		if (weightTree.containsKey(weight)) 
			return weightTree.get(weight);

		int totalCount = 0;
		for (int i = weight; i >=2; i--) {
			int value = howManyTree(weight/i);
			totalCount += value;
		}
		weightTree.put(weight, totalCount);
		return totalCount;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int wei = sc.nextInt();
		SR42018 tree = new SR42018();
		tree.weightTree.put(1, 1);
		System.out.println(tree.howManyTree(wei));
		
		sc.close();
	}

}
