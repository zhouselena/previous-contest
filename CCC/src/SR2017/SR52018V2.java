package SR2017;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class SR52018V2 {
	
	public List<Integer>[] lines;
	public int[] stations;
	public int[] sumPeople;
	public int N;
	public int M;
	public int startPos;
	
	@SuppressWarnings("unchecked")
	public SR52018V2(int n, int m) {
		this.N = n;
		this.M = m;
		this.stations = new int[N];
		this.sumPeople = new int[N];
		this.lines = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			lines[i] = new ArrayList<Integer>();
		}
		this.startPos = 0;
	}
	
	public void addStationToLine(int line, int numbPeople, int index) {
		lines[line].add(index);
		stations[index] = numbPeople;
	}
	
	public int surveyAnswer(int from, int to) {
		
		for (int i = startPos; i <= to; i++) {
			if (i==0) {
				sumPeople[i] = stations[i];
			}
			else {
				sumPeople[i] = stations[i] + sumPeople[i-1];
			}
		}
		if (startPos <= to) {
			startPos = to+1;
		}
		
		return sumPeople[to] - (from==0?0:sumPeople[from-1]);
		
	}
	
	public void operate(int line) {
		int temp = stations[lines[line].get(lines[line].size()-1)];
		for (int i = lines[line].size()-1; i>0; i--) {
			stations[lines[line].get(i)] = stations[lines[line].get(i-1)];
		}
		stations[lines[line].get(0)] = temp;
		if (startPos > lines[line].get(0)) {
			startPos = lines[line].get(0);
		}
	}
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int numbStations = sc.nextInt();
		int numbLine = sc.nextInt();
		int numbActions = sc.nextInt();
		
		int[] stationsLines = new int[numbStations];
		for (int i = 0; i < numbStations; i++) {
			stationsLines[i] = sc.nextInt()-1;
		}
		int[] numbPeople = new int[numbStations];
		for (int i = 0; i < numbStations; i++) {
			numbPeople[i] = sc.nextInt();
		}
		
		SR52018V2 game = new SR52018V2(numbStations, numbLine);
		
		for (int i = 0; i < numbStations; i++) {
			game.addStationToLine(stationsLines[i], numbPeople[i], i);
		}
		
		List<Integer> toPrint = new ArrayList<Integer>();
		
		for (int i = 0; i < numbActions; i++) {
			if (sc.nextInt()==1) {
				toPrint.add(game.surveyAnswer(sc.nextInt()-1, sc.nextInt()-1));
			}
			else {
				game.operate(sc.nextInt()-1);
			}
		}
		
		for (int print: toPrint) {
			System.out.println(print);
		}
		
		sc.close();
		
	}

}
