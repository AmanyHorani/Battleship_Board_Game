package amany.battleship;

import java.util.List;

/**
 * The Ship class represents a ship in the Battleship game.
 * It holds information about the coordinates occupied by the ship on
 * the game board and provides methods to interact with these coordinates.
 */
public class Ship {
    private final List<Coord> coordinates;

    /**
     * Returns an array of all possible sizes for ships
     *
     * @return An array of possible ship sizes
     */
    public static int[] possibleSizes() {
        return new int[] {2, 3, 3, 4, 5};
    }

    /**
     * Constructs a Ship object with the specified list of coordinates.
     *
     * @param coordinates The list of coordinates occupied by the ship.
     */
    public Ship(List<Coord> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Returns the list of coordinates occupied by the ship.
     *
     * @return The list of coordinates occupied by the ship.
     */
    public List<Coord> getCoordinates() {
        return coordinates;
    }

    /**
     * Returns the size of the ship, which is the number of coordinates it occupies.
     *
     * @return The size of the ship.
     */
    public int getSize() {
        return coordinates.size();
    }

    /**
     * Returns the index of the given coordinate in the list of ship's coordinates.
     *
     * @param coord The coordinate to search for.
     * @return The index of the coordinate if found, otherwise -1.
     */
    public int getIndex(Coord coord) {
        for (int i = 0; i < coordinates.size(); i++) {
            Coord currentCoord = coordinates.get(i);
            if (currentCoord.getRow() == coord.getRow() && currentCoord.getCol() == coord.getCol())
                return i;
        }
        return -1;
    }

    /**
     * Checks if the given coordinate is occupied by the ship.
     *
     * @param coord The coordinate to check.
     * @return True if the coordinate is occupied by the ship, otherwise false.
     */
    public boolean inUse(Coord coord) {
        return getIndex(coord) != -1;
    }

    /**
     * Checks if the ship is hit by a shot at the given coordinate.
     *
     * @param coord The coordinate where the shot was fired.
     * @return A boolean array indicating if the shot was a hit (true or false) and if the ship sank as a result of the hit (true or false).
     */
    public boolean[] didHit(Coord coord) {
        int index = getIndex(coord);
        if (index == -1) { // if shot is miss
            return new boolean[]{false, false};
        }
        coordinates.get(index).setHit(true);

        boolean sank = true; // initialise sank with true

        for (Coord c : coordinates) {
            if (!c.getHit()) {
                sank = false; // if not hit then assign false to sank
                break;
            }
        }
        return new boolean[] {true, sank}; // if hit
    }
}
