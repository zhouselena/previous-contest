package SR2014;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class SR42014 {
	
	public static class Line implements Comparable<Line> {
		
		int x = 0;
		int y1 = 0;
		int y2 = 0;
		int tint = 0;
		
		public Line(int x, int y1, int y2, int t) {
			this.x = x;
			this.y1 = y1;
			this.y2 = y2;
			this.tint = t;
		}
		
		public int compareTo(Line arg0) {
			if (x == arg0.x) {
				if (tint > 0) {
					return -1;
				}
				else if (arg0.tint > 0){
					return 1;
				}
			}
			return x - arg0.x;
		}
		
	}
	
	int numbGlasses = 0;
	int threshold = 0;
	int maxX = 0;
	int maxY = 0;
	ArrayList<Line> glassLines = null;
	HashMap<Integer, Integer> y = null;
	
	public SR42014(int N, int t) {
		this.numbGlasses = N;
		this.threshold = t;
		this.maxX = 0;
		this.maxY = 0;
		this.glassLines = new ArrayList<Line>();
	}
	
	public void addGlass(int x1, int y1, int x2, int y2, int tint) {
		
		if (x2 > maxX) {
			maxX = x2;
		}
		if (x1 > maxX) {
			maxX = x1;
		}
		if (y2 > maxY) {
			maxY = y2;
		}
		if (y1 > maxY) {
			maxY = y1;
		}
		
		Line first = new Line(x1, y1, y2-1, tint);
		this.glassLines.add(first);
		Line second = new Line(x2, y1, y2-1, tint*(-1));
		this.glassLines.add(second);
	}
	
	public int run() {
		
		Collections.sort(glassLines);
		this.y = new HashMap<Integer, Integer>();
		
		int area = 0;
		
		for (int i = 0; i < this.maxX; i++) {
			for (int l = 0; l < glassLines.size(); l++) {
				Line line = glassLines.get(l);
				if (line.x==i) {
					for (int yCoord = line.y1; yCoord <= line.y2; yCoord++) {
						if (y.containsKey(yCoord))
							y.replace(yCoord, y.get(yCoord)+line.tint);
						else
							y.put(yCoord, line.tint);
					}
				}
			}
			for (int yCoord: y.keySet()) {
				if (y.get(yCoord) >= this.threshold) {
					area++;
				}
			}
		}
		
		return area;
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		SR42014 game = new SR42014(Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine()));
		
		for (int i = 0; i < game.numbGlasses; i++) {
			game.addGlass(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
			sc.nextLine();
		}

		System.out.println(game.run());
		
	}

}
