import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SR32018 {
	
    public static Queue<tuple> que = new LinkedList<>();
	
	public static class tuple {
		public int x;
		public int y;
		public tuple(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private void visit(char[][] board, int[][] status, tuple currentPos) {
		tuple[] addQ = {new tuple(currentPos.x+1, currentPos.y), new tuple(currentPos.x, currentPos.y+1),
				new tuple(currentPos.x-1, currentPos.y), new tuple(currentPos.x, currentPos.y-1)};
		for (tuple pos: addQ) {

			if (board[pos.x][pos.y]=='R' || board[pos.x][pos.y]=='L' || 
					board[pos.x][pos.y]=='D' || board[pos.x][pos.y]=='U') {
				pos = findLastTup(board, status, pos);
			}
			if (pos.x < 0 || pos.y < 0 || pos.x > board.length || pos.y > board[0].length) {
				continue;
			}
			if (board[pos.x][pos.y]=='W' || board[pos.x][pos.y]=='X') {
				continue;
			}
			
			if (status[pos.x][pos.y] < 0) {
				status[pos.x][pos.y] = status[currentPos.x][currentPos.y] + 1;
				que.add(pos);
			}
		}
	}
	
	private tuple findLastTup(char[][] board, int[][] status, tuple current) {
		while(true) {
			if (status[current.x][current.y]>=0) 
				return new tuple(-1, -1);
			if (board[current.x][current.y]=='R') {
				status[current.x][current.y] = 0 ;
				current.y++;
			}
			else if (board[current.x][current.y]=='L') {
				status[current.x][current.y] = 0 ;
				current.y--;
			}
			else if (board[current.x][current.y]=='D') {
				status[current.x][current.y] = 0 ;
				current.x++;
			}
			else if (board[current.x][current.y]=='U') {
				status[current.x][current.y] = 0 ;
				current.x--;
			}
			else {
				break;
			}
		}
		return current;
	}
	
	public int[][] canGetThere(char[][] board, tuple startPos) {
		int[][] statusBoard = new int[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				statusBoard[i][j] = -1;
			}
		}
		
		int currentX = 0;
		int currentY = 0;
		boolean camSeeSource = false;
		for (int i = 0; i < board.length; i++) {
			if (camSeeSource) {
				break;
			}
			for (int j = 0; j < board[0].length; j++) {
				if (camSeeSource) {
					break;
				}
				if (board[i][j]=='C') {
					currentX = i+1;
					currentY = j;
					while(board[currentX][currentY]!='W') {
						if(board[currentX][currentY]=='.') {
							board[currentX][currentY] = 'X';
						}
						else if(board[currentX][currentY]=='S') {
							camSeeSource = true;
						}
						currentX++;
					}
					currentX = i-1;
					while(board[currentX][currentY]!='W') {
						if(board[currentX][currentY]=='.') {
							board[currentX][currentY] = 'X';
						}
						else if(board[currentX][currentY]=='S') {
							camSeeSource = true;
						}
						currentX--;
					}
					currentX = i;
					currentY = j+1;
					while(board[currentX][currentY]!='W') {
						if(board[currentX][currentY]=='.') {
							board[currentX][currentY] = 'X';
						}
						else if(board[currentX][currentY]=='S') {
							camSeeSource = true;
						}
						currentY++;
					}
					currentY = j-1;
					while(board[currentX][currentY]!='W') {
						if(board[currentX][currentY]=='.') {
							board[currentX][currentY] = 'X';
						}
						else if(board[currentX][currentY]=='S') {
							camSeeSource = true;
						}
						currentY--;
					}
				}
			}
		}
		
		if(!camSeeSource) {
			statusBoard[startPos.x][startPos.y] = 0;
			que.add(startPos);
			while(!que.isEmpty()) {
				visit(board, statusBoard, que.poll());
			}
		}
		
		return statusBoard;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String[] info = sc.nextLine().split(" ");
		int rows = Integer.parseInt(info[0]);
		int cols = Integer.parseInt(info[1]);
		
		char[][] board = new char[rows][cols];
		for (int i = 0; i < rows; i++) {
			String temp = sc.nextLine();
			board[i] = temp.toCharArray();
		}
		
		tuple startPos = new tuple(0, 0);
		
		boolean startFound = false;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (board[i][j]=='S') {
					startPos.x = i;
					startPos.y = j;
					startFound = true;
					break;
				}
			}
			if (startFound) {
				break;
			}
		}
		
		SR32018 game = new SR32018();
		
		int[][] stat = game.canGetThere(board, startPos);
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (board[i][j]=='.' || board[i][j]=='X') {
					System.out.println(stat[i][j]);
				}
			}
		}
		
		sc.close();
	}
}
