package SR2011;

import java.util.Scanner;

public class SR22011 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int numb = Integer.parseInt(sc.nextLine());
		String[] student = new String[numb];
		String[] answer = new String[numb];
		
		for (int i = 0; i < numb; i++) {
			student[i] = sc.nextLine();
		}
		
		for (int i = 0; i < numb; i++) {
			answer[i] = sc.nextLine();
		}
		
		int correct = 0;
		for (int i = 0; i < numb; i++) {
			if (student[i].equals(answer[i])) {
				correct++;
			}
		}
		
		System.out.println(correct);
		
	}

}
