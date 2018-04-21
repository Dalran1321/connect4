package chiasson.mark;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Driver {

	public static void main(String[] args) throws InterruptedException {

		Scanner in = new Scanner(System.in);

		// Ask user for size of board
		final int ROWS = 6;
		final int COLS = 7;

		// create the board
		Board board = new Board(ROWS, COLS);
		board.display();


		boolean done = false;

		// Always fill from top left corner
		int x = 0;
		int y = 0;
		boolean error = false;
		Random r = new Random();
		int row = 0;
		int turns = 1;
		System.out.println("Connect four to win!");
		System.out.println("Enter a number between 1-7 to select a column to put a chip into");
		while (!done && in.hasNextInt()) {
			x = 0;
			y = 0;
			int value = in.nextInt();
			CellState c = CellState.P2;
			if (turns % 2 == 0){
			 //value = r.nextInt(7);
			//c = CellState.P1;
			}
			
			if (value >= 1 && value <= 7) {
				if (board.isColumnFilled(value) == false) {
					row =board.place(value, c);
					/*if (board.isVerticalWinner(value,row)) {
						System.out.println("Vertical Winner");
						done = true;
					}
					 if (board.isHorizontalWinner(value,row)) {
						System.out.println("Horizontal Winner");
						done = true;
					}*/
					 if (board.isDiagonalWinner(value,row)) {
						System.out.println("Diagonal Winner");
						done = true;
					}
					 if (board.isDiagonalWinner2(value,row)) {
							System.out.println("Diagonal Winner2");
							done = true;
						}
				} else {
					System.out.println("The column was filled pick a different one");
					error = true;
				}

			} else {
				System.out.println("The number must be between 1-7");
				error = true;
			}

			if (error == false) {
				turns++;
				board.display();
				System.out.println("turns " + turns);
			}
			error = false;
		}

	}
}