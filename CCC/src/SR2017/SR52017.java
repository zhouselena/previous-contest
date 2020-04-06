package SR2017;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class SR52017 {
	
	public class Station {
		
		int stationNumb;
		int currPeople;
		int line;
		
		public Station(int people, int line, int index) {
			this.currPeople = people;
			this.line = line;
			this.stationNumb = index;
		}
		
	}
	
	public List<Station>[] lines;
	public Station[] stations;
	public int N;
	public int M;
	
	@SuppressWarnings("unchecked")
	public SR52017(int n, int m) {
		this.N = n;
		this.M = m;
		this.stations = new Station[N];
		this.lines = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			lines[i] = new ArrayList<Station>();
		}
	}
	
	public void addStationToLine(int line, int numbPeople, int index) {
		Station st = new Station(numbPeople, line, index);
		lines[line].add(st);
		stations[index] = st;
	}
	
	public int surveyAnswer(int from, int to) {
		int total = 0;
		int index = from;
		
		while (index != to) {
			total += stations[index].currPeople;
			if (index >= stations.length-1) {
				index = 0;
			}
			else {
				index++;
			}
		}
		total += stations[to].currPeople;
		
		return total;
	}
	
	public void operate(int line) {
		int temp = lines[line].get(lines[line].size()-1).currPeople;
		for (int i = lines[line].size()-1; i>0; i--) {
			lines[line].get(i).currPeople = lines[line].get(i-1).currPeople;
		}
		lines[line].get(0).currPeople = temp;
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
		
		SR52017 game = new SR52017(numbStations, numbLine);
		
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
