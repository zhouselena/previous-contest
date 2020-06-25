package SR2015;

import java.util.Scanner;

public class SR22015 {

	public class Athlete {
		public int size;
		public int jersey;
		
		public Athlete(int s, int j) {
			size = s;
			jersey = j;
		}
	}
	
	public int numbJerseys;
	public int numbAthletes;
	public int[] jerseys;
	public Athlete[] athletes;
	
	public SR22015(int j, int a) {
		this.numbJerseys = j;
		this.numbAthletes = a;
		this.jerseys = new int[numbJerseys];
		this.athletes = new Athlete[numbAthletes];
	}
	
	public void addJersey(char c, int i) {
		if (c=='S') {
			this.jerseys[i] = 1;
		}
		else if (c=='M') {
			this.jerseys[i] = 2;
		}
		else if (c=='L') {
			this.jerseys[i] = 3;
		}
	}
	
	public void addAthlete(char size, int jersey, int numb) {
		jersey = jersey-1;
		if (size=='S') {
			athletes[numb] = new Athlete(1, jersey);
		}
		else if (size=='M') {
			athletes[numb] = new Athlete(2, jersey);
		}
		else if (size=='L') {
			athletes[numb] = new Athlete(3, jersey);
		}
	}
	
	public int run() {
		
		int assigned = 0;
		
		for (int i = 0; i < this.numbAthletes; i++) {
			Athlete curr = this.athletes[i];
			if (jerseys[curr.jersey] < curr.size) {
				continue;
			}
			else {
				assigned++;
				jerseys[curr.jersey] = 0;
			}
		}
		
		return assigned;
		
	}
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int j = Integer.parseInt(sc.nextLine());
		int a = Integer.parseInt(sc.nextLine());
		
		SR22015 game = new SR22015(j, a);
		
		for (int i = 0; i < j; i++) {
			game.addJersey(sc.nextLine().charAt(0), i);
		}
		
		for (int i = 0; i < a; i++) {
			String info[] = sc.nextLine().split(" ");
			game.addAthlete(info[0].charAt(0), Integer.parseInt(info[1]), i);
		}
				
		System.out.println(game.run());
		
		sc.close();
		
	}

}
