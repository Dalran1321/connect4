package chiasson.mark;



import chiasson.mark.CellState;

public class Cell {
	private CellState state;
	
	public Cell(CellState cs) {
		state = cs;
	}

	
	public Cell() {
		state = CellState.EMPTY;

	}
	
	public void setState (CellState cs){
		state = cs;
	}
	
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