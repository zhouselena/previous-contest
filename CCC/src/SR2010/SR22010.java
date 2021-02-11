package SR2010;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SR22010 {

	public static void main(String[] args) {
		
		Map<String, Character> alphabet = new HashMap<>();
		Scanner sc = new Scanner(System.in);
		
		int n = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < n; i++) {
			String[] info = sc.nextLine().split(" ");
			alphabet.put(info[1], info[0].charAt(0));
		}
		
		String code = sc.nextLine();
		
		StringBuilder decoded = new StringBuilder();
		StringBuilder curr = new StringBuilder();
		for (int i = 0; i < code.length(); i++) {
			curr.append(code.charAt(i));
			if (alphabet.containsKey(curr.toString())) {
				decoded.append(alphabet.get(curr.toString()));
				curr = new StringBuilder();
			}
		}
		
		System.out.println(decoded.toString());
		
	}

}
