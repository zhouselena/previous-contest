package SR2017;

import java.util.ArrayList;
import java.util.Scanner;

public class SR52017V3 {
	
	/*
	 * 10 2 Q
	 * 1 1 1 1 1 2 2 2 2 2
	 * 1 2 3 4 5 6 7 8 9 10
	 * 1 1 4 (1+2+3+4)
	 * 1 7 9 (7+8+9)
	 * 1 9 2 (9+10+1+2)
	 * 1 4 7 (4+5+6+7)
	 * 2 1
	 * 1 4 7 (3+4+6+7)
	 * 2 1
	 * 1 4 7 (2+3+6+7)
	 * 1 9 2 (9+10+4+5)
	 * 2 1
	 * 1 4 7 (1+2+6+7)
	 * 2 1
	 * 2 1
	 * 1 4 7 (4+5+6+7)
	 */
	
	interface RMT{
		void stationLine(int[] stationLine);
		void addStationsToLine(int[] passengers);
		void run(int line);
		int survey(int from, int to);
	}
	
	public class case2 implements RMT{
		
		public class Line{
			
			public int firstStation = -1;
			public int startPos = 0;
			public int totalSum = 0;
			public int totalStations = 0;
			public ArrayList<Integer> stationSum;
			
			public Line() {
				this.stationSum = new ArrayList<Integer>();
			}
			
			public void addStation(int numbPassengers, int statNumb) {
				if (firstStation == -1) {
					firstStation = statNumb;
					stationSum.add(numbPassengers);
				}
				else {
					int sum = stationSum.get(stationSum.size()-1) + numbPassengers;
					stationSum.add(sum);
				}
				totalSum += numbPassengers;
				totalStations++;
			}
			
			public int returnRangeSum(int from, int to) {
				from = from - firstStation;
				to = to - firstStation;
				int sum = 0;
				int realFromIndex = (from - startPos + totalStations) % totalStations;
				int realToIndex = (to - startPos + totalStations) % totalStations;
				if (realToIndex >= realFromIndex) {
					sum = stationSum.get(realToIndex) - (realFromIndex==0?0:stationSum.get(realFromIndex-1));
				}
				else {
					int tempSum = stationSum.get(realFromIndex-1) - stationSum.get(realToIndex);
					sum = totalSum - tempSum;
				}
				return sum;
			}
			
		}
		
		private int N;
		private int M;
		private int Q;
		public Line[] lines;
		public int[] stationLines;
		
		public case2(int numbStations, int numbLines, int numbActions) {
			this.N = numbStations;
			this.M = numbLines;
			this.Q = numbActions;
			this.lines = new Line[M];
			this.stationLines = new int[N];
			lines = new Line[numbLines];
			for (int i = 0; i < numbLines; i++) {
				lines[i] = new Line();
			}
		}
		
		@Override
		public void run(int line) {
			lines[line].startPos = (lines[line].startPos+1) % lines[line].totalStations;
		}
		@Override
		public int survey(int from, int to) {
  			int totalPassengers = 0;
			
			int fromLine = stationLines[from];
			int toLine = stationLines[to];
			
			if (fromLine != toLine) {
				totalPassengers += lines[fromLine].returnRangeSum(from, lines[fromLine].totalStations-1+lines[fromLine].firstStation);
				totalPassengers += lines[toLine].returnRangeSum(lines[fromLine].firstStation, to);
				
				int i = fromLine+1;
				while (i < toLine) {
					totalPassengers += lines[i].totalSum;
					i++;
				}
			}
			else {
				totalPassengers += lines[fromLine].returnRangeSum(from, to);
			}
			
			return totalPassengers;
		}

		@Override
		public void stationLine(int[] stationLine) {
			System.arraycopy(stationLine, 0, stationLines, 0, N);
		}

		@Override
		public void addStationsToLine(int[] passengers) {
			for (int i = 0; i < passengers.length; i++) {
				lines[stationLines[i]].addStation(passengers[i], i);
			}
		}
	}
	
	public class case3 implements RMT{

		public class Line{
			
			public int startPos = 0;
			public int totalStations = 0;
			public int totalSum = 0;
			public ArrayList<Integer> stationSum = null;
			public ArrayList<Integer> stations = null;
			
			public Line() {
				stationSum = new ArrayList<Integer>();
				stations = new ArrayList<Integer>();
			}
			
			public void addStation(int numbPassengers, int statNumb) {
				if (stations.isEmpty()) {
					stationSum.add(numbPassengers);
				}
				else {
					stationSum.add(stationSum.get(totalStations-1)+numbPassengers);
				}
				stations.add(statNumb);
				totalStations++;
				totalSum += numbPassengers;
			}
			
			public int findFirstIndex(int find) {
				if (stations.get(0) > find || stations.get(0) == find) {
					return 0;
				}
				else if (stations.get(stations.size()-1) < find) {
					return -1;
				}
				else if (stations.get(stations.size()-1) == find) {
					return stations.size()-1;
				}
				
				int index = -1;
				int leftInd = 0;
				int rightInd = stations.size()-1;
				while (leftInd < rightInd-1) {
					int middle = (leftInd+rightInd)/2;
					if (stations.get(middle)==find) {
						index = middle;
						break;
					}
					else if (stations.get(middle) > find) {
						rightInd = middle;
					}
					else {
						leftInd = middle;
					}
				}
				if (index==-1) {
					index = rightInd;
				}
				
				return index;
			}
			
			public int findLastIndex(int find) {
				if (stations.get(0) > find) {
					return -1;
				}
				else if (stations.get(stations.size()-1) <= find) {
					return stations.size()-1;
				}
				else if (stations.get(0) == find) {
					return 0;
				}
				
				int index = -1;
				int leftInd = 0;
				int rightInd = stations.size()-1;
				while (leftInd < rightInd-1) {
					int middle = (leftInd+rightInd)/2;
					if (middle==find) {
						index = middle;
						break;
					}
					else if (middle > find) {
						rightInd = middle;
					}
					else {
						leftInd = middle;
					}
				}
				if (index==-1) {
					index = leftInd;
				}
				return index;
			}
			
			public int sumFromRange(int from, int to) {
				from = from - stations.get(0);
				to = to - stations.get(0);
				int sum = 0;
				int realFromIndex = (from - startPos + totalStations) % totalStations;
				int realToIndex = (to - startPos + totalStations) % totalStations;
				if (realToIndex >= realFromIndex) {
					sum = stationSum.get(realToIndex) - (realFromIndex==0?0:stationSum.get(realFromIndex-1));
				}
				else {
					int tempSum = stationSum.get(realFromIndex-1) - stationSum.get(realToIndex);
					sum = totalSum - tempSum;
				}
				return sum;
			}
			
		}
		
		private int N=0;
		private int M=0;
		private int Q=0;
		public Line[] lines;
		public int[] stationBelongToLine;
		
		public case3(int n, int m, int q) {
			this.N = n;
			this.M = m;
			this.Q = q;
			lines = new Line[M];
			stationBelongToLine = new int[N];
			for (int i = 0; i < M; i++) {
				lines[i] = new Line();
			}
		}
		
		@Override
		public void stationLine(int[] stationLine) {
			System.arraycopy(stationLine, 0, stationBelongToLine, 0, N);
			
		}

		@Override
		public void addStationsToLine(int[] passengers) {
			for (int i = 0; i < N; i++) {
				lines[stationBelongToLine[i]].addStation(passengers[i], i);
			}
		}

		@Override
		public void run(int line) {
			lines[line].startPos = (lines[line].startPos + 1)%lines[line].totalStations;
		}

		@Override
		public int survey(int from, int to) {
			int sum = 0;
			
			for (int i = 0; i < M; i++) {
				Line current = lines[i];
				int startInd = current.findFirstIndex(from);
				int endInd = current.findFirstIndex(to);
				if (startInd == -1 || endInd == -1) {
					continue;
				}
				else {
					sum += current.sumFromRange(startInd, endInd);
				}
			}
			
			return sum;
		}
		
	}
	
	public class case4 implements RMT{

		final int BLOCKSIZE = 200;
		private int N;
		private int M;
		private int Q;
		
		private int[] stationBelongsToLine;
		private int[] passengersInStation;
		private int[] blockSum;
		private ArrayList<Integer>[] lines;
		
		public case4(int n, int m) {
			this.N = n;
			this.M = m;
			stationBelongsToLine = new int[N];
			passengersInStation = new int[N];
			blockSum = new int[N/BLOCKSIZE+1];
			lines = new ArrayList[M];
			for (int i = 0; i < M; i++) {
				lines[i] = new ArrayList<Integer>();
			}
		}
		
		@Override
		public void stationLine(int[] stationLine) {
			for (int i = 0; i < N; i++) {
				stationBelongsToLine[i] = stationLine[i];
				lines[stationBelongsToLine[i]].add(i);
			}
			
		}

		@Override
		public void addStationsToLine(int[] passengers) {
			for (int i = 0; i < N; i++) {
				passengersInStation[i] = passengers[i];
				blockSum[i/BLOCKSIZE] += passengers[i];
			}
			
		}

		@Override
		public void run(int line) {
			int lastStatPass = passengersInStation[lines[line].get(lines[line].size()-1)];
			for (int i = lines[line].size()-1; i > 0; i--) {
				blockSum[lines[line].get(i)/BLOCKSIZE] -= passengersInStation[lines[line].get(i)];
				passengersInStation[lines[line].get(i)] = passengersInStation[lines[line].get(i-1)];
				blockSum[lines[line].get(i)/BLOCKSIZE] += passengersInStation[lines[line].get(i)];
			}
			blockSum[lines[line].get(0)/BLOCKSIZE] -= passengersInStation[lines[line].get(0)];
			passengersInStation[lines[line].get(0)] = lastStatPass;
			blockSum[lines[line].get(0)/BLOCKSIZE] += passengersInStation[lines[line].get(0)];
		}

		@Override
		public int survey(int from, int to) {
			int total = 0;
			
			if (from/BLOCKSIZE == to/BLOCKSIZE) {
				for (int i = from; i < to; i++) {
					total += passengersInStation[i];
				}
			}
			else {
				int i = from;
				while (i % BLOCKSIZE != 0) {
					total += passengersInStation[i];
					i++;
				}
				while ((i/BLOCKSIZE)+1 <= to/BLOCKSIZE) {
					total += blockSum[i/BLOCKSIZE];
					i += BLOCKSIZE;
				}
				while (i <= to) {
					total += passengersInStation[i];
					i++;
				}
			}
			
			return total;
		}
		
	}
	
	public void run() {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		int q = sc.nextInt();
		
		int[] belongToLines = new int[n];
		for (int i = 0; i < n; i++) {
			belongToLines[i] = sc.nextInt()-1;
		}
		
		int[] passengersInStations = new int[n];
		for (int i = 0; i < n; i++) {
			passengersInStations[i] = sc.nextInt();
		}
		
		RMT simulator = new case3(n, m, q);
		
		simulator.stationLine(belongToLines);
		simulator.addStationsToLine(passengersInStations);
		
		for (int i = 0; i < q; i++) {
			int command = sc.nextInt();
			if (command==1) {
				System.out.println(simulator.survey(sc.nextInt()-1, sc.nextInt()-1));
			}
			else {
				simulator.run(sc.nextInt()-1);
			}
		}
		
		sc.close();
	
	}
	
	public static void main(String[] args) {

		SR52017V3 game = new SR52017V3();
		game.run();		
	}

}
