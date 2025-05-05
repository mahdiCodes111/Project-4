//https://youtu.be/1h5L9rvJOdM
//https://youtu.be/1h5L9rvJOdM
import java.util.Random;


public class Grid {
    // Number of rows in the grid
    private int numRows;
    // Number of columns in the grid
    private int numColumns;
    // Total number of bombs to place
    private int numBombs;
    // 2D boolean array indicating bomb locations
    private boolean[][] bombGrid;
    // 2D int array storing count of adjacent bombs per cell
    private int[][] countGrid;

    /**
     * Default constructor: creates a 10x10 grid with 25 bombs.
     */
    public Grid() {
        numRows = 10;
        numColumns = 10;
        numBombs = 25;
        bombGrid = new boolean[numRows][numColumns];
        countGrid = new int[numRows][numColumns];
        createBombGrid();   // Place bombs randomly
        createCountGrid();  // Compute adjacent bomb counts
    }

    /**
     * Constructor: creates a grid with specified rows and columns, defaults to 25 bombs.
     * @param rows number of rows
     * @param columns number of columns
     */
    public Grid(int rows, int columns) {
        numRows = rows;
        numColumns = columns;
        numBombs = 25;
        bombGrid = new boolean[numRows][numColumns];
        countGrid = new int[numRows][numColumns];
        createBombGrid();   // Place bombs randomly
        createCountGrid();  // Compute adjacent bomb counts
    }

    /**
     * Constructor: creates a grid with specified rows, columns, and bomb count.
     * @param rows number of rows
     * @param columns number of columns
     * @param bombs number of bombs to place
     */
    public Grid(int rows, int columns, int bombs) {
        numRows = rows;
        numColumns = columns;
        numBombs = bombs;
        bombGrid = new boolean[numRows][numColumns];
        countGrid = new int[numRows][numColumns];
        createBombGrid();   // Place bombs randomly
        createCountGrid();  // Compute adjacent bomb counts
    }

    /**
     * @return number of rows in the grid
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * @return number of columns in the grid
     */
    public int getNumColumns() {
        return numColumns;
    }

    /**
     * @return total number of bombs in the grid
     */
    public int getNumBombs() {
        return numBombs;
    }

    /**
     * Provides a defensive copy of the bomb grid.
     * @return clone of bombGrid array
     */
    public boolean[][] getBombGrid() {
        boolean[][] bombGridClone = new boolean[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                bombGridClone[i][j] = bombGrid[i][j];
            }
        }
        return bombGridClone;
    }

    /**
     * Provides a defensive copy of the count grid.
     * @return clone of countGrid array
     */
    public int[][] getCountGrid() {
        int[][] countGridClone = new int[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                countGridClone[i][j] = countGrid[i][j];
            }
        }
        return countGridClone;
    }

    /**
     * Checks if there is a bomb at the specified location.
     * @param row row index
     * @param column column index
     * @return true if a bomb is present, false otherwise
     */
    public boolean isBombAtLocation(int row, int column) {
        return bombGrid[row][column];
    }
    
    /**
     * Gets the adjacent bomb count at the specified location.
     * @param row row index
     * @param column column index
     * @return number of bombs adjacent (and including) this cell
     */
    public int getCountAtLocation(int row, int column) {
        return countGrid[row][column];
    }

    /**
     * Placeholder for revealing a bomb at a given location.
     * Implementation can be added to manage game state.
     * @param row row index
     * @param column column index
     */
    public void revealBombLocation(int row, int column) {
        // TODO: implement reveal logic if needed
    }

    /**
     * Randomly places bombs in the grid, ensuring no duplicate placements.
     */
    public void createBombGrid() {
        // Initialize all cells to false (no bomb)
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                bombGrid[i][j] = false;
            }
        }

        Random random = new Random();

        // Place bombs at random positions without overlap
        for (int k = 0; k < numBombs; k++) {
            int i = random.nextInt(numRows);
            int j = random.nextInt(numColumns);
    
            // If position already has a bomb, retry
            while (bombGrid[i][j]) {
                i = random.nextInt(numRows);
                j = random.nextInt(numColumns);
            }
    
            bombGrid[i][j] = true;
        }
    }

    /**
     * Calculates the count of bombs adjacent to each cell (including itself).
     */
    public void createCountGrid() {
        // Flags for surrounding positions
        boolean center;
        boolean left;
        boolean right;
        boolean topLeft;
        boolean topRight;
        boolean top;
        boolean bottom;
        boolean bottomLeft;
        boolean bottomRight;
        
        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numColumns; column++) {

                int count = 0;

                // Check the cell itself
                center = bombGrid[row][column];
                if (center) { count++; }

                // Check each neighbor if within bounds
                if (column - 1 >= 0) {
                    left = bombGrid[row][column - 1];
                    if (left) { count++; }
                }

                if (column + 1 < numColumns) {
                    right = bombGrid[row][column + 1];
                    if (right) { count++; }
                }

                if (row - 1 >= 0 && column - 1 >= 0) {
                    topLeft = bombGrid[row - 1][column - 1];
                    if (topLeft) { count++; }
                }

                if (row - 1 >= 0 && column + 1 < numColumns) {
                    topRight = bombGrid[row - 1][column + 1];
                    if (topRight) { count++; }
                }

                if (row - 1 >= 0) {
                    top = bombGrid[row - 1][column];
                    if (top) { count++; }
                }

                if (row + 1 < numRows) {
                    bottom = bombGrid[row + 1][column];
                    if (bottom) { count++; }
                }

                if (row + 1 < numRows && column + 1 < numColumns) {
                    bottomRight = bombGrid[row + 1][column + 1];
                    if (bottomRight) { count++; }
                }

                if (row + 1 < numRows && column - 1 >= 0) {
                    bottomLeft = bombGrid[row + 1][column - 1];
                    if (bottomLeft) { count++; }
                }
        
                // Store the computed count
                countGrid[row][column] = count;
            }
        }
    }
}
