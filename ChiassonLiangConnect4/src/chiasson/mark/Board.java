package chiasson.mark;

import chiasson.mark.Cell;
import chiasson.mark.CellState;

public class Board {
	private Cell[][] board;
	private int rows;
	private int cols;

	public Board(int aRows, int aCols) {
		board = new Cell[aRows][aCols];
		rows = aRows;
		cols = aCols;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				board[i][j] = new Cell(); // no color
			}
		}
	}

	public CellState getState(int x, int y) {
		return  board[x][y].getState();
	}

	public boolean isValid(int x, int y) {
		return (x >= 0 && x < rows) && (y >= 0 && y < cols);
	}

	public boolean isColumnFilled(int col) {
		if(board[0][col - 1].getState() != CellState.EMPTY) {
			return true;
		}
		return false;
	}

	public int place(int col, CellState player) {

		int r = rows ;
		int c = col -1 ;

		boolean foundRow = false;
		while (!foundRow) {
			if (board[r-1][c].getState() == CellState.EMPTY) {
				foundRow = true;
			}
			r--;
		}
		board[r][c].setState(player);
		return r;
	}

	/*public boolean isVerticalWinner(int col) {
		int counter =0;
		if(place<=4) {
			for (int i =0; i<=4; i++) {
				if (board[r][col].getState() == board[r-i][col].getState())
				counter++;
			}
		}
		if (counter==4) {
		return true;
		}
		return false;
	}*/

	public void display() {
		System.out.println("CONNECT FOUR BOARD");
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.printf("%s ", board[i][j]);
			}
			System.out.println();
		}
	}
}
