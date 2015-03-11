package life.generator;

import java.util.Random;

public class RandomBoardGenerator implements BoardGenerator {

	private final Random rand = new Random();
	private final int height;
	private final int width;
	private final int maxStartingTiles;
	
	
	/**
	 * Board which starts in a random configuration.
	 * @param height number of tiles which fit on the board from top to bottom
	 * @param width number of tiles which fit on the board from side to side
	 * @param maxStartingTiles randomly attempt to set this number of tiles to a living state
	 */
	public RandomBoardGenerator(final int height, final int width, int maxStartingTiles) {
		this.height = height;
		this.width = width;
		this.maxStartingTiles = maxStartingTiles;
	}
	
	/**
	 * {@code maxStartingTiles} defaults to {@code height * width / 3}.
	 * @see #RandomBoardGenerator(height,width,maxStartingTiles)
	 */
	public RandomBoardGenerator(final int height, final int width) {
		this(height, width, height * width / 3);
	}
	
	
	@Override
	public int[][] getBoard() {
		final int[][] boardArray = new int[height][width];
		
		for(int i = 0; i < maxStartingTiles; i++) {
			boardArray[rand.nextInt(height)][rand.nextInt(width)] = 1;
		}
		return boardArray;
	}
}
