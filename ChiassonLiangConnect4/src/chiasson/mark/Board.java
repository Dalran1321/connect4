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

	public boolean isVerticalAI(int col, int row) {
		int counter = 0;
		col -= 1; // Set to index values
		int cRow = row;

		CellState player = board[row][col].getState();
		boolean foundDifferent = false;
		while (!foundDifferent && cRow < rows && counter < 3) {
			if (board[cRow][col].getState() == player) {
				counter++;
				cRow++;
			} else {
				foundDifferent = true;
			}
		}
		return counter == 3;
	}

	public boolean isHorizontalWinner(int col, int row) {
		int counter = 0;
		col -= 1; // Set to index values
		int cCol = col;

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
		cCol= col;
		while (!foundDifferentRight && cCol - 1 >= 0 && counter < 4) {
			if (board[row][cCol - 1].getState() == player) {
				counter++;
				cCol--;
			} else {
				foundDifferentRight = true;
			}
		}
		return counter == 4;
	}

	public boolean isDiagonalWinner(int col, int row) {
		int counter = 0;
		col -= 1; // Set to index values
		int cCol = col;
		int cRow = row;

		CellState player = board[row][col].getState();
		boolean foundDifferentDiagonalDown = false;
		boolean foundDifferentDiagonalUp = false;

		// bottom right to top left
		while (!foundDifferentDiagonalDown && cCol >= 0 && cRow >= 0 && counter < 4) {
			if (board[cRow][cCol].getState() == player) {
				counter++;
				cCol--;
				cRow--;
			} else {
				foundDifferentDiagonalDown = true;
			}
		}
		 cCol = col;
		 cRow = row;
		// 7 to 4 top left to bottom right
		while (!foundDifferentDiagonalUp && cCol + 1 < cols && cRow + 1 < rows && counter < 4) {
			if (board[cRow + 1][cCol + 1].getState() == player) {
				counter++;
				cCol++;
				cRow++;
			} else {
				foundDifferentDiagonalUp = true;
			}
		}

		return counter == 4;
	}

	public boolean isDiagonalWinner2(int col, int row) {
		int counter = 0;
		col -= 1; // Set to index values
		int cCol = col;
		int cRow = row;

		CellState player = board[row][col].getState();
		boolean foundDifferentDiagonalDown = false;
		boolean foundDifferentDiagonalUp = false;

		// 4 to 1 top right to bottom left
		while (!foundDifferentDiagonalDown && cCol >= 0 && cRow < rows && counter < 4) {
			if (board[cRow][cCol].getState() == player) {
				counter++;
				cCol--;
				cRow++;
			} else {
				foundDifferentDiagonalDown = true;
			}
		}
		 cCol = col;
		 cRow = row;
		// 4 to 1 bottom left to top right
		while (!foundDifferentDiagonalUp && cCol + 1 < cols && cRow - 1 >= 0 && counter< 4) {
			if (board[cRow - 1][cCol + 1].getState() == player) {
				counter++;
				cCol++;
				cRow--;
			} else {
				foundDifferentDiagonalUp = true;
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
