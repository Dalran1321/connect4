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
	
		boolean error = false;
		Random r = new Random();
		int row = 0;
		int turns = 1;
		int temp = 0;
		int value = 0;
		CellState c = CellState.P2;
		System.out.println("Connect four to win!");
		System.out.println("Enter 1 for one player or any other number for two player mode");
		int AIState = in.nextInt();
		System.out.println("Enter a number between 1-7 to select a column to put a chip into");
		while (!done && in.hasNextInt()) {
			if (turns % 2 != 0) {
				value = in.nextInt();
				temp = value;
				c = CellState.P2;
			}

			else {
				if (AIState == 1) {
					if (board.isVerticalAI(temp, row) && temp != 0) {
						temp = 0;
					} else {
						value = r.nextInt(7);
					}
				} else {
					value = in.nextInt();
				}
				c = CellState.P1;
			}

			if (value >= 1 && value <= 7) {
				if (board.isColumnFilled(value) == false) {
					row = board.place(value, c);
					if (board.isVerticalWinner(value, row)) {
						System.out.println("Vertical Winner " + c + " won");
						done = true;
					}
					if (board.isHorizontalWinner(value, row)) {
						System.out.println("Horizontal Winner " + c + " won");
						done = true;
					}
					if (board.isDiagonalWinner(value, row)) {
						System.out.println("Diagonal Winner " + c + " won");
						done = true;
					}
					if (board.isDiagonalWinner2(value, row)) {
						System.out.println("Diagonal Winner " + c + " won");
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
				System.out.println(c + " turn: " + turns);
				board.display();
			}
			error = false;
		}

	}
}