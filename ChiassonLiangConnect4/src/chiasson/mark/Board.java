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
		return board[x][y].getState();
	}

	public boolean isValid(int x, int y) {
		return (x >= 0 && x < rows) && (y >= 0 && y < cols);
	}

	public boolean isColumnFilled(int col) {
		if (board[0][col - 1].getState() != CellState.EMPTY) {
			return true;
		}
		return false;
	}

	public int place(int col, CellState player) {

		int r = rows;
		int c = col - 1;

		boolean foundRow = false;
		while (!foundRow) {
			if (board[r - 1][c].getState() == CellState.EMPTY) {
				foundRow = true;
			}
			r--;
		}
		board[r][c].setState(player);
		return r;
	}

	public boolean isVerticalWinner(int col, int row) {
		int  counter = 0;
		col -= 1; // Set to index values
		int cRow = row;

		CellState player = board[row][col].getState();
		boolean foundDifferent = false;
		while (!foundDifferent && cRow < rows  && counter < 4) {
			if (board[cRow][col].getState() == player) {
				counter++;
				cRow++;
			} else {
				foundDifferent = true;
			}
		}
		return counter == 4;
	}

	public boolean isHorizontalWinner(int col, int row) {
		int counter = 0;
		col -= 1; // Set to index values
		int cCol = col;
		int cCol2 = col-1;

		CellState player = board[row][col].getState();
		boolean foundDifferentLeft = false;
		boolean foundDifferentRight = false;
		while (!foundDifferentLeft && cCol < cols && counter < 4) {
			if (board[row][cCol].getState() == player) {
				counter++;
				cCol++;
			} else {
				foundDifferentLeft = true;
			}
		}
		while (!foundDifferentRight && cCol2 >=0 && counter < 4) {
			if (board[row][cCol2].getState() == player) {
				counter++;
				cCol2--;
				//System.out.println("col "+cCol2);
				//System.out.println("counter "+counter);
			} else {
				foundDifferentRight = true;
			}
		}
		return counter == 4;
	}

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
