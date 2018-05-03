package chiasson.mark;

import chiasson.mark.Cell;
import chiasson.mark.CellState;

/**
 * Represents the board used to play the connect four
 *
 * @author Fred Liang, Mark Chiasson,Aaron Robertson
 * @version 1.0
 */
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

	/**
	 * Provides the state of the current cell
	 * 
	 * @param row
	 *            -the row of the current cell
	 * 
	 * @param col
	 *            -the column of the current cell
	 * 
	 * @return the state of the current cell
	 */
	public CellState getState(int row, int col) {
		return board[row][col].getState();
	}

	/*public boolean isValid(int row, int col) {
		return (row >= 0 && row < rows) && (col >= 0 && col < cols);
	}*/
	/**
	 * Determines if 
	 * @param col - the column of the current cell
	 * @return either true or false 
	 */
	public boolean isColumnFilled(int col) {
		if (board[0][col - 1].getState() != CellState.EMPTY) {
			return true;
		}
		return false;
	}
	public boolean isSpaceFilled(int col, int row ) {
		if (board[row][col-1].getState() != CellState.EMPTY) {
			return true;
		}
		return false;
	}

	/**
	 * Provides the state of the current cell
	 * 
	 * @param player
	 *            -the cell state that represents the player
	 * 
	 * @param col
	 *            -the column of the current cell
	 * 
	 * @return the row of the cell
	 */
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

	/**
	 * Determines if the current player has won vertically. Checks if the current
	 * player has four cells of their state in a row below their current cell
	 * 
	 * 
	 * @param row
	 *            -the row of the current cell
	 * 
	 * @param col
	 *            -the column of the current cell
	 * 
	 * @return true when the cells of the current player's state are 4 in a row else false
	 */
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

	/**
	 * Determines if the player has three cells in a row of their state vertically.
	 * If so the AI would block the player from getting four in a row by placing
	 * their piece on the same column as the player
	 * 
	 * 
	 * @param row
	 *            -the row of the current cell
	 * 
	 * @param col
	 *            -the column of the current cell
	 * 
	 * @return true when the cells of the player's state are 3 in a row else false
	 */
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

	/**
	 * Determines if the current player has won horizontally. Checks if the current
	 * player has four cells of their state in a row horizontally from their current
	 * cell. Checks the columns before and after the current cell.
	 * 
	 * 
	 * @param row
	 *            -the row of the current cell
	 * 
	 * @param col
	 *            -the column of the current cell
	 * 
	 * @return true when the cells of the current player's state are 4 in a row else false
	 */
	public boolean isHorizontalWinner(int col, int row) {
		int counter = 0;
		col -= 1; // Set to index values
		int cCol = col;

		CellState player = board[row][col].getState();
		boolean foundDifferent = false;
		boolean foundDifferent2 = false;
		while (!foundDifferent && cCol < cols && counter < 4) {
			if (board[row][cCol].getState() == player) {
				counter++;
				cCol++;
			} else {
				foundDifferent = true;
			}
		}
		cCol = col;
		while (!foundDifferent2 && cCol - 1 >= 0 && counter < 4) {
			if (board[row][cCol - 1].getState() == player) {
				counter++;
				cCol--;
			} else {
				foundDifferent2 = true;
			}
		}
		return counter == 4;
	}
	/**
	 * Determines if the player has two cells in a row of their state horizontally.
	 * If so the AI would block the player from getting four in a row by placing
	 * their piece on the column as the player
	 * 
	 * 
	 * @param row
	 *            -the row of the current cell
	 * 
	 * @param col
	 *            -the column of the current cell
	 * 
	 * @return true when the cells of the player's state are 2 in a row horizontally else false
	 */
	public boolean isHorizontalAI(int col, int row) {
		int counter = 0;
		col -= 1; // Set to index values
		int cCol = col;

		CellState player = board[row][col].getState();
		boolean foundDifferent = false;
		boolean foundDifferent2 = false;
		while (!foundDifferent && cCol < cols && counter < 2) {
			if (board[row][cCol].getState() == player) {
				counter++;
				cCol++;
			} else {
				foundDifferent = true;
			}
		}
		cCol = col;
		while (!foundDifferent2 && cCol - 1 >= 0 && counter < 2) {
			if (board[row][cCol - 1].getState() == player) {
				counter++;
				cCol--;
			} else {
				foundDifferent2 = true;
			}
		}
		return counter == 2;
	}
	/**
	 * Determines if the current player has won diagonally from the top left to the
	 * bottom right. Checks if the current player has four cells of their state in a
	 * row diagonally from their current cell.Checks if the cells in the top left of
	 * the current cell and the bottom right 
	 * 
	 * 
	 * @param row
	 *            -the row of the current cell
	 * 
	 * @param col
	 *            -the column of the current cell
	 * 
	 * @return true when the cells of the current player's state are 4 in a row else false
	 */
	public boolean isDiagonalWinner(int col, int row) {
		int counter = 0;
		col -= 1; // Set to index values
		int cCol = col;
		int cRow = row;

		CellState player = board[row][col].getState();
		boolean foundDifferentDiagonal = false;
		boolean foundDifferentDiagonal2 = false;

		// checks how many cells from the top left of the current cell that are the same
		// state in a row
		while (!foundDifferentDiagonal && cCol >= 0 && cRow >= 0 && counter < 4) {
			if (board[cRow][cCol].getState() == player) {
				counter++;
				cCol--;
				cRow--;
			} else {
				foundDifferentDiagonal = true;
			}
		}
		cCol = col;
		cRow = row;
		// checks how many cells from the bottom right of the current cell that are the
		// same state in a row
		while (!foundDifferentDiagonal2 && cCol + 1 < cols && cRow + 1 < rows && counter < 4) {
			if (board[cRow + 1][cCol + 1].getState() == player) {
				counter++;
				cCol++;
				cRow++;
			} else {
				foundDifferentDiagonal2 = true;
			}
		}

		return counter == 4;
	}

	/**
	 * Determines if the current player has won diagonally from the top right to the
	 * bottom left. Checks if the current player has four cells of their state in a
	 * row diagonally from their current cell.Checks if the cells in the top right of
	 * the current cell and the bottom left 
	 * 
	 * 
	 * @param row
	 *            -the row of the current cell
	 * 
	 * @param col
	 *            -the column of the current cell
	 * 
	 * @return true when the cells of the current player's state are 4 in a row else false
	 */
	public boolean isDiagonalWinner2(int col, int row) {
		int counter = 0;
		col -= 1; // Set to index values
		int cCol = col;
		int cRow = row;

		CellState player = board[row][col].getState();
		boolean foundDifferentDiagonal = false;
		boolean foundDifferentDiagonal2 = false;

		// checks how many cells from the bottom left of the current cell that are the
		// same state in a row
		while (!foundDifferentDiagonal && cCol >= 0 && cRow < rows && counter < 4) {
			if (board[cRow][cCol].getState() == player) {
				counter++;
				cCol--;
				cRow++;
			} else {
				foundDifferentDiagonal = true;
			}
		}
		cCol = col;
		cRow = row;
		// checks how many cells from the top right of the current cell that are the
		// same state in a row
		while (!foundDifferentDiagonal2 && cCol + 1 < cols && cRow - 1 >= 0 && counter < 4) {
			if (board[cRow - 1][cCol + 1].getState() == player) {
				counter++;
				cCol++;
				cRow--;
			} else {
				foundDifferentDiagonal2 = true;
			}
		}
		return counter == 4;
	}

	/**
	 * Displays the board
	 */
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
