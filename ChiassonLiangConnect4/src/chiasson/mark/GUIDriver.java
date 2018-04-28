package chiasson.mark;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIDriver extends Application {

	ColumnButton[] columnBtns = new ColumnButton[7];
	Button[][] cellBtns = new Button[7][6];
	final int ROWS = 6;
	final int COLS = 7;
	// create the board
	Board board = new Board(ROWS, COLS);
	CellState cState = CellState.P2;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage window) throws Exception {
		VBox area = new VBox();
		HBox columnSelector = new HBox();
		for (int i = 0; i < columnBtns.length; i++) {
			int n = i;
			columnBtns[i] = new ColumnButton(String.valueOf(i + 1), i + 1);
			columnSelector.getChildren().add(i, columnBtns[i]);
			columnBtns[i].setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					boolean done = false;

					int c = ((ColumnButton) event.getSource()).getColumnValue();
					if (!board.isColumnFilled(c)) {
						int row = board.place(c, cState);
						if (cState != CellState.P1) {
							cellBtns[c - 1][row].setStyle("-fx-base: #2A4CD8;");
							cState = CellState.P1;
						} else {
							cellBtns[c - 1][row].setStyle("-fx-base:  #f44242;");
							cState = CellState.P2;
						}
						if (board.isVerticalWinner(c, row)) {
							System.out.println("Vertical Winner " + cState + " won");
							done = true;
						}
						if (board.isHorizontalWinner(c, row)) {
							System.out.println("Horizontal Winner " + cState + " won");
							done = true;
						}
						if (board.isDiagonalWinner(c, row) || board.isDiagonalWinner2(c, row)) {
							System.out.println("Diagonal Winner " + cState + " won");
							done = true;
						}
					}
					if (done == true) {
						for (int i = 0; i < columnBtns.length; i++) {
							columnBtns[i].setDisable(done == true);
						}
					}

					board.display();

				}
			});
		}

		GridPane gp = new GridPane();
		for (int row = 0; row < cellBtns.length; row++) {
			for (int col = 0; col < cellBtns[0].length; col++) {
				cellBtns[row][col] = new Button("+");
				cellBtns[row][col].setStyle("-fx-base: #C0C0C0;");
				gp.add(cellBtns[row][col], row, col);
			}

		}
		area.getChildren().add(columnSelector);
		area.getChildren().add(gp);

		Scene scene = new Scene(area);
		window.setScene(scene);
		window.show();

	}

}
