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

		int[] colNum = new int[7];

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

		int turns = 1;
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
						board.fill(5 - colNum[0], 0, board.getColor(x, y), playerColor(turns));
						done = board.check(playerColor(turns));
						if (colNum[0]>=4){
							colNum[0] = 4;
						}
						colNum[0]++;
					} else if (value.equals("2")) {
						board.fill(5 - colNum[1], 1, board.getColor(x, y), playerColor(turns));
						done = board.check(playerColor(turns));
						if (colNum[1]>=4){
							colNum[1] = 4;
						}
						colNum[1]++;
					} else if (value.equals("3")) {
						board.fill(5 - colNum[2], 2, board.getColor(x, y), playerColor(turns));
						done = board.check(playerColor(turns));
						if (colNum[2]>=4){
							colNum[2] = 4;
						}
						colNum[2]++;
					} else if (value.equals("4")) {
						board.fill(5 - colNum[3], 3, board.getColor(x, y), playerColor(turns));
						done = board.check(playerColor(turns));
						if (colNum[3]>=4){
							colNum[3] = 4;
						}
						colNum[3]++;
					} else if (value.equals("5")) {
						board.fill(5 - colNum[4], 4, board.getColor(x, y), playerColor(turns));
						done = board.check(playerColor(turns));
						if (colNum[4]>=4){
							colNum[4] = 4;
						}
						colNum[4]++;
					} else if (value.equals("6")) {
						board.fill(5 - colNum[5], 5, board.getColor(x, y), playerColor(turns));
						done = board.check(playerColor(turns));
						if (colNum[5]>=4){
							colNum[5] = 4;
						}
						colNum[5]++;
					} else if (value.equals("7")) {
						board.fill(5 - colNum[6], 6, board.getColor(x, y), playerColor(turns));
						done = board.check(playerColor(turns));
						if (colNum[6]>=4){
							colNum[6] = 4;
						}
						colNum[6]++;
					}
				}
			}
			System.out.println("turns " + turns);
			board.unVisit();
			board.display();
			turns++;
			if (!done) {
				System.out.print("");
			}
		}

	}

	private static ColorState playerColor(int turns) {
		if (turns % 2 == 0) {
			return ColorState.RED;
		} else {
			return ColorState.BLUE;
		}
	}
}