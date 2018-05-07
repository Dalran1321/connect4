package chiasson.mark;

import java.util.Random;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * Program that uses GUI to allow the user to play the game connect four either
 * with an AI or another person
 *
 * @author Fred Liang, Mark Chiasson, Aaron Robertson
 * @version 1.0
 */
public class GUIDriver extends Application {

	ColumnButton[] columnBtns = new ColumnButton[7];
	Button[][] cellBtns = new Button[7][6];
	final int ROWS = 6;
	final int COLS = 7;
	// create the board
	Board board = new Board(ROWS, COLS);
	CellState cState = CellState.P2;
	String turn = String.valueOf(cState);
	boolean isSinglePlayer = true;
	boolean humanTurn = true;
	Random r = new Random();
	int lastCol = 1;
	int row = 0;// update

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage window) throws Exception {

		VBox area = new VBox();
		HBox columnSelector = new HBox();
		for (int i = 0; i < columnBtns.length; i++) {
			columnBtns[i] = new ColumnButton("  " + String.valueOf(i + 1), i + 1);
			columnSelector.getChildren().add(i, columnBtns[i]);
			columnBtns[i].setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					String winCon = "";
					boolean done = false;
					int c = 0;
					if (humanTurn) {
						c = ((ColumnButton) event.getSource()).getColumnValue();
						lastCol = c;
						if (isSinglePlayer) {
							humanTurn = false;
						}
					} else if (isSinglePlayer) {
						if (board.isVerticalAI(lastCol, row)) {
							c = lastCol;

						} else if (board.isHorizontalAI(lastCol, row)) {
							if (lastCol != 1 && !board.isSpaceFilled(lastCol - 1, row)
									&& (row == 5 || board.isSpaceFilled(lastCol + 1, row + 1))) {
								c = lastCol - 1;
							} else if (lastCol != 7 && !board.isSpaceFilled(lastCol + 1, row)
									&& (row == 5 || board.isSpaceFilled(lastCol + 1, row + 1))) {
								c = lastCol + 1;
							} else {
								c = r.nextInt(7) + 1; // # generate random
														// column
							}
						} else {
							c = r.nextInt(7) + 1; // # generate random column
						}
						humanTurn = true;
					}

					if (!board.isColumnFilled(c)) {
						row = board.place(c, cState);
						if (cState != CellState.P1) {
							cState = CellState.P1;
							turn = String.valueOf(cState);
							cellBtns[c - 1][row].setText(String.valueOf(cState));
							cellBtns[c - 1][row].setStyle("-fx-base: #2A4CD8;");
						} else {
							cState = CellState.P2;
							if (!isSinglePlayer) {
								turn =String.valueOf(cState);
								cellBtns[c - 1][row].setText(String.valueOf(cState));
								cellBtns[c - 1][row].setStyle("-fx-base:  #f44242;");
							} else if (isSinglePlayer) {
								turn = "AI";
								cellBtns[c - 1][row].setText(String.valueOf("AI"));
								cellBtns[c - 1][row].setStyle("-fx-base: #f5f907");
							}
						}

						if (board.isVerticalWinner(c, row)) {
							winCon = "Vertical Winner " + turn + " Won!";
							System.out.println(winCon);
							done = true;
						}
						if (board.isHorizontalWinner(c, row)) {
							winCon = "Horizontal Winner " + turn + " Won!";
							System.out.println(winCon);
							done = true;
						}
						if (board.isDiagonalWinner(c, row) || board.isDiagonalWinner2(c, row)) {
							winCon = "Diagonal Winner " + turn + " Won!";
							System.out.println(winCon);
							done = true;
						}
					} else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Connect4 Error");
						alert.setHeaderText("The column you selected is full.");
						alert.setContentText("Please select another column.");
						alert.showAndWait();
						humanTurn = true;
					}
					if (done == true) {
						for (int i = 1; i < columnBtns.length; i++) {
							columnSelector.getChildren().remove(columnBtns[i]);
						}
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information Dialog");
						alert.setHeaderText("Congrats! " + turn);
						// alert.setContentText("Winner winner chicken
						// dinner.");

						alert.showAndWait();
						columnBtns[0].setDisable(done == true);
						columnBtns[0].setText(winCon);
						columnBtns[0].setAlignment(Pos.BASELINE_CENTER);
						columnBtns[0].prefWidthProperty().bind(columnSelector.widthProperty());

					}
					if (!humanTurn) {
						columnBtns[0].fire();
					}
					board.display();
				}
			});
		}

		GridPane gp = new GridPane();
		for (int row = 0; row < cellBtns.length; row++) {
			for (int col = 0; col < cellBtns[0].length; col++) {
				cellBtns[row][col] = new Button(" ");
				cellBtns[row][col].prefWidthProperty().bind(columnSelector.widthProperty());
				cellBtns[row][col].setStyle("-fx-base: #C0C0C0;");
				gp.add(cellBtns[row][col], row, col);
			}

		}
		final Popup popup = new Popup();

		VBox layout = new VBox(1);
		Button pvp = new Button("PVP");
		Button pv1 = new Button("PV1");
		Button option = new Button("PVP or PV1");
		option.setAlignment(Pos.BASELINE_CENTER);
		pvp.setAlignment(Pos.BASELINE_CENTER);
		pv1.setAlignment(Pos.BASELINE_CENTER);
		option.prefWidthProperty().bind(layout.widthProperty());
		pvp.prefWidthProperty().bind(layout.widthProperty());
		pv1.prefWidthProperty().bind(layout.widthProperty());
		pvp.setStyle("-fx-base:  #f44242;");
		option.setStyle("-fx-base: #2A4CD8;");
		pv1.setStyle("-fx-base:#f5f907");
		pvp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				popup.show(window);
				isSinglePlayer = false;
				area.getChildren().add(columnSelector);
				area.getChildren().add(gp);
				Scene scene = new Scene(area);
				window.setScene(scene);
				window.show();
			}
		});
		pv1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				popup.show(window);
				area.getChildren().add(columnSelector);
				area.getChildren().add(gp);
				Scene scene = new Scene(area);
				window.setScene(scene);
				window.show();
			}
		});

		layout.getChildren().addAll(option, pvp, pv1);
		window.setScene(new Scene(layout));
		window.show();

	}

}