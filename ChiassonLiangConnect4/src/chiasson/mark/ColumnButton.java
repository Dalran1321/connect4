package chiasson.mark;

import javafx.scene.Node;
import javafx.scene.control.Button;

public class ColumnButton extends Button {
	private int column;

	public ColumnButton(int col) {
		column = col;
	}

	public ColumnButton(String arg0, int col) {
		super(arg0);
		column = col;
	}

	public ColumnButton(String arg0, Node arg1, int col) {
		super(arg0, arg1);
		column = col;
	}

	public int getColumnValue() {
		return column;
	}

	

}
