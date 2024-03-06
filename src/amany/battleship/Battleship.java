package amany.battleship;

import java.util.Scanner;

/**
 * The Battleship class is responsible for managing the Battleship game.
 * It initializes the game, creates a player and a game board, and runs
 * the main game loop where players take turns guessing coordinates and
 * attempting hits until the game ends. It also handles user input validation
 * for coordinate guesses and quitting the game.
 */
public class Battleship {
    public static void main(String[] args) {
        int boardSize = 10;
        Player player = new Player(boardSize);
        Board board = new Board(boardSize, player);

        runGameLoop(board);
    }

    /**
     * Create an infinite loop to:
     * 1) Draw the current game board
     * 2) Print the last action ("Miss!", "Hit!", etc.)
     * 3) Ask the user for a coordinate input or far "quit"
     * 4) Quit the game if "quit" was entered
     * 5) Check if the coordinate was valid, if so attempt a hit
     * on that coordinate. Store the last action string to print
     * it out after the board is displayed on the next loop iteration.
     * 6) If the coordinate is not valid then jump back to step 3
     * This is where all the parts of our game come together.
     *
     * @param board The board of the game
     */
    private static void runGameLoop(Board board) {
        Scanner input = new Scanner(System.in);
        String coordinate;
        String lastAction = null;

        while (true) {
            System.out.println(board);
            if (lastAction != null) {
                System.out.println(lastAction);
            }
            do {
                System.out.println("Enter a coordinate (ex (\"A0\") or \"quit\" to stop playing).");
                coordinate = input.next().toUpperCase();

                if (coordinate.equalsIgnoreCase("quit")) {
                    System.out.println("Thanks for playing!");
                    return;
                }
            } while (!isValidCoord(coordinate));

            if (isValidCoord(coordinate)) {
                int column = board.getLetterIndex(coordinate.charAt(0));
                int row = coordinate.charAt(1) - '0';  // Convert char to int

                lastAction = board.attemptHit(row, column);
            } else {
                System.out.println("Invalid coordinate. Please enter a valid coordinate.");
            }
        }
    }

    /**
     * Checks if the user input is valid
     * Requirements for valid input:
     * 2 characters
     * Must be a letter (A-J only) followed by a number (0-9 only)
     * TIP: Input is received in Strings so getting an int value of the 2nd
     * character (0-9) will not act like 0-9. Instead, it will act like it is ASCII
     * chart values. This means 0 = 48, 1 = 49 ... 9 = 57.
     * To check if the STRING input of the 2nd character is 0-9
     * you actually need to check for >= 48 && <= 57.
     *
     * @param coord The user input, ex: A0, B5, c2
     * @return If the user input is valid
     */
    private static boolean isValidCoord(String coord) {
        if (coord.length() != 2)
            return false;

        char letter = coord.charAt(0);
        char digit = coord.charAt(1);

        if (letter < 'A' || letter > 'J')
            return false;

        return digit >= '0' && digit <= '9';
    }
}


