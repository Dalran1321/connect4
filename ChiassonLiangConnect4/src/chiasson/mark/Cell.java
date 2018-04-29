package chiasson.mark;

/**
 * Represents a cell on the board
 *
 * @author Fred Liang, Mark Chiasson,Aaron Robertson
 * @version 1.0
 */

import chiasson.mark.CellState;

public class Cell {
	private CellState state;

	/**
	 * Cell constructor based on the what state the cell is in on the board
	 * 
	 * @param cs
	 *            - the state of the cell
	 */
	public Cell(CellState cs) {
		state = cs;
	}

	/**
	 * Sets the default cell state of each cell as empty
	 * 
	 */
	public Cell() {
		state = CellState.EMPTY;

	}

	/**
	 * Helper method to update the state of a cell
	 * 
	 * @param cs
	 *            - the state of the cell
	 */
	public void setState(CellState cs) {
		state = cs;
	}

	/**
	 * Provides the state of a cell
	 * 
	 * @return the state of a cell
	 */
	public CellState getState() {
		return state;
	}

	public String toString() {
		switch (state) {
		case EMPTY:
			return "-";
		case P1:
			return "R";
		case P2:
			return "B";
		default:
			return "-";
		}
	}

}