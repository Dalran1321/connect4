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
		int counter = 0;
		col -= 1; // Set to index values
		int cRow = row;

		CellState player = board[row][col].getState();
		boolean foundDifferent = false;
		while (!foundDifferent && cRow < rows && counter < 4) {
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
		int cCol2 = col;

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
		while (!foundDifferentRight && cCol2-1 >= 0 && counter < 4) {
			if (board[row][cCol2 - 1].getState() == player) {
				counter++;
				cCol2--;
			} else {
				foundDifferentRight = true;
			}
		}
		return counter == 4;
	}

	public boolean isDiagonalWinner(int col, int row) {
		int counter = 0;
		int counter2 = 0;
		col -= 1; // Set to index values
		int cCol = col;
		int cCol2 = col;
		int cRow = row;
		int cRow2 = row;

		CellState player = board[row][col].getState();
		boolean foundDifferentDiagonalDown = false;
		boolean foundDifferentDiagonalUp = false;

		// bottom right to top left
		while (!foundDifferentDiagonalDown && cCol >= 0 && cRow >= 0 && counter < 4) {
			if (board[cRow][cCol].getState() == player) {
				counter++;
				cCol--;
				cRow--;
				System.out.println(" counter " + counter);
			} else {
				foundDifferentDiagonalDown = true;
			}
		}
		// 7 to 4 top left to bottom right
		while (!foundDifferentDiagonalUp && cCol2 + 1 < cols && cRow2 + 1 < rows && counter < 4) {
			if (board[cRow2 + 1][cCol2 + 1].getState() == player) {
				counter++;
				cCol2++;
				cRow2++;
				System.out.println(" counter " + counter);
			} else {
				foundDifferentDiagonalUp = true;
			}
		}

		return counter == 4;
	}

	public boolean isDiagonalWinner2(int col, int row) {
		int counter = 0;
		int counter2 = 0;
		col -= 1; // Set to index values
		int cCol3 = col;
		int cCol4 = col;
		int cRow3 = row;
		int cRow4 = row;

		CellState player = board[row][col].getState();
		boolean foundDifferentDiagonalDown = false;
		boolean foundDifferentDiagonalUp = false;

		// 4 to 1 top right to bottom left
		while (!foundDifferentDiagonalDown && cCol3 >= 0 && cRow3 < rows && counter2 < 4) {
			if (board[cRow3][cCol3].getState() == player) {
				counter2++;
				cCol3--;
				cRow3++;
				System.out.println(" counter2 " + counter2);
			} else {
				foundDifferentDiagonalDown = true;
			}
		}

		// 4 to 1 bottom left to top right
		while (!foundDifferentDiagonalUp && cCol4+1 < cols && cRow4-1 >= 0 && counter2 < 4) {
			if (board[cRow4-1][cCol4+1].getState() == player) {
				counter2++;
				cCol4++;
				cRow4--;
				System.out.println(" counter2 " + counter2);
			} else {
				foundDifferentDiagonalUp = true;
			}
		}
		return counter2 == 4;
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
