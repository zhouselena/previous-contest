package SR2014;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class SR42014V2 {
	
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
	
	public static class Y implements Comparable<Y> {
		
		int index = 0;
		int tint = 0;
		
		public Y(int index) {
			this.index = index;
			this.tint = 0;
		}
		
		public int compareTo(Y arg0) {
			if (index == arg0.index) {
				if (tint > 0) {
					return -1;
				}
				else if (arg0.tint > 0){
					return 1;
				}
			}
			return index - arg0.index;
		}
		
	}
	
	int numbGlasses = 0;
	int threshold = 0;
	int maxX = 0;
	int maxY = 0;
	
	ArrayList<Line> glassLines = null;
	ArrayList<Y> yline = null;
	
	public SR42014V2(int N, int t) {
		this.numbGlasses = N;
		this.threshold = t;
		this.maxX = 0;
		this.glassLines = new ArrayList<Line>();
		this.yline = new ArrayList<Y>();
	}
	
	public void addGlass(int x1, int y1, int x2, int y2, int tint) {
		
		if (x2 > maxX) {
			maxX = x2;
		}
		if (x1 > maxX) {
			maxX = x1;
		}
		if (y2-1 > maxY) {
			maxY = y2-1;
		}
		if (y1 > maxY) {
			maxY = y1;
		}
		
		Line first = new Line(x1, y1, y2-1, tint);
		this.glassLines.add(first);
		Line second = new Line(x2, y1, y2-1, tint*(-1));
		this.glassLines.add(second);
		
		Y temp = new Y(y1);
		if (!yline.contains(temp)) {
			yline.add(temp);
		}
		
		temp = new Y(y2-1);
		if (!yline.contains(temp)) {
			yline.add(temp);
		}
		
	}
	
	public void addToLines(int y1, int y2, int tint) {
		for (int i = 0; i < yline.size(); i++) {
			Y temp = yline.get(i);
			if (temp.index>=y1 && temp.index<y2) {
				temp.tint += tint;
			}
		}
	}
	
	public int findMax() {
		
		int y1 = -1;
		int y2 = -1;
		
		int cont = 0;
		for (int i = 0; i < yline.size(); i++) {
			if (yline.get(i).tint >= this.threshold) {
				y1 = yline.get(i).index;
				cont = i;
				break;
			}
		}
		
		for (int i = cont; cont < yline.size(); i++) {
			if (yline.get(i).tint < this.threshold) {
				y2 = yline.get(i).index;
				break;
			}
		}
		
		if (y1==-1) {
			return 0;
		}
		if (y2==-1) {
			return (maxY - yline.get(y1).index);
		}
		
		return y2-y1;
		
	}
	
	public int run() {
		
		Collections.sort(glassLines);
		Collections.sort(yline);
		
		int totalArea = 0;
		
		for (int i = 0; i < maxX; i++) {
			
			for (int j = 0; j < glassLines.size(); j++) {
				Line line = glassLines.get(j);
				if (line.x==i) {
					addToLines(line.y1, line.y2, line.tint);
				}
			}
			totalArea += findMax();
			
		}
		
		
		return totalArea;
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		SR42014V2 game = new SR42014V2(Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine()));
		
		for (int i = 0; i < game.numbGlasses; i++) {
			game.addGlass(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
			sc.nextLine();
		}

		System.out.println(game.run());
		
		sc.close();
		
	}

}
