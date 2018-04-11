package chiasson.mark;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) throws InterruptedException {

		Scanner in = new Scanner(System.in);
		
		// Ask user for size of board
		final int ROWS = 6;
		final int COLS = 7;
		
		// create the board
		Board board = new Board(ROWS, COLS);
		board.display();

		// List of possible input values
		List<String> values = new ArrayList<>();
		values.add("1");
		values.add("2");
		values.add("3");
		values.add("4");
		values.add("5");
		values.add("6");
		values.add("7");
	
		boolean done = false;
		String value = "";
		
		// Always fill from top left corner
		int x = 0;
		int y = 0;

		int turns = 0;
		System.out.print("Connect four to win");
		while (!done && in.hasNextLine()) {
			x = 0;
			y = 0;
			value = in.nextLine().trim().toUpperCase();
			if (values.contains(value)) {
				if (value.equals("0")) {
					done = true;
				} else {
					if (value.equals("1")) {
						board.fill(5, 0, board.getColor(5-x, 0), ColorState.RED);
						done = board.check(ColorState.RED);
					} else if (value.equals("2")) {
						board.fill(5, 1, board.getColor(x, y), ColorState.RED);
						done = board.check(ColorState.RED);
					} 
					else if (value.equals("3")) {
						board.fill(5, 2, board.getColor(x, y), ColorState.RED);
						done = board.check(ColorState.RED);
					} 
					else if (value.equals("4")) {
						board.fill(5, 3, board.getColor(x, y), ColorState.RED);
						done = board.check(ColorState.RED);
					} 
					else if (value.equals("5")) {
						board.fill(5, 4, board.getColor(x, y), ColorState.RED);
						done = board.check(ColorState.RED);
					} 
					else if (value.equals("6")) {
						board.fill(5, 5, board.getColor(x, y), ColorState.RED);
						done = board.check(ColorState.RED);
					} 
					else if (value.equals("7")) {
						board.fill(5, 6, board.getColor(x, y), ColorState.RED);
						done = board.check(ColorState.RED);
					} 
				}
			}
			board.unVisit();
			board.display();
			turns++;
			if (!done) {
				System.out.print("New Color (RGBY) (X to finish): ");
			} 
		}
		

	}
}