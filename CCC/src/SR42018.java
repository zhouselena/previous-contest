import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SR42018 {
	
	public Map<Integer, Long> weightTree = new HashMap<Integer, Long>();
	
	public long howManyTree(int weight) {
		if (weight==1)	return 1;
		if (weightTree.containsKey(weight)) 
			return weightTree.get(weight);

		long totalCount = (weight+1)/2;  // this is the count of (child weight == 1) ;
		
		int sqrtW = (int)(Math.sqrt(weight));
		
		for (int childWeight = 2; childWeight<=sqrtW; childWeight++)
			totalCount += (weight/childWeight-weight/(childWeight+1)) * howManyTree(childWeight) ;
		
		for (int i=2; weight/i>sqrtW; i++)
			totalCount += howManyTree(weight/i);
	
/*
		for (int i = weight/2; i >=2; i--) {
			long value = howManyTree(weight/i);
			totalCount += value;
		}
*/
		weightTree.put(weight, totalCount);
		return totalCount;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int wei = sc.nextInt();
		SR42018 tree = new SR42018();
		tree.weightTree.put(1, (long) 1);
		System.out.println(tree.howManyTree(wei));
		
		sc.close();
	}

}
