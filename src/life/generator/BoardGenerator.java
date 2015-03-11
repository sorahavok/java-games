package life.generator;

@FunctionalInterface
public interface BoardGenerator {
	/**
	 * Create the initial game board from a source.
	 * @return the starting position for the game board
	 */
	int[][] getBoard();
}
