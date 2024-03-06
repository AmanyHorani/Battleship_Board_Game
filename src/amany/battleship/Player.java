package amany.battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The Player class represents a player in the Battleship game.
 * It is responsible for managing the player's fleet of ships
 * and their placement on the game board.
 */
public class Player {
    private final List<Ship> ships = new ArrayList<>();

    // constructor
    public Player(int boardSize) {
        placeShips(boardSize);
    }

    // returns the list of ships owned by the player
    public List<Ship> getShips() {
        return ships;
    }

    /**
     * Ensure the ship will fit on the board
     * @param boardSize The size of the board
     * @param boatSize The size of the ship
     * @return The starting position for the ship to fit on the board
     */
    private int random(int boardSize, int boatSize) {
        Random rand = new Random();
        int random = rand.nextInt(boardSize);
        while (random + boatSize > boardSize) {
            --random;
        }
        return random;
    }

    /**
     * creates a list of coordinates from a starting to an ending point
     * Example:
     * Start: 0, 0
     * End: 0, 3
     * List returned:
     * 0, 0
     * 0, 1
     * 0, 2
     * @param start The starting point
     * @param end The ending point
     * @return The list of all coordinates
     */
    private List<Coord> getAllCoordinates(Coord start, Coord end) {
        List<Coord> coordinates = new ArrayList<>();
        int startX = start.getRow();
        int startY = start.getCol();
        int endX = end.getRow();
        int endY = end.getCol();

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                coordinates.add(new Coord(x, y));
            }
        }

        return coordinates;
    }

    /**
     * Checks if a coordinate is already in use by any ship
     * @param coord The coordinate to check
     * @return If the coordinate is in use
     */
    private boolean isInUse(Coord coord) {
        for (Ship ship: ships) {
            if (ship.inUse(coord)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Run Ship.getSizes() to get all ships and randomly assign if each ship should be
     * vertical or not. Randomly assign a starting coordinate and add on the ship size
     * to get the ending coordinate. Make sure that all coordinates allow the ship to
     * fit on the board and are not in use by any other ships. Finally, add the resulting
     * ship to the ships list.
     * TIP: the starting coordinate's X and Y values should be randomized. The ending
     * coordinate's X and Y values should simply be their ship size added to them. You
     * should only add to EITHER the X or the Y depending on if it's vertical, never both.
     *
     * @param boardSize The size of the board
     */
    private void placeShips(int boardSize) {
        int[] possibleSizes = Ship.possibleSizes();

        for (int size : possibleSizes) {
            boolean isVertical = ThreadLocalRandom.current().nextBoolean();
            int startX = random(boardSize, size);
            int startY = random(boardSize, size);
            int endX, endY;

            if (isVertical) {
                endX = startX;
                endY = startY + size - 1;
            } else {
                endX = startX + size - 1;
                endY = startY;
            }

            Coord startCoord = new Coord(startX, startY);
            Coord endCoord = new Coord(endX, endY);

            boolean validPlacement = true;
            List<Coord> shipCoords = getAllCoordinates(startCoord, endCoord);


            for (Coord coord : shipCoords) {
                if (isInUse(coord) || coord.getRow() >= boardSize || coord.getCol() >= boardSize) {
                    validPlacement = false;
                    break;
                }
            }

            if(validPlacement) {
                ships.add(new Ship(shipCoords));
            } else {
                break;
            }
        }
    }
}
