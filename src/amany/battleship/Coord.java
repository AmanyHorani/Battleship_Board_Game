package amany.battleship;

/**
 * The Coord class represents a coordinate on the game board
 * in the Battleship game. It stores the row and column indices
 * of the coordinate and whether it has been hit by a shot.
 */
public class Coord {
    private final int row;
    private final int col;
    private boolean hit = false;

    // constructor
    public Coord(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Accessor and Mutator Methods
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean getHit() {
        return hit;
    }
    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public String toString() {
        return row + "," + col;
    }
}
