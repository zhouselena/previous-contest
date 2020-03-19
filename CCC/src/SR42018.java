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
		
/*		int childWeight = 2 ;
		while(childWeight<=weight/2) {
			if ((childWeight+1)*2 > weight)
			{
				totalCount += (weight/childWeight-1) * howManyTree(childWeight) ;
				break;
			}
			else
			{
				int count = weight/childWeight - weight/(childWeight+1) ;
				totalCount += (count*howManyTree(childWeight));
				childWeight++;
			}
		}
*/
		for (int i = weight/2; i >=2; i--) {
			long value = howManyTree(weight/i);
			totalCount += value;
		}
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
