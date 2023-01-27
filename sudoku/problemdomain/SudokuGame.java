package sudoku.problemdomain;
import java.io.Serializable;

public class SudokuGame implements Serializable{
    private final GameState gameState;
    private final int[][] griState;

    public static final int GRID_BOUNDARY = 9; // Number of squares in a Sudoku puzzle

    // Constructor
    public SudokuGame(GameState gameState, int[][] gridState) {
        this.gameState = gameState;
        this.gridState = gridState;
    }

    // Getter
    public GameState getGameState() {
        return gameState;
    }

    public int[][] getCopyOfGridState() {
        return SudokuUtilities.copyToNewArray(gridState); // This protects the SudokuGame object from being messed with/changed
    }

}