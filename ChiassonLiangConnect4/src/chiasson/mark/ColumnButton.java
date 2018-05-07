package chiasson.mark;

import javafx.scene.Node;
import javafx.scene.control.Button;
/**
 * Represents the column button in the GUI
 *
 * @author Fred Liang, Mark Chiasson,Aaron Robertson
 * @version 1.0
 */
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
