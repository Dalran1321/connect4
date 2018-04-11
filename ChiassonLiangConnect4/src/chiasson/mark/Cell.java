package chiasson.mark;



import chiasson.mark.ColorState;

public class Cell {
	private ColorState color;
	private boolean visited;
	
	public Cell(ColorState cs) {
		color = cs;
		visited = false;
	}
	
	public void setVisited(boolean value){
		visited = value;
	}
	
	public boolean hasVisited(){
		return visited;
	}
	
	public void setColor (ColorState cs){
		color = cs;
	}
	
	public ColorState getColor() {
		return color;
	}

	public String toString() {
		switch (color) {
		case NULL:
			return "+";
		case RED:
			return "R";
		case BLUE:
			return "B";
		default:
			return "-";
		}
	}
}