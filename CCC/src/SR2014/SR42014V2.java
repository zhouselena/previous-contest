package SR2014;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

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
			return index - arg0.index;
		}
		
		@Override
	    public boolean equals(Object obj) {
			if (this==obj)
				return true;
			if (obj == null || obj.getClass()!= this.getClass()) 
	           return false;
			
			return this.index == ((Y)obj).index ;
		}
	    
	}
	
	int numbGlasses = 0;
	int threshold = 0;
	int maxX = 0;
	int maxY = 0;
	
	ArrayList<Line> glassLines = null;
	Set<Y> yline = null;
	
	public SR42014V2(int N, int t) {
		this.numbGlasses = N;
		this.threshold = t;
		this.maxX = 0;
		this.glassLines = new ArrayList<Line>();
		this.yline = new TreeSet<Y>();
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
		
		Line first = new Line(x1, y1, y2, tint);
		this.glassLines.add(first);
		Line second = new Line(x2, y1, y2, tint*(-1));
		this.glassLines.add(second);
		
		Y temp = new Y(y1);
		yline.add(temp);
		
		temp = new Y(y2);
		yline.add(temp);
	}
	
	public void addToLines(int y1, int y2, int tint) {
		
		for (Y y: yline) {
			if (y.index < y1) continue ;
			if (y.index >= y2) break;
			
			y.tint += tint;
		}
	}
	
	public long findMax() {
		
		long count = 0 ;
		int previousY = 0;
		boolean found = false ;
		
		for(Y y : yline) {
			if (found) {
				count += (y.index-previousY);
				found = false ;
			}
			previousY = y.index;
			if (y.tint >= this.threshold)
				found = true;
		}
		
		return count;
	}
	
	public long run() {
		
		Collections.sort(glassLines);
		
		long totalArea = 0;
		int lastLine = 0 ;
		
		for (int i=0;i<glassLines.size();i++) {
			Line line = glassLines.get(i);
			if (lastLine!=line.x) {
				
				totalArea += ((line.x - lastLine)*findMax()) ;
				lastLine =  line.x;
			}
			
			addToLines(line.y1,line.y2, line.tint);
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
