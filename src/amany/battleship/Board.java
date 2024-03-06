package amany.battleship;

import java.util.Arrays;
import java.util.List;

/**
 * The Board class represents the game board for the Battleship game.
 * It manages the grid of cells where ships are placed and tracks the state
 * of each cell during the game. The class initializes the board, places ships
 * on the board, handles attempts to hit ships, and provides a method to print
 * the current state of the board to the console.
 */
public class Board {
    private final int boardSize;
    private final Player player;
    private final List<Character> letters = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J');
    private final char[][] board;

    // constructor of the board class
    public Board(int boardSize, Player player) {
        this.boardSize = boardSize;
        this.player = player;
        this.board = new char[boardSize][boardSize];
        createGameBoard();
        placeShips();

    }

    /**
     * Fill each coordinate in the board[][] with a space character.
     */
    private void createGameBoard() {
        for (int i = 0; i < 10; i++) { // this loop iterates over the rows of the board
            for (int j = 0; j < 10; j++) { // this loop iterates over the columns of the board
                this.board[i][j] = ' '; // initializes each cell of the board with an empty space
            }
        }
    }

    /**
     * Loop through player.getShips() and then ship.getCoordinates() to get the
     * x and y values. Assign the ship's size as the character in board[x][y]
     * TIP: An integer will count as a character, but not the way you want.
     * You need to convert ship.getSize() into a string with String.valueOf()
     * and then access the first character of the result.
     */
    private void placeShips() {
        for (Ship ship : player.getShips()) { // loops through each ship
            for (Coord coord : ship.getCoordinates()) { // loops through each coordinate of the ship
                int x = coord.getRow(); // gets the x value
                int y = coord.getCol(); // gets the y value

                if (isWithinBounds(x, y)) {
                    board[x][y] = String.valueOf(ship.getSize()).charAt(0);
                }
            }
        }
    }

    private boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < boardSize && y >= 0 && y < boardSize;
    }

    /**
     * Return the numeric value for each letter in the letters array.
     *
     * @param letter The letter to get the index of
     * @return The index of the character in the letters array
     */
    public int getLetterIndex(char letter) {
        return letters.indexOf(letter);
    }

    /**
     * See if a coordinate would be a hit or not
     *
     * @param x The x value of the coordinate
     * @param y The y value of the coordinate
     * @return The resulting string:
     * miss = "Miss!"
     * hit = "Hit!"
     * sank = "You sank their battleship!"
     */
    public String attemptHit(int x, int y) {
        Coord coord = new Coord(x, y);

        for (Ship ship : player.getShips()) {
            boolean[] HitInfo = ship.didHit(coord);
            boolean hit = HitInfo[0];
            boolean sank = HitInfo[1];

            if (hit) {
                board[x][y] = 'X';
                if (sank)
                    return "You sank their battleship!";
                else
                    return "Hit!";
            }
        }
        return "Miss!";
    }

    /**
     * When printing an object to the console the toString() method is called.
     * We can override this method to make printing the board to the console
     * print exactly what we want.
     * This should loop through all board coordinates and print the correct characters.
     * TIP: Print the column labels first (the letters), then print each row one
     * at a time starting with the row number then each coordinate for that row.
     *
     * @return The string of the board to print
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // prints spaces before the column labels to align the board
        sb.append("     ");

        // prints column labels (A to J) with printf() formatting
        for (int col = 0; col < board.length; col++)
            sb.append((char)('A' + col)).append(" | "); //(char)(k+65)
        sb.append("\n");

        // loop to print row number (0 to 9) followed by "|"
        for (int row = 0; row < board.length; row++) {
            sb.append(String.format("%2d | ", row)); // Append row number
            for (int col = 0; col < board[row].length; col++) {
                sb.append(board[row][col]).append(" | ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
